package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.component.ActLvAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupMenu.OnMenuItemClickListener;

/**
 * 
 * 课程组列表页面
 */
public class ClassListActivity extends Activity {
	/**弹出菜单*/
	private PopupMenu popupMenu;
	/**菜单*/
	private Menu menu;
	/**列表控件*/
	ListView list;
	
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_list);
		
		initial();
		initialMenu();
		loadList();
	}

	/**
	 * 
	 * 弹出菜单
	 * @param v
	 */
	public void popupmenu(View v) {  
	    popupMenu.show();  
	}  
	
	/**
	 * 
	 * 初始化界面控件
	 */
	public void initial(){
		list = (ListView) findViewById(R.id.classListView);
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
	    menu = popupMenu.getMenu(); 
	    setListListener();
	}
	
	/**
	 * 
	 * 初始化菜单
	 */
	public void initialMenu(){
		//通过XML导入菜单栏
	    MenuInflater menuInflater = getMenuInflater();  
	    menuInflater.inflate(R.menu.class_list_menu, menu); 
	   
	    // 设置监听事件
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

	    	@Override  
	    	public boolean onMenuItemClick(MenuItem item) {  
	    		switch (item.getItemId()) {  
	    		case R.id.create:  
	    			Intent intent = new Intent();
					intent = new Intent(ClassListActivity.this, ClassInfoActivity.class);
					startActivity(intent);
//					ClassListActivity.this.finish(); 
	    			break; 
	    		default:  
	    			break;  
	    		}  
	    		return false;  
	    	}  
	    });  
	}
	
	/**
	 * 
	 * 加载列表
	 */
	public void loadList(){

		ArrayList<HashMap<String, Object>> mylist = new ArrayList<HashMap<String, Object>>();  

		HashMap<String, Object> map1 = new HashMap<String, Object>(); 
		HashMap<String, Object> map2 = new HashMap<String, Object>();  
		HashMap<String, Object> map3 = new HashMap<String, Object>();  
		map1.put("ItemTitle", "111");  
		map1.put("ItemText", "aaa"); 
		map1.put("pic", R.drawable.add);
		map2.put("ItemTitle", "BBB");  
		map2.put("ItemText", "bbb");
		map2.put("pic", R.drawable.minus);
		map3.put("ItemTitle", "CCC");  
		map3.put("ItemText", "ccc");  
		map3.put("pic", R.drawable.minus);
		mylist.add(map1);  
		mylist.add(map2);
		mylist.add(map3);

		ActLvAdapter  mSchedule = new ActLvAdapter(this,  
				mylist,
				R.layout.two_decimal_item,         
				new String[] {"ItemTitle", "ItemText","pic"},   
				new int[] {R.id.ItemTitle,R.id.ItemText,R.id.addC},ClassListActivity.this);  

		list.setAdapter(mSchedule); 
	}
	
	
	/**
	 * 
	 * 返回事件处理
	 * @param view
	 */
	public void clickBack(View view){	
		//界面跳转到日历界面
		Intent intent = new Intent();
		intent = new Intent(ClassListActivity.this, CalendarActivity.class);
		startActivity(intent);
		ClassListActivity.this.finish();
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
				intent.setClass(ClassListActivity.this, ClassInfoActivity.class);
				intent.putExtra("index", id);
				System.out.println(id);
				startActivity(intent);
			}

		});
	}
	
}
