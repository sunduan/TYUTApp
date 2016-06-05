package com.example.tyutapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.adapter.PyfaAdapter;
import TYUT.network.ConnectTYUT;
import TYUT.network.LoginAgain;
import TYUT.tmp.Tmp;
import TYUTservice.data.MessagePyfa;
import TYUTservice.data.msgdata.PyfaMsg;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class Pyfa_Activity extends Activity {
	private ListView main_listview;
	private List<PyfaMsg> pyfaMsgs;
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.obj != null) {
				pyfaMsgs=(List<PyfaMsg>) msg.obj;
				PyfaAdapter adapter=new PyfaAdapter(Pyfa_Activity.this,pyfaMsgs);
				main_listview.setAdapter(adapter);
			}
		}
	};
	private RelativeLayout main_background;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_func);
		main_listview=(ListView)findViewById(R.id.main_listview);
		main_background=(RelativeLayout)findViewById(R.id.main_background);
		
		ActionBar bar = getActionBar();
		main_background.setBackgroundColor(Color.rgb(0xaa, 0x22, 0x00));
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ee2200")));
		setMainView();
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
				MessagePyfa pyfa = new MessagePyfa();
				// 参数对象
				ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
				//params.add(new BasicNameValuePair("address", address));
				// 连接
				String facjStatus = connectTYUT.getByPost(
						"http://" + Tmp.getServerIp() + "/pyfa", params);
				String cookielock=Tmp.getCookies();
				Log.i("facj数据1", facjStatus + " ");
				if(facjStatus.indexOf("响应吗50") != -1) {
					LoginAgain again = new LoginAgain();
					again.loginAgain(Pyfa_Activity.this);
					
					while(Tmp.getCookies()==cookielock){
						
					}
					params.remove(0);
					params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
					facjStatus = connectTYUT.getByPost(
							"http://" + Tmp.getServerIp() + "/pyfa", params);
				}
				
				Log.i("facj数据2", facjStatus + " ");
				// json
				JSONObject jsonObject = null;
				JSONArray key = new JSONArray();
				try {
					jsonObject = new JSONObject(facjStatus);
					status = jsonObject.getInt("status");
					if (status == 0) {

					} else if (status == 3) {
						pyfa.setStatus(status);
						key = jsonObject.getJSONArray("pyfaMsgs");
						int l = key.length();
						// 局部facjmsg
						
						List<PyfaMsg> pyfaMsgs = new ArrayList<PyfaMsg>();
						for (int i = 0; i < l; i++) {
							JSONObject jsonObject2 = key.getJSONObject(i);
							PyfaMsg pyfaMsg = new PyfaMsg();
							if(i==0){
							pyfaMsg.setMon("培养方案完成情况");	
							pyfaMsg.setTue("");
							pyfaMsg.setWed(jsonObject2.getString("Wed"));
							pyfaMsg.setThu(jsonObject2.getString("Tue"));
							pyfaMsg.setFri(jsonObject2.getString("Wed"));
							pyfaMsg.setSat(jsonObject2.getString("Sat"));
							pyfaMsg.setSun(jsonObject2.getString("Sun"));
							}else{
							pyfaMsg.setMon(jsonObject2.getString("Mon"));
							pyfaMsg.setTue(jsonObject2.getString("Tue"));
							pyfaMsg.setWed(jsonObject2.getString("Wed"));
							pyfaMsg.setThu(jsonObject2.getString("Thu"));
							pyfaMsg.setFri(jsonObject2.getString("Fri"));
							pyfaMsg.setSat(jsonObject2.getString("Sat"));
							pyfaMsg.setSun(jsonObject2.getString("Sun"));
							}
							pyfaMsgs.add(pyfaMsg);
							
						}
						
						//facj.setFacjMsgs(facjMsgs);
						message = new Message();
						message.obj =pyfaMsgs;
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
