package com.example.tyutapp;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.database.Dbconnetc;
import TYUT.network.ConnectTYUT;
import TYUT.tmp.Tmp;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

public class IndexActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		// 数据库
		Dbconnetc dbconnetc = new Dbconnetc(this);
		final SQLiteDatabase database = dbconnetc.getReadableDatabase();

		// 创建USER数据库如果存在则不创建
		dbconnetc.onCreate(database);
		
		Cursor c = database.query("user",null,null,null,null,null,null);//查询并获得游标
		if(c.moveToFirst()){//判断游标是否为空
		    
		        c.move(0);//移动到指定记录,只去第一条
		        final String username = c.getString(c.getColumnIndex("username"));
		        final String password = c.getString(c.getColumnIndex("password"));
		    
		        new Thread() {
					public void run() {
						// TODO Auto-generated method stub
		        ConnectTYUT connectTYUT = new ConnectTYUT();
		        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("username", username));
				params.add(new BasicNameValuePair("password", password));
				String loginstatus = "";
				
					loginstatus = connectTYUT.getByPost(
							"http://" + Tmp.getServerIp()
									+ "/login.action", params);

				if (loginstatus != "") {
					JSONObject jsonObject = null;
					int id = 0;
					int status = 0;
					String cookie = "";
					try {

						jsonObject = new JSONObject(loginstatus);
						Log.i(jsonObject + "", "登录json接受");
						id = jsonObject.getInt("id");
						Log.i("id", ":" + id);
						if (id == 1) {

							status = jsonObject.getInt("status");
							Log.i("status", ":" + status);
							cookie = jsonObject.getString("cookie");
							Log.i("cookie", ":" + cookie);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 登录成功
					if (status == 3) {
						Tmp.setCookies(cookie);
						Intent intent = new Intent(IndexActivity.this,
								MainActivity.class);
						// 启动活动
						startActivity(intent);
						finish();
					} else {
						Intent intent = new Intent(IndexActivity.this,
								Login_Activity.class);
						// 启动活动
						startActivity(intent);
						finish();
					}
				
				}
					}
		        }.start();
		        
		}else{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent intent = new Intent(IndexActivity.this,
					Login_Activity.class);
			// 启动活动
			startActivity(intent);
			finish();
		}
		
	}
}
