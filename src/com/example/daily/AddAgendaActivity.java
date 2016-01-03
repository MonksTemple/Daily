package com.example.daily;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.example.model.Team;
import com.example.model.User;
import com.example.presenter.ActManage;
import com.example.presenter.TeamManage;
import com.example.util.DateTimePickDialogUtil;
import com.example.util.DateUtil;
import com.example.view.ActView;
import com.example.view.TeamView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

/**
 * 
 * 添加日程页面
 */
public class AddAgendaActivity extends Activity implements ActView,TeamView {
	/**活动管理类对象*/
	private ActManage actManage;
	private TeamManage teamManage;
	/**活动类*/
	com.example.model.Activity act=new com.example.model.Activity();
	/**日程开始时间编辑框*/
	private EditText startDateTime;
	/**日程结束时间编辑框*/
	private EditText endDateTime;
	/**日程提醒时间编辑框*/
	private EditText remindDateTime;
	/**日程名称编辑框*/
	private EditText aName;
	/**日程信息编辑框*/
	private EditText aInfo;
	/**日程地点编辑框*/
	private EditText place;
	/**确认按钮*/
	Button sure;
	
	/**日程开始时间字符串*/
	private String initStartDateTime = DateUtil.getStringFromDate(new Date());
	/**日程结束时间字符串*/
	private String initEndDateTime = DateUtil.getStringFromDate(new Date());
	/**日程地点字符串*/
	private String initRemindDateTime = DateUtil.getStringFromDate(new Date());
	
	/**处理类对象*/
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			boolean resp=bundle.getBoolean("resp");
			if(resp){
				Toast.makeText(AddAgendaActivity.this, "创建成功",  
						Toast.LENGTH_SHORT).show(); 
				finish();
			}else{
				Toast.makeText(AddAgendaActivity.this, "创建失败",  
						Toast.LENGTH_SHORT).show();  
			}
		}
	};

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
		//初始化控件
				initial();
				//设置时间选择控件
				setUpTime();
				actManage=new ActManage(this);
				teamManage=new TeamManage(this);
	}

	/**
	 * 
	 * 确认按钮事件处理
	 * @param view
	 */
	public void sure(View view){
		new Thread(){
			public void run(){					
				Message msg = new Message();
				Bundle bundle = new Bundle();
				Team team=teamManage.addPersonTeam();
				boolean resp=actManage.addActivity(team);
				bundle.putBoolean("resp", resp);
				msg.setData(bundle);
				handler.sendMessage(msg);
			}
		}.start();
	}

	/**
	 * 
	 * 返回按钮事件处理
	 * @param view
	 */
	public void back(View view){
		finish();
	}

	@Override
	public com.example.model.Activity getActivity() {
		// TODO Auto-generated method stub
		act.setType(3);
		act.setDescription(aInfo.getText().toString());
		act.setStartTime(DateUtil.getDateFromString(startDateTime.getText().toString()));
		act.setEndTime(DateUtil.getDateFromString(endDateTime.getText().toString()));
		act.setName(aName.getText().toString());
		act.setPlace(place.getText().toString());
		act.setRemindTime(DateUtil.getDateFromString(remindDateTime.getText().toString()));
		return act;
	}

	@Override
	public void setActivity(com.example.model.Activity activity) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * 处理控件
	 */
	public void initial(){
		//创建时间选择器
		startDateTime = (EditText) findViewById(R.id.startime);
		endDateTime = (EditText) findViewById(R.id.endtime);
		remindDateTime = (EditText) findViewById(R.id.remindtime);
		aName=(EditText) findViewById(R.id.tname);
		aInfo=(EditText) findViewById(R.id.info);
		place=(EditText) findViewById(R.id.place);
		aName.setText("");
		aInfo.setText("");
		place.setText("");
	}
	
	/**
	 * 
	 * 设置时间
	 */
	public void setUpTime(){
		startDateTime.setText(initStartDateTime);
		endDateTime.setText(initEndDateTime);
		remindDateTime.setText(initRemindDateTime);
		startDateTime.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						AddAgendaActivity.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(startDateTime);

			}
		});

		endDateTime.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						AddAgendaActivity.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(endDateTime);
			}
		});

		remindDateTime.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						AddAgendaActivity.this, initRemindDateTime);
				dateTimePicKDialog.dateTimePicKDialog(remindDateTime);
			}
		});
	}

	@Override
	public Team getTeam() {
		// TODO Auto-generated method stub
		Team team=new Team();
		SharedPreferences sp = getApplication().getSharedPreferences("userInfo", Context.MODE_APPEND);
		String user = sp.getString("user", "");
		User users = JSON.parseObject(user,User.class);
		team.setCreator(users);
		team.setCno(0);
		team.settype(0);
		return team;
	}

	@Override
	public void setTeam(Team team) {
		// TODO Auto-generated method stub
		
	}

}
