package com.example.presenter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.example.model.User;
import com.example.view.UserView;

public class UserManage {
	
	private UserView userView;
	
	public UserManage(UserView tempUserView){
		userView = tempUserView;
		new Thread (){
			public void run() {
				HttpClient client = new DefaultHttpClient();
				String userString = JSON.toJSONString(userView);
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("user", userString);
				list.add(pair);
				try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
					HttpPost post = new HttpPost("http://localhost:8080/server/Servlet");
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					if (response.getStatusLine().getStatusCode() == 200) {
						InputStream in = response.getEntity().getContent();
						//handler.sendEmptyMessage(in.read());//将服务器返回给客户端的标记直接传给handler
						in.close();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}.start();
	}
	
	public boolean register(User user) {
		
		
		
		return true;
	}
	
	public boolean login(){
		User user = userView.getUser();
		return true;
	}
	
	public boolean logout(int userId){
		
		
		
		return true;
	}
	
	public boolean modify(User user){

		return true;
	}
	
	
}
