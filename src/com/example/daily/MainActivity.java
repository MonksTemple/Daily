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
/**
 * 日程中心界面
 */
public class MainActivity extends Activity {

	/**ViewPager控件**/
	ViewPager pager = null;
	/**
	 * 标签
	 */
	PagerTabStrip tabStrip = null;
	/**
	 * 用来存放子界面的容器
	 */
	ArrayList<View> viewContainter = new ArrayList<View>();
	/**
	 * 用来存放子界面名字的容器
	 */
	ArrayList<String> titleContainer = new ArrayList<String>();
	
	/**
	 * 页面控制器
	 */
	private LocalActivityManager mactivityManager = null; 

	/**
	 * 菜单栏
	 */
	private PopupMenu popupMenu;  
	private Menu menu;
	Date date;
	int type=0;
	int weekDay=0;

	/*
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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

	/**
	 * 
	 * 初始化title的值
	 */
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


	/**
	 * 通过XML导入菜单栏,并设置菜单栏的监听事件
	 */
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
					pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type,pager.getCurrentItem()));
					pager.getAdapter().notifyDataSetChanged();
					pager.childDrawableStateChanged(viewContainter.get(0));
					pager.getAdapter().notifyDataSetChanged();
					Toast.makeText(MainActivity.this, "活动列表",  
							Toast.LENGTH_SHORT).show(); 
					break;  
				case R.id.task:  
					type=2;
					pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type,pager.getCurrentItem()));
					Toast.makeText(MainActivity.this, "任务列表",  
							Toast.LENGTH_SHORT).show(); 
					break;   
				case R.id.agenda:  
					type=3;
					pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type,pager.getCurrentItem()));
					Toast.makeText(MainActivity.this, "日程列表",  
							Toast.LENGTH_SHORT).show();  
					break;  
				case R.id.person:  
					type=4;
					pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type,pager.getCurrentItem()));
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
	

	/**
	 * 
	 * 点击三道线图片响应事件：弹出菜单栏
	 * @param v
	 */
	public void popupmenu(View v) {  
		popupMenu.show();  
	} 
	
	/**
	 * 
	 * 点击返回按钮响应事件：返回日历界面
	 * @param view
	 */
	public void clickBack(View view){	
		//界面跳转
		Intent intent = new Intent();
		intent = new Intent(MainActivity.this, CalendarActivity.class);
		startActivity(intent);
		MainActivity.this.finish();
	}

	/**
	 * 
	 * 设置当前显示的页面
	 */
	public void getDate(){
		Intent intent=getIntent();
		date=(Date) intent.getSerializableExtra("date");
		weekDay=DateUtil.getWeekDayFromDate(date);
		pager.setCurrentItem(weekDay);
		pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer,date,type,pager.getCurrentItem()));
	}
}



/**
 * 定义自己的ViewPager适配器
 */
class MyViewPagerAdapter extends PagerAdapter{  
	//viewpager中的组件数量
	List<View> viewContainter;
	List<String> titleContainer;

	/**
	 * 构造函数
	 *
	 * @param viewContainter
	 * @param titleContainer
	 */
	public MyViewPagerAdapter(List<View> viewContainter,List<String> titleContainer) {  
		this.viewContainter = viewContainter;  
		this.titleContainer=titleContainer;
	}

	/**
	 * 
	 * 得到viewPager的子界面的个数
	 * @return
	 */
	@Override
	public int getCount() {
		return viewContainter.size();
	}
	
	/**
	 * 
	 * 滑动切换的时候销毁当前的组件
	 * @param container
	 * @param position
	 * @param object
	 */
	@Override
	public void destroyItem(ViewGroup container, int position,
			Object object) {
		((ViewPager) container).removeView(viewContainter.get(position));
	}

	/**
	 * 
	 * 每次滑动的时候生成的组件
	 * @param container
	 * @param position
	 * @return
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		((ViewPager) container).addView(viewContainter.get(position));
		return viewContainter.get(position);
	}

	/**
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	/**
	 * 得到子界面的标题
	 * @param position
	 * @return
	 */
	@Override
	public CharSequence getPageTitle(int position) {
		return titleContainer.get(position);
	}
}

/**
 * 页面状态改变监听事件
 */
class MyOnPageChangeListener implements OnPageChangeListener{  
	private LocalActivityManager mactivityManager = null;
	ArrayList<String> titleContainer = new ArrayList<String>();
	Date date;
	int type;
	int currentPage;

	/**
	 * 构造函数
	 *
	 * @param mactivityManager
	 * @param titleContainer
	 * @param date
	 * @param type
	 * @param currentPage
	 */
	public MyOnPageChangeListener(LocalActivityManager mactivityManager, ArrayList<String> titleContainer,Date date,int type,int currentPage){
		this.mactivityManager=mactivityManager;
		this.titleContainer=titleContainer;
		this.date=date;
		this.type=type;
		this.currentPage=currentPage;
	}

	/**
	 * 界面状态改变
	 * @param arg0
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
		         Log.d("mainactivity", "--------changed:" + arg0);
	}

	/**
	 * 界面滑动
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		         Log.d("mainactivity", "-------scrolled arg0:" + arg0);
		         Log.d("mainactivity", "-------scrolled arg1:" + arg1);
		         Log.d("mainactivity", "-------scrolled arg2:" + arg2);
	}

	/**
	 * 选择页面
	 * @param arg0
	 */
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
        	getCudate(arg0);
        	currentPage=0;
            if(curActivity != null && curActivity instanceof MondayActivity){  
                ((MondayActivity)curActivity).loadData(date,type);;  
            }  
            break;  
        case 1:  
        	getCudate(arg0);
        	currentPage=1;
        	  if(curActivity != null && curActivity instanceof TuesdayActivity){  
                  ((TuesdayActivity)curActivity).loadData(date,type);;  
              }  
              break;   
        case 2:  
        	getCudate(arg0);
        	currentPage=2;
      	  if(curActivity != null && curActivity instanceof WedActivity){  
                ((WedActivity)curActivity).loadData(date,type);;  
            }  
            break;   
        case 3:  
        	getCudate(arg0);
        	currentPage=3;
      	  if(curActivity != null && curActivity instanceof ThurActivity){  
                ((ThurActivity)curActivity).loadData(date,type);;  
            }  
            break; 
        case 4: 
        	getCudate(arg0);
        	currentPage=4;
      	  if(curActivity != null && curActivity instanceof FriActivity){  
                ((FriActivity)curActivity).loadData(date,type);;  
            }  
            break;   
        case 5:  
        	getCudate(arg0);
        	currentPage=5;
      	  if(curActivity != null && curActivity instanceof SatActivity){  
                ((SatActivity)curActivity).loadData(date,type);;  
            }  
            break; 
        case 6:  
        	getCudate(arg0);
        	currentPage=6;
      	  if(curActivity != null && curActivity instanceof SunActivity){  
                ((SunActivity)curActivity).loadData(date,type);;  
            }  
            break; 
     
        }  
    }  

    /**
     * 获得子界面对应的日期
     * @param arg0
     */
    private void getCudate(int arg0){
    	if(currentPage<arg0)
    		date=DateUtil.getForword(date, 1);
    	else
    		date=DateUtil.getBackday(date, 1);
    }
}