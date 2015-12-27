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


public class ActLvAdapter extends BaseAdapter {
	boolean result = false;
	Context mycontext;

	private class buttonViewHolder {
		TextView title;
		TextView text;
		ImageView add;
	}

	private ArrayList<HashMap<String, Object>> mAppList;
	private LayoutInflater mInflater;
	private Context mContext;
	private String[] keyString;
	private int[] valueViewID;
	private buttonViewHolder holder;
	int userId;
	private Context c;

	public ActLvAdapter(Context c,
			ArrayList<HashMap<String, Object>> appList, int resource,
			String[] from, int[] to,Context con) {
		mycontext = c;
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

	@Override
	public int getCount() {
		return mAppList.size();
	}

	@Override
	public Object getItem(int position) {
		return mAppList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


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

	class lvButtonListener implements OnClickListener {

		private int position;
		private String title;
		private int bid;
		private ImageView pic;
		private Context c;

		lvButtonListener(int pos,String title,int bid,ImageView pic,Context c) {
			position = pos;
			this.title=title;
			this.bid=bid;
			this.pic=pic;
			this.c=c;
		}

		@Override
		public void onClick(View v) {
			int vid = v.getId();
			addItem();	
		}


		//根据title将活动列表添加或者移除到日程列表中
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
