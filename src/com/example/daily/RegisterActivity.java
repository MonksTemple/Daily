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

public class RegisterActivity extends Activity implements UserView{
	private EditText nameText;
	private EditText pwdText;
	private EditText pwd2Text;
	private EditText snoText;

	private UserManage userManage;
	
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		nameText = (EditText) findViewById(R.id.nameText);
		pwdText = (EditText) findViewById(R.id.passwordText);
		pwd2Text = (EditText) findViewById(R.id.password2);
		snoText =  (EditText) findViewById(R.id.snoNum);
<<<<<<< HEAD

=======
			
		userManage = new UserManage(this);
>>>>>>> 89131b995df200660be7bd1319a7a6174d1a2ad1
	}

	public void register(View view){
		if(nameText.getText().toString() == null || nameText.getText().toString().length() <= 0 
				|| pwdText.getText().toString() == null || pwdText.getText().toString().length() <= 0 
				|| pwd2Text.getText().toString() == null || pwd2Text.getText().toString().length() <= 0 
				|| snoText.getText().toString() == null || snoText.getText().toString().length() <= 0){
			Toast.makeText(getApplicationContext(),"请完整填写信息", Toast.LENGTH_SHORT).show();
		}else{
<<<<<<< HEAD
			User user = new User();
			user.setUserName(nameText.getText().toString());
			user.setPassword(pwdText.getText().toString());

			if(userManage.register(user)){
				Intent intent = new Intent();
				intent = new Intent(RegisterActivity.this, LoginActivity.class);
				startActivity(intent);
				RegisterActivity.this.finish();
=======
			//若两次密码不相同
			if(!(pwd2Text.getText().toString()).equals(pwdText.getText().toString())){
				Toast.makeText(getApplicationContext(),"两次密码不相同 请重新输入", Toast.LENGTH_SHORT).show();
>>>>>>> 89131b995df200660be7bd1319a7a6174d1a2ad1
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
<<<<<<< HEAD
=======
<<<<<<< HEAD


=======
			
			
			
//			if(userManage.register(user)){
//				Intent intent = new Intent();
//				intent = new Intent(RegisterActivity.this, LoginActivity.class);
//				startActivity(intent);
//				RegisterActivity.this.finish();
//			}else{
//				Toast.makeText(getApplicationContext(),"信息错误", Toast.LENGTH_SHORT).show();
//			}
			
			
>>>>>>> 89131b995df200660be7bd1319a7a6174d1a2ad1
>>>>>>> 76ea4ad6bc350410ef0a00d233e303f210b9f23d
		}
	}

	public void back(View view){
		//注册界面跳转
		Intent intent = new Intent();
		intent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(intent);
		RegisterActivity.this.finish();
	}

	@Override
	public User getUser() {
		User user = new User();
		user.setUserId(Integer.parseInt(nameText.getText().toString()));
		user.setPassword(pwdText.getText().toString());
		return user;
	}

	@Override
	public void setUser(User user) {
<<<<<<< HEAD
		
=======
		// TODO Auto-generated method stub

>>>>>>> 76ea4ad6bc350410ef0a00d233e303f210b9f23d
	}
}
