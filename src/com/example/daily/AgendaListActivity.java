package com.example.daily;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AgendaListActivity extends Activity {

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
	}
}
