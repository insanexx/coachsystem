package com.cc.coachsystem.course.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.coachsystem.beans.Coach;
import com.cc.coachsystem.beans.Course;
import com.cc.coachsystem.dao.CourseDao;
import com.cc.coachsystem.dao.impl.CourseDaoImpl;

@WebServlet("/course/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDao courseDao = new CourseDaoImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Coach coach = (Coach) request.getSession().getAttribute("coach");
		if(coach==null) {
			request.setAttribute("message", "你还没有登录");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
		String method = request.getParameter("method");
		if (method == null || method.trim().equals("")) {
			return;
		}
		switch (method) {
		case "addcourse":
			addcourse(request,response);
			break;
		case "deletecourse":
			deletecourse(request,response);
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
		List<Course> courseList = courseDao.getListByCoach(0, 1000, coach.getCoachid());
		request.getSession().setAttribute("courseList", courseList);
		response.sendRedirect(request.getServletContext().getContextPath()+"/jsp/coach/index.jsp");
		return;
	}

	private void deletecourse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			int id = Integer.parseInt(request.getParameter("courseid"));
			courseDao.delete(id);
			index(request, response);
			return;
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "删除失败");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
	}

	private void addcourse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Coach coach = (Coach) request.getSession().getAttribute("coach");
			Course course = null;
			course = createCourse(request);
			int courseid = courseDao.add(course);
			course.setCourseid(courseid);
			courseDao.connectcourseandcoach(course, coach);
			response.sendRedirect(request.getContextPath()+"/coach/CoachServlet?method=index");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			return;
		}
	}

	private Course createCourse(HttpServletRequest request) {
		String time = request.getParameter("time");
		String place = request.getParameter("place");
		String content = request.getParameter("content");
		Course course = new Course(-1,time,place,content,false);
		return course;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
