package com.example.model;

import java.io.Serializable;

/**
 * 
 * 通知类
 */
public class Notification implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**通知的编号*/
	private int nId;                
	 /**通知信息*/
	private String description;    
	 /**是否已经阅读，0表示未读，1表示已读*/
	private boolean state;           
	 /**用户*/
	private User user;          
	
	/**
	 * 通知类默认构造函数
	 */
	public Notification(){
		this.nId = 0;
		this.description ="";
		this.state = false;
		this.user = null;
	}
	
	/**
	 *
	 * @param notification
	 */
	public Notification(Notification notification){
		this.nId = notification.getnId();
		this.description = notification.getDescription();
		this.state = notification.getState();
		this.user = new User(notification.getUser());
	}
	
	/**
	 * 
	 * 获得通知的描述
	 * @return 通知描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * 设置通知描述
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * 获得通知状态
	 * @return 通知状态
	 */
	public boolean getState() {
		return state;
	}
	
	/**
	 * 
	 * 设置通知状态
	 * @param state
	 */
	public void setState(boolean state) {
		this.state = state;
	}
	
	/**
	 * 
	 * 活动通知的Id
	 * @return 通知Id
	 */
	public int getnId() {
		return nId;
	}
	
	/**
	 * 
	 * 设置通知的Id
	 * @param nId
	 */
	public void setnId(int nId) {
		this.nId = nId;
	}
	
	/**
	 * 获得通知的主人
	 * @return 用户
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * 设置通知的主任
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
