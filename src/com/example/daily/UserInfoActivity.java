package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UserInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
	}
	
	public void edit(){
		Intent intent1 = new Intent();
		intent1 = new Intent(UserInfoActivity.this, ChangeUserInfoActivity.class);
		startActivity(intent1);
		UserInfoActivity.this.finish();
	}
}
