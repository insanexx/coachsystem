package com.cc.coachsystem.beans;

public class Admin {
	private int adminid;
	private String password;
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", password=" + password + "]";
	}
	public Admin(int adminid, String password) {
		super();
		this.adminid = adminid;
		this.password = password;
	}
	public Admin(){}

}
