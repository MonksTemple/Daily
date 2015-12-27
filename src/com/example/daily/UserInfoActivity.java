package com.example.daily;

import com.example.model.User;
import com.example.view.UserView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

	public void edit(View view){
		Intent intent1 = new Intent();
		intent1 = new Intent(UserInfoActivity.this, ChangeUserInfoActivity.class);
		startActivity(intent1);
		UserInfoActivity.this.finish();
	}

	//返回箭头
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(UserInfoActivity.this, CalendarActivity.class);
		startActivity(intent);
		UserInfoActivity.this.finish();
	}
}
