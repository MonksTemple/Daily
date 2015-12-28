package com.example.view;

import java.util.List;

import com.example.model.Activity;

/** 
 * 活动列表接口
 */
public interface ActListView {
	/**
	 * 活动列表的set方法
	 * @param actList 
	 */
	public void setActList(List<Activity> actList);
}
