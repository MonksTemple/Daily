package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.example.model.User;
import com.example.presenter.ActManage;
import com.example.view.ActListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
 * 日程列表页面
 */
public class AgendaListActivity extends Activity implements ActListView {
	/**弹出菜单*/
	private PopupMenu popupMenu;
	/**菜单*/
	private Menu menu;
	/**列表控件*/
	private ListView list;
	/**活动管理类*/
	private ActManage actManage;
	/**活动列表*/
	private List<com.example.model.Activity> myList;
	/**处理类对象*/
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			ArrayList list = bundle.getParcelableArrayList("myList");
			myList=(List<com.example.model.Activity>) list.get(0);
			loadList(myList);
		}
	};
	
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_list);
		initial();
		initialMenu();
		actManage=new ActManage(this);
		//加载列表
		loadData();
	}
	
	/*
	 * 重新调用该界面
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		loadData();
		super.onResume();
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
	    menuInflater.inflate(R.menu.agenda_list_menu, menu); 
	   
	    // 设置监听事件
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

	    	@Override  
	    	public boolean onMenuItemClick(MenuItem item) {  
	    		switch (item.getItemId()) {  
	    		case R.id.create:  
	    			Intent intent1 = new Intent();
        			intent1 = new Intent(AgendaListActivity.this, AddAgendaActivity.class);
        			startActivity(intent1);
//        			AgendaListActivity.this.finish();
	    			Toast.makeText(AgendaListActivity.this, "创建日程",  
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
	 * @param myList
	 */
	public void loadList(List<com.example.model.Activity> myList){
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		for(com.example.model.Activity act:myList){
			HashMap<String, String> map1 = new HashMap<String, String>(); 
			map1.put("ItemTitle", act.getName());
			map1.put("ItemText", act.getDescription());
			mylist.add(map1);  
		}
		
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,
				R.layout.agenda_list,

				       
				new String[] {"ItemTitle", "ItemText"},   

				
				new int[] {R.id.ItemTitle,R.id.ItemText});  
	
		list.setAdapter(mSchedule); 
	}
	
	//返回箭头
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(AgendaListActivity.this, CalendarActivity.class);
		startActivity(intent);
		AgendaListActivity.this.finish();
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
				intent.setClass(AgendaListActivity.this, AgendaInfoActivity.class);
				intent.putExtra("agenda", myList.get((int) id));
				System.out.println(id);
				startActivity(intent);
			}

		});
	}

	/*
	 * 
	 * 设置活动信息
	 * @param actList 
	 * @see com.example.view.ActListView#setActList(java.util.List)
	 */
	@Override
	public void setActList(List<com.example.model.Activity> actList) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * 加载数据
	 */
	public void loadData(){
		new Thread(){
			public void run(){
				Message msg = new Message();
				Bundle bundle = new Bundle();
				ArrayList list=new ArrayList();
				SharedPreferences sp = getApplication().getSharedPreferences("userInfo", Context.MODE_APPEND);
				String user = sp.getString("user", "");
				User users = JSON.parseObject(user,User.class);
				int uid=users.getUserId();
				List<com.example.model.Activity> myList=actManage.showAgendByUserId(uid,0);
				list.add(myList);
				bundle.putParcelableArrayList("myList", list);
				msg.setData(bundle);
				handler.sendMessage(msg);
			}
		}.start();
	}
}
