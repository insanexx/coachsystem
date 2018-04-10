package com.cc.coachsystem.dao;

import java.util.List;

import com.cc.coachsystem.beans.Admin;

public interface AdminDao {
	public void add(Admin a);
	public void delete(int id);
	public void update(Admin a);
	public Admin getById(int id);
	public List<Admin> getAll();
	public Admin getByIdAndPassword(int adminid, String password);
}
