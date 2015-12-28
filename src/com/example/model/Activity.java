package com.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Activity implements Serializable{

	private int aId;               //�id
	private Team team;             //�Ŷӣ�����Ǹ��˻��Ϊ��
	private String name;           //�����
	private String description;    //����
	private Date startTime;        //��ʼʱ��
	private Date endTime;          //����ʱ��
	private String place;          //�ص�
	private Date remindTime;       //����ʱ��
	private int type;              //����ͣ�TypeΪ1��ʱ��Ϊ�Ŷӻ��typeΪ2��ʱ��Ϊ����type3 �Ǹ��˻��type4�ǵ����Ļ
	private Set participants;      //������в�����
	
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
