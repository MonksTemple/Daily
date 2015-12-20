package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ActListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_list);
		
		ListView list = (ListView) findViewById(R.id.actListView); 
		  
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		HashMap<String, String> map2 = new HashMap<String, String>();  
		HashMap<String, String> map3 = new HashMap<String, String>();  
		map1.put("ItemTitle", "北交大乒乓球协会“星星杯”比赛");  
		map1.put("ItemText", "乒乓球协会的活动是根据国家的体育方针。。。");  
		map2.put("ItemTitle", "129活动来啦");  
		map2.put("ItemText", "129活动又称129抗日救亡运动。。。");  
		map3.put("ItemTitle", "2015跨年晚会");  
		map3.put("ItemText", "“跨年晚会”是指在西元（阳历，公历）年末。。。");  
		mylist.add(map1);  
		mylist.add(map2);
		mylist.add(map3);

		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,
				R.layout.two_decimal_item,         
				new String[] {"ItemTitle", "ItemText"},   
				new int[] {R.id.ItemTitle,R.id.ItemText});  

		list.setAdapter(mSchedule); 
	}

}
