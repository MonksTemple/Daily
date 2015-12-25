package com.example.daily;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import android.widget.PopupMenu;
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

		pager.setCurrentItem(3);
		pager.setAdapter(new MyViewPagerAdapter(viewContainter,titleContainer));
		pager.setOnPageChangeListener(new MyOnPageChangeListener(mactivityManager,titleContainer));
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
				case R.id.classes:  
					//界面跳转
					Intent intent1 = new Intent();
					intent1 = new Intent(MainActivity.this, ClassListActivity.class);
					startActivity(intent1);
					MainActivity.this.finish();
					break;   
				case R.id.act:  
					Intent intent2 = new Intent();
					intent2 = new Intent(MainActivity.this, ActListActivity.class);
					startActivity(intent2);
					MainActivity.this.finish();  
					break;  
				case R.id.team:  
					Intent intent3 = new Intent();
					intent3 = new Intent(MainActivity.this, TeamListActivity.class);
					startActivity(intent3);
					MainActivity.this.finish();
					break;   
				case R.id.agenda:  
					Intent intent4 = new Intent();
					intent4 = new Intent(MainActivity.this, AgendaListActivity.class);
					startActivity(intent4);
					MainActivity.this.finish(); 
					break;  
				case R.id.user:  
					Intent intent5 = new Intent();
					intent5 = new Intent(MainActivity.this, UserInfoActivity.class);
					startActivity(intent5);
					MainActivity.this.finish();
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
//		MainActivity.this.finish();
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
		return super.getItemPosition(object);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleContainer.get(position);
	}
}


class MyOnPageChangeListener implements OnPageChangeListener{  
	private LocalActivityManager mactivityManager = null;
	ArrayList<String> titleContainer = new ArrayList<String>();

	public MyOnPageChangeListener(LocalActivityManager mactivityManager, ArrayList<String> titleContainer){
		this.mactivityManager=mactivityManager;
		this.titleContainer=titleContainer;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		//         Log.d(TAG, "--------changed:" + arg0);

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		//         Log.d(TAG, "-------scrolled arg0:" + arg0);
		//         Log.d(TAG, "-------scrolled arg1:" + arg1);
		//         Log.d(TAG, "-------scrolled arg2:" + arg2);
	}

	@Override
	public void onPageSelected(int arg0) {
		//         Log.d(TAG, "------selected:" + arg0);
		//    	 loadCurActivity(arg0); 
	}


}