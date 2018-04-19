package com.cc.coachsystem.beans;
/**
 * 课程
 */
public class Course {
	
	private int courseid;
	private String time;
	private String place;
	private String content;
	private boolean pass;
	private boolean entered;
	private Coach coach;
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public boolean isEntered() {
		return entered;
	}
	public void setEntered(boolean entered) {
		this.entered = entered;
	}
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Course(int courseid, String time, String place, String content, boolean pass, boolean entered, Coach coach) {
		this.courseid = courseid;
		this.time = time;
		this.place = place;
		this.content = content;
		this.pass = pass;
		this.entered = entered;
		this.coach = coach;
	}
	public Course() {}
}
