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
import android.widget.Toast;

/**
 * 
 * 注册界面
 */
public class RegisterActivity extends Activity implements UserView{
	/**id编辑框*/
	private EditText idText;
	/**姓名编辑框*/
	private EditText nameText;
	/**密码编辑框*/
	private EditText pwdText;
	/**确认密码编辑框*/
	private EditText pwd2Text;
	/**电话编辑框*/
	private EditText phoneText;
	/**用户管理类对象*/
	private UserManage userManage;
	/**处理类对象*/
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle  = msg.getData();
			String login = bundle.getString("register");
			if (login.equals("true")) {//为true说明登陆成功
				Intent intent = new Intent();
				intent = new Intent(RegisterActivity.this, LoginActivity.class);
				startActivity(intent);
				RegisterActivity.this.finish();
			}else {
				Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT)
				.show();
			}
		}
	};
	
	/*
	 * 
	 * 界面生成函数
	 * @param savedInstanceState 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		idText =  (EditText) findViewById(R.id.sno);
		pwdText = (EditText) findViewById(R.id.passwordText);
		pwd2Text = (EditText) findViewById(R.id.password2);
		nameText = (EditText) findViewById(R.id.nameText);
		phoneText = (EditText) findViewById(R.id.phoneText);
			
		userManage = new UserManage(this);
	}
	
	/**
	 * 
	 * 注册按钮事件处理
	 * @param view
	 */
	public void register(View view){
		if(idText.getText().toString() == null || idText.getText().toString().length() <= 0 
			|| pwdText.getText().toString() == null || pwdText.getText().toString().length() <= 0 
			|| pwd2Text.getText().toString() == null || pwd2Text.getText().toString().length() <= 0 
			|| nameText.getText().toString() == null || nameText.getText().toString().length() <= 0
			|| phoneText.getText().toString() == null || phoneText.getText().toString().length() <= 0){
			Toast.makeText(getApplicationContext(),"请完整填写信息", Toast.LENGTH_SHORT).show();
		}else{
			//若两次密码不相同
			if(!(pwd2Text.getText().toString()).equals(pwdText.getText().toString())){
				Toast.makeText(getApplicationContext(),"两次密码不相同 请重新输入", Toast.LENGTH_SHORT).show();
			}else{
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
		}
	}
	
	/**
	 * 
	 * 跳转事件处理
	 * @param view
	 */
	public void back(View view){
		//注册界面跳转
		Intent intent = new Intent();
		intent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(intent);
		this.finish();
	}

	/*
	 * 
	 * 获得用户信息
	 * @return 
	 * @see com.example.view.UserView#getUser()
	 */
	@Override
	public User getUser() {
		User user = new User();
		user.setUserId(Integer.parseInt(idText.getText().toString()));
		user.setPassword(pwdText.getText().toString());
		user.setUserName(nameText.getText().toString());
		user.setPhoneno(phoneText.getText().toString());
		return user;
	}
	
	/*
	 * 
	 * 设置用户信息
	 * @param user 
	 * @see com.example.view.UserView#setUser(com.example.model.User)
	 */
	@Override
	public void setUser(User user) {
		
	}
}
