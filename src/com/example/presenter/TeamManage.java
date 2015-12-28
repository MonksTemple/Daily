package com.example.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.example.model.Team;
import com.example.model.User;
import com.example.util.ConnectUtil;
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
	public Boolean modifyInfo(Team team){
		
		return null;
	}
	
	//14
	public Boolean deleteTeam(int tId){
		
		return null;
	}
	
	//15
	public Set<Team> ShowTeamList(){ 
		
		//String teamString = JSON.toJSONString(team);
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "15");
		//map.put("team", teamString);
		String resp = ConnectUtil.getResponse(map);
		
		return null;
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
