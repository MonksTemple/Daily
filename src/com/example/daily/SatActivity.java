package com.example.daily;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.example.model.User;
import com.example.presenter.ActManage;
import com.example.util.DateUtil;
import com.example.view.ActListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

public class SatActivity extends Activity implements ActListView {
	private Drawable  redCircle;
	private Drawable  blueCircle;
	private Drawable  yellowCircle;
	private Drawable  orangeCircle;

	ListView list;
	Date curDate;
	
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
			curDate=(Date) list.get(1);
			loadList(myList);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sat);

		//初始化控件
		initial();
		actManage=new ActManage(this);
		//加载列表
		loadData(new Date(),0);

		setOnListListener();
	}

	/**
	 * 
	 */
	public void initial(){
		redCircle= this.getResources().getDrawable(R.drawable.redc);
		blueCircle= this.getResources().getDrawable(R.drawable.bluec);
		yellowCircle= this.getResources().getDrawable(R.drawable.yellowc);
		orangeCircle=this.getResources().getDrawable(R.drawable.orange);
		list= (ListView) findViewById(R.id.classListView); 
	}

	public void loadList(List<com.example.model.Activity> alist){
		ArrayList<HashMap<String, Object>> mylist = new ArrayList<HashMap<String, Object>>();  
		
		for(com.example.model.Activity act:alist){
			if(DateUtil.compareDates(act.getEndTime(), curDate)){
			HashMap<String, Object> map1 = new HashMap<String, Object>(); 
			map1.put("ItemTitle", act.getName());
			map1.put("ItemText", act.getDescription());
			switch(act.getType()){
			case 1:
				map1.put("type",yellowCircle);
				break;
			case 2:
				map1.put("type",redCircle);
				break;
			case 3:
				map1.put("type",blueCircle);
				break;
			default:
				map1.put("type",orangeCircle);
				break;
			}
			
			mylist.add(map1);
			}
		}
		
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,
				R.layout.main_list,


				new String[] {"ItemTitle", "ItemText","type"},   


				new int[] {R.id.ItemTitle,R.id.ItemText,R.id.type});  

		mSchedule.setViewBinder(new ViewBinder() { 
			@Override 
			public boolean setViewValue(View view, Object data, 
					String textRepresentation) { 
				if (view instanceof ImageView && data instanceof Drawable) { 
					ImageView iv = (ImageView)view; 
					iv.setImageDrawable((Drawable)data); 
					return true; 
				} 
				return false; 
			} 
		}); 

		list.setAdapter(mSchedule); 

	}


	private void setOnListListener() {
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根

				Intent intent =new Intent();
				intent.setClass(SatActivity.this, AgendaInfoActivity.class);
				intent.putExtra("agenda", myList.get((int) id));
				System.out.println(id);
				startActivity(intent);
			}

		});
	}

	@Override
	public void setActList(List<com.example.model.Activity> actList) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @param date  当前页面的日期
	 * @param Type  要查询的类型，Type为0的时候得到所有的列表，Type为1的时候为团队活动，type为2的时候为任务，type3 是个人活动，type4是单独的活动	
	 */
	public void loadData(Date date,int type){
		new MyThread(date,type){
			public void run(){
				Message msg = new Message();
				Bundle bundle = new Bundle();
				ArrayList list=new ArrayList();
				List<com.example.model.Activity> myList = null;
				SharedPreferences sp = getApplication().getSharedPreferences("userInfo", Context.MODE_APPEND);
				String user = sp.getString("user", "");
				User users = JSON.parseObject(user,User.class);
				int uid=users.getUserId();
				myList=actManage.showAgendByUserId(uid,type);	
				list.add(myList);
				list.add(date);
				bundle.putParcelableArrayList("myList", list);
				msg.setData(bundle);
				handler.sendMessage(msg);
			}
		}.start();
	}
	
}
