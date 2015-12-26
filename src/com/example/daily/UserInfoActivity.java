package com.example.daily;

import com.example.model.User;
import com.example.view.UserView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UserInfoActivity extends Activity implements UserView{
	private TextView userNameText;
	private TextView snoText;
	private TextView telText;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		
		userNameText = (TextView)findViewById(R.id.username);
		snoText = (TextView)findViewById(R.id.sno);
		telText = (TextView)findViewById(R.id.tel);
	}

	@Override
	public User getUser() {
		
		return null;
	}

	@Override
	public void setUser(User user) {
		userNameText.setText(user.getUserName());
		snoText.setText(user.getUserId());
		telText.setText(user.getPhoneno());
	}
}
