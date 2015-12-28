package com.example.daily;

import com.example.model.Team;
import com.example.presenter.TeamManage;
import com.example.view.TeamView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TeamInfoActivity extends Activity implements TeamView{
	EditText teamName;
	EditText teamDescription;
	Button sure;

	String teamNameText;
	String teamDescriptionText;
	
	ImageView pen;
	Team currentTeam;
	
	TeamManage teamManage;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			String info = bundle.getString("teamList");
			if (info.equals("true")) {//为true说明创建成功
				setTeam(currentTeam);	
				sure.setVisibility(4);
				pen.setVisibility(0);
				teamName.setEnabled(false);
				teamDescription.setEnabled(false);
				
			}else {
				Toast.makeText(TeamInfoActivity.this, "修改团队信息失败", Toast.LENGTH_SHORT)
				.show();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_info);
		initial();
		getTeamInfo();
	}

	public void initial(){
		teamName = (EditText) findViewById(R.id.cname);
		teamDescription = (EditText) findViewById(R.id.info);
		sure = (Button) findViewById(R.id.sure);
		pen = (ImageView) findViewById(R.id.edit);

		teamName.setEnabled(false);
		teamDescription.setEnabled(false);
		sure.setVisibility(4);
		
		teamManage = new TeamManage(this);
	}

	public void back(View view){
//		//界面跳转到创建团队
//		Intent intent = new Intent();
//		intent = new Intent(TeamInfoActivity.this, TeamListActivity.class);
//		startActivity(intent);
//		TeamInfoActivity.this.finish();
		finish();
	}

	//编辑信息
	public void edit(View view){
		sure.setVisibility(0);
		pen.setVisibility(4);
		teamName.setEnabled(true);
		teamDescription.setEnabled(true);
	}
	
	public void sure(View view){
		teamNameText = teamName.getText().toString();
		teamDescriptionText = teamDescription.getText().toString();
		
		if(teamNameText == null || teamNameText.length() <= 0 
			|| teamDescriptionText == null || teamDescriptionText.length() <= 0){
			Toast.makeText(TeamInfoActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
		}else{
			new Thread (){
				public void run() {
					Message msg = new Message();
					Bundle bundle = new Bundle();

					if(teamManage.modifyInfo()){
						bundle.putString("modify", "true");	
						msg.setData(bundle);
						handler.sendMessage(msg);
					}
					else{
						bundle.putString("modify", "false");
						msg.setData(bundle);
						handler.sendMessage(msg);
					}
					
				}
			}.start();	
		}
		
	}
	
	/**
	 * 
	 * 将团队信息显示在信息页面中
	 * @param team
	 */
	public void setTeamInfo(Team team){
		teamName.setText(team.getName());
		teamDescription.setText(team.getDescription());
	}
	
	/**
	 * 
	 * 获得团队信息
	 */
	public void getTeamInfo(){
		Intent intent = TeamInfoActivity.this.getIntent(); 
		currentTeam = (Team)intent.getSerializableExtra("team");
		setTeamInfo(currentTeam);
	}

	@Override
	public Team getTeam() {
		currentTeam.setName(teamNameText);
		currentTeam.setDescription(teamDescriptionText);
			
		return currentTeam;
	}

	@Override
	public void setTeam(Team team) {
		teamName.setText(team.getName());
		teamName.setText(team.getDescription());
	}

}
