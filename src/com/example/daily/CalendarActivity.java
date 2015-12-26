package com.example.daily;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.example.component.CalendarView;
import com.example.component.CalendarView.CallBack;
import com.example.component.CalendarViewBuilder;
import com.example.component.CalendarViewPagerLisenter;
import com.example.component.CustomViewPagerAdapter;
import com.example.util.CalendarLunarUtil;
import com.example.util.CustomDate;

@SuppressWarnings("deprecation")
public class CalendarActivity extends Activity implements CallBack{
	private ViewPager viewPager;
	private CalendarView[] views;
	private TextView showYearView;
	private TextView showMonthView;
	private TextView showWeekView;
	private CalendarViewBuilder builder = new CalendarViewBuilder();
	private View mContentPager;
	private CustomDate mClickDate;
	private CustomDate lastDate;
	//菜单栏
	private PopupMenu popupMenu;  
	private Menu menu;
	private Typeface typeface;
	Date dates=new Date();
	
	Calendar cal=Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_calendar);
		findViewbyId();
		initialMenu();
	}
	
	
	
	private void findViewbyId() {
		viewPager = (ViewPager) this.findViewById(R.id.viewpager);
		showMonthView = (TextView)this.findViewById(R.id.show_month_view);
		showYearView = (TextView)this.findViewById(R.id.show_year_view);
		showWeekView = (TextView)this.findViewById(R.id.show_week_view);
		views = builder.createMassCalendarViews(this, 5, this);
		mContentPager = this.findViewById(R.id.contentPager);
		showMonthView.setOnClickListener(new changeDateListener());
		showYearView.setOnClickListener(new changeDateListener());
		showWeekView.setOnClickListener(new changeDateListener());
		setTypeface();
		setViewPager();
	}

	private void setTypeface(){
		typeface=Typeface.createFromAsset(this.getAssets(), "fonts/hwht.ttf");
		showMonthView.setTypeface(typeface);
		showYearView.setTypeface(typeface);
		showWeekView.setTypeface(typeface);
	}
	

	private void setViewPager() {
		CustomViewPagerAdapter<CalendarView> viewPagerAdapter = new CustomViewPagerAdapter<CalendarView>(views);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setCurrentItem(498); 
		viewPager.setOnPageChangeListener(new CalendarViewPagerLisenter(viewPagerAdapter));
	}


 @Override  
 protected void onDestroy() {  
     super.onDestroy();  
 }  


	
 
 public void setShowDateViewText(int year ,int month,String week,String lunar){
	 showYearView.setText(year+"年");
	 showMonthView.setText(month+"月");
	 showWeekView.setText(week);
 }


	@Override
	public void onMesureCellHeight(int cellSpace) {
	}

	@Override
	public void clickDate(CustomDate date) {
		mClickDate = date;
		lastDate=date;
		//Toast.makeText(this, date.year+"-"+date.month+"-"+date.day, Toast.LENGTH_SHORT).show();
		changeDate(date);
	}

	//事件响应
	@Override
	public void changeDate(CustomDate date) {
		//得到农历
		cal.set(Calendar.YEAR, date.year);
		cal.set(Calendar.MARCH,date.month-1);
		cal.set(Calendar.DAY_OF_MONTH,date.day);
		CalendarLunarUtil cu=new CalendarLunarUtil(cal);
		dates=cal.getTime();
		setShowDateViewText(date.year,date.month,CalendarLunarUtil.getWeek(cal),cu.getDay());
	}
 
	
	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			CustomDate date=new CustomDate(year,monthOfYear+1,dayOfMonth);
			builder.turnSpecialDays(date);
		}
	};

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1: 
			int year=lastDate.year;
			int month=lastDate.month-1;
			int day=lastDate.day;
			return new DatePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,onDateSetListener, year, month,day);
		}
		return null;
	}

	class changeDateListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			showDialog(1);
		}

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
					//界面跳转到课程列表
					Intent intent1 = new Intent();
					intent1 = new Intent(CalendarActivity.this, ClassListActivity.class);
					startActivity(intent1);
					CalendarActivity.this.finish();
					break;   
				case R.id.act: 
					//界面跳转到活动列表
					Intent intent2 = new Intent();
					intent2 = new Intent(CalendarActivity.this, ActListActivity.class);
					startActivity(intent2);
					CalendarActivity.this.finish();  
					break;  
				case R.id.team: 
					//界面跳转到团队列表
					Intent intent3 = new Intent();
					intent3 = new Intent(CalendarActivity.this, TeamListActivity.class);
					startActivity(intent3);
					CalendarActivity.this.finish();
					break;   
				case R.id.agenda: 
					//界面跳转到日程列表
					Intent intent4 = new Intent();
					intent4 = new Intent(CalendarActivity.this, AgendaListActivity.class);
					startActivity(intent4);
					CalendarActivity.this.finish(); 
					break;  
				case R.id.user:  
					//界面跳转到用户信息
					Intent intent5 = new Intent();
					intent5 = new Intent(CalendarActivity.this, UserInfoActivity.class);
					startActivity(intent5);
					CalendarActivity.this.finish();
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
	
	public void clickSure(View view){
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		
		//界面跳转
		Intent intent = new Intent();
		intent = new Intent(CalendarActivity.this, MainActivity.class);
		startActivity(intent);
		CalendarActivity.this.finish();
	}

}  



	


