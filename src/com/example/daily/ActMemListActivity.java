package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ActMemListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_memlist);
		
		ListView list = (ListView) findViewById(R.id.actMemListView); 
		
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		map1.put("ItemTitle", "孙剑");  
		mylist.add(map1);
		
		HashMap<String, String> map2 = new HashMap<String, String>(); 
		map2.put("ItemTitle", "孙剑");  
		mylist.add(map2);
		
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,  
				R.layout.normal_item, 
				new String[] {"ItemTitle", "ItemText"},
				new int[] {R.id.ItemTitle,R.id.ItemText});
		 
		list.setAdapter(mSchedule);
	}

}
