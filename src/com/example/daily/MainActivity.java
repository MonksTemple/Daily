package com.example.daily;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.util.DateUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;
public class MainActivity extends Activity {

	ViewPager pager = null;
	PagerTabStrip tabStrip = null;
	ArrayList<View> viewContainter = new ArrayList<View>();
	ArrayList<String> titleContainer = new ArrayList<String>();
	private LocalActivityManager mactivityManager = null; 

	//菜单栏
	private PopupMenu popupMenu;  
	private Menu menu;
	Date date;
	int type=0;
	int weekDay=0;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mactivityManager = new LocalActivityManager(this, true);  
		mactivityManager.dispatchCreate(savedInstanceState);  
		//初始化title的值
		initialTitile();
		initialMenu();
		//设置当前显示的页面
		getDate();
	}

	//初始化title的值
	@SuppressWarnings("deprecation")
	public void initialTitile(){
		pager = (ViewPager) this.findViewById(R.id.vPager);
		tabStrip = (PagerTabStrip) this.findViewById(R.id.pagertab);
		//取消tab下面的长横线
		tabStrip.setDrawFullUnderline(false);
		//设置tab的背景色
		tabStrip.setBackgroundColor(this.getResources().getColor(R.color.theme));
		//设置当前tab页签的下划线颜色
		tabStrip.setTabIndicatorColor(this.getResources().getColor(R.color.white));
		tabStrip.setTextSpacing(200);

		//页签项
		titleContainer.add("周一");
		titleContainer.add("周二");
		titleContainer.add("周三");
		titleContainer.add("周四");
		titleContainer.add("周五");
		titleContainer.add("周六");
		titleContainer.add("周日");


		View view1=getView(titleContainer.get(0),new Intent(getApplicationContext(),MondayActivity.class));
		View view2=getView(titleContainer.get(1),new Intent(getApplicationContext(),TuesdayActivity.class));
		View view3=getView(titleContainer.get(2),new Intent(getApplicationContext(),WedActivity.class));
		View view4=getView(titleContainer.get(3),new Intent(getApplicationContext(),ThurActivity.class));
		View view5=getView(titleContainer.get(4),new Intent(getApplicationContext(),FriActivity.class));
		View view6=getView(titleContainer.get(5),new Intent(getApplicationContext(),SatActivity.class));
		View view7=getView(titleContainer.get(6),new Intent(getApplicationContext(),SunActivity.class));

		viewContainter.add(view1);
		viewContainter.add(view2);
		viewContainter.add(view3);
		viewContainter.add(view4);
		viewContainter.add(view5);
		viewContainter.add(view6);
		viewContainter.add(view7);

		
		pager.setAdapter(new MyViewPagerAdapter(viewContainter,titleContainer));
		pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type));
	}


	/** 
	 * 通过activity获取视图 
	 *  
	 * @param id 
	 * @param intent 
	 * @return 
	 */  
	private View getView(String id, Intent intent) {  
		return mactivityManager.startActivity(id, intent).getDecorView();  
	}  


	public void initialMenu(){
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
		menu = popupMenu.getMenu(); 

		//通过XML导入菜单栏
		MenuInflater menuInflater = getMenuInflater();  
		menuInflater.inflate(R.menu.main_menu, menu); 

		// 设置监听事件
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

			@Override  
			public boolean onMenuItemClick(MenuItem item) {  
				switch (item.getItemId()) {  
				
				case R.id.act:  
					type=1;
					pager.getAdapter().notifyDataSetChanged();
					pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type));
					pager.getAdapter().notifyDataSetChanged();
					pager.childDrawableStateChanged(viewContainter.get(0));
					pager.getAdapter().notifyDataSetChanged();
					Toast.makeText(MainActivity.this, "活动列表",  
							Toast.LENGTH_SHORT).show(); 
					break;  
				case R.id.task:  
					type=2;
					pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type));
					Toast.makeText(MainActivity.this, "任务列表",  
							Toast.LENGTH_SHORT).show(); 
					break;   
				case R.id.agenda:  
					type=3;
					pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type));
					Toast.makeText(MainActivity.this, "日程列表",  
							Toast.LENGTH_SHORT).show();  
					break;  
				case R.id.person:  
					type=4;
					pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type));
					Toast.makeText(MainActivity.this, "单独列表",  
							Toast.LENGTH_SHORT).show();  
					break; 
				default:  
					break;  
				}  
				return false;  
			}  
		});  
	}
	

	public void popupmenu(View v) {  
		popupMenu.show();  
	} 
	
	public void clickBack(View view){	
		//界面跳转
		Intent intent = new Intent();
		intent = new Intent(MainActivity.this, CalendarActivity.class);
		startActivity(intent);
		MainActivity.this.finish();
	}

	//设置当前显示的页面
	public void getDate(){
		Intent intent=getIntent();
		date=(Date) intent.getSerializableExtra("date");
		weekDay=DateUtil.getWeekDayFromDate(date);
		pager.setCurrentItem(weekDay);
	}
}




class MyViewPagerAdapter extends PagerAdapter{  
	//viewpager中的组件数量
	List<View> viewContainter;
	List<String> titleContainer;

	public MyViewPagerAdapter(List<View> viewContainter,List<String> titleContainer) {  
		this.viewContainter = viewContainter;  
		this.titleContainer=titleContainer;
	}

	@Override
	public int getCount() {
		return viewContainter.size();
	}
	//滑动切换的时候销毁当前的组件
	@Override
	public void destroyItem(ViewGroup container, int position,
			Object object) {
		((ViewPager) container).removeView(viewContainter.get(position));
	}
	//每次滑动的时候生成的组件
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		((ViewPager) container).addView(viewContainter.get(position));
		return viewContainter.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleContainer.get(position);
	}
}


class MyOnPageChangeListener implements OnPageChangeListener{  
	private LocalActivityManager mactivityManager = null;
	ArrayList<String> titleContainer = new ArrayList<String>();
	Date date;
	int type;
	int currentPage;

	public MyOnPageChangeListener(LocalActivityManager mactivityManager, ArrayList<String> titleContainer,Date date,int type){
		this.mactivityManager=mactivityManager;
		this.titleContainer=titleContainer;
		this.date=date;
		this.type=type;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		         Log.d("mainactivity", "--------changed:" + arg0);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		         Log.d("mainactivity", "-------scrolled arg0:" + arg0);
		         Log.d("mainactivity", "-------scrolled arg1:" + arg1);
		         Log.d("mainactivity", "-------scrolled arg2:" + arg2);
	}

	@Override
	public void onPageSelected(int arg0) {
		         Log.d("mainactivity", "------selected:" + arg0);
		         loadCurActivity(arg0);
	}

	/** 
     *  
     * @param arg0:页面位置 
     * @function:调用子Activity中的方法 
     *  
     */  
    private void loadCurActivity(int arg0){  
        Activity curActivity = mactivityManager.getActivity(titleContainer.get(arg0));  
        switch(arg0){  
        case 0:  
        	currentPage=0;
            if(curActivity != null && curActivity instanceof MondayActivity){  
                ((MondayActivity)curActivity).loadData(date,type);;  
            }  
            break;  
        case 1:  
        	currentPage=1;
        	  if(curActivity != null && curActivity instanceof TuesdayActivity){  
                  ((TuesdayActivity)curActivity).loadData(date,type);;  
              }  
              break;   
        case 2:  
        	currentPage=2;
      	  if(curActivity != null && curActivity instanceof WedActivity){  
                ((WedActivity)curActivity).loadData(date,type);;  
            }  
            break;   
        case 3:  
        	currentPage=3;
      	  if(curActivity != null && curActivity instanceof ThurActivity){  
                ((ThurActivity)curActivity).loadData(date,type);;  
            }  
            break; 
        case 4:  
        	currentPage=4;
      	  if(curActivity != null && curActivity instanceof FriActivity){  
                ((FriActivity)curActivity).loadData(date,type);;  
            }  
            break;   
        case 5:  
        	currentPage=5;
      	  if(curActivity != null && curActivity instanceof SatActivity){  
                ((SatActivity)curActivity).loadData(date,type);;  
            }  
            break; 
        case 6:  
        	currentPage=6;
      	  if(curActivity != null && curActivity instanceof SunActivity){  
                ((SunActivity)curActivity).loadData(date,type);;  
            }  
            break; 
     
        }  
    }  

}