package com.example.tyutapp;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

import TYUT.database.Dbconnetc;
import TYUT.network.ConnectTYUT;
import TYUT.tmp.Tmp;
import TYUTservice.data.MessageLogin;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login_Activity extends Activity {

	private EditText uId;
	private EditText passWd;
	private Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_);
		// 数据库
		Dbconnetc dbconnetc = new Dbconnetc(this);
		SQLiteDatabase database = null;

		database = dbconnetc.getReadableDatabase();
		// 创建USER数据库如果存在则不创建
		dbconnetc.onCreate(database);

		uId = (EditText) findViewById(com.example.tyutapp.R.id.uId);
		passWd = (EditText) findViewById(com.example.tyutapp.R.id.passWd);
		login = (Button) findViewById(R.id.login);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread() {
					public void run() {
						// TODO Auto-generated method stub
						ConnectTYUT connectTYUT = new ConnectTYUT();
						ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
						params.add(new BasicNameValuePair("username", uId
								.getText().toString()));
						params.add(new BasicNameValuePair("password", passWd
								.getText().toString()));
						String loginstatus = connectTYUT.getByPost("http://"
								+ Tmp.getServerIp()
								+ ":8080/TyutAppService/login.action", params);
						if (loginstatus != "") {
							JSONObject jsonObject = null;
							int id=0;
							int status = 0;
							String cookie = "";
							try {
								
								jsonObject = new JSONObject(loginstatus);
								Log.i(jsonObject + "", "登录json接受");
								id=jsonObject.getInt("id");
								Log.i("id",":"+id);
								if(id==1){
								
								status =jsonObject.getInt("status");
								Log.i("status",":"+status);
								cookie=jsonObject.getString("cookie");
								Log.i("cookie",":"+cookie);
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// 登录成功
							if (status == 3) {
								Tmp.setCookies(cookie);
								Intent intent = new Intent(Login_Activity.this,
										MainActivity.class);
								// 启动活动
								startActivity(intent);
							} else if (status == 2) {

								Log.i("失败", "密码错误");
							}

						}
					}
				}.start();
			}
		});

	}
}
