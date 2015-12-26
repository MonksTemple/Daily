package com.example.model;

public class Notification {

	private int nId;             //֪ͨ�ı��
	//private int userId;          //�û�
	private String description;     //֪ͨ��Ϣ
	private boolean state;           //�Ƿ��Ѿ��Ķ���0��ʾδ����1��ʾ�Ѷ�
	private User user;           //�û�
	
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
