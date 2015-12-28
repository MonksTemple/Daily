package com.example.model;

import java.io.Serializable;
import java.util.Set;

public class Team implements Serializable{

	private int tId;             //团队编号
	private String name;         //团队名称
	private String description;  //团队简介
	private int type;            //团队类型,Type为1的时候为团队组，type为2的时候为课程组
	private int cno;             //课程id
	private User creator;        //创建人
	private Set activities;      //该团队创建的活动
	private Set members;         //该团队所有的成员
	
	
	public Team(){
		this.tId = 0;
		this.name = "";
		this.description = "";
		this.type = 0;
		this.cno = 0;
		this.creator = null;
	}
	
	public Team(Team team){
		this.tId = team.gettId();
		this.name = team.getName();
		this.description = team.getDescription();
		this.type = team.getType();
		this.cno = team.getCno();
		this.creator = new User(team.getCreator());
	}
	
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
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
	public int getType() {
		return type;
	}
	public void settype(int type) {
		this.type = type;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public Set getActivities() {
		return activities;
	}
	public void setActivities(Set activities) {
		this.activities = activities;
	}
	public Set getMembers() {
		return members;
	}
	public void setMembers(Set members) {
		this.members = members;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}

}
