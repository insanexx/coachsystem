package com.cc.coachsystem.beans;
/**
 * 教练
 */
public class Coach {
	private int coachid;
	private String password;
	private String name;
	private String phone;
	private String idcard;
	public int getCoachid() {
		return coachid;
	}
	public void setCoachid(int coachid) {
		this.coachid = coachid;
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
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	@Override
	public String toString() {
		return "Coach [coachid=" + coachid + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", idcard=" + idcard + "]";
	}
	public Coach(int coachid, String password, String name, String phone, String idcard) {
		super();
		this.coachid = coachid;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.idcard = idcard;
	}
	public Coach() {}
}
