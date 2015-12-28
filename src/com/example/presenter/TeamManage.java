package com.example.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.example.model.Activity;
import com.example.model.Team;
import com.example.model.User;
import com.example.util.ConnectUtil;
import com.example.util.DateUtil;
import com.example.view.TeamListView;
import com.example.view.TeamView;

/**
 * 
 * 团队处理类
 */
public class TeamManage {
	/**团队视图接口*/
	private TeamView teamView;
	/**团队列表视图接口*/
	private TeamListView teamListView;
	
	/**
	 * 
	 * 构造函数
	 * @param teamView
	 */
	public TeamManage(TeamView teamView){
		this.teamView = teamView;
	}
	
	/**
	 * 
	 * 构造函数
	 * @param teamListView
	 */
	public TeamManage(TeamListView teamListView){
		this.teamListView = teamListView;
	}
	
	/**
	 * 
	 * 创建团队
	 * @return true--创建成功，false--创建失败
	 */
	public Boolean createTeam(){
		Team team = teamView.getTeam();
		String teamString = JSON.toJSONString(team);
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "11");
		map.put("team", teamString);
		String resp = ConnectUtil.getResponse(map);
		if(resp.equals("false")){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 添加团队成员--根据指定的团队id和用户id
	 * @param tId
	 * @param userId
	 * @return true--添加成功，false--添加失败
	 */
	public Boolean addMember(int tId,int userId){
		return null;
	}
	
	/**
	 * 
	 * 修改团队信息
	 * @return true--修改成功，false--修改失败
	 */
	public Boolean modifyInfo(){
		Team team = teamView.getTeam();
		String teamString = JSON.toJSONString(team);
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "13");
		map.put("team", teamString);
		String resp = ConnectUtil.getResponse(map);
		if(resp.equals("false")){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 删除团队--根据指定的id
	 * @param tId
	 * @return true--删除成功，false--删除失败
	 */
	public Boolean deleteTeam(int tId){
		
		return null;
	}
	
	/**
	 * 
	 * 查看团队列表
	 * @return 团队列表
	 */
	public List<Team> ShowTeamList(){ 
		List<Team> teams = new ArrayList<Team>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "15");
		String resp = ConnectUtil.getResponse(map);
		
		if(!resp.equals("false")){		
			try {
				//将json转换为对象数组
				JSONArray teamList = new JSONArray(resp);
				
				for(int i = 0; i < teamList.length(); i++){
					JSONObject oj = teamList.getJSONObject(i);
					
					int type = oj.getInt("type");
					
					//type为1的时候为团队
					if(type == 1){
						Team tempTeam = new Team();
						tempTeam.setCreator(JSON.parseObject(oj.getString("creator"), User.class));
						tempTeam.settId(oj.getInt("tId"));
						tempTeam.setName(oj.getString("name"));
						tempTeam.settype(1);
						tempTeam.setDescription(oj.getString("description"));
						teams.add(tempTeam);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}	
		return teams;
	}
	
	/**
	 * 
	 * 删除团队成员---根据指定的团队id和用户id
	 * @param tId
	 * @param userId
	 * @return true--删除成功，false--删除失败
	 */
	public Boolean deleteMember(int tId,int userId){
		
		return null;
	}
	
	/**
	 * 
	 * 显示团队成员列表--根据指定的团队id
	 * @param tId
	 * @return 团队成员列表
	 */
	public Set<User> showMemberList(int tId){
		
		return null;
	}

}
