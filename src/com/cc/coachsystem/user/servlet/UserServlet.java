package com.cc.coachsystem.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.coachsystem.beans.Course;
import com.cc.coachsystem.beans.User;
import com.cc.coachsystem.dao.CourseDao;
import com.cc.coachsystem.dao.UserDao;
import com.cc.coachsystem.dao.impl.CourseDaoImpl;
import com.cc.coachsystem.dao.impl.UserDaoImpl;
import com.cc.coachsystem.utils.MD5Util;

@WebServlet("/user/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDaoImpl();
	private CourseDao courseDao = new CourseDaoImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String method = request.getParameter("method");
		if (method == null || method.trim().equals("")) {
			return;
		}
		if("register".equals(method)) {
			register(request,response);
			return;
		}else if("login".equals(method)) {
			login(request,response);
			return;
		}
		User u = (User) request.getSession().getAttribute("user");
		if(u==null) {
			request.setAttribute("message", "你还没有登录");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		switch (method) {
		case "logout":
			logout(request,response);
			break;
		case "takecourse":
			takecourse(request,response);
			break;
		case "index":
			index(request,response);
			break;
		default:
			break;
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message", "你还没有登录");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		List<Course> courseList = courseDao.getList(0, 1000,user.getUserid(),true);
		request.getSession().setAttribute("courseList", courseList);
		request.getRequestDispatcher("/jsp/user/index.jsp").forward(request, response);
		return;
	}

	private void takecourse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			User u = (User) request.getSession().getAttribute("user");
			int courseid = Integer.parseInt(request.getParameter("courseid"));
			Course course = courseDao.getById(courseid);
			if(userDao.istookCourse(course, u)) {
				request.setAttribute("message", "你已经预定过了，请勿重复预定");
				request.getRequestDispatcher("/jsp/user/index.jsp").forward(request, response);
				return;
			}
			
			userDao.takecourse(course, u);
			request.setAttribute("message", "预定成功");
			response.sendRedirect(request.getContextPath()+"/user/UserServlet?method=index");
			return;
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "预定失败");
			request.getRequestDispatcher("/jsp/user/index.jsp").forward(request, response);
			return;
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("user");
		response.sendRedirect(request.getContextPath()+"/jsp/user/login.jsp");
		return;
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		User user = userDao.getByUsernameAndPassword(name,MD5Util.getMD5(password));
		if(user==null) {
			request.setAttribute("message", "用户名或者密码输入错误");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+"/user/UserServlet?method=index");
		return;
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = createUser(request);
		String errorMessage = null;
		errorMessage = validate(user);
		if(errorMessage==null) {
			user.setPassword(MD5Util.getMD5(user.getPassword()));
			userDao.add(user);
			//跳转登陆页面
			request.setAttribute("message", "注册成功");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/jsp/user/login.jsp").forward(request, response);
			return;
		}else {
			//回显数据到注册页面
			request.setAttribute("message", "注册失败:"+errorMessage);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/jsp/user/register.jsp").forward(request, response);
			return;
		}
	}

	private String validate(User user) {
		if(user.getPassword()==null||user.getPassword().trim().equals("")) {
			return "密码不能为空";
		}
		if(user.getUsername()==null||user.getUsername().trim().equals("")) {
			return "用户名不能为空";
		}
		return null;
	}

	private User createUser(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		User user = new User(-1,username,password,name,phone);
		return user;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
