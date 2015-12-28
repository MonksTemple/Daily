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

/**
 * 
 * 任务信息页面
 */
public class TaskInfoActivity extends Activity {
	/**开始时间编辑框*/
	private EditText startDateTime;
	/**结束时间编辑框*/
	private EditText endDateTime;
	/**提醒时间编辑框*/
	private EditText remindDateTime;
	/**任务名称编辑框*/
	private EditText taskName;
	/**任务简介编辑框*/
	private EditText taskDes;
	/**任务发起人编辑框*/
	private EditText taskHolder;
	/**热舞地点编辑框*/
	private EditText place;
	/**确认按钮*/
	private Button sure;
	
	ImageView lines;
	
	/**开始时间字符串*/
	private String initStartDateTime = "2015年12月30日 10:44";
	/**结束时间字符串*/
	private String initEndDateTime = "2015年01月30日 11:44";
	/**提醒时间字符串*/
	private String initRemindDateTime = "2015年01月30日 11:40"; 
	/**弹出菜单*/
	private PopupMenu popupMenu;
	/**菜单*/
	private Menu menu; 
	
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_info);
		
		initial();
		initialMenu();
		setupTime();
	}

	/**
	 * 
	 * 弹出菜单
	 * @param v
	 */
	public void popupmenu(View v) {  
	    popupMenu.show();  
	}  
	
	/**
	 * 
	 * 初始化界面控件
	 */
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
	
	/**
	 * 
	 * 初始化菜单
	 */
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
	
	/**
	 * 
	 * 修改信息
	 */
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
	
	/**
	 * 
	 * 设置时间
	 */
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
