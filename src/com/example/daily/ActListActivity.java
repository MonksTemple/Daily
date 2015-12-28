package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.component.ActLvAdapter;
import com.example.presenter.ActManage;
import com.example.util.DataUtil;
import com.example.view.ActListView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class ActListActivity extends ListActivity implements ActListView {
	private PopupMenu popupMenu;  
	private Menu menu; 
	ListView list;
	ActManage actManage;
	List<com.example.model.Activity> myList;
	
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

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_list);
		//初始化控件
		initial();
		//初始化菜单栏
		initialMenu();
		actManage=new ActManage(this);
		//加载列表
		loadData();
		
	}

	public void popupmenu(View v) {  
		popupMenu.show();  
	}  

	public void initial(){
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
		menu = popupMenu.getMenu(); 
		list = (ListView) findViewById(android.R.id.list); 
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

	public void loadData(){
		new Thread(){
			public void run(){
				Message msg = new Message();
				Bundle bundle = new Bundle();
				ArrayList list=new ArrayList();
				List<com.example.model.Activity> myList=actManage.showIsolateActivities(4);
				list.add(myList);
				bundle.putParcelableArrayList("myList", list);
				msg.setData(bundle);
				handler.sendMessage(msg);
			}
		}.start();
	}

	public void loadList(List<com.example.model.Activity> alist){	
		ArrayList<HashMap<String, Object>> mylist = new ArrayList<HashMap<String, Object>>();  
		for(com.example.model.Activity act:alist){
			String text=act.getDescription();
			//限制text的长度
			if(text.length()>DataUtil.TEXTLENTH){
				text=text.substring(0,DataUtil.TEXTLENTH);
				text+="...";
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", act.getName());  
			map.put("ItemText", text); 
			map.put("pic", R.drawable.add);
			mylist.add(map);
		};
		
		ActLvAdapter  mSchedule = new ActLvAdapter(this,  
				mylist,
				R.layout.two_decimal_item,         
				new String[] {"ItemTitle", "ItemText","pic"},   
				new int[] {R.id.ItemTitle,R.id.ItemText,R.id.addC},ActListActivity.this);  

		list.setAdapter(mSchedule); 
	}
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        // TODO Auto-generated method stub
		Intent intent =new Intent();
		intent.setClass(ActListActivity.this, ActInfoActivity.class);
		intent.putExtra("act",myList.get((int) id));
		System.out.println(id);
		startActivity(intent);
    }

	//返回箭头
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(ActListActivity.this, CalendarActivity.class);
		startActivity(intent);
		ActListActivity.this.finish();
	}

	@Override
	public void setActList(List<com.example.model.Activity> actList) {
		// TODO Auto-generated method stub
		
	}

}
