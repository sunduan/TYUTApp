/*package com.example.tyutapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.adapter.KccxAdapter;
import TYUT.adapter.PyfaAdapter;
import TYUT.network.ConnectTYUT;
import TYUT.network.LoginAgain;
import TYUT.tmp.Tmp;
import TYUTservice.data.MessageKccx;
import TYUTservice.data.MessagePyfa;
import TYUTservice.data.msgdata.KccxMsg;
import TYUTservice.data.msgdata.PyfaMsg;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class KccxActivity extends Activity {
	private ListView main_listview;
	private List<KccxMsg> kccxMsgs;
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.obj != null) {
				kccxMsgs = (List<KccxMsg>) msg.obj;
				KccxAdapter adapter = new KccxAdapter(KccxActivity.this,
						kccxMsgs);
				main_listview.setAdapter(adapter);
			}
		}
	};

	public void setMainView() {

		new Thread() {
			public void run() {
				// 消息
				Message message = null;
				// 连接对象
				ConnectTYUT connectTYUT = new ConnectTYUT();
				// 数据接收对象
				int id = 0;
				int status = 0;
				MessageKccx kccx = new MessageKccx();
				// 参数对象
				ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
				// params.add(new BasicNameValuePair("address", address));
				// 连接
				String kccxStatus = connectTYUT.getByPost(
						"http://" + Tmp.getServerIp() + "/kccx", params);
				String cookielock = Tmp.getCookies();
				Log.i("facj数据1", kccxStatus + " ");
				if (kccxStatus.indexOf("响应吗50") != -1) {
					LoginAgain again = new LoginAgain();
					again.loginAgain();
					kccxStatus = connectTYUT.getByPost(
							"http://" + Tmp.getServerIp() + "/kccx", params);
					while (Tmp.getCookies() == cookielock) {

					}
				}

				Log.i("facj数据2", kccxStatus + " ");
				// json
				JSONObject jsonObject = null;
				JSONArray key = new JSONArray();
				try {
					jsonObject = new JSONObject(kccxStatus);
					status = jsonObject.getInt("status");
					if (status == 0) {

					} else if (status == 3) {
						kccx.setStatus(status);
						key = jsonObject.getJSONArray("kccxMsgs");
						int l = key.length();
						// 局部facjmsg

						List<KccxMsg> kccxMsgs = new ArrayList<KccxMsg>();
						KccxMsg kccxMsg1=new KccxMsg();
						kccxMsg1.setCweek("周一");
						KccxMsg kccxMsg2=new KccxMsg();
						kccxMsg2.setCweek("周二");
						KccxMsg kccxMsg3=new KccxMsg();
						kccxMsg3.setCweek("周三");
						KccxMsg kccxMsg4=new KccxMsg();
						kccxMsg4.setCweek("周四");
						KccxMsg kccxMsg5=new KccxMsg();
						kccxMsg5.setCweek("周五");
						KccxMsg kccxMsg6=new KccxMsg();
						kccxMsg6.setCweek("周六");
						KccxMsg kccxMsg7=new KccxMsg();
						kccxMsg7.setCweek("周日");
						for (int i = 0; i < l; i++) {
							JSONObject jsonObject2 = key.getJSONObject(i);
							switch (i) {
							case 0:
								kccxMsg1.setCone(jsonObject2.getString("Mon"));
								kccxMsg2.setCone(jsonObject2.getString("Tue"));
								kccxMsg3.setCone(jsonObject2.getString("Wed"));
								kccxMsg4.setCone(jsonObject2.getString("Thu"));
								kccxMsg5.setCone(jsonObject2.getString("Fri"));
								kccxMsg6.setCone(jsonObject2.getString("Sat"));
								kccxMsg7.setCone(jsonObject2.getString("Sun"));
								break;
							case 2:
								kccxMsg1.setCtwo(jsonObject2.getString("Mon"));
								kccxMsg2.setCtwo(jsonObject2.getString("Tue"));
								kccxMsg3.setCtwo(jsonObject2.getString("Wed"));
								kccxMsg4.setCtwo(jsonObject2.getString("Thu"));
								kccxMsg5.setCtwo(jsonObject2.getString("Fri"));
								kccxMsg6.setCtwo(jsonObject2.getString("Sat"));
								kccxMsg7.setCtwo(jsonObject2.getString("Sun"));
								break;
							case 4:
								kccxMsg1.setCthree(jsonObject2.getString("Mon"));
								kccxMsg2.setCthree(jsonObject2.getString("Tue"));
								kccxMsg3.setCthree(jsonObject2.getString("Wed"));
								kccxMsg4.setCthree(jsonObject2.getString("Thu"));
								kccxMsg5.setCthree(jsonObject2.getString("Fri"));
								kccxMsg6.setCthree(jsonObject2.getString("Sat"));
								kccxMsg7.setCthree(jsonObject2.getString("Sun"));
								break;
							case 6:
								kccxMsg1.setCfour(jsonObject2.getString("Mon"));
								kccxMsg2.setCfour(jsonObject2.getString("Tue"));
								kccxMsg3.setCfour(jsonObject2.getString("Wed"));
								kccxMsg4.setCfour(jsonObject2.getString("Thu"));
								kccxMsg5.setCfour(jsonObject2.getString("Fri"));
								kccxMsg6.setCfour(jsonObject2.getString("Sat"));
								kccxMsg7.setCfour(jsonObject2.getString("Sun"));
								break;
							case 8:
								kccxMsg1.setCfive(jsonObject2.getString("Mon"));
								kccxMsg2.setCfive(jsonObject2.getString("Tue"));
								kccxMsg3.setCfive(jsonObject2.getString("Wed"));
								kccxMsg4.setCfive(jsonObject2.getString("Thu"));
								kccxMsg5.setCfive(jsonObject2.getString("Fri"));
								kccxMsg6.setCfive(jsonObject2.getString("Sat"));
								kccxMsg7.setCfive(jsonObject2.getString("Sun"));
								break;
	
							default:
								break;
							}
							
							
							

						}
						kccxMsgs.add(kccxMsg1);
						kccxMsgs.add(kccxMsg2);
						kccxMsgs.add(kccxMsg3);
						kccxMsgs.add(kccxMsg4);
						kccxMsgs.add(kccxMsg5);
						kccxMsgs.add(kccxMsg6);
						kccxMsgs.add(kccxMsg7);
						
						// facj.setFacjMsgs(facjMsgs);
						message = new Message();
						message.obj = kccxMsgs;
						myHandler.sendMessage(message);

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Log.i("msg个数",facj.getFacjMsgs().size()+" ");

			}
		}.start();
	}
	private RelativeLayout main_background;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_func);
		main_listview=(ListView)findViewById(R.id.main_listview);
		main_background=(RelativeLayout)findViewById(R.id.main_background);
		ActionBar bar = getActionBar();
		main_background.setBackgroundColor(Color.rgb(0x66, 0xaa, 0x00));
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#66ee00")));
		setMainView();
	}
}
*/