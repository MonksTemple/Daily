package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.example.component.ActLvAdapter;
import com.example.model.Team;
import com.example.presenter.TeamManage;
import com.example.view.TeamListView;
import com.example.view.TeamView;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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
 * 团队列表界面
 */
public class TeamListActivity extends Activity implements TeamListView{
	/**弹出菜单*/
	private PopupMenu popupMenu;  
	/**菜单*/
	private Menu menu;
	/**列表控件*/
	private ListView list ;
	/**存储团队信息的列表*/
	private List<Team> teamList ;
	/**团队管理类对象*/
	private TeamManage teamManage;
	/**处理类对象*/
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			String info = bundle.getString("teamList");
			if (info.equals("true")) {//为true说明创建成功
				setTeamList(teamList);
			}else {
				Toast.makeText(TeamListActivity.this, "加载团队列表失败", Toast.LENGTH_SHORT)
				.show();
			}
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
		setContentView(R.layout.activity_team_list);
		
		teamManage = new TeamManage(this);
		
		initial();
		initialMenu();
		loadList();
	}
	/**
	 * 
	 * 弹出菜单事件处理
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
	    list = (ListView) findViewById(R.id.teamListView); 
	    setListListener();
	}
	
	/**
	 * 
	 * 初始化菜单项
	 */
	public void initialMenu(){
	    MenuInflater menuInflater = getMenuInflater();  
	    menuInflater.inflate(R.menu.team_list_menu, menu); 
	   
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  
	    	@Override  
	    	public boolean onMenuItemClick(MenuItem item) {  
	    		switch (item.getItemId()) {  
		    		//点击右上角菜单
		    		case R.id.edit: 
		    			//界面跳转到创建团队
		    			Intent intent = new Intent();
		    			intent = new Intent(TeamListActivity.this, TeamInfoActivity.class);
		    			startActivity(intent);
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
	
	/**
	 * 
	 * 加载团队列表
	 */
	public void loadList(){
		new Thread (){
			public void run() {
				
				Message msg = new Message();
				Bundle bundle = new Bundle();			
				teamList = 	teamManage.ShowTeamList();
				
				if(teamList.size() != 0){		
					bundle.putString("teamList", "true");					
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
				else{
					bundle.putString("teamList", "false");
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
			}
		}.start();
	}
	
	/**
	 * 
	 * 点击左上角返回箭头返回日历页面
	 * @param view
	 */
	public void back(View view){
//		Intent intent1 = new Intent();
//		intent1 = new Intent(TeamListActivity.this, CalendarActivity.class);
//		startActivity(intent1);
//		TeamListActivity.this.finish();
		finish();
	}
	
	/**
	 * 
	 * 设置列表点击事件
	 */
	private void setListListener() {
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent();
				intent.setClass(TeamListActivity.this, ActListActivity.class);
				intent.putExtra("team",teamList.get((int) id));
				System.out.println(id);
				startActivity(intent);
			}

		});
	}
	
	
	/**
	 * 
	 * 将得到的团队列表数据显示在用户界面上
	 * @param teamList
	 */
	@Override
	public void setTeamList(List<Team> teamList) {
		ArrayList<HashMap<String, Object>> mylist = new ArrayList<HashMap<String, Object>>();  
		
		for(int i = 0; i < teamList.size(); i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", teamList.get(i).getName());  
			map.put("ItemText",teamList.get(i).getDescription()); 
			map.put("pic", R.drawable.add);
			mylist.add(map);
		}

		ActLvAdapter  mSchedule = new ActLvAdapter(this,  
				mylist,
				R.layout.two_decimal_item,         
				new String[] {"ItemTitle", "ItemText","pic"},   
				new int[] {R.id.ItemTitle,R.id.ItemText,R.id.addC},TeamListActivity.this);  

		list.setAdapter(mSchedule); 
	}
}