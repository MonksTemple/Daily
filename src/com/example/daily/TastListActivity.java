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
 * 日程列表界面
 */
public class TastListActivity extends Activity {
	/**弹出菜单*/
	private PopupMenu popupMenu;
	/**菜单*/
	private Menu menu;
	/**列表控件*/
	ListView list ;
	
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tast_list);
		
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
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
	    menu = popupMenu.getMenu();
	    list = (ListView) findViewById(R.id.classListView); 
	    setListListener();
	}
	
	/**
	 * 
	 * 初始化菜单
	 */
	public void initialMenu(){
		//通过XML导入菜单栏
	    MenuInflater menuInflater = getMenuInflater();  
	    menuInflater.inflate(R.menu.task_list_menu, menu); 
	   
	    // 设置监听事件
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

	    	@Override  
	    	public boolean onMenuItemClick(MenuItem item) {  
	    		switch (item.getItemId()) {  
	    		case R.id.checkClass:  
	    			Intent intent1 = new Intent();
        			intent1 = new Intent(TastListActivity.this, ClassInfoActivity.class);
        			startActivity(intent1);
        			TastListActivity.this.finish();
	    			Toast.makeText(TastListActivity.this, "查看课程组信息",  
	    					Toast.LENGTH_LONG).show();  
	    			break; 
	    		case R.id.checkMen:  
	    			Intent intent2 = new Intent();
        			intent2 = new Intent(TastListActivity.this, ActMemListActivity.class);
        			startActivity(intent2);
        			TastListActivity.this.finish();
	    			Toast.makeText(TastListActivity.this, "查看成员列表",  
	    					Toast.LENGTH_LONG).show();  
	    			break; 
	    		case R.id.delete:  
	    			Toast.makeText(TastListActivity.this, "退出课程组",  
	    					Toast.LENGTH_LONG).show();  
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
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		HashMap<String, String> map2 = new HashMap<String, String>();  
		map1.put("ItemTitle", "AAAAA");  
		map1.put("ItemText", "bbbbbb");  
		map2.put("ItemTitle", "CCCCC");  
		map2.put("ItemText", "ddddd");  
		mylist.add(map1);  
		mylist.add(map2);
		//                ===  ListItem  
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,//      Դ   
				R.layout.two_decimal_item,//ListItem  XMLʵ    

				//  ̬      ListItem  Ӧ                
				new String[] {"ItemTitle", "ItemText"},   

				//ListItem  XML ļ          TextView ID  
				new int[] {R.id.ItemTitle,R.id.ItemText});  
		//  Ӳ     ʾ  
		list.setAdapter(mSchedule); 
	}
	
	/**
	 * 
	 * 返回按钮事件
	 * @param view
	 */
	public void back(View view){	
		//界面跳转
		Intent intent = new Intent();
		intent = new Intent(TastListActivity.this, CalendarActivity.class);
		startActivity(intent);
		TastListActivity.this.finish();
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
				intent.setClass(TastListActivity.this, TaskInfoActivity.class);
				intent.putExtra("index", id);
				System.out.println(id);
				startActivity(intent);
			}

		});
	}
}
