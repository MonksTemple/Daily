package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * 活动发布界面
 */
public class ActHoldActActivity extends Activity  {
	
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_holdact);
	}
	
	/**
	 * 
	 * 确认事件处理
	 * @param view
	 */
	public void clickSure(View view){
		Intent intent = new Intent();
		intent = new Intent(ActHoldActActivity.this, ActListActivity.class);
		startActivity(intent);
		ActHoldActActivity.this.finish();
	}
	
	/**
	 * 
	 * 返回事件处理
	 * @param view
	 */
	public void back(View view){
		finish();
	}
	
}
