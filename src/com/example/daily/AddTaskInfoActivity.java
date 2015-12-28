package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * 
 * 添加任务信息页面
 */
public class AddTaskInfoActivity extends Activity {
	
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task_info);
	}
	
	
	/**
	 * 
	 * 确认按钮事件处理
	 * @param view
	 */
	void sure(View view){
		Intent intent1 = new Intent();
		intent1 = new Intent(AddTaskInfoActivity.this, TastListActivity.class);
		startActivity(intent1);
		AddTaskInfoActivity.this.finish();
	}
	
	/**
	 * 
	 * 返回按钮事件处理
	 */
	void back(){
		Intent intent1 = new Intent();
		intent1 = new Intent(AddTaskInfoActivity.this, TastListActivity.class);
		startActivity(intent1);
		AddTaskInfoActivity.this.finish();
	}
}
