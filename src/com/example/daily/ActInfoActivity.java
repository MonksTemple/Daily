package com.example.daily;

import com.example.presenter.ActManage;
import com.example.util.DateUtil;
import com.example.view.ActView;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class ActInfoActivity extends Activity implements ActView{
	private EditText actName;
	private EditText actDes;
	private EditText actCreator;
	private EditText startTime;
	private EditText endTime;
	private EditText place;
	private EditText remindTime;

	private ImageView lines;
	private Button sure;

	private PopupMenu popupMenu;  
	private Menu menu; 
	
	private ActManage actManage;
	com.example.model.Activity act;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_info);
		initial();
		//初始化菜单栏
		initialMenu();
		actManage=new ActManage(this);
	}

	public void popupmenu(View v) {  
		popupMenu.show();  
	}  


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

	//返回箭头
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(ActInfoActivity.this, ActListActivity.class);
		startActivity(intent);
		ActInfoActivity.this.finish();
	}

	//确定按钮
	public void sure(View view){
		Intent intent = new Intent();
		intent = new Intent(ActInfoActivity.this, ActListActivity.class);
		startActivity(intent);
		ActInfoActivity.this.finish();
	}

	@Override
	public com.example.model.Activity getActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActivity(com.example.model.Activity activity) {
		// TODO Auto-generated method stub
		actName.setText(activity.getName());
		actDes.setText(activity.getDescription());
		actCreator.setText(activity.getTeam().getCreator().getUserName());
		place.setText(activity.getPlace());
		startTime.setText(DateUtil.getStringFromDate(activity.getStartTime()));
		endTime.setText(DateUtil.getStringFromDate(activity.getStartTime()));
		remindTime.setText(DateUtil.getStringFromDate(activity.getRemindTime()));
	}
	
	public void getAct(){
		Intent intent=this.getIntent();
		intent.getBundleExtra("");
	}
}


