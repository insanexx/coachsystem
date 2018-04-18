package com.cc.coachsystem.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.coachsystem.beans.Admin;
import com.cc.coachsystem.beans.Coach;
import com.cc.coachsystem.beans.Course;
import com.cc.coachsystem.beans.User;
import com.cc.coachsystem.dao.AdminDao;
import com.cc.coachsystem.dao.CoachDao;
import com.cc.coachsystem.dao.CourseDao;
import com.cc.coachsystem.dao.UserDao;
import com.cc.coachsystem.dao.impl.AdminDaoImpl;
import com.cc.coachsystem.dao.impl.CoachDaoImpl;
import com.cc.coachsystem.dao.impl.CourseDaoImpl;
import com.cc.coachsystem.dao.impl.UserDaoImpl;
import com.cc.coachsystem.utils.MD5Util;

@WebServlet("/admin/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao aDao = new AdminDaoImpl();
	private UserDao uDao = new UserDaoImpl();
	private CoachDao cDao = new CoachDaoImpl();
	private CourseDao csDao = new CourseDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		}
		//检查管理员登陆状态
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null) {
			request.setAttribute("message", "你还没有登录");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		switch (method) {
		case "logout":
			logout(request,response);
			break;
		case "coachlist":
			coachlist(request,response);
			break;
		case "userlist":
			userlist(request,response);
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
	//查看课程预订列表
	private void listuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		List<User> list = uDao.getUsersByCourse(courseid);
		request.setAttribute("list", list);
		Course course = csDao.getById(courseid);
		request.setAttribute("course", course);
		request.getRequestDispatcher("/jsp/admin/listcourseuser.jsp").forward(request, response);
		return;
	}
	
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin==null) {
			request.setAttribute("message", "你还没有登录");
			request.getRequestDispatcher("/jsp/admin.jsp").forward(request, response);
			return;
		}
		List<Course> courseList = csDao.getList(0, 1000);
		request.getSession().setAttribute("courseList", courseList);
		request.getRequestDispatcher("/jsp/admin/index.jsp").forward(request, response);
		return;
	}

	private void coachlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Coach> list = cDao.getAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/admin/coachlist.jsp").forward(request, response);
		return;
	}

	private void userlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = uDao.getList(0, 9999);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/admin/userlist.jsp").forward(request, response);
		return;
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("admin");
		response.sendRedirect(request.getContextPath()+"/jsp/admin/login.jsp");
		return;
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int adminid = Integer.parseInt(request.getParameter("adminid"));
		String password = request.getParameter("password");
		if(adminid<=0) {
			request.setAttribute("message", "请输入管理员id");
			request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);
			return;
		}
		if(password==null||password.trim().equals("")) {
			request.setAttribute("message", "请输入密码");
			request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);
			return;
		}
		Admin admin = aDao.getByIdAndPassword(adminid, MD5Util.getMD5(password));
		if(admin==null) {
			request.setAttribute("message", "管理员id或者密码输入错误");
			request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("admin", admin);
		response.sendRedirect(request.getContextPath()+"/admin/AdminServlet?method=index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
