package com.example.model;
import java.io.Serializable;


public class Notification implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private int nId;             //֪ͨ�ı��
	//private int userId;          //�û�
	private String description;     //֪ͨ��Ϣ
	private boolean state;           //�Ƿ��Ѿ��Ķ���0��ʾδ����1��ʾ�Ѷ�
	private User user;           //�û�
	
	public Notification(){
		this.nId = 0;
		this.description ="";
		this.state = false;
		this.user = null;
	}
	
	public Notification(Notification notification){
		this.nId = notification.getnId();
		this.description = notification.getDescription();
		this.state = notification.getState();
		this.user = new User(notification.getUser());
	}
	
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
