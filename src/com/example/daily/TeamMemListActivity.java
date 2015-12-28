package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * 
 * 团队成员列表页面
 */
public class TeamMemListActivity extends Activity {
	/**列表控件*/
	private ListView list;
	
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_memlist);
		initial();
		loadList();
	}
	
	/**
	 * 
	 * 初始化界面控件
	 */
	public void initial(){
		list= (ListView) findViewById(R.id.teamMemListView); ;
	}
	
	/**
	 * 
	 * 加载列表
	 */
	public void loadList(){
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		map1.put("ItemTitle", "北交大乒乓球协会");  
		mylist.add(map1);  
		
		HashMap<String, String> map2 = new HashMap<String, String>(); 
		map2.put("ItemTitle", "学生会");  
		mylist.add(map2);  
		
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,  
				R.layout.normal_item, 
				new String[] {"ItemTitle", "ItemText"},   		
				new int[] {R.id.ItemTitle,R.id.ItemText});  
		 
		list.setAdapter(mSchedule); 
	}
}
