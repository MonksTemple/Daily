package com.example.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.example.model.Activity;
import com.example.model.User;
import com.example.util.ConnectUtil;
import com.example.util.DateUtil;
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
	 * 活动type：2
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

	/**
	 * 根据当前用户的id号得到他的事件列表
	 * @param uid
	 * @return
	 */
	public List<Activity> showActivitiesByUserId(int uid) {
		List<Activity> activitys = new ArrayList();
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "25");
		map.put("userId", uid+"");
		String resp = ConnectUtil.getResponse(map);
		if(!resp.equals("false")){		
			try {
				JSONArray actList = new JSONArray(resp);
				int size=actList.length();
				for(int i=0;i<size;i++){
					JSONObject oj = actList.getJSONObject(i);
					int type=oj.getInt("type");
					//type为3 的时候为个人活动
					if(type==3){
						Activity a=new Activity();
						a.setaId(oj.getInt("aId"));
						a.setName(oj.getString("name"));
						a.setDescription(oj.getString("description"));
						a.setEndTime(DateUtil.getDateFromString(oj.getString("endTime")));
						a.setStartTime(DateUtil.getDateFromString(oj.getString("startTime")));
						a.setRemindTime(DateUtil.getDateFromString(oj.getString("remindTime")));
						a.setPlace(oj.getString("place"));
						activitys.add(a);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return activitys;
	}

	/**
	 * 获得个人发布的活动列表
	 * @return List<Activity>
	 */
	public List<Activity> showIsolateActivities() {
		List<Activity> activitys = new ArrayList();
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "26");
		String resp = ConnectUtil.getResponse(map);
		if(!resp.equals("false")){		
			try {
				JSONArray actList = new JSONArray(resp);
				int size=actList.length();
				for(int i=0;i<size;i++){
					JSONObject oj = actList.getJSONObject(i);
					int type=oj.getInt("type");
					//type为4的时候为单独活动
					if(type==4){
						Activity a=new Activity();
						a.setaId(oj.getInt("aId"));
						a.setName(oj.getString("name"));
						a.setDescription(oj.getString("description"));
						a.setEndTime(DateUtil.getDateFromString(oj.getString("endTime")));
						a.setStartTime(DateUtil.getDateFromString(oj.getString("startTime")));
						a.setRemindTime(DateUtil.getDateFromString(oj.getString("remindTime")));
						a.setPlace(oj.getString("place"));
						activitys.add(a);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return activitys;
	}

	public List<User> showMembers() {
		List<User> users = new ArrayList<User>();


		return users;
	}
}
