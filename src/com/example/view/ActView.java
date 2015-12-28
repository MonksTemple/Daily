package com.example.view;

import com.example.model.Activity;

/**
 * 单个活动接口
 */
public interface ActView {
	/**
	 * 获得活动对象
	 * @return 活动对象
	 */
	public Activity getActivity();
	
	/**
	 * 设置活动对象
	 * @param activity
	 */
	public void setActivity(Activity activity);
}
