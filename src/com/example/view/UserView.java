package com.example.view;

import java.util.List;

import com.example.model.*;

/**
 * 用户对象
 */
public interface UserView {	
	//注册 登录  界面共用
	/**
	 * 获得用户对象
	 * @return 用户对象
	 */
	public User getUser();
	
	/**
	 *设置用户对象
	 * @param user
	 */
	public void setUser(User user);
}
