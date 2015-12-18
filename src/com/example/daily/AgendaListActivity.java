package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AgendaListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_list);
		

		ListView list = (ListView) findViewById(R.id.classListView); 

		//生成动态数组，并且转载数据  
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		HashMap<String, String> map2 = new HashMap<String, String>();  
		map1.put("ItemTitle", "AAAAA");  
		map1.put("ItemText", "bbbbbb");  
		map2.put("ItemTitle", "CCCCC");  
		map2.put("ItemText", "ddddd");  
		mylist.add(map1);  
		mylist.add(map2);
		//生成适配器，数组===》ListItem  
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,//数据来源   
				R.layout.agenda_list,//ListItem的XML实现  

				//动态数组与ListItem对应的子项          
				new String[] {"ItemTitle", "ItemText"},   

				//ListItem的XML文件里面的两个TextView ID  
				new int[] {R.id.ItemTitle,R.id.ItemText});  
		//添加并且显示  
		list.setAdapter(mSchedule); 
	}
}
