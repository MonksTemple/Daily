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
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class TeamListActivity extends Activity {

	private PopupMenu popupMenu;  
	private Menu menu;
	ListView list ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_list);
		initial();
		initialMenu();
		loadList();
	}

	public void popupmenu(View v) {  
	    popupMenu.show();  
	}  
	
	public void initial(){
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
	    menu = popupMenu.getMenu(); 
	    list = (ListView) findViewById(R.id.teamListView); 
	    setListListener();
	}
	
	public void initialMenu(){
		//通过XML导入菜单栏
	    MenuInflater menuInflater = getMenuInflater();  
	    menuInflater.inflate(R.menu.team_list_menu, menu); 
	   
	    // 设置监听事件
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

	    	@Override  
	    	public boolean onMenuItemClick(MenuItem item) {  
	    		switch (item.getItemId()) {  
	    		case R.id.edit: 
	    			//界面跳转到创建团队
	    			Intent intent = new Intent();
	    			intent = new Intent(TeamListActivity.this, TeamCreateActivity.class);
	    			startActivity(intent);
//	    			TeamListActivity.this.finish();
	    			Toast.makeText(TeamListActivity.this, "创建团队",  
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
				new int[] {R.id.ItemTitle,R.id.ItemText,R.id.addC},TeamListActivity.this);  

		list.setAdapter(mSchedule); 
	}
	
	public void back(View view){
		Intent intent1 = new Intent();
		intent1 = new Intent(TeamListActivity.this, CalendarActivity.class);
		startActivity(intent1);
		TeamListActivity.this.finish();
	}
	
	private void setListListener() {
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Intent intent =new Intent();
				intent.setClass(TeamListActivity.this, TeamInfoActivity.class);
				intent.putExtra("index", id);
				System.out.println(id);
				startActivity(intent);
			}

		});
	}
}