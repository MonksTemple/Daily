package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AddTaskInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task_info);
	}
	
	
	//点击确认按钮
	void sure(View view){
		Intent intent1 = new Intent();
		intent1 = new Intent(AddTaskInfoActivity.this, TastListActivity.class);
		startActivity(intent1);
		AddTaskInfoActivity.this.finish();
	}
	
	//点击返回箭头
	void back(){
		Intent intent1 = new Intent();
		intent1 = new Intent(AddTaskInfoActivity.this, TastListActivity.class);
		startActivity(intent1);
		AddTaskInfoActivity.this.finish();
	}
}
