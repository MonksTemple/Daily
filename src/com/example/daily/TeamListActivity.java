package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class TeamListActivity extends Activity {

	private PopupMenu popupMenu;  
	private Menu menu;
	ListView list ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_list);
		initial();
		initialMenu();
		loadList();
	}

	public void popupmenu(View v) {  
	    popupMenu.show();  
	}  
	
	public void initial(){
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
	    menu = popupMenu.getMenu(); 
	    list = (ListView) findViewById(R.id.teamListView); 
	}
	
	public void initialMenu(){
		//通过XML导入菜单栏
	    MenuInflater menuInflater = getMenuInflater();  
	    menuInflater.inflate(R.menu.team_list_menu, menu); 
	   
	    // 设置监听事件
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

	    	@Override  
	    	public boolean onMenuItemClick(MenuItem item) {  
	    		switch (item.getItemId()) {  
	    		case R.id.edit:  
	    			Toast.makeText(TeamListActivity.this, "创建团队",  
	    					Toast.LENGTH_LONG).show();  
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
		map1.put("ItemTitle", "北交大乒乓球协会");  
		map1.put("ItemText", "乒乓球协会的活动是根据国家的体育方针。。。");  
		map2.put("ItemTitle", "学生会");  
		map2.put("ItemText", "学生会是在党委领导下，团委指导下。。。");  
		mylist.add(map1);  
		mylist.add(map2);

		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,
				R.layout.two_decimal_item,         
				new String[] {"ItemTitle", "ItemText"},   
				new int[] {R.id.ItemTitle,R.id.ItemText});  

		list.setAdapter(mSchedule); 

	}
}