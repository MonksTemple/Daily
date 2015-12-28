package com.example.view;

import com.example.model.Team;

/**
 * 团队接口
 */
public interface TeamView {
	/**
	 * 获得团队对象
	 * @return 团队对象
	 */
	public Team getTeam();
	
	/**
	 * 设置团队对象
	 * @param team
	 */
	public void setTeam(Team team);
}		
