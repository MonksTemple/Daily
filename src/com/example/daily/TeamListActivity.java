package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TeamListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_list);
		
		ListView list = (ListView) findViewById(R.id.teamListView); 

		//锟斤拷锟缴讹拷态锟斤拷锟介，锟斤拷锟斤拷转锟斤拷锟斤拷锟斤拷  
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		HashMap<String, String> map2 = new HashMap<String, String>();  
		map1.put("ItemTitle", "北交大乒乓球协会");  
		map1.put("ItemText", "乒乓球协会的活动是根据国家的体育方针。。。");  
		map2.put("ItemTitle", "学生会");  
		map2.put("ItemText", "学生会是在党委领导下，团委指导下。。。");  
		mylist.add(map1);  
		mylist.add(map2);
		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷===锟斤拷ListItem  
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,//锟斤拷锟斤拷锟斤拷源   
				R.layout.two_decimal_item,//ListItem锟斤拷XML实锟斤拷  

				//锟斤拷态锟斤拷锟斤拷锟斤拷ListItem锟斤拷应锟斤拷锟斤拷锟斤拷          
				new String[] {"ItemTitle", "ItemText"},   

				//ListItem锟斤拷XML锟侥硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟絋extView ID  
				new int[] {R.id.ItemTitle,R.id.ItemText});  
		//锟斤拷硬锟斤拷锟斤拷锟绞�  
		list.setAdapter(mSchedule); 
	}
	
}
