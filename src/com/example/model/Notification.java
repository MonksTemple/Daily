package com.example.model;

public class Notification {

	private int nId;             //通知的编号
	//private int userId;          //用户
	private String description;     //通知信息
	private boolean state;           //是否已经阅读，0表示未读，1表示已读
	private User user;           //用户
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getnId() {
		return nId;
	}
	public void setnId(int nId) {
		this.nId = nId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
