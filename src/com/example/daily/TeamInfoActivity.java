package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class TeamInfoActivity extends Activity {
	EditText teamName;
	EditText teamDescription;
	Button sure;

	ImageView pen;
	ImageView cross;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_info);
		initial();
	}

	public void initial(){
		teamName=(EditText) findViewById(R.id.cname);
		teamDescription=(EditText) findViewById(R.id.info);
		sure=(Button) findViewById(R.id.sure);
		pen=(ImageView) findViewById(R.id.edit);
		cross=(ImageView) findViewById(R.id.cross);

		teamName.setEnabled(false);
		teamDescription.setEnabled(false);
		sure.setVisibility(4);
	}

	void back(){
		//界面跳转到创建团队
		Intent intent = new Intent();
		intent = new Intent(TeamInfoActivity.this, TastListActivity.class);
		startActivity(intent);
		TeamInfoActivity.this.finish();
	}

	//编辑信息
	void edit(){
		sure.setVisibility(0);
		cross.setVisibility(0);
	}

	void cross(){
		//删除团队
		Intent intent = new Intent();
		intent = new Intent(TeamInfoActivity.this, TeamListActivity.class);
		startActivity(intent);
		TeamInfoActivity.this.finish();
	}
	
	void sure(View view){
		//修改完信息并提交
		Intent intent = new Intent();
		intent = new Intent(TeamInfoActivity.this, TeamListActivity.class);
		startActivity(intent);
		TeamInfoActivity.this.finish();
	}

}
