package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

public class TuesdayActivity extends Activity {
	private Drawable  redCircle;
	private Drawable  blueCircle;
	private Drawable  yellowCircle;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tuesday);
		//初始化控件
		initial();
		//加载日程列表
		loadList();
	}

	public void initial(){
		redCircle= this.getResources().getDrawable(R.drawable.redc);
		blueCircle= this.getResources().getDrawable(R.drawable.bluec);
		yellowCircle= this.getResources().getDrawable(R.drawable.yellowc);
		list= (ListView) findViewById(R.id.classListView); 
	}

	public void loadList(){
		ArrayList<HashMap<String, Object>> mylist = new ArrayList<HashMap<String, Object>>();  

		HashMap<String, Object> map1 = new HashMap<String, Object>(); 
		HashMap<String, Object> map2 = new HashMap<String, Object>(); 
		HashMap<String, Object> map3 = new HashMap<String, Object>();

		//添加任务类型日程到日程列表
		map1.put("ItemTitle", "任务类型");  
		map1.put("ItemText", "55555");  
		map1.put("type",redCircle); 

		//添加日程类型日程到日程列表
		map2.put("ItemTitle", "日程类型");  
		map2.put("ItemText", "ddddd"); 
		map2.put("type",blueCircle);

		//添加活动类型日程到日程列表
		map3.put("ItemTitle", "活动类型");  
		map3.put("ItemText", "eeeee"); 
		map3.put("type",yellowCircle);

		mylist.add(map1);  
		mylist.add(map2);
		mylist.add(map3);

		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,
				R.layout.main_list,


				new String[] {"ItemTitle", "ItemText","type"},   


				new int[] {R.id.ItemTitle,R.id.ItemText,R.id.type});  

		mSchedule.setViewBinder(new ViewBinder() { 
			@Override 
			public boolean setViewValue(View view, Object data, 
					String textRepresentation) { 
				if (view instanceof ImageView && data instanceof Drawable) { 
					ImageView iv = (ImageView)view; 
					iv.setImageDrawable((Drawable)data); 
					return true; 
				} 
				return false; 
			} 
		}); 

		list.setAdapter(mSchedule); 

	}
}
