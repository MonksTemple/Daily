package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ChangeUserInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_user_info);
	}

	//返回箭头
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
		startActivity(intent);
		ChangeUserInfoActivity.this.finish();
	}
	
	public void sure(View view){
		Intent intent1 = new Intent();
		intent1 = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
		startActivity(intent1);
		ChangeUserInfoActivity.this.finish();
	}
}
