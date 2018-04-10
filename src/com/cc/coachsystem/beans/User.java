package com.cc.coachsystem.beans;

public class User {
	private int userid;
	private String username;
	private String password;
	private String name;
	private String phone;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + "]";
	}
	public User(int userid, String username, String password, String name, String phone) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}
	public User() { }
}
