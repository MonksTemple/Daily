package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * 
 * 添加日程页面
 */
public class AddAgendaActivity extends Activity {

	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_agenda);
	}

	/**
	 * 
	 * 确认按钮事件处理
	 * @param view
	 */
	public void sure(View view){
		Intent intent1 = new Intent();
		intent1 = new Intent(AddAgendaActivity.this, AgendaListActivity.class);
		startActivity(intent1);
		AddAgendaActivity.this.finish();
	}

	/**
	 * 
	 * 返回按钮事件处理
	 * @param view
	 */
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(AddAgendaActivity.this, AgendaListActivity.class);
		startActivity(intent);
		AddAgendaActivity.this.finish();
	}

}
