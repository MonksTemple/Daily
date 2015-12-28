package com.example.daily;

import com.example.util.DateTimePickDialogUtil;
import com.example.util.DateUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class AgendaInfoActivity extends Activity {
	private EditText startDateTime;
	private EditText endDateTime;
	private EditText remindDateTime;
	private EditText aName;
	private EditText aInfo;
	private EditText place;
	Button sure;

	private String initStartDateTime = "2015年12月30日 10:44"; // ��ʼ����ʼʱ��
	private String initEndDateTime = "2015年01月30日 11:44"; // ��ʼ������ʱ��
	private String initRemindDateTime = "2015年01月30日 11:40"; // ��ʼ������ʱ��

	private PopupMenu popupMenu;  
	private Menu menu; 
	com.example.model.Activity act;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_info);
		//初始化控件
		initial();
		//初始化菜单栏
		initialMenu();
		//设置时间选择控件
		setUpTime();
		getAct();
	}

	public void popupmenu(View v) {  
		popupMenu.show();  
	}  

	public void initial(){
		//创建时间选择器
		startDateTime = (EditText) findViewById(R.id.startime);
		endDateTime = (EditText) findViewById(R.id.endtime);
		remindDateTime = (EditText) findViewById(R.id.remindtime);
		popupMenu = new PopupMenu(this, findViewById(R.id.lines)); 
		aName=(EditText) findViewById(R.id.tname);
		aInfo=(EditText) findViewById(R.id.info);
		place=(EditText) findViewById(R.id.place);
		sure=(Button) findViewById(R.id.sure);
		menu = popupMenu.getMenu(); 
		startDateTime.setClickable(false);
		endDateTime.setClickable(false);
		remindDateTime.setClickable(false);
		aName.setEnabled(false);
		aInfo.setEnabled(false);
		place.setEnabled(false);
		sure.setVisibility(4);
	}

	public void initialMenu(){
		//通过XML导入菜单栏
		MenuInflater menuInflater = getMenuInflater();  
		menuInflater.inflate(R.menu.agenda_info_menu, menu); 

		// 设置监听事件
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

			@Override  
			public boolean onMenuItemClick(MenuItem item) {  
				switch (item.getItemId()) {  
				case R.id.edit:  
					edit();
					break;   
				case R.id.delete:  
					Toast.makeText(AgendaInfoActivity.this, "删除日程",  
							Toast.LENGTH_LONG).show();  
					break;  
				default:  
					break;  
				}  
				return false;  
			}  
		});  
	}
	
	public void edit(){
		startDateTime.setClickable(true);
		endDateTime.setClickable(true);
		remindDateTime.setClickable(true);
		aName.setEnabled(true);
		aInfo.setEnabled(true);
		place.setEnabled(true);
		sure.setVisibility(0);
	}

	public void setUpTime(){
		startDateTime.setText(initStartDateTime);
		endDateTime.setText(initEndDateTime);
		remindDateTime.setText(initRemindDateTime);
		startDateTime.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						AgendaInfoActivity.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(startDateTime);

			}
		});

		endDateTime.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						AgendaInfoActivity.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(endDateTime);
			}
		});

		remindDateTime.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						AgendaInfoActivity.this, initRemindDateTime);
				dateTimePicKDialog.dateTimePicKDialog(remindDateTime);
			}
		});
	}
	
	public void sure(View view){
		Intent intent = new Intent();
		intent = new Intent(AgendaInfoActivity.this, AgendaListActivity.class);
		startActivity(intent);
		AgendaInfoActivity.this.finish();
	}
	
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(AgendaInfoActivity.this, AgendaListActivity.class);
		startActivity(intent);
		AgendaInfoActivity.this.finish();
	}
	
	public void getAct(){
		Intent intent= AgendaInfoActivity.this.getIntent(); 
		act= (com.example.model.Activity)intent.getSerializableExtra("agenda");
		setActivity(act);
	}
	
	private void setActivity(com.example.model.Activity activity) {
		// TODO Auto-generated method stub
		aName.setText(activity.getName());
		aInfo.setText(activity.getDescription());
		place.setText(activity.getPlace());
		startDateTime.setText(DateUtil.getStringFromDate(activity.getStartTime()));
		endDateTime.setText(DateUtil.getStringFromDate(activity.getStartTime()));
		remindDateTime.setText(DateUtil.getStringFromDate(activity.getRemindTime()));
	}
}
