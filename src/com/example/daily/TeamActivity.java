package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TeamActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);
		
		ListView list = (ListView) findViewById(R.id.teamView); 

		//
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		HashMap<String, String> map2 = new HashMap<String, String>(); 
		HashMap<String, String> map3 = new HashMap<String, String>(); 
		map1.put("ItemTitle", "11.20日校级乒乓球比赛初赛");  
		map1.put("ItemText", "11.20晚7点在校内体育馆举办本年度。。。");  
		map2.put("ItemTitle", "11.22日校级乒乓球比赛半决赛");  
		map2.put("ItemText", "11.22晚7点在校内体育馆举办本年度。。。");  
		map3.put("ItemTitle", "11.26日校级乒乓球比赛决赛");  
		map3.put("ItemText", "11.26晚7点在校内体育馆举办本年度。。。");  
		mylist.add(map1);  
		mylist.add(map2);
		mylist.add(map3);
		// 
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,//
				R.layout.two_decimal_item,// 

				//     
				new String[] {"ItemTitle", "ItemText"},   

				//
				new int[] {R.id.ItemTitle,R.id.ItemText});  
		//
		list.setAdapter(mSchedule); 
	}

}
