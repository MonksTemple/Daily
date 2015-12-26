package com.example.model;

import java.util.HashSet;
import java.util.Set;

public class User {
	private int userId;        //学生编号，或者教师编号
	private String userName;   //用户名
	private String password;   //密码
	private String phoneno;    //手机号码
	private Set notifications = new HashSet();  //该用户的所有的通知信息 
	private Set cTeams = new HashSet();          //该用户所建的所有的团队
	private Set activities = new HashSet();     //该用户参加的所有的活动----即该用户的日程（不过包括完成的和未完成的）
	private Set jTeams = new HashSet();         //该用户加入的所有的团队
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public Set getNotifications() {
		return notifications;
	}
	public void setNotifications(Set notifications) {
		this.notifications = notifications;
	}
	public Set getActivities() {
		return activities;
	}
	public void setActivities(Set activities) {
		this.activities = activities;
	}
	public Set getjTeams() {
		return jTeams;
	}
	public void setjTeams(Set jTeams) {
		this.jTeams = jTeams;
	}
	public Set getcTeams() {
		return cTeams;
	}
	public void setcTeams(Set cTeams) {
		this.cTeams = cTeams;
	}
}
