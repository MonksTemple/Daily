package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class ActMemListActivity extends Activity {
	private PopupMenu popupMenu;  
	private Menu menu;
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_memlist);
		//初始化控件
				initial();
				//初始化菜单栏
				initialMenu();
				//加载列表
				loadList();
	}

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

	private void initialMenu() {
		// TODO Auto-generated method stub
		//通过XML导入菜单栏
        MenuInflater menuInflater = getMenuInflater();  
        menuInflater.inflate(R.menu.act_info_menu, menu); 
       
        // 设置监听事件
        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

        	@Override  
        	public boolean onMenuItemClick(MenuItem item) {  
        		switch (item.getItemId()) {  
        		case R.id.create:  
        			Toast.makeText(ActMemListActivity.this, "发布活动",  
        					Toast.LENGTH_LONG).show();  
        			break;   
        		case R.id.delete:  
        			Toast.makeText(ActMemListActivity.this, "撤销活动",  
        					Toast.LENGTH_LONG).show();  
        		break;  
        		default:  
        			break;  
        		}  
        		return false;  
        	}  
        });  
		
	}

	private void initial() {
		// TODO Auto-generated method stub
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
        menu = popupMenu.getMenu(); 
        list = (ListView) findViewById(R.id.actMemListView); 
		
	}

	public void popupmenu(View v) {  
        popupMenu.show();  
    }  
}
