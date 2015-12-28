package com.example.daily;

import com.alibaba.fastjson.JSON;
import com.example.model.User;
import com.example.presenter.UserManage;
import com.example.view.UserView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 修改用户信息界面
 */
public class ChangeUserInfoActivity extends Activity implements UserView{
	/**电话编辑框*/
	private EditText telText;
	/**密码编辑框*/
	private EditText pwdText;
	/**密码编辑框*/
	private EditText ipwdText;
	/**学号编辑框*/
	private TextView snoText;
	/**姓名编辑框*/
	private TextView userNameText;
	
	/**电话字符串*/
	private String strTel;
	/**密码字符串*/
	private String strPwd;
	/**密码字符串*/
	private String strPwd2;
	/**电话字符串*/
	private User  tempUser;
	/**信息存储类对象*/
	private SharedPreferences sp;
	/**用户管理类对象*/
	private UserManage userManage;
	/**处理类对象*/
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			String modify = bundle.getString("modify");
			if (modify.equals("true")) {//为true说明修改成功		
				sp = getApplication().getSharedPreferences("userInfo", Context.MODE_APPEND);
				
				
				Editor editor=sp.edit();
				editor.putString("user", JSON.toJSONString(getUser()));
				editor.commit();
				
				Intent intent1 = new Intent();
				intent1 = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
				startActivity(intent1);
				ChangeUserInfoActivity.this.finish();
			}else {
				Toast.makeText(ChangeUserInfoActivity.this, "修改失败", Toast.LENGTH_SHORT)
				.show();
			}
		}
	};
	

	@Override
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_user_info);
		
		telText = (EditText) findViewById(R.id.tel);
		pwdText = (EditText) findViewById(R.id.pwd);
		ipwdText = (EditText) findViewById(R.id.ipwd);
		
		snoText = (TextView) findViewById(R.id.sno);
		userNameText = (TextView) findViewById(R.id.sname);
		
		SharedPreferences sp = getApplication().getSharedPreferences("userInfo", Context.MODE_APPEND);
		String user = sp.getString("user", "");
		tempUser = JSON.parseObject(user, User.class);
		
		telText.setText(tempUser.getPhoneno());
		snoText.setText(tempUser.getUserId()+"");
		userNameText.setText(tempUser.getUserName());
		
		userManage = new UserManage(this);
	}

	/**
	 * 
	 * 返回按钮事件处理
	 * @param view
	 */
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
		startActivity(intent);
		ChangeUserInfoActivity.this.finish();
	}
	
	/**
	 * 
	 * 确认按钮事件处理
	 * @param view
	 */
	public void sure(View view){
		strTel = telText.getText().toString();
		strPwd = pwdText.getText().toString();
		strPwd2 = ipwdText.getText().toString();
		
		//没有修改密码
		if(strPwd.length() <= 0 || strPwd == null || strPwd2.length() <= 0 || strPwd2 == null){
			strPwd = tempUser.getPassword();
		}
		//手机号码为空
		if(strTel.length() <= 0 || strTel == null){
			strTel = tempUser.getPhoneno();
		}
			
		new Thread (){
			public void run() {
				Message msg = new Message();
				Bundle bundle = new Bundle();

				if(userManage.modify()){
					bundle.putString("modify", "true");	
				
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
				else{
					bundle.putString("modify", "false");
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
				
			}
		}.start();	
	}
	
	/*
	 * 
	 * 获得用户信息
	 * @return 
	 * @see com.example.view.UserView#getUser()
	 */
	@Override
	public User getUser() {
		SharedPreferences sp = getApplication().getSharedPreferences("userInfo", Context.MODE_APPEND);
		String user = sp.getString("user", "");
		User returnUser = JSON.parseObject(user, User.class);
		
		returnUser.setPhoneno(strTel);
		returnUser.setPassword(strPwd);
		return returnUser;
	}

	/*
	 * 
	 * 设置用户信息
	 * @param user 
	 * @see com.example.view.UserView#setUser(com.example.model.User)
	 */
	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
