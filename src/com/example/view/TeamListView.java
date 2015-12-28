package com.example.view;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Team;

/**
 * 团队列表接口
 */
public interface TeamListView {
	/**
	 * 设置团队列表
	 * @param teamList
	 */
	public void setTeamList(List<Team> teamList);
}
