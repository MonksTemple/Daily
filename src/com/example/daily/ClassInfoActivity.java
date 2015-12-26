package com.example.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class ClassInfoActivity extends Activity {
	EditText className;
	EditText createPerson;
	EditText description;
	Button sure;
	ImageView lines;
	ImageView cross;

	private PopupMenu popupMenu;  
	private Menu menu; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_info);

		initial();
		initialMenu();	    

	}

	public void popupmenu(View v) {  
		popupMenu.show();  
	}  

	public void initial(){
		className=(EditText) findViewById(R.id.cname);
		createPerson=(EditText) findViewById(R.id.createname);
		description=(EditText) findViewById(R.id.info);
		sure=(Button) findViewById(R.id.sure);
		lines=(ImageView) findViewById(R.id.lines);
		cross=(ImageView) findViewById(R.id.cross);
		sure.setVisibility(4);  //0 为可见，4为不可见，8为Gone
		className.setEnabled(false);
		createPerson.setEnabled(false);
		description.setEnabled(false);
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
		menu = popupMenu.getMenu(); 
	}

	public void initialMenu(){
		//通过XML导入菜单栏
		MenuInflater menuInflater = getMenuInflater();  
		menuInflater.inflate(R.menu.class_info_menu, menu); 

		// 设置监听事件
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

			@Override  
			public boolean onMenuItemClick(MenuItem item) {  
				switch (item.getItemId()) {  
				case R.id.edit:  
					//编辑信息
					sure.setVisibility(0);  //0 为可见，4为不可见，8为Gone
					className.setEnabled(true);
					createPerson.setEnabled(true);
					description.setEnabled(true);
					lines.setVisibility(8); 
					cross.setVisibility(8);
					break; 
				case R.id.delete:  
					Toast.makeText(ClassInfoActivity.this, "撤销课程组",  
							Toast.LENGTH_LONG).show();  
					break; 
				default:  
					break;  
				}  
				return false;  
			}  
		});  
	}

	public void clickBack(View view){	
		//界面跳转
		Intent intent = new Intent();
		intent = new Intent(ClassInfoActivity.this, ClassListActivity.class);
		startActivity(intent);
		ClassInfoActivity.this.finish();
	}

	//点击提交信息按钮
	public void clickButton(View view){
		//界面跳转
		Intent intent = new Intent();
		intent = new Intent(ClassInfoActivity.this, ClassListActivity.class);
		startActivity(intent);
		ClassInfoActivity.this.finish();
	}
}
