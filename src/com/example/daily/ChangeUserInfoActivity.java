package com.example.daily;

import com.example.model.User;
import com.example.presenter.UserManage;
import com.example.view.UserView;

import android.app.Activity;
import android.content.Intent;
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
	
	private UserManage userManage;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			String login = bundle.getString("register");
			if (login.equals("true")) {//为true说明登陆成功
				Intent intent1 = new Intent();
				intent1 = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
				startActivity(intent1);
				ChangeUserInfoActivity.this.finish();
			}else {
				Toast.makeText(ChangeUserInfoActivity.this, "注册失败", Toast.LENGTH_SHORT)
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
		
		telText.setText("18813090935");
		snoText.setText("13301043");
		userNameText.setText("张三");
		
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
		
		//如果两项都为空  则为不修改
		if((strTel == "" || strTel == null) && (strPwd == "" || strPwd == null)){
			
		}
		//如果手机号不为空  密码为空  则为修改手机号码
		else if(!(strTel == "" || strTel == null) && (strPwd == "" || strPwd == null)){
			new Thread (){
				public void run() {
					Message msg = new Message();
					Bundle bundle = new Bundle();

					if(userManage.register()){
						bundle.putString("register", "true");
						msg.setData(bundle);
						handler.sendMessage(msg);
					}
					else{
						bundle.putString("register", "false");
						msg.setData(bundle);
						handler.sendMessage(msg);
					}
					
				}
			}.start();	
		}
		//如果手机号不为空  密码为空  则为修改手机号码
		else if((strTel == "" || strTel == null) && !(strPwd == "" || strPwd == null)){
			
		}
		
		
	}

	
	
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
