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
		// ���ݿ�
		Dbconnetc dbconnetc = new Dbconnetc(this);
		final SQLiteDatabase database = dbconnetc.getReadableDatabase();

		// ����USER���ݿ���������򲻴���
		dbconnetc.onCreate(database);
		
		Cursor c = database.query("user",null,null,null,null,null,null);//��ѯ������α�
		if(c.moveToFirst()){//�ж��α��Ƿ�Ϊ��
		    
		        c.move(0);//�ƶ���ָ����¼,ֻȥ��һ��
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
						Log.i(jsonObject + "", "��¼json����");
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
					// ��¼�ɹ�
					if (status == 3) {
						Tmp.setCookies(cookie);
						Intent intent = new Intent(IndexActivity.this,
								MainActivity.class);
						// �����
						startActivity(intent);
						finish();
					} else {
						Intent intent = new Intent(IndexActivity.this,
								Login_Activity.class);
						// �����
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
			// �����
			startActivity(intent);
			finish();
		}
		
	}
}
