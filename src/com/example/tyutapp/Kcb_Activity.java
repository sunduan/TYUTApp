package com.example.tyutapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.network.ConnectTYUT;
import TYUT.network.LoginAgain;
import TYUT.tmp.Tmp;
import TYUTservice.data.MessageFacj;
import TYUTservice.data.msgdata.Course;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Kcb_Activity extends Activity {
	HashMap<String, Integer> co=new HashMap<String,Integer>();
	List<Course>[] courses = new ArrayList[7];
	LinearLayout weekPanels[] = new LinearLayout[7];
	// List courseData[]=new ArrayList[7];
	int itemHeight;
	int marTop, marLeft;

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {

				for (int i = 0; i < 7; i++) {
					initWeekPanel(weekPanels[i], courses[i], i);
				}
				// FacjAdapter adapter=new
				// FacjAdapter(Facj_Activity.this,facjMsgs);
				// main_listview.setAdapter(adapter);
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kcb);

		Tmp.contexts.add(this);
		itemHeight = getResources().getDimensionPixelSize(
				R.dimen.weekItemHeight);
		marTop = getResources().getDimensionPixelSize(R.dimen.weekItemMarTop);
		marLeft = getResources().getDimensionPixelSize(R.dimen.weekItemMarLeft);

		weekPanels[0] = (LinearLayout) findViewById(R.id.weekPanel_1);
		weekPanels[1] = (LinearLayout) findViewById(R.id.weekPanel_2);
		weekPanels[2] = (LinearLayout) findViewById(R.id.weekPanel_3);
		weekPanels[3] = (LinearLayout) findViewById(R.id.weekPanel_4);
		weekPanels[4] = (LinearLayout) findViewById(R.id.weekPanel_5);
		weekPanels[5] = (LinearLayout) findViewById(R.id.weekPanel_6);
		weekPanels[6] = (LinearLayout) findViewById(R.id.weekPanel_7);

		setMainView();
	}

	@SuppressLint("NewApi")
	public void initWeekPanel(LinearLayout ll, List<Course> data, int m) {
		if (ll == null || data == null || data.size() < 1)
			return;
		int re[] = {1, 2, 3, 4, 5,6}; 
		/*if (m == 1) {
			
			re[0]=Color.rgb(0xad, 0x82, 0xdd);
			re[1]=Color.rgb(0x66, 0xcc, 0x00);
			re[2]=Color.rgb(0xcc, 0x00, 0x00);
			re[3]=Color.rgb(0x62, 0x92, 0xe4);
			re[4]=Color.rgb(0xaa, 0xc5, 0xf0);
			re[5]=Color.rgb(0xff, 0x87, 0x0f);
		} else if (m % 2 == 0) {
			re[5]=Color.rgb(0xad, 0x82, 0xdd);
			re[4]=Color.rgb(0x66, 0xcc, 0x00);
			re[3]=Color.rgb(0xcc, 0x00, 0x00);
			re[2]=Color.rgb(0x62, 0x92, 0xe4);
			re[1]=Color.rgb(0xaa, 0xc5, 0xf0);
			re[0]=Color.rgb(0xff, 0x87, 0x0f);
		} else {
			re[0]=Color.rgb(0xad, 0x82, 0xdd);
			re[1]=Color.rgb(0x66, 0xcc, 0x00);
			re[2]=Color.rgb(0xcc, 0x00, 0x00);
			re[3]=Color.rgb(0x62, 0x92, 0xe4);
			re[4]=Color.rgb(0xaa, 0xc5, 0xf0);
			re[5]=Color.rgb(0xff, 0x87, 0x0f);
		}*/
		re[0]=Color.rgb(0xad, 0x82, 0xdd);
		re[1]=Color.rgb(0x66, 0xcc, 0x00);
		re[2]=Color.rgb(0xcc, 0x00, 0x00);
		re[3]=Color.rgb(0x62, 0x92, 0xe4);
		re[4]=Color.rgb(0xaa, 0xc5, 0xf0);
		re[5]=Color.rgb(0xff, 0x87, 0x0f);
		Log.i("Msg", "初始化面板");
		Course pre = data.get(0);
		for (int i = 0; i < data.size(); i++) {
			Course c = data.get(i);
			TextView tv = new TextView(this);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT, itemHeight
							* c.getStep() + marTop * (c.getStep() - 1));
			if (i > 0) {
				lp.setMargins(marLeft,
						(c.getStart() - (pre.getStart() + pre.getStep()))
								* (itemHeight + marTop) + marTop, 0, 0);
			} else {
				lp.setMargins(marLeft, (c.getStart() - 1)
						* (itemHeight + marTop) + marTop, 0, 0);
			}
			if(co.isEmpty()){
				co.put(c.getName(), re[0]);
			}else{
				if(co.get(c.getName())==null){
					co.put(c.getName(), re[co.size()%6]);
				}
			}
			tv.setLayoutParams(lp);
			tv.setGravity(Gravity.TOP);
			tv.setGravity(Gravity.CENTER_HORIZONTAL);
			tv.setTextSize(12);
			tv.setTextColor(getResources().getColor(R.color.courseTextColor));
			tv.setText(c.getName() + "\n");
			tv.setBackgroundColor(co.get(c.getName()));
			//tv.setBackground(getResources().getDrawable(R.drawable.gerenguanli));
			ll.addView(tv);
			pre = c;
		}
	}

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
				MessageFacj facj = new MessageFacj();
				// 参数对象
				ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));

				// 连接
				String facjStatus = connectTYUT.getByPost(
						"http://" + Tmp.getServerIp() + "/kccx", params);
				String cookielock = Tmp.getCookies();
				Log.i("facj数据1", facjStatus + " ");
				try {
					if (facjStatus.indexOf("响应吗50") != -1||(new JSONObject(facjStatus).getInt("status"))==2) {
						LoginAgain again = new LoginAgain();
						again.loginAgain(Kcb_Activity.this);
						
						while (Tmp.getCookies() == cookielock) {

						}
						params.remove(0);
						params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
						facjStatus = connectTYUT.getByPost(
								"http://" + Tmp.getServerIp() + "/kccx", params);
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Log.i("facj数据2", facjStatus + " ");
				// json
				JSONObject jsonObject = null;
				JSONObject key = new JSONObject();
				try {
					jsonObject = new JSONObject(facjStatus);
					status = jsonObject.getInt("status");
					if (status == 0) {

					} else if (status == 3) {
						facj.setStatus(status);
						key = jsonObject.getJSONObject("kccxMsgs");
						int l = key.length();

						for (int i = 0; i < l; i++) {
							JSONArray jsonObject2 = key.getJSONArray(i + "");
							List<Course> course = new ArrayList<Course>();
							int ll = jsonObject2.length();
							for (int j = 0; j < ll; j++) {
								JSONObject json = jsonObject2.getJSONObject(j);
								course.add(new Course(json.getString("name"),
										Integer.parseInt(json
												.getString("start")), Integer
												.parseInt(json
														.getString("step"))));

							}
							courses[i] = course;
						}

						// facj.setFacjMsgs(facjMsgs);
						message = new Message();
						message.what = 1;
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

}
