
package com.example.model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private int userId;        //ѧ����ţ����߽�ʦ���
	private String userName;   //�û���
	private String password;   //����
	private String phoneno;    //�ֻ�����
	private Set notifications = new HashSet();  //���û������е�֪ͨ��Ϣ 
	private Set cTeams = new HashSet();          //���û����������е��Ŷ�
	private Set activities = new HashSet();     //���û��μӵ����еĻ----�����û����ճ̣�����������ɵĺ�δ��ɵģ�
	private Set jTeams = new HashSet();         //���û���������е��Ŷ�
	
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
