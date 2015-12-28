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


public class TeamManage {
	
	private TeamView teamView;
	private TeamListView teamListView;
	
	public TeamManage(TeamView teamView){
		this.teamView = teamView;
	}
	
	public TeamManage(TeamListView teamListView){
		this.teamListView = teamListView;
	}
	
	//团队类型——1
	
	//11
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
	
	//12
	public Boolean addMember(int tId,int userId){
		return null;
	}
	
	//13
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
	
	//14
	public Boolean deleteTeam(int tId){
		
		return null;
	}
	
	//15——查看团队列表
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
	
	//16
	public Boolean deleteMember(int tId,int userId){
		
		return null;
	}
	
	//17
	public Set<User> showMemberList(int tId){
		
		return null;
	}

}
