package com.example.model;

import java.io.Serializable;
import java.util.Set;

public class Team implements Serializable{

	private int tId;             //�Ŷӱ��
	private String name;         //�Ŷ�����
	private String description;  //�ŶӼ��
	private int type;            //�Ŷ�����,TypeΪ1��ʱ��Ϊ�Ŷ��飬typeΪ2��ʱ��Ϊ�γ���
	private int cno;             //�γ�id
	private User creator;        //������
	private Set activities;      //���ŶӴ����Ļ
	private Set members;         //���Ŷ����еĳ�Ա
	
	
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
