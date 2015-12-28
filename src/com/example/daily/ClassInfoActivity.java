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

/**
 * 
 * 课程组信息页面
 */
public class ClassInfoActivity extends Activity {
	/**课程组名称编辑框*/
	private EditText className;
	/**创建人编辑框*/
	private EditText createPerson;
	/**课程组简介编辑框*/
	private EditText description;
	/**确认按钮*/
	private Button sure;
	/**图片*/
	private ImageView lines;
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
		setContentView(R.layout.activity_class_info);

		initial();
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
		className=(EditText) findViewById(R.id.cname);
		createPerson=(EditText) findViewById(R.id.createname);
		description=(EditText) findViewById(R.id.info);
		sure=(Button) findViewById(R.id.sure);
		lines=(ImageView) findViewById(R.id.lines);
		sure.setVisibility(4);  //0 为可见，4为不可见，8为Gone
		className.setEnabled(false);
		createPerson.setEnabled(false);
		description.setEnabled(false);
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
		menu = popupMenu.getMenu(); 
	}

	/**
	 * 
	 * 初始化菜单
	 */
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

	/**
	 * 
	 * 返回事件处理
	 * @param view
	 */
	public void clickBack(View view){	
		//界面跳转
		Intent intent = new Intent();
		intent = new Intent(ClassInfoActivity.this, ClassListActivity.class);
		startActivity(intent);
		ClassInfoActivity.this.finish();
	}

	/**
	 * 
	 * 确认事件处理
	 * @param view
	 */
	public void clickButton(View view){
		//界面跳转
		Intent intent = new Intent();
		intent = new Intent(ClassInfoActivity.this, ClassListActivity.class);
		startActivity(intent);
		ClassInfoActivity.this.finish();
	}
}
