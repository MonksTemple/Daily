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
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements UserView{
	private EditText nameText;
	private EditText pwdText;
	private EditText pwd2Text;
	private EditText snoText;
	
	private UserManage userManage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		nameText = (EditText) findViewById(R.id.nameText);
		pwdText = (EditText) findViewById(R.id.passwordText);
		pwd2Text = (EditText) findViewById(R.id.password2);
		snoText =  (EditText) findViewById(R.id.snoNum);
			
	}
	
	public void register(View view){
		if(nameText.getText().toString() == null || nameText.getText().toString().length() <= 0 
			|| pwdText.getText().toString() == null || pwdText.getText().toString().length() <= 0 
			|| pwd2Text.getText().toString() == null || pwd2Text.getText().toString().length() <= 0 
			|| snoText.getText().toString() == null || snoText.getText().toString().length() <= 0){
			Toast.makeText(getApplicationContext(),"请完整填写信息", Toast.LENGTH_SHORT).show();
		}else{
			User user = new User();
			user.setUserName(nameText.getText().toString());
			user.setPassword(pwdText.getText().toString());
			
			if(userManage.register(user)){
				Intent intent = new Intent();
				intent = new Intent(RegisterActivity.this, LoginActivity.class);
				startActivity(intent);
				RegisterActivity.this.finish();
			}else{
				Toast.makeText(getApplicationContext(),"信息错误", Toast.LENGTH_SHORT).show();
			}
			
			
		}
	}
	
	void back(View view){
		
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
