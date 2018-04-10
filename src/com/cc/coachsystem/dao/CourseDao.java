package com.cc.coachsystem.dao;

import java.util.List;

import com.cc.coachsystem.beans.Coach;
import com.cc.coachsystem.beans.Course;

public interface CourseDao {
	public int add(Course course);
	public void delete(int id);
	public void update(Course course);
	public Course getById(int id);
	public List<Course> getList(int pageIndex,int pageSize, int userid);
	public List<Course> getListByCoach(int pageIndex,int pageSize,int coachid);
	public List<Course> getList(int pageIndex, int pageSize);
	void connectcourseandcoach(Course c, Coach coach);
	
}
