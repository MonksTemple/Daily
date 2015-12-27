package com.example.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.model.Activity;
import com.example.model.User;
import com.example.util.ConnectUtil;
import com.example.view.ActListView;
import com.example.view.ActView;

public class ActManage {
	private ActView actView;
	private ActListView actListView;

	public ActManage(ActView actView){
		this.actView=actView;
	}

	public ActManage(ActListView actListView){
		this.actListView=actListView;
	}

	/**
	 * 活动type：3
	 */
	public void addActivity(){

	}

	public void modifyActivity(){
		
	}
	
	public void deleteActivity(int aId){

	}

	public Set<Activity> showActivitiesByTeamId() {
		Set<Activity> activitys = null;


		return activitys;
	}

	public Set<Activity> showActivitiesByUserId() {
		Set<Activity> activitys = null;


		return activitys;
	}

	public List<Activity> showIsolateActivities() {
		List<Activity> activitys = new ArrayList();
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "26");
		String resp = ConnectUtil.getResponse(map);
		if(!resp.equals("false"))
			activitys = JSON.parseArray(resp,Activity.class);
		return activitys;
	}

	public List<User> showMembers() {
		List<User> users = new ArrayList<User>();


		return users;
	}
}