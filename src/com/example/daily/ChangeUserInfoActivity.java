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

public class ChangeUserInfoActivity extends Activity implements UserView{
	private EditText telText;
	private EditText pwdText;
	private EditText ipwdText;
	private TextView snoText;
	private TextView userNameText;
	
	private String strTel;
	private String strPwd;
	private String strPwd2;
	private User  tempUser;
	
	private SharedPreferences sp;
	
	private UserManage userManage;
	
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

	//返回箭头
	public void back(View view){
		Intent intent = new Intent();
		intent = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
		startActivity(intent);
		ChangeUserInfoActivity.this.finish();
	}
	
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
	
	@Override
	public User getUser() {
		SharedPreferences sp = getApplication().getSharedPreferences("userInfo", Context.MODE_APPEND);
		String user = sp.getString("user", "");
		User returnUser = JSON.parseObject(user, User.class);
		
		returnUser.setPhoneno(strTel);
		returnUser.setPassword(strPwd);
		return returnUser;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
