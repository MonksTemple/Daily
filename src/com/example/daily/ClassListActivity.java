package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class ClassListActivity extends Activity {
	private PopupMenu popupMenu;  
	private Menu menu;
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_list);
		
		initial();
		initialMenu();
		loadList();
	}

	public void popupmenu(View v) {  
	    popupMenu.show();  
	}  
	
	public void initial(){
		list = (ListView) findViewById(R.id.classListView);
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
	    		case R.id.create:  
	    			Intent intent = new Intent();
					intent = new Intent(ClassListActivity.this, ClassInfoActivity.class);
					startActivity(intent);
					ClassListActivity.this.finish(); 
	    			break; 
	    		default:  
	    			break;  
	    		}  
	    		return false;  
	    	}  
	    });  
	}
	
	public void loadList(){

				ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

				HashMap<String, String> map1 = new HashMap<String, String>(); 
				HashMap<String, String> map2 = new HashMap<String, String>();  
				map1.put("ItemTitle", "AAAAA");  
				map1.put("ItemText", "bbbbbb");  
				map2.put("ItemTitle", "CCCCC");  
				map2.put("ItemText", "ddddd");  
				mylist.add(map1);  
				mylist.add(map2);
				SimpleAdapter mSchedule = new SimpleAdapter(this,  
						mylist, 
						R.layout.two_decimal_item, 

					    
						new String[] {"ItemTitle", "ItemText"},   
 
						new int[] {R.id.ItemTitle,R.id.ItemText});  

				list.setAdapter(mSchedule);
	}
	
	
	public void clickBack(View view){	
		//界面跳转到日历界面
		Intent intent = new Intent();
		intent = new Intent(ClassListActivity.this, CalendarActivity.class);
		startActivity(intent);
		ClassListActivity.this.finish();
	}
}
