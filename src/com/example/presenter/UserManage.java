package com.example.presenter;

import java.util.Iterator;
import java.util.Set;

import com.example.model.User;
import com.example.view.UserView;

public class UserManage {
	
	private UserView userView;
	
	public UserManage(UserView tempUserView){
		userView = tempUserView;
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
