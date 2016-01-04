package com.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 连接服务器辅助类c
 */
public class ConnectUtil {
	/**服务器的地址*/
	private static String URL = "http://192.168.23.1:8080/Tell/TellServlet";
	
	/**
	 * 
	 * 连接服务器并发送请求
	 * @param map
	 * @return 返回的请求数据
	 */
	public static String getResponse(Map<String, String> map){
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		String respHtml = null;
		for (Map.Entry<String, String> entry : map.entrySet()) {  
			NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			list.add(pair);
		}		
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
			HttpPost post = new HttpPost(URL);
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				respHtml = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return respHtml;
	} 

}
