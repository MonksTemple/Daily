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

public class TeamListActivity extends Activity implements TeamListView{

	private PopupMenu popupMenu;  
	private Menu menu;
	private ListView list ;
	
	private TeamManage teamManage;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			String login = bundle.getString("createTeam");
			if (login.equals("true")) {//为true说明创建成功
				Intent intent = new Intent();
				intent = new Intent(TeamListActivity.this, TeamListActivity.class);
				startActivity(intent);
				TeamListActivity.this.finish();
			}else {
				Toast.makeText(TeamListActivity.this, "创建失败", Toast.LENGTH_SHORT)
				.show();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_list);
		
		teamManage = new TeamManage(this);
		
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
	
	//初始化菜单
	public void initialMenu(){
		//通过XML导入菜单栏
	    MenuInflater menuInflater = getMenuInflater();  
	    menuInflater.inflate(R.menu.team_list_menu, menu); 
	   
	    // 设置监听事件
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

	    	@Override  
	    	public boolean onMenuItemClick(MenuItem item) {  
	    		switch (item.getItemId()) {  
		    		//点击右上角菜单
		    		case R.id.edit: 
		    			//界面跳转到创建团队
		    			Intent intent = new Intent();
		    			intent = new Intent(TeamListActivity.this, TeamCreateActivity.class);
		    			startActivity(intent);
		    			//TeamListActivity.this.finish();
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
	
	//加载团队列表
	public void loadList(){
		
		new Thread (){
			public void run() {
				
				Message msg = new Message();
				Bundle bundle = new Bundle();
				Set<Team> teamList = teamManage.ShowTeamList();
				
				if(teamManage.createTeam()){
					bundle.putString("createTeam", "true");
					msg.setData(bundle);
					
					handler.sendMessage(msg);
				}
				else{
					bundle.putString("createTeam", "false");
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
				
			}
		}.start();
		
		
		
		
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

	@Override
	public void setTeamList(List<Team> teamList) {
		// TODO Auto-generated method stub
		
	}

}