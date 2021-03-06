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
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

/**
 * 
 * 团队活动页面
 */
public class TeamActivity extends Activity {
	/**弹出菜单*/
	private PopupMenu popupMenu;  
	/**菜单*/
	private Menu menu;
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
		setContentView(R.layout.activity_team);

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
		list = (ListView) findViewById(R.id.teamView); 
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
	    menu = popupMenu.getMenu(); 
	}
	
	/**
	 * 
	 * 加载列表
	 */
	public void loadList(){
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		HashMap<String, String> map2 = new HashMap<String, String>(); 
		HashMap<String, String> map3 = new HashMap<String, String>(); 
		map1.put("ItemTitle", "11.20日校级乒乓球比赛初赛");  
		map1.put("ItemText", "11.20晚7点在校内体育馆举办本年度。。。");  
		map2.put("ItemTitle", "11.22日校级乒乓球比赛半决赛");  
		map2.put("ItemText", "11.22晚7点在校内体育馆举办本年度。。。");  
		map3.put("ItemTitle", "11.26日校级乒乓球比赛决赛");  
		map3.put("ItemText", "11.26晚7点在校内体育馆举办本年度。。。");  
		mylist.add(map1);  
		mylist.add(map2);
		mylist.add(map3);
		// 
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,//
				R.layout.two_decimal_item,// 

				//     
				new String[] {"ItemTitle", "ItemText"},   

				//
				new int[] {R.id.ItemTitle,R.id.ItemText});  
		//
		list.setAdapter(mSchedule); 
	}
	
	/**
	 * 
	 * 初始化菜单控件
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
	    		case R.id.checkTeam:  
	    			Intent intent1 = new Intent();
        			intent1 = new Intent(TeamActivity.this, TeamInfoActivity.class);
        			startActivity(intent1);
        			TeamActivity.this.finish();
	    			Toast.makeText(TeamActivity.this, "查看团队信息",  
	    					Toast.LENGTH_LONG).show();  
	    			break; 
	    		case R.id.checkMen:  
	    			Intent intent2 = new Intent();
        			intent2 = new Intent(TeamActivity.this, ActMemListActivity.class);
        			startActivity(intent2);
        			TeamActivity.this.finish();
	    			Toast.makeText(TeamActivity.this, "查看成员列表",  
	    					Toast.LENGTH_LONG).show();  
	    			break;
	    		case R.id.delete:  
	    			Toast.makeText(TeamActivity.this, "退出团队",  
	    					Toast.LENGTH_LONG).show();  
	    			break;
	    		default:  
	    			break;  
	    		}  
	    		return false;  
	    	}  
	    });  
	}
}
