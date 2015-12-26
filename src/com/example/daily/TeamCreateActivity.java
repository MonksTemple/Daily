package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TeamCreateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_create);
	}
	
	void sure(View view){
		Intent intent = new Intent();
		intent = new Intent(TeamCreateActivity.this, TeamListActivity.class);
		startActivity(intent);
		TeamCreateActivity.this.finish();
	}
}
