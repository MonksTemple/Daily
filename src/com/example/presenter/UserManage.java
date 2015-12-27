package com.example.presenter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.example.model.Activity;
import com.example.model.Team;
import com.example.model.User;
import com.example.util.ConnectUtil;
import com.example.view.UserView;

public class UserManage {
	
	private UserView userView;
	
	public UserManage(UserView tempUserView){
		userView = tempUserView;
	}
	
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
	
	public String login(){
		User user = userView.getUser();
		String userString = JSON.toJSONString(user);
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "01");
		map.put("user", userString);
		String resp = ConnectUtil.getResponse(map);
		return resp;
	}
	
	public boolean logout(int userId){
		
		
		
		return true;
	}
	
	public boolean modify(User user){
		
		
		return true;
	}
	
	
}
