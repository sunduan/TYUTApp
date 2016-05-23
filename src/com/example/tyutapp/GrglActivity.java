package com.example.tyutapp;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import vo.StudentInfo;
import TYUT.network.ConnectTYUT;
import TYUT.network.LoginAgain;
import TYUT.tmp.Tmp;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GrglActivity extends Activity {
	private Button bexit;
	private TextView name;
	private TextView userper;
	private TextView tel;
	private TextView address;
	private TextView email;
	private StudentInfo info;
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.obj != null) {
				info = (StudentInfo) msg.obj;
				name.setText(info.getName());
				userper.setText(info.getUserper());
				tel.setText(info.getTel());
				address.setText(info.getAddress());
				email.setText(info.getEmail());
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grgl);
		
		name=(TextView)findViewById(R.id.grgl_name);
		userper=(TextView)findViewById(R.id.grgl_userper);
		tel=(TextView)findViewById(R.id.grgl_tel);
		address=(TextView)findViewById(R.id.grgl_address);
		email=(TextView)findViewById(R.id.grgl_email);
		bexit=(Button)findViewById(R.id.bexit);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#333333")));
		setMainView();
		
		bexit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	public void setMainView() {
		
		new Thread() {
			public void run() {
				//消息
				Message message = null;
				// 连接对象
				ConnectTYUT connectTYUT = new ConnectTYUT();
				// 数据接收对象
				int id = 0;
				int status = 0;
				StudentInfo info = new StudentInfo();
				// 参数对象
				ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
				//params.add(new BasicNameValuePair("address", address));
				// 连接
				String grglStatus = connectTYUT.getByPost(
						"http://" + Tmp.getServerIp() + "/grxx", params);
				String cookielock=Tmp.getCookies();
				Log.i("facj数据1", grglStatus + " ");
				if(grglStatus.indexOf("响应吗50") != -1) {
					LoginAgain again = new LoginAgain();
					again.loginAgain();
					grglStatus = connectTYUT.getByPost(
							"http://" + Tmp.getServerIp() + "/grxx", params);
					while(Tmp.getCookies()==cookielock){
						
					}
				}
				
				Log.i("facj数据2", grglStatus + " ");
				// json
				JSONObject jsonObject = null;
				JSONObject key = new JSONObject();
				try {
					jsonObject = new JSONObject(grglStatus);
					status = jsonObject.getInt("status");
					if (status == 0) {

					} else if (status == 3) {
						
						key = jsonObject.getJSONObject("grxx");
						
						// 局部facjmsg
						
						info.setName(key.getString("name"));
						info.setUserper(key.getString("userper"));
						info.setTel(key.getString("tel"));
						info.setAddress(key.getString("address"));
						info.setEmail(key.getString("email"));
						
						//facj.setFacjMsgs(facjMsgs);
						message = new Message();
						message.obj =info;
						myHandler.sendMessage(message);
						
						
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Log.i("msg个数",facj.getFacjMsgs().size()+" ");

			}
		}.start();
	}
}
