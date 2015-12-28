package com.example.component;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.daily.ActListActivity;
import com.example.daily.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 自定义的适配器
 */
public class ActLvAdapter extends BaseAdapter {
	/**
	 * 父容器
	 */

	/**
	 * 自定义类用来存放ListItem中的元素
	 */
	private class buttonViewHolder {
		TextView title;
		TextView text;
		ImageView add;
	}

	/**
	 * 存放存储数据的容器
	 */
	private ArrayList<HashMap<String, Object>> mAppList;
	/**
	 * 布局填充器
	 */
	private LayoutInflater mInflater;
	private Context mContext;
	private String[] keyString;
	private int[] valueViewID;
	private buttonViewHolder holder;
	int userId;
	/**
	 * listView容器
	 */
	private Context c;

	/**
	 * 构造函数
	 *
	 * @param c
	 * @param appList
	 * @param resource
	 * @param from
	 * @param to
	 * @param con
	 */
	public ActLvAdapter(Context c,
			ArrayList<HashMap<String, Object>> appList, int resource,
			String[] from, int[] to,Context con) {
		mAppList = appList;
		mContext = c;
		this.c=con;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		keyString = new String[from.length];
		valueViewID = new int[to.length];
		System.arraycopy(from, 0, keyString, 0, from.length);
		System.arraycopy(to, 0, valueViewID, 0, to.length);
	}

	/*
	 * 获得父容器大小
	 * @return 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return mAppList.size();
	}

	/*
	 * 
	 * @param position
	 * @return 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return mAppList.get(position);
	}

	/*
	 * 
	 * @param position
	 * @return 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

/*
 * 
 * @param position
 * @param convertView
 * @param parent
 * @return 
 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView != null) {
			holder = (buttonViewHolder) convertView.getTag();
		} else {
			convertView = mInflater.inflate(R.layout.two_decimal_item, null);
			holder = new buttonViewHolder();
			holder.title = (TextView) convertView
					.findViewById(valueViewID[0]);
			holder.text = (TextView) convertView
					.findViewById(valueViewID[1]);
			holder.add = (ImageView) convertView
					.findViewById(valueViewID[2]);
			convertView.setTag(holder);
		}

		HashMap<String, Object> appInfo = mAppList.get(position);


		if (appInfo != null) {
			String title = (String) appInfo.get(keyString[0]);
			String text = (String) appInfo.get(keyString[1]);
			int bid = (Integer) appInfo.get(keyString[2]);
			holder.title.setText(title);
			holder.text.setText(text);
			holder.add.setImageDrawable(holder.add
					.getResources().getDrawable(bid));
			holder.add
			.setOnClickListener(new lvButtonListener(position,title,bid,holder.add,c));
		}
		return convertView;
	}

	/**
	 * 图片的监听类
	 */
	class lvButtonListener implements OnClickListener {

		private int position;
		private String title;
		private int bid;
		private ImageView pic;
		private Context c;

		/**
		 * 构造函数
		 *
		 * @param pos
		 * @param title
		 * @param bid
		 * @param pic
		 * @param c
		 */
		lvButtonListener(int pos,String title,int bid,ImageView pic,Context c) {
			position = pos;
			this.title=title;
			this.bid=bid;
			this.pic=pic;
			this.c=c;
		}

		/*
		 * 
		 * 点击事件
		 * @param v 
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View v) {
			int vid = v.getId();
			addItem();	
		}


		/**
		 * 根据title将活动列表添加或者移除到日程列表中
		 */
		public void addItem(){
			String object=c.toString();
			HashMap<String, Object> item=(HashMap<String, Object>) getItem(position);
			if(bid==R.drawable.add){
				pic.setImageDrawable(holder.add
						.getResources().getDrawable(R.drawable.minus));
				bid=R.drawable.minus;

				//将活动加入日程
				if(object.indexOf("ActListActivity")!=-1){

					Toast.makeText(c, "活动:"+title+"已加入你的日程列表",  
							Toast.LENGTH_SHORT).show(); 
				}
				//将课程组加入到日程
				if(object.indexOf("ClassListActivity")!=-1){
					Toast.makeText(c, "你已加入课程组:"+title,  
							Toast.LENGTH_SHORT).show(); 
				}

				//加入团队
				if(object.indexOf("TeamListActivity")!=-1){
					Toast.makeText(c, "你已加入团队："+title,  
							Toast.LENGTH_SHORT).show(); 
				}

			}else{
				pic.setImageDrawable(holder.add
						.getResources().getDrawable(R.drawable.add));
				bid=R.drawable.add;
				//将活动移出日程
				if(object.indexOf("ActListActivity")!=-1){
					Toast.makeText(c, "活动:"+title+"已移除你的日程列表",  
							Toast.LENGTH_SHORT).show();
				}
				//退出课程组
				if(object.indexOf("ClassListActivity")!=-1){
					Toast.makeText(c, "你已退出课程组:"+title,  
							Toast.LENGTH_SHORT).show(); 
				}

				if(object.indexOf("TeamListActivity")!=-1){
					Toast.makeText(c, "你已退出团队："+title,   
							Toast.LENGTH_SHORT).show(); 
				}

			}
		}
	}
}
