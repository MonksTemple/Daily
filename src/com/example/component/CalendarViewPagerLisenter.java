package com.example.component;

import android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * 日历控件监听类
 */
public class CalendarViewPagerLisenter implements OnPageChangeListener {

	private SildeDirection mDirection = SildeDirection.NO_SILDE;
	int mCurrIndex = 498;
	private CalendarView[] mShowViews;

	/**
	 * 构造函数
	 *
	 * @param viewAdapter
	 */
	public CalendarViewPagerLisenter(CustomViewPagerAdapter<CalendarView> viewAdapter) {
		super();
		this.mShowViews = viewAdapter.getAllItems();
	}
	
	/*
	 * 页面选中事件 
	 * @param arg0 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected(int)
	 */
	@Override
	public void onPageSelected(int arg0) {
		measureDirection(arg0);
		updateCalendarView(arg0);
	}

	/**
	 * 
	 * 更新日历控件
	 * @param arg0
	 */
	private void updateCalendarView(int arg0) {
		if(mDirection == SildeDirection.RIGHT){
			mShowViews[arg0 % mShowViews.length].rightSilde();
		}else if(mDirection == SildeDirection.LEFT){
			mShowViews[arg0 % mShowViews.length].leftSilde();
		}
		mDirection = SildeDirection.NO_SILDE;
	}

	
	/**
	 * 判断手势滑动方向
	 * @param arg0
	 */
	private void measureDirection(int arg0) {

		if (arg0 > mCurrIndex) {
			mDirection = SildeDirection.RIGHT;

		} else if (arg0 < mCurrIndex) {
			mDirection = SildeDirection.LEFT;
		}
		mCurrIndex = arg0;
	}

	/*
	 * 页面滚动事件
	 * @param arg0
	 * @param arg1
	 * @param arg2 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled(int, float, int)
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	/*
	 * 
	 * 页面状态改变事件
	 * @param arg0 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}


	/**
	 * 页面滑动方向
	 */
	enum SildeDirection {
		RIGHT, LEFT, NO_SILDE;
	}
}