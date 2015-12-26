package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AddAgendaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_agenda);
	}
	
	//提交信息
	void sure(View view){
		Intent intent1 = new Intent();
		intent1 = new Intent(AddAgendaActivity.this, AgendaListActivity.class);
		startActivity(intent1);
		AddAgendaActivity.this.finish();
	}
}
