package com.example.daily;

import com.alibaba.fastjson.JSON;
import com.example.model.Team;
import com.example.model.User;
import com.example.presenter.TeamManage;
import com.example.view.TeamView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TeamCreateActivity extends Activity implements TeamView{
	private EditText teamName;
	private EditText teamInfo;
	private String teamNameText;
	private String teamInfoText;
	
	private TeamManage teamManage;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			String login = bundle.getString("createTeam");
			if (login.equals("true")) {//为true说明创建成功
				Intent intent = new Intent();
				intent = new Intent(TeamCreateActivity.this, TeamListActivity.class);
				startActivity(intent);
				TeamCreateActivity.this.finish();
			}else {
				Toast.makeText(TeamCreateActivity.this, "创建失败", Toast.LENGTH_SHORT)
				.show();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_create);
		
		teamName = (EditText) findViewById(R.id.teamName);
		teamInfo = (EditText) findViewById(R.id.teamInfo);
		
		
		teamManage = new TeamManage(this);
	}
	
	//提交按钮
	public void sure(View view){
		//获取信息
		teamNameText = teamName.getText().toString();
		teamInfoText = teamInfo.getText().toString();
		
		if(teamNameText == null || teamNameText.length() <= 0 
			|| teamInfoText == null || teamInfoText.length() <= 0){
			Toast.makeText(TeamCreateActivity.this, "请完整填写信息", Toast.LENGTH_SHORT).show();
		}else{
			new Thread (){
				public void run() {
					
					Message msg = new Message();
					Bundle bundle = new Bundle();
					
					if(teamManage.createTeam()){
						bundle.putString("createTeam", "true");
						msg.setData(bundle);
						
						handler.sendMessage(msg);
					}
					else{
						bundle.putString("createTeam", "false");
						msg.setData(bundle);
						handler.sendMessage(msg);
					}
					
				}
			}.start();
		}	
	}
	
	//返回箭头
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(TeamCreateActivity.this, TeamListActivity.class);
		startActivity(intent);
		TeamCreateActivity.this.finish();
	}
	
	
	@Override
	public Team getTeam() {
		Team team = new Team();
		team.setName(teamNameText);
		team.setDescription(teamInfoText);
		team.settype(1);

		SharedPreferences sp = getApplication().getSharedPreferences("userInfo", Context.MODE_APPEND);
		String userStr = sp.getString("user", "");
		User user = JSON.parseObject(userStr, User.class);
		team.setCreator(user);
		
		return team;
	}

	@Override
	public void setTeam(Team team) {
		// TODO Auto-generated method stub
		
	}
}
