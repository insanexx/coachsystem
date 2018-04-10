package com.cc.coachsystem.dao;

import java.util.List;

import com.cc.coachsystem.beans.Course;
import com.cc.coachsystem.beans.User;

public interface UserDao {
	public void add(User u);
	public void delete(int id);
	public void update(User u);
	public User getById(int id);
	public List<User> getList(int pageIndex,int pageSize);
	public User getByUsernameAndPassword(String username, String password);
	public void takecourse(Course c,User u);
	public boolean istookCourse(Course c, User u);
	public List<User> getUsersByCourse(int courseid);
}
