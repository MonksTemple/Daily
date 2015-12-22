package com.example.daily;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class PersonClassInfoActivity extends Activity {
	private PopupMenu popupMenu;  
	private Menu menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_class_info);

		initial();
		initialMenu();
	}

	public void popupmenu(View v) {  
		popupMenu.show();  
	}

	public void initial(){
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
		menu = popupMenu.getMenu(); 
	}

	public void initialMenu(){
		//通过XML导入菜单栏
		MenuInflater menuInflater = getMenuInflater();  
		menuInflater.inflate(R.menu.class_list_menu, menu); 

		// 设置监听事件
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

			@Override  
			public boolean onMenuItemClick(MenuItem item) {  
				switch (item.getItemId()) {  
				case R.id.delete:  
					Toast.makeText(PersonClassInfoActivity.this, "退出课程组",  
							Toast.LENGTH_LONG).show();  
					break; 
				default:  
					break;  
				}  
				return false;  
			}  
		});  
	}
}
