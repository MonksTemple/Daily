package com.example.component;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 自定义页面适配器
 * @param <V>
 */
public class CustomViewPagerAdapter<V extends View> extends PagerAdapter {
	
	private V[] views;

	/**
	 * 构造函数
	 *
	 * @param views
	 */
	public CustomViewPagerAdapter(V[] views) {
		super();
		this.views = views;
	}

	/*
	 * 
	 * @param arg0 
	 * @see android.support.v4.view.PagerAdapter#finishUpdate(android.view.View)
	 */
	@Override
	public void finishUpdate(View arg0) {
	}

	/*
	 * @see android.support.v4.view.PagerAdapter#notifyDataSetChanged()
	 */
	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	/*
	 * 
	 * @return 
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	/*
	 * 
	 * @param arg0
	 * @param arg1
	 * @return 
	 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view.View, int)
	 */
	@Override
	public Object instantiateItem(View arg0, int arg1) {
		if (((ViewPager) arg0).getChildCount() == views.length) {
			((ViewPager) arg0).removeView(views[arg1 % views.length]);
		}
		((ViewPager) arg0).addView(views[arg1 % views.length], 0);

		return views[arg1 % views.length];
	}

	/*
	 * 
	 * @param arg0
	 * @param arg1
	 * @return 
	 * @see android.support.v4.view.PagerAdapter#isViewFromObject(android.view.View, java.lang.Object)
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	/*
	 * 
	 * @return 
	 * @see android.support.v4.view.PagerAdapter#saveState()
	 */
	@Override
	public Parcelable saveState() {
		return null;
	}

	/*
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2 
	 * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View, int, java.lang.Object)
	 */
	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * 
	 * @param arg0 
	 * @see android.support.v4.view.PagerAdapter#startUpdate(android.view.View)
	 */
	@Override
	public void startUpdate(View arg0) {
	}

	/*
	 * 获取所有界面
	 */
	public V[] getAllItems() {
		return views;
	}
}
