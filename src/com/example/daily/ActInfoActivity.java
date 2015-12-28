package com.example.daily;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Team;
import com.example.presenter.ActManage;
import com.example.presenter.TeamManage;
import com.example.util.DateUtil;
import com.example.view.ActView;
import com.example.view.TeamView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

/**
 * 
 * 活动信息页面
 */
public class ActInfoActivity extends Activity {
	/**活动名称编辑框*/
	private EditText actName;
	/**活动简介编辑框*/
	private EditText actDes;
	/**活动创始人编辑框*/
	private EditText actCreator;
	/**活动开始时间编辑框*/
	private EditText startTime;
	/**活动结束时间编辑框*/
	private EditText endTime;
	/**活动地点编辑框*/
	private EditText place;
	/**活动提醒时间编辑框*/
	private EditText remindTime;

	/**图片*/
	private ImageView lines;
	/**确认按钮*/
	private Button sure;
	/**弹出菜单*/
	private PopupMenu popupMenu;
	/**菜单*/
	private Menu menu; 
	
	/**活动类*/
	com.example.model.Activity act;
	
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_info);
		initial();
		getAct();
		//初始化菜单栏
		initialMenu();
		
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
		actName=(EditText) findViewById(R.id.actNameText);
		actDes=(EditText) findViewById(R.id.actIntroText);
		actCreator=(EditText) findViewById(R.id.actSponserText);
		startTime=(EditText) findViewById(R.id.actStartTimeText);
		endTime=(EditText) findViewById(R.id.actEndTimeText);
		place=(EditText) findViewById(R.id.actPlaceText);
		remindTime=(EditText) findViewById(R.id.actRemindTimeText);
		lines=(ImageView) findViewById(R.id.lines);
		sure=(Button) findViewById(R.id.sure);

		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
		menu = popupMenu.getMenu(); 

		actName.setEnabled(false);
		actDes.setEnabled(false);
		actCreator.setEnabled(false);
		place.setEnabled(false);
		startTime.setClickable(false);
		endTime.setClickable(false);
		remindTime.setClickable(false);
		sure.setVisibility(4);
	}

	/**
	 * 
	 * 初始化菜单控件
	 */
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
					changeInfo();
					Toast.makeText(ActInfoActivity.this, "修改活动信息",  
							Toast.LENGTH_LONG).show();  
					break;  
				case R.id.check:  
					Intent intent = new Intent();
					intent = new Intent(ActInfoActivity.this, ActMemListActivity.class);
					startActivity(intent);
					ActInfoActivity.this.finish();
					Toast.makeText(ActInfoActivity.this, "查看成员列表",  
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
	
	/**
	 * 
	 * 修改信息
	 */
	public void changeInfo(){
		actName.setEnabled(false);
		actDes.setEnabled(false);
		actCreator.setEnabled(false);
		place.setEnabled(false);
		startTime.setClickable(false);
		endTime.setClickable(false);
		remindTime.setClickable(false);
		sure.setVisibility(0);
		lines.setVisibility(4);
	}
	
	/**
	 * 
	 * 返回事件处理
	 * @param view
	 */
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(ActInfoActivity.this, ActListActivity.class);
		startActivity(intent);
		ActInfoActivity.this.finish();
	}

	/**
	 * 
	 * 确认按钮事件处理
	 * @param view
	 */
	public void sure(View view){
		Intent intent = new Intent();
		intent = new Intent(ActInfoActivity.this, ActListActivity.class);
		startActivity(intent);
		ActInfoActivity.this.finish();
	}

	/**
	 * 
	 * 设置活动信息
	 * @param activity
	 */
	private void setActivity(com.example.model.Activity activity) {
		// TODO Auto-generated method stub
		actName.setText(activity.getName());
		actDes.setText(activity.getDescription());
		if(activity.getTeam()==null){
			actCreator.setText("");
		}else{
			actCreator.setText(activity.getTeam().getCreator().getUserName()==null?"":activity.getTeam().getCreator().getUserName());
		}
		place.setText(activity.getPlace());
		startTime.setText(DateUtil.getStringFromDate(activity.getStartTime()));
		endTime.setText(DateUtil.getStringFromDate(activity.getStartTime()));
		remindTime.setText(DateUtil.getStringFromDate(activity.getRemindTime()));
	}
	
	/**
	 * 
	 * 获得活动信息
	 */
	public void getAct(){
		Intent intent= ActInfoActivity.this.getIntent(); 
		act= (com.example.model.Activity)intent.getSerializableExtra("act");
		setActivity(act);
	}
	
}


