package com.example.model;

import java.io.Serializable;
import java.util.Set;

/**
 * 团队信息类
 */
public class Team implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**团队编号*/
	private int tId;           
	/**团队名称*/
	private String name;        
	/**团队简介*/
	private String description; 
	/**团队类型,type为1的时候为团队组，type为2的时候为课程组*/
	private int type;           
	/**课程id*/
	private int cno;            
	/**创建人*/
	private User creator;       
	/**该团队创建的活动*/
	private Set activities;     
	/**该团队所有的成员*/
	private Set members;         
	
	
	/**
	 * 团队信息类默认构造函数
	 */
	public Team(){
		this.tId = 0;
		this.name = "";
		this.description = "";
		this.type = 0;
		this.cno = 0;
		this.creator = null;
	}
	
	/**
	 * @param team
	 */
	public Team(Team team){
		this.tId = team.gettId();
		this.name = team.getName();
		this.description = team.getDescription();
		this.type = team.getType();
		this.cno = team.getCno();
		this.creator = new User(team.getCreator());
	}
	
	/** 
	 * 获取团队Id
	 * @return 团队Id
	 */
	public int gettId() {
		return tId;
	}
	
	/**
	 * 设置团队Id
	 * @param tId
	 */
	public void settId(int tId) {
		this.tId = tId;
	}
	
	/**
	 * 获得团队名
	 * @return 团队名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置团队名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获得团队描述信息
	 * @return 团队描述信息
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置团队描述信息
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 得到团队类型,type为1的时候为团队组，type为2的时候为课程组
	 * @return 团队类型
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * 设置团队类型: type为1的时候为团队组，type为2的时候为课程组
	 * @param type
	 */
	public void settype(int type) {
		this.type = type;
	}
	
	/**
	 * 得到团队对应的课程号（如果该团队是课程组）
	 * @return 课程号
	 */
	public int getCno() {
		return cno;
	}
	
	/**
	 * 设置课程号（如果该团队是课程组）
	 * @param cno
	 */
	public void setCno(int cno) {
		this.cno = cno;
	}
	
	/**
	 * 得到团队所创建的所有活动信息
	 * @return 活动列表
	 */
	public Set getActivities() {
		return activities;
	}
	
	/**
	 * 
	 * 设置团队的活动信息
	 * @param activities
	 */
	public void setActivities(Set activities) {
		this.activities = activities;
	}
	
	/**
	 * 
	 * 获得团队所有的成员
	 * @return 团队成员列表
	 */
	public Set getMembers() {
		return members;
	}
	
	/**
	 * 
	 * 设置团队的成员
	 * @param members
	 */
	public void setMembers(Set members) {
		this.members = members;
	}
	
	/**
	 * 
	 * 得到团队的创建人
	 * @return 团队创建人
	 */
	public User getCreator() {
		return creator;
	}
	
	/**
	 * 
	 * 设置团队的创建人
	 * @param creator
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

}
