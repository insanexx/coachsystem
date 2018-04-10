package com.cc.coachsystem.coach.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.coachsystem.beans.Coach;
import com.cc.coachsystem.beans.Course;
import com.cc.coachsystem.beans.User;
import com.cc.coachsystem.dao.CoachDao;
import com.cc.coachsystem.dao.CourseDao;
import com.cc.coachsystem.dao.UserDao;
import com.cc.coachsystem.dao.impl.CoachDaoImpl;
import com.cc.coachsystem.dao.impl.CourseDaoImpl;
import com.cc.coachsystem.dao.impl.UserDaoImpl;
import com.cc.coachsystem.utils.MD5Util;

@WebServlet("/coach/CoachServlet")
public class CoachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CoachDao coachDao = new CoachDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private CourseDao csDao = new CourseDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String method = request.getParameter("method");
		if (method == null || method.trim().equals("")) {
			return;
		}
		if("login".equals(method)) {
			login(request,response);
			return;
		} else if("register".equals(method)) {
			register(request,response);
			return;
		}
		Coach coach = (Coach) request.getSession().getAttribute("coach");
		if(coach==null) {
			request.setAttribute("message", "你还没有登录");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		switch (method) {
		case "logout":
			logout(request,response);
			break;
		case "listuser":
			listuser(request,response);
			break;
		case "index":
			index(request,response);
			break;
		default:
			break;
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Coach coach = (Coach) request.getSession().getAttribute("coach");
		if(coach==null) {
			request.setAttribute("message", "你还没有登录");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		List<Course> courseList = csDao.getListByCoach(0, 999, coach.getCoachid());
		request.getSession().setAttribute("courseList", courseList);
		request.getRequestDispatcher("/jsp/coach/index.jsp").forward(request, response);
		return;
	}

	private void listuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		List<User> list = userDao.getUsersByCourse(courseid);
		request.setAttribute("list", list);
		Course course = csDao.getById(courseid);
		request.setAttribute("course", course);
		request.getRequestDispatcher("/jsp/coach/listcourseuser.jsp").forward(request, response);
		return;
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Coach coach = createCoach(request);
		String errorMessage = null;
		errorMessage = validate(coach);
		if(errorMessage==null) {
			coach.setPassword(MD5Util.getMD5(coach.getPassword()));
			coachDao.add(coach);
			//跳转登陆页面
			request.setAttribute("message", "注册成功");
			request.setAttribute("coach", coach);
			request.getRequestDispatcher("/jsp/coach/login.jsp").forward(request, response);
			return;
		}else {
			//回显数据到注册页面
			request.setAttribute("message", "注册失败:"+errorMessage);
			request.setAttribute("coach", coach);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
	}

	private String validate(Coach coach) {
		if(coach.getPassword()==null||coach.getPassword().trim().equals("")) {
			return "密码不能为空";
		}
		if(coach.getName()==null||coach.getName().trim().equals("")) {
			return "用户名不能为空";
		}
		return null;
	}

	private Coach createCoach(HttpServletRequest request) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String idcard = request.getParameter("idcard");
		Coach coach = new Coach(-1,password,name,phone,idcard);
		return coach;
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("coach");
		response.sendRedirect(request.getContextPath()+"/jsp/coach/login.jsp");
		return;
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		if(name==null||name.trim().equals("")) {
			request.setAttribute("message", "请输入用户名");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		if(password==null||password.trim().equals("")) {
			request.setAttribute("message", "请输入密码");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		Coach coach = coachDao.getByNameAndPassword(name, MD5Util.getMD5(password));
		if(coach==null) {
			request.setAttribute("message", "用户名或者密码输入错误");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("coach", coach);
		response.sendRedirect(request.getContextPath()+"/coach/CoachServlet?method=index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
