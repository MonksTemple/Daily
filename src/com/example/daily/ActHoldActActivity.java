package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActHoldActActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_holdact);
	}
	
	void clickSure(){
		Intent intent = new Intent();
		intent = new Intent(ActHoldActActivity.this, ActListActivity.class);
		startActivity(intent);
		ActHoldActActivity.this.finish();
	}
	
}
