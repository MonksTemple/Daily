package com.example.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息类
 */
public class User implements Serializable{

	/**用户id*/
	private int userId;        
	/**用户名*/
	private String userName;  
	/**用户密码*/
	private String password;   
	/**手机号码*/
	private String phoneno;    
	/**该用户的所有的通知信*/
	private Set notifications = new HashSet();  
	/**该用户所建的所有的团队*/
	private Set cTeams = new HashSet();          
	/**该用户参加的所有的活动----即该用户的日程（不过包括完成的和未完成的）*/
	private Set activities = new HashSet();     
	/**该用户加入的所有的团队*/
	private Set jTeams = new HashSet();         
	
	/**
	 * 默认构造函数 
	 */
	public User(){
		this.userId = 0;
		this.userName = "";
		this.password = "";
		this.phoneno = "";
		this.notifications = null;
		this.cTeams = null;
		this.activities = null;
		this.jTeams = null;		
	}
	
	/**
	 * 根据现有的user生成一个新的User类对象
	 * @param user
	 */
	public User(User user){
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.phoneno = user.getPhoneno();
		this.notifications = null;
		this.cTeams = null;
		this.activities = null;
		this.jTeams = null;
	}
	
	/**
	 * 获得用户的Id
	 * @return 用户的Id
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * 设置用户Id
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 获得用户名
	 * @return 用户名
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 设置用户名
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 得到用户密码
	 * @return 用户密码
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 设置用户密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 得到用户的手机号
	 * @return 用户手机号
	 */
	public String getPhoneno() {
		return phoneno;
	}
	
	/**
	 * 设置用户的手机号
	 * @param phoneno
	 */
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	/**
	 * 得到用户的通知集
	 * @return 用户的通知集
	 */
	public Set getNotifications() {
		return notifications;
	}
	
	/**
	 * 设置用户的通知集
	 * @param notifications
	 */
	public void setNotifications(Set notifications) {
		this.notifications = notifications;
	}
	
	/**
	 * 得到活动的列表
	 * @return 活动列表
	 */
	public Set getActivities() {
		return activities;
	}
	
	/**
	 * 设置活动及
	 * @param activities
	 */
	public void setActivities(Set activities) {
		this.activities = activities;
	}
	
	/**
	 * 获得用户创建的团队信息
	 * @return 用户创建的团队信息
	 */
	public Set getjTeams() {
		return jTeams;
	}
	
	/**
	 * 添加一个用户创建的团队
	 * @param jTeams
	 */
	public void setjTeams(Set jTeams) {
		this.jTeams = jTeams;
	}
	
	/**
	 * 获得用户参加的团队信息
	 * @return 用户参见的团队信息
	 */
	public Set getcTeams() {
		return cTeams;
	}
	
	/**
	 * 设置用户参加的团队
	 * @param cTeams
	 */
	public void setcTeams(Set cTeams) {
		this.cTeams = cTeams;
	}
}
