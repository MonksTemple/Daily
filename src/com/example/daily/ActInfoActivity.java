package com.example.daily;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class ActInfoActivity extends Activity {
	private EditText actNameText;
	private EditText actIntroText;
	private EditText actSponserText;
	private EditText actStartTimeText;
	private EditText actEndTimeText;
	private EditText actPlaceText;
	private EditText actRemindTimeText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_info);
		
		actNameText = (EditText) findViewById(R.id.actNameText); 
		actIntroText = (EditText) findViewById(R.id.actIntroText); 
		actSponserText = (EditText) findViewById(R.id.actSponserText); 
		actStartTimeText = (EditText) findViewById(R.id.actStartTimeText); 
		actEndTimeText = (EditText) findViewById(R.id.actEndTimeText); 
		actPlaceText = (EditText) findViewById(R.id.actPlaceText); 
		actRemindTimeText = (EditText) findViewById(R.id.actRemindTimeText); 
		
		
		
		
	}

}
