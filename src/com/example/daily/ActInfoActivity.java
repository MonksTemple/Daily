package com.example.daily;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class ActInfoActivity extends Activity {
	private EditText actNameText;
	private EditText actIntroText;
	private EditText actSponserText;
	private EditText actStartTimeText;
	private EditText actEndTimeText;
	private EditText actPlaceText;
	private EditText actRemindTimeText;
	
	private PopupMenu popupMenu;  
	private Menu menu; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_info);
		initial();
		//初始化菜单栏
		initialMenu();
	}
	
	public void popupmenu(View v) {  
        popupMenu.show();  
    }  
	
	
	public void initial(){
		actNameText = (EditText) findViewById(R.id.actNameText); 
		actIntroText = (EditText) findViewById(R.id.actIntroText); 
		actSponserText = (EditText) findViewById(R.id.actSponserText); 
		actStartTimeText = (EditText) findViewById(R.id.actStartTimeText); 
		actEndTimeText = (EditText) findViewById(R.id.actEndTimeText); 
		actPlaceText = (EditText) findViewById(R.id.actPlaceText); 
		actRemindTimeText = (EditText) findViewById(R.id.actRemindTimeText); 
		
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
        menu = popupMenu.getMenu(); 
	}
	
	public void initialMenu(){
		//通过XML导入菜单栏
        MenuInflater menuInflater = getMenuInflater();  
        menuInflater.inflate(R.menu.act_info_menu, menu); 
       
        // 设置监听事件
        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

        	@Override  
        	public boolean onMenuItemClick(MenuItem item) {  
        		switch (item.getItemId()) {  
        		case R.id.change: 
        			changeMemInfo();
        			Toast.makeText(ActInfoActivity.this, "修改成员信息",  
        					Toast.LENGTH_LONG).show();  
        			break;  
        		case R.id.check:  
        			Intent intent = new Intent();
        			intent = new Intent(ActInfoActivity.this, ActListActivity.class);
        			startActivity(intent);
        			ActInfoActivity.this.finish();
        			Toast.makeText(ActInfoActivity.this, "查看活动列表",  
        					Toast.LENGTH_LONG).show();  
        			break;  
        		case R.id.delete:  
        			Toast.makeText(ActInfoActivity.this, "撤销活动",  
        					Toast.LENGTH_LONG).show();  
        		break;  
        		default:  
        			break;  
        		}  
        		return false;  
        	}  
        });  
	}
	
	public void changeMemInfo(){
		
	}
}


