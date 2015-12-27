package com.example.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
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
	public Boolean addMember(int tId,int userId){
		return null;
	}
	
	public Boolean modifyInfo(Team team){
		
		return null;
	}
	public Boolean deleteTeam(int tId){
		
		return null;
	}
	public Set<Team> ShowTeamList(){ 
		
		return null;
	}
	public Boolean deleteMember(int tId,int userId){
		
		return null;
	}
	public Set<User> showMemberList(int tId){
		
		return null;
	}

}
