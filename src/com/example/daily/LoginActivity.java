package com.example.daily;

import com.example.model.User;
import com.example.presenter.UserManage;
import com.example.view.UserView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		account_text = (EditText)findViewById(R.id.username);
		password_text = (EditText)findViewById(R.id.password);
		
		userManage = new UserManage(this);

		
	}

	public void login(View view){
		//判断
		//...
		if(account_text.getText().toString() == null || account_text.getText().toString().length() <= 0
			|| password_text.getText().toString() == null || password_text.getText().toString().length() <= 0){
			
			Toast.makeText(getApplicationContext(),"请完整填写的信息", Toast.LENGTH_SHORT).show();
		}else{
			//如果成功登录
			if(userManage.login()){
				//登录界面跳转
				Intent intent = new Intent();
				intent = new Intent(LoginActivity.this, CalendarActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
			}else{
				Toast.makeText(getApplicationContext(),"账号密码错误", Toast.LENGTH_SHORT).show();
			}
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
		returnUser.setUserName(account_text.getText().toString());
		returnUser.setPassword(password_text.getText().toString());

		return returnUser;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub

	}



}
