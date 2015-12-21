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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_memlist);
		
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
        menu = popupMenu.getMenu(); 
        
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

	public void popupmenu(View v) {  
        popupMenu.show();  
    }  
}
