package com.example.daily;

import com.example.component.DateTimePickDialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class AgendaInfoActivity extends Activity {
	private EditText startDateTime;
	private EditText endDateTime;
	private EditText remindDateTime;

	private String initStartDateTime = "2015年12月30日 10:44"; // ��ʼ����ʼʱ��
	private String initEndDateTime = "2015年01月30日 11:44"; // ��ʼ������ʱ��
	private String initRemindDateTime = "2015年01月30日 11:40"; // ��ʼ������ʱ��

	private PopupMenu popupMenu;  
	private Menu menu; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_info);
		
	//创建时间选择器
	startDateTime = (EditText) findViewById(R.id.startime);
	endDateTime = (EditText) findViewById(R.id.endtime);
	remindDateTime = (EditText) findViewById(R.id.remindtime);

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
	
	popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
    menu = popupMenu.getMenu(); 
    
 //通过XML导入菜单栏
    MenuInflater menuInflater = getMenuInflater();  
    menuInflater.inflate(R.menu.agenda_info_menu, menu); 
   
    // 设置监听事件
    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

    	@Override  
    	public boolean onMenuItemClick(MenuItem item) {  
    		switch (item.getItemId()) {  
    		case R.id.edit:  
    			Toast.makeText(AgendaInfoActivity.this, "编辑日程",  
    					Toast.LENGTH_LONG).show();  
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

public void popupmenu(View v) {  
    popupMenu.show();  
}  
}
