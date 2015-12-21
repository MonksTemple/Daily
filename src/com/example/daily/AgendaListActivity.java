package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class AgendaListActivity extends Activity {
	private PopupMenu popupMenu;  
	private Menu menu; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda_list);
		

		ListView list = (ListView) findViewById(R.id.classListView); 

		//���ɶ�̬���飬����ת������  
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();  

		HashMap<String, String> map1 = new HashMap<String, String>(); 
		HashMap<String, String> map2 = new HashMap<String, String>();  
		map1.put("ItemTitle", "AAAAA");  
		map1.put("ItemText", "bbbbbb");  
		map2.put("ItemTitle", "CCCCC");  
		map2.put("ItemText", "ddddd");  
		mylist.add(map1);  
		mylist.add(map2);
		//����������������===��ListItem  
		SimpleAdapter mSchedule = new SimpleAdapter(this,  
				mylist,//������Դ   
				R.layout.agenda_list,//ListItem��XMLʵ��  

				//��̬������ListItem��Ӧ������          
				new String[] {"ItemTitle", "ItemText"},   

				//ListItem��XML�ļ����������TextView ID  
				new int[] {R.id.ItemTitle,R.id.ItemText});  
		//��Ӳ�����ʾ  
		list.setAdapter(mSchedule); 
		
		
		popupMenu = new PopupMenu(this, findViewById(R.id.lines));  
	    menu = popupMenu.getMenu(); 
	    
	 //通过XML导入菜单栏
	    MenuInflater menuInflater = getMenuInflater();  
	    menuInflater.inflate(R.menu.agenda_list_menu, menu); 
	   
	    // 设置监听事件
	    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {  

	    	@Override  
	    	public boolean onMenuItemClick(MenuItem item) {  
	    		switch (item.getItemId()) {  
	    		case R.id.create:  
	    			Toast.makeText(AgendaListActivity.this, "创建日程",  
	    					Toast.LENGTH_LONG).show();  
	    			break;   
	    		default:  
	    			break;  
	    		}  
	    		return false;  
	    	}  
	    });  
	}

	public void popupmenu(View v) {  
	    popupMenu.show();  
	}  
	
	
}
