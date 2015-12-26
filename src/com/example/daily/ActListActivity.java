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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class ActListActivity extends Activity {
	private PopupMenu popupMenu;  
	private Menu menu; 
	ListView list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_list);
		//初始化控件
		initial();
		//初始化菜单栏
		initialMenu();
		//加载列表
		loadList();
	}
	
	public void popupmenu(View v) {  
        popupMenu.show();  
    }  
	
	public void initial(){
		
		
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
        menu = popupMenu.getMenu(); 
        list = (ListView) findViewById(R.id.actListView); 
        
       
	}
	
	public void initialMenu(){
		 //通过XML导入菜单栏
        MenuInflater menuInflater = getMenuInflater();  
        menuInflater.inflate(R.menu.act_list_menu, menu); 
       
        // 设置监听事件
        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

        	@Override  
        	public boolean onMenuItemClick(MenuItem item) {  
        		switch (item.getItemId()) { 
        		//发布活动
        		case R.id.create:  
        			Intent intent = new Intent();
        			intent = new Intent(ActListActivity.this, ActHoldActActivity.class);
        			startActivity(intent);
        			ActListActivity.this.finish();
        			Toast.makeText(ActListActivity.this, "发布活动",  
        					Toast.LENGTH_LONG).show();  
        			break;   
        		case R.id.delete:  
        			Toast.makeText(ActListActivity.this, "撤销活动",  
        					Toast.LENGTH_LONG).show();  
        		break;  
        		default:  
        			break;  
        		}  
        		return false;  
        	}  
        });  
	}
	
	
	public void loadList(){
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		HashMap<String, String> map2 = new HashMap<String, String>();  
		HashMap<String, String> map3 = new HashMap<String, String>();  
		map1.put("ItemTitle", "111");  
		map1.put("ItemText", "aaa");  
		map2.put("ItemTitle", "BBB");  
		map2.put("ItemText", "bbb");  
		map3.put("ItemTitle", "CCC");  
		map3.put("ItemText", "ccc");  
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
