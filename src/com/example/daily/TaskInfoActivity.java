package com.example.daily;

import com.example.component.DateTimePickDialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class TaskInfoActivity extends Activity {
	private EditText startDateTime;
	private EditText endDateTime;
	private EditText remindDateTime;

	private String initStartDateTime = "2015��12��30�� 10:44"; // ��ʼ����ʼʱ��
	private String initEndDateTime = "2015��1��30�� 11:44"; // ��ʼ������ʱ��
	private String initRemindDateTime = "2015��1��30�� 11:40"; // ��ʼ������ʱ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_info);
		
		// �����
		startDateTime = (EditText) findViewById(R.id.startime);
		endDateTime = (EditText) findViewById(R.id.endtime);
		remindDateTime = (EditText) findViewById(R.id.remindtime);

		startDateTime.setText(initStartDateTime);
		endDateTime.setText(initEndDateTime);
		remindDateTime.setText(initRemindDateTime);

		startDateTime.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						TaskInfoActivity.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(startDateTime);

			}
		});

		endDateTime.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						TaskInfoActivity.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(endDateTime);
			}
		});
		
		remindDateTime.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						TaskInfoActivity.this, initRemindDateTime);
				dateTimePicKDialog.dateTimePicKDialog(remindDateTime);
			}
		});
	}
}
