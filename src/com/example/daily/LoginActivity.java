package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	public void login(View view){
		//登录界面跳转
				Intent intent = new Intent();
				intent = new Intent(LoginActivity.this, CalendarActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
	}
	
	public void register(View view){
		//注册界面跳转
		Intent intent = new Intent();
		intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(intent);
	}
}
