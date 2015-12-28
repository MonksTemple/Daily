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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupMenu.OnMenuItemClickListener;

/**
 * 
 * 活动成员列表页面
 */
public class ActMemListActivity extends Activity {
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_memlist);
		//初始化控件
				initial();
				//加载列表
				loadList();
	}

	/**
	 * 
	 * 加载列表
	 */
	private void loadList() {
		// TODO Auto-generated method stub
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

	/**
	 * 
	 * 初始化界面控件
	 */
	private void initial() {
		// TODO Auto-generated method stub
        list = (ListView) findViewById(R.id.actMemListView); 
        setListListener();
	}
	
	/**
	 * 
	 * 设置监听器
	 */
	private void setListListener() {
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Intent intent =new Intent();
				intent.setClass(ActMemListActivity.this, UserInfoActivity.class);
				intent.putExtra("index", id);
				System.out.println(id);
				startActivity(intent);
			}

		});
	}
}
