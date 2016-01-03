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
import com.example.model.Team;
import com.example.model.User;
import com.example.util.ConnectUtil;
import com.example.util.DateUtil;
import com.example.view.ActListView;
import com.example.view.ActView;

/**
 * 
 * 活动操作类
 */
public class ActManage {
	/**活动视图接口*/
	private ActView actView;
	/**活动列表视图接口*/
	private ActListView actListView;

	/**
	 * 
	 * 构造函数
	 * @param actView
	 */
	public ActManage(ActView actView){
		this.actView=actView;
	}

	/**
	 * 
	 *构造函数
	 * @param actListView
	 */
	public ActManage(ActListView actListView){
		this.actListView=actListView;
	}

	/**
	 * 
	 * 添加活动
	 */
	public boolean addActivity(Team team){
		if(team==null)
			return false;
		Activity act=actView.getActivity();
		act.setTeam(team);
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "21");
		map.put("activity", JSON.toJSONString(act));
		String resp = ConnectUtil.getResponse(map);
		if(resp.equals("false")){
			return false;
		}
		else
			return true;
	}

	/**
	 * 
	 * 修改活动
	 */
	public String modifyActivity(Activity act){
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "22");
		map.put("activity", JSON.toJSONString(act));
		String resp = ConnectUtil.getResponse(map);
		return resp;
	}

	/**
	 * 
	 * 删除团队--根据指定的id
	 * @param aId
	 */
	public void deleteActivity(int aId){

	}


	/**
	 * 
	 * 得到所有的团队的活动
	 * @return 团队活动列表
	 */
	public List<Activity> showActivitiesByTeamId(int tId) {
		List<Activity> activitys = new ArrayList();
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "24");
		map.put("tId", tId+"");
		String resp = ConnectUtil.getResponse(map);
		if(!resp.equals("false")){		
			activitys = getActivityFromJson(resp,1);
		}
		return activitys;
	}

	/**
	 * 根据当前用户的id号得到需要的日程类型列表
	 * @param uid
	 * @param type 需要获得的事件类型  Type为0的时候得到所有的列表，Type为1的时候为团队活动，type为2的时候为任务，type3 是个人活动，type4是单独的活动	
	 * @return 日程列表
	 */
	public List<Activity> showAgendByUserId(int uid,int type) {
		List<Activity> activitys = new ArrayList();
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "25");
		map.put("userId", uid+"");
		String resp = ConnectUtil.getResponse(map);
		if(!resp.equals("false")){		
			activitys=getActivityFromJson(resp,type);
		}
		return activitys;
	}	
	

	/**
	 * 获得 需要类型的活动列表
	 * @param type 需要获得的事件类型  Type为0的时候得到所有的列表，Type为1的时候为团队活动，type为2的时候为任务，type3 是个人活动，type4是单独的活动	
	 * @return 活动列表
	 */
	public List<Activity> showIsolateActivities(int type) {
		List<Activity> activitys = new ArrayList();
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "26");
		String resp = ConnectUtil.getResponse(map);
		if(!resp.equals("false")){		
			activitys=getActivityFromJson(resp,type);
		}
		return activitys;
	}
	
	/**
	 * 
	 * 显示所有活动
	 * @param type
	 * @return
	 */
	public List<Activity> showActivities(int type) {
		List<Activity> activitys = new ArrayList();
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "28");
		String resp = ConnectUtil.getResponse(map);
		
		if(!resp.equals("false")){		
			activitys=getActivityFromJson(resp,type);
		}
		return activitys;
	}

	public List<User> showMembers() {
		List<User> users = new ArrayList<User>();


		return users;
	}
	

	/**
	 * 从Json中解析出Activity类
	 * @param resp 传输过来的Json文件字符串
	 * @param myType activity的类型 活动类型,Type为0的时候得到所有的列表，Type为1的时候为团队活动，type为2的时候为任务，type3 是个人活动，type4是单独的活动	
	 * @return 对应类型的activity对象列表
	 */
	private List<Activity> getActivityFromJson(String resp,int myType){
		List<Activity> activitys = new ArrayList();
		try {
			JSONArray actList = new JSONArray(resp);
			int size=actList.length();
			for(int i=0;i<size;i++){
				JSONObject oj = actList.getJSONObject(i);
				int type=oj.getInt("type");  //type只可能会为1，2，3
				//type为4的时候为单独活动
				if(type==myType){
					Activity a=new Activity();
					a.setaId(oj.getInt("aId"));
					a.setName(oj.getString("name"));
					a.setType(type);
					a.setDescription(oj.getString("description"));
					a.setEndTime(DateUtil.getDateFromString(oj.getString("endTime")));
					a.setStartTime(DateUtil.getDateFromString(oj.getString("startTime")));
					a.setRemindTime(DateUtil.getDateFromString(oj.getString("remindTime")));
					a.setPlace(oj.getString("place"));
					//设置Team
					Team team=new Team();
					User user=new User();
					JSONObject toj = oj.getJSONObject("team");
					team.settId(toj.getInt("tId"));
					JSONObject uoj=toj.getJSONObject("creator");
					user.setUserId(uoj.getInt("userId"));
					user.setUserName(uoj.getString("userName"));
					team.setCreator(user);
					a.setTeam(team);
					activitys.add(a);
				}
				if(myType==0){
					Activity a=new Activity();
					a.setaId(oj.getInt("aId"));
					a.setName(oj.getString("name"));
					a.setType(type);
					a.setDescription(oj.getString("description"));
					a.setEndTime(DateUtil.getDateFromString(oj.getString("endTime")));
					a.setStartTime(DateUtil.getDateFromString(oj.getString("startTime")));
					a.setRemindTime(DateUtil.getDateFromString(oj.getString("remindTime")));
					a.setPlace(oj.getString("place"));
					//设置Team
					Team team=new Team();
					User user=new User();
					JSONObject toj = oj.getJSONObject("team");
					team.settId(toj.getInt("tId"));
					JSONObject uoj=toj.getJSONObject("creator");
					user.setUserId(uoj.getInt("userId"));
					user.setUserName(uoj.getString("userName"));
					team.setCreator(user);
					a.setTeam(team);
					activitys.add(a);
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activitys;
	}
}
