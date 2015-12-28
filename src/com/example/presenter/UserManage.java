package com.example.presenter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.example.model.Activity;
import com.example.model.Team;
import com.example.model.User;
import com.example.util.ConnectUtil;
import com.example.view.UserView;

/**
 * 
 * 用户类处理
 */
public class UserManage {
	
	/**用户视图接口*/
	private UserView userView;
	
	/**
	 * 
	 * 构造函数
	 * @param tempUserView
	 */
	public UserManage(UserView tempUserView){
		userView = tempUserView;
	}
	
	/**
	 * 
	 * 注册
	 * @return  true--登陆成功，false--登陆失败
	 */
	public boolean register() {
		User user = userView.getUser();
		String userString = JSON.toJSONString(user);
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "02");
		map.put("user", userString);
		String resp = ConnectUtil.getResponse(map);
		if(resp.equals("false")){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 注册
	 * @return 用户后的信息
	 */
	public String login(){
		User user = userView.getUser();
		String userString = JSON.toJSONString(user);
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "01");
		map.put("user", userString);
		String resp = ConnectUtil.getResponse(map);
		return resp;
	}
	
	/**
	 * 
	 * 用户注销
	 * @param userId
	 * @return true--注销成功，false--注销失败
	 */
	public boolean logout(int userId){
		
		return true;
	}
	
	/**
	 * 
	 * 修改用户信息
	 * @return true--修改成功，false--修改失败
	 */
	public boolean modify(){
		User user = userView.getUser();
		String userString = JSON.toJSONString(user);
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "04");
		map.put("user", userString);
		String resp = ConnectUtil.getResponse(map);
		if(resp.equals("false")){
			return false;
		}
		return true;
	}
	
	
}
