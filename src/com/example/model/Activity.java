package com.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 
 * 活动信息类
 */
public class Activity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**活动id*/
	private int aId;            
	/**团队，如果是个人活动就为空*/
	private Team team;            
	/**活动名称*/
	private String name;         
	/**活动简介*/
	private String description;   
	/**开始时间*/
	private Date startTime;       
	/**结束时间*/
	private Date endTime;         
	/**地点*/
	private String place;         
	/**提醒时间*/
	private Date remindTime;       
	/**活动类型，type为1的时候为团队活动，type为2的时候为任务，type3 是个人活动，type4是单独的活动*/
	private int type;             
	/**活动的所有参与者*/
	private Set participants;      
	
	/**
	 * 活动信息类默认构造函数
	 */
	public Activity(){
		this.aId = 0;
		this.name = "";
		this.description = "";
		this.startTime = new Date();
		this.endTime =  new Date();
		this.place = "";
		this.remindTime = new Date();
		this.type = 0;
		this.team = null;
		this.participants = null;
	}
	
	/**
	 * 
	 *
	 * @param activity
	 */
	public Activity(Activity activity){
		this.aId = activity.getaId();
		this.name = activity.getName();
		this.description = activity.getDescription();
		this.startTime = activity.getStartTime();
		this.endTime = activity.getEndTime();
		this.place = activity.getPlace();
		this.remindTime = activity.getRemindTime();
		this.type = activity.getType();
		this.team = new Team(activity.getTeam());
		this.participants = null;
	}
	
	/**
	 * 
	 * 获得活动的Id
	 * @return 活动Id
	 */
	public int getaId() {
		return aId;
	}
	
	/**
	 * 
	 * 设置活动的Id
	 * @param aId
	 */
	public void setaId(int aId) {
		this.aId = aId;
	}
	
	/**
	 * 
	 * 获得活动名
	 * @return 活动名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * 设置活动名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * 获得活动描述
	 * @return 活动描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * 设置活动描述
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * 获得活动描述
	 * @return 活动描述
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * 
	 * 设置活动开始时间
	 * @param startTime
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 
	 * 获得活动结束时间 
	 * @return 活动结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * 
	 * 设置活动结束时间
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 
	 * 获得活动举办地
	 * @return 活动举办地
	 */
	public String getPlace() {
		return place;
	}
	
	/**
	 * 
	 * 设置活动举办地
	 * @param place
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	
	/**
	 * 
	 * 得到活动提醒时间
	 * @return 活动提醒时间
	 */
	public Date getRemindTime() {
		return remindTime;
	}
	
	/**
	 * 
	 * 设置活动提醒时间
	 * @param remindTime
	 */
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}
	
	/**
	 * 
	 * 获得活动类型：type为1的时候为团队活动，type为2的时候为任务，type3 是个人活动，type4是单独的活动
	 * @return 活动类型
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * 
	 * 设置活动类型
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * 
	 * 获得活动的举办团队
	 * @return 举办团队
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * 设置活动的举办团队
	 * @param team
	 */
	public void setTeam(Team team) {
		this.team = team;
	}
	
	/**
	 * 
	 * 获得活动的参与人员列表
	 * @return 参与人员列表
	 */
	public Set getParticipants() {
		return participants;
	}
	
	/**
	 * 
	 * 设置活动的参与人员
	 * @param participants
	 */
	public void setParticipants(Set participants) {
		this.participants = participants;
	}
}
