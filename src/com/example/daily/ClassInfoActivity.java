package com.example.daily;

import com.example.menudemo.MainActivity;
import com.example.menudemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class ClassInfoActivity extends Activity {
	PopupMenu popupMenu;  
    Menu menu; 
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_info);
		
		 popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
	     menu = popupMenu.getMenu();  
	  
	     // 通过XML文件添加菜单项  
	     MenuInflater menuInflater = getMenuInflater();  
	     menuInflater.inflate(R.menu.change_class_info_menu, menu);  
	     
	     // 监听事件  
	        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  
	  
	            @Override  
	            public boolean onMenuItemClick(MenuItem item) {  
	                switch (item.getItemId()) {  
	                case R.id.news:  
	                    Toast.makeText(MainActivity.this, "新建",  
	                            Toast.LENGTH_LONG).show();  
	                    break;  
	                case R.id.open:  
	                    Toast.makeText(MainActivity.this, "打开",  
	                            Toast.LENGTH_LONG).show();  
	                    break;  
	                case Menu.FIRST + 0:  
	                    Toast.makeText(MainActivity.this, "复制",  
	                            Toast.LENGTH_LONG).show();  
	                    break;  
	                case Menu.FIRST + 1:  
	                    Toast.makeText(MainActivity.this, "粘贴",  
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
