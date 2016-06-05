package com.example.tyutapp;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.database.Dbconnetc;
import TYUT.network.ConnectTYUT;
import TYUT.tmp.Tmp;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login_Activity extends Activity {

	private EditText uId;
	private EditText passWd;
	private Button login;
	private boolean send = false;
	private Dialog dialog;
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				dialog.show();
			} else if (msg.what == 2) {
				dialog.dismiss();
				uId.setBackgroundResource(R.drawable.shape_error);
				passWd.setBackgroundResource(R.drawable.shape_error);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_);

		Tmp.contexts.add(this);
		// 数据库
		Dbconnetc dbconnetc = new Dbconnetc(this);
		final SQLiteDatabase database = dbconnetc.getReadableDatabase();

		// 创建USER数据库如果存在则不创建
		dbconnetc.onCreate(database);

		uId = (EditText) findViewById(com.example.tyutapp.R.id.uId);
		passWd = (EditText) findViewById(com.example.tyutapp.R.id.passWd);
		login = (Button) findViewById(R.id.login);

		dialog = new Dialog(this) {
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.dialog_loading);
			}
		};
		dialog.setCanceledOnTouchOutside(false);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread() {
					public void run() {
						// TODO Auto-generated method stub
						Message message = null;
						ConnectTYUT connectTYUT = new ConnectTYUT();
						ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
						params.add(new BasicNameValuePair("username", uId
								.getText().toString()));
						params.add(new BasicNameValuePair("password", passWd
								.getText().toString()));
						String loginstatus = "";
						if (!send) {
							// 加锁
							send = true;
							// 连接服务器状态：1开启转圈
							message = new Message();
							message.what = 1;
							myHandler.sendMessage(message);

							loginstatus = connectTYUT.getByPost(
									"http://" + Tmp.getServerIp()
											+ "/login.action", params);

						} else {
							return;
						}
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
								Tmp.setUsername(uId.getText().toString());
								Tmp.setPassword(passWd.getText().toString());
								Intent intent = new Intent(Login_Activity.this,
										MainActivity.class);
								// 启动活动
								startActivity(intent);

								// 先清空数据
								String sql = "delete from user";// 删除操作的SQL语句
								database.execSQL(sql);// 执行删除操作

								ContentValues cv = new ContentValues();// 实例化一个ContentValues用来装载待插入的数据
								cv.put("username", uId.getText().toString());// 添加用户名
								cv.put("password", passWd.getText().toString()); // 添加密码
								database.insert("user", null, cv);// 执行插入操作

								finish();
							} else {
								message = new Message();
								// 密码错误编号：2
								message.what = 2;
								myHandler.sendMessage(message);
								Log.i("失败", "密码错误");
							}
							// 解锁
							send = false;
						}
					}
				}.start();
			}
		});

	}
}
