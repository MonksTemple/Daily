package com.example.model;

import java.util.Date;
import java.util.Set;

public class Activity {

	private int aId;               //活动id
	private Team team;             //团队，如果是个人活动就为空
	private String name;           //活动名称
	private String description;    //活动简介
	private Date startTime;        //开始时间
	private Date endTime;          //结束时间
	private String place;          //地点
	private Date remindTime;       //提醒时间
	private int type;              //活动类型，Type为1的时候为团队活动，type为2的时候为任务，type3 是个人活动，type4是单独的活动
	private Set participants;      //活动的所有参与者
	
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Date getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Set getParticipants() {
		return participants;
	}
	public void setParticipants(Set participants) {
		this.participants = participants;
	}
}
