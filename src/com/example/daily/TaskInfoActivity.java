package com.example.daily;

import com.example.util.DateTimePickDialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class TaskInfoActivity extends Activity {
	private EditText startDateTime;
	private EditText endDateTime;
	private EditText remindDateTime;
	EditText taskName;
	EditText taskDes;
	EditText taskHolder;
	EditText place;
	Button sure;
	
	ImageView lines;

	private String initStartDateTime = "2015年12月30日 10:44";
	private String initEndDateTime = "2015年01月30日 11:44"; 
	private String initRemindDateTime = "2015年01月30日 11:40"; 

	private PopupMenu popupMenu;  
	private Menu menu; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_info);
		
		initial();
		initialMenu();
		setupTime();
	}

	public void popupmenu(View v) {  
	    popupMenu.show();  
	}  
	
	public void initial(){
		taskName = (EditText) findViewById(R.id.tname);
		taskDes = (EditText) findViewById(R.id.info);
		taskHolder = (EditText) findViewById(R.id.createname);
		place = (EditText) findViewById(R.id.place);
		sure = (Button) findViewById(R.id.sure);
		startDateTime = (EditText) findViewById(R.id.startime);
		endDateTime = (EditText) findViewById(R.id.endtime);
		remindDateTime = (EditText) findViewById(R.id.remindtime);
		lines=(ImageView) findViewById(R.id.lines);
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
	    menu = popupMenu.getMenu(); 
	    
	    taskName.setEnabled(false);
	    taskDes.setEnabled(false);
	    taskHolder.setEnabled(false);
	    place.setEnabled(false);
	    sure.setVisibility(4);
	    startDateTime.setClickable(false);
	    endDateTime.setClickable(false);
	    remindDateTime.setClickable(false);
	}
	
	public void initialMenu(){
		 //通过XML导入菜单栏
	    MenuInflater menuInflater = getMenuInflater();  
	    menuInflater.inflate(R.menu.task_info_menu, menu); 
	   
	    // 设置监听事件
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

	    	@Override  
	    	public boolean onMenuItemClick(MenuItem item) {  
	    		switch (item.getItemId()) {  
	    		case R.id.edit: 
	    			changeInfo();
	    			Toast.makeText(TaskInfoActivity.this, "修改信息",  
	    					Toast.LENGTH_LONG).show();  
	    			break; 
	    		case R.id.delete:  
	    			Toast.makeText(TaskInfoActivity.this, "撤销任务",  
	    					Toast.LENGTH_LONG).show();  
	    			break; 
	    		default:  
	    			break;  
	    		}  
	    		return false;  
	    	}  
	    });  
	}
	
	
	void changeInfo(){
		taskName.setEnabled(true);
	    taskDes.setEnabled(true);
	    taskHolder.setEnabled(true);
	    place.setEnabled(true);
	    sure.setVisibility(0);
	    startDateTime.setClickable(true);
	    endDateTime.setClickable(true);
	    remindDateTime.setClickable(true);
	    lines.setVisibility(8);
	}
	
	public void setupTime(){
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
