package com.cc.coachsystem.dao;

import java.util.List;

import com.cc.coachsystem.beans.Coach;

public interface CoachDao {
	public void add(Coach c);
	public void delete(int id);
	public void update(Coach c);
	public Coach getById(int id);
	public List<Coach> getAll();
	public Coach getByNameAndPassword(String name, String password);
}
