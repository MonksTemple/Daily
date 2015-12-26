package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}
	
	void back(View view){
		Intent intent = new Intent();
		intent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(intent);
		RegisterActivity.this.finish();
	}
}
