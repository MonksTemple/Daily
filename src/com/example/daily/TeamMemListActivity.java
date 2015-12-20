package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TeamMemListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_memlist);
		
		ListView list = (ListView) findViewById(R.id.teamMemListView); 

		String[] arrayString = {"item1","item2","item3"}; 
		
		//ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		//HashMap<String, String> map1 = new HashMap<String, String>(); 
		//HashMap<String, String> map2 = new HashMap<String, String>();  
		//map1.put("ItemTitle", "北交大乒乓球协会");  
		//map1.put("ItemText", "乒乓球协会的活动是根据国家的体育方针。。。");  
		//map2.put("ItemTitle", "学生会");  
		//map2.put("ItemText", "学生会是在党委领导下，团委指导下。。。");  
		//mylist.add(map1);  
		//mylist.add(map2);
		
//		SimpleAdapter mSchedule = new SimpleAdapter(this,  
//				mylist,  
//				R.layout.two_decimal_item, 
//				new String[] {"ItemTitle", "ItemText"},   
//
//				
//				new int[] {R.id.ItemTitle,R.id.ItemText});  
		
		
		 
//		list.setAdapter(mSchedule); 
	}

}
