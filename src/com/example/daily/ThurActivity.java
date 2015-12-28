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

/**
 * 周四日程列表子界面
 */
public class ThurActivity extends Activity implements ActListView {
	/**
	 * 红色小圆圈，代表任务
	 */
	private Drawable  redCircle;
	/**
	 * 绿色小圆圈，代表个人活动
	 */
	private Drawable  blueCircle;
	/**
	 * 黄色小圆圈，代表社团活动
	 */
	private Drawable  yellowCircle;
	/**
	 * 橙色小圆圈，代表单独活动
	 */
	private Drawable  orangeCircle;

	/**事件列表**/
	ListView list;
	/**当前界面对应的日期**/
	Date curDate;
	
	/**活动管理类对象*/
	ActManage actManage;
	/**活动列表*/
	List<com.example.model.Activity> myList;
	
	/**处理类对象*/
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
	/*
	 * 界面显示
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thur);

		//初始化控件
		initial();
		actManage=new ActManage(this);
		//加载列表
		loadData(new Date(),0);

		setOnListListener();
	}

	/**
	 * 初始化界面
	 */
	public void initial(){
		redCircle= this.getResources().getDrawable(R.drawable.redc);
		blueCircle= this.getResources().getDrawable(R.drawable.bluec);
		yellowCircle= this.getResources().getDrawable(R.drawable.yellowc);
		orangeCircle=this.getResources().getDrawable(R.drawable.orange);
		list= (ListView) findViewById(R.id.classListView); 
	}

	/**
	 * 加载并显示事件列表
	 * @param alist
	 */
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


	/**
	 * 列表子项点击事件响应
	 */
	private void setOnListListener() {
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根

				Intent intent =new Intent();
				intent.setClass(ThurActivity.this, AgendaInfoActivity.class);
				intent.putExtra("agenda", myList.get((int) id));
				System.out.println(id);
				startActivity(intent);
			}

		});
	}

	/**
	 * 设置事件列表的值
	 * @param actList
	 */
	@Override
	public void setActList(List<com.example.model.Activity> actList) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 加载事件列表数据
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