package com.example.daily;

import com.example.model.User;
import com.example.presenter.UserManage;
import com.example.view.UserView;

import android.annotation.SuppressLint;
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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements UserView{
	private EditText account_text;
	private EditText password_text;
	private UserManage userManage;
	private SharedPreferences sp;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			String login = bundle.getString("login");
			if (login.equals("true")) {//为true说明登陆成功
				Intent intent = new Intent();
				intent = new Intent(LoginActivity.this, ActListActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
				//Toast.makeText(get, "登陆成功",Toast.LENGTH_LONG).show();
			}else {
				Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT)
				.show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);	
		sp = getApplication().getSharedPreferences("userInfo", Context.MODE_APPEND);
		account_text = (EditText)findViewById(R.id.username);
		password_text = (EditText)findViewById(R.id.password);
		
		userManage = new UserManage(this);
	}

	public void login(View view){
		//判断
		if(account_text.getText().toString() == null || account_text.getText().toString().length() <= 0
			|| password_text.getText().toString() == null || password_text.getText().toString().length() <= 0){
			
			Toast.makeText(getApplicationContext(),"请完整填写的信息", Toast.LENGTH_SHORT).show();
		}else{
				new Thread (){
					public void run() {
						
						Message msg = new Message();
						Bundle bundle = new Bundle();
						String myUser = userManage.login();
						if(!myUser.equals("false")){
							bundle.putString("login", "true");
							Editor editor=sp.edit();
							editor.putString("user", myUser);
							editor.commit();
							msg.setData(bundle);
							handler.sendMessage(msg);
						}
						else{
							bundle.putString("login", "false");
							msg.setData(bundle);
							handler.sendMessage(msg);
						}
						
					}
				}.start();
			}
		}

	public void register(View view){
		//注册界面跳转
		Intent intent = new Intent();
		intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(intent);
	}



	@Override
	public User getUser() {
		User returnUser = new User();
		returnUser.setUserId(Integer.valueOf(account_text.getText().toString()));
		returnUser.setPassword(password_text.getText().toString());

		return returnUser;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub

	}
}
