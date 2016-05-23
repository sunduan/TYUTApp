package com.example.tyutapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.adapter.FacjAdapter;
import TYUT.network.ConnectTYUT;
import TYUT.network.LoginAgain;
import TYUT.tmp.Tmp;
import TYUTservice.data.MessageFacj;
import TYUTservice.data.msgdata.FacjMsg;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Facj_Activity extends Activity {
	private ListView facjlist;
	private ListView main_listview;
	private List<FacjMsg> facjMsgs;
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.obj != null) {
				facjMsgs=(List<FacjMsg>) msg.obj;
				FacjAdapter adapter=new FacjAdapter(Facj_Activity.this,facjMsgs);
				main_listview.setAdapter(adapter);
			}
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bs_main);

		// 修改标题栏颜色
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ad82dd")));

		facjlist = (ListView) findViewById(R.id.facjlist);
		main_listview=(ListView)findViewById(R.id.main_listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData());

		facjlist.setAdapter(adapter);
		
		// 事件监听
		facjlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					setMainView("1");
					break;
				case 1:
					setMainView("2");
					break;
				case 2:
					setMainView("3");
					break;
				case 3:
					setMainView("4");
					break;
				case 4:
					setMainView("5");
					break;
				case 5:
					setMainView("6");
					break;
				case 6:
					setMainView("7");
					break;
				case 7:
					setMainView("8");
					break;
				case 8:
					setMainView("9");
					break;
				case 9:
					setMainView("10");
					break;
				case 10:
					setMainView("11");
					break;
				case 11:
					setMainView("12");
					break;
				case 12:
					setMainView("13");
					break;
				default:
					break;
				}
			}
		});
	}

	public void setMainView(String md) {
		final String address = md;
		new Thread() {
			public void run() {
				//消息
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
				params.add(new BasicNameValuePair("address", address));
				// 连接
				String facjStatus = connectTYUT.getByPost(
						"http://" + Tmp.getServerIp() + "/facj", params);
				String cookielock=Tmp.getCookies();
				Log.i("facj数据1", facjStatus + " ");
				if(facjStatus.indexOf("响应吗50") != -1) {
					LoginAgain again = new LoginAgain();
					again.loginAgain();
					facjStatus = connectTYUT.getByPost(
							"http://" + Tmp.getServerIp() + "/facj", params);
					while(Tmp.getCookies()==cookielock){
						
					}
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
						facj.setStatus(status);
						key = jsonObject.getJSONArray("facjMsgs");
						int l = key.length();
						// 局部facjmsg
						
						List<FacjMsg> facjMsgs = new ArrayList<FacjMsg>();
						for (int i = 0; i < l; i++) {
							JSONObject jsonObject2 = key.getJSONObject(i);
							FacjMsg facjMsg = new FacjMsg();
							facjMsg.setKch(jsonObject2.getString("kch"));
							facjMsg.setKxh(jsonObject2.getString("kxh"));
							facjMsg.setKcm(jsonObject2.getString("kcm"));
							facjMsg.setYwkcm(jsonObject2.getString("ywkcm"));
							facjMsg.setXf(jsonObject2.getString("xf"));
							facjMsg.setKcsx(jsonObject2.getString("kcsx"));
							facjMsg.setCj(jsonObject2.getString("cj"));
							facjMsg.setWtgyy(jsonObject2.getString("wtgyy"));
							facjMsgs.add(facjMsg);
							
						}
						
						//facj.setFacjMsgs(facjMsgs);
						message = new Message();
						message.obj =facjMsgs;
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

	public List<String> getData() {

		List<String> data = new ArrayList<String>();
		data.add("软工方案");
		data.add("任        选");
		data.add("综合必修");
		data.add("专业必修");
		data.add("学科必修");
		data.add("专业选修");
		data.add("学科选修");
		data.add("实践环节");
		data.add("综合任选");
		data.add("曾不及格");
		data.add("尚不及格");
		data.add("本学期成绩查询");
		data.add("自主实践");
		return data;

		/*
		 * 
		 * String[] names = { "方  案    成  绩", "课程属性成绩", "不 及 格 成 绩",
		 * "本 学 期 成 绩", "自主实践成绩" };
		 * 
		 * String[][] childnames = { { "软工方案", "", "", "", "", "", "", "" }, {
		 * "任        选", "综合必修", "专业必修", "学科必修", "专业选修", "学科选修", "实践环节", "综合任选"
		 * },
		 * 
		 * { "曾不及格", "尚不及格", "", "", "", "", "", "" }, { "本学期成绩查询", "", "", "",
		 * "", "", "", "" }, { "自主实践", "", "", "", "", "", "", "" } }; group =
		 * new ArrayList<Map<String, String>>(); // 定义子列表项List数据集合 ss = new
		 * ArrayList<List<Map<String, String>>>(); for (int i = 0; i <
		 * names.length; i++) { // 提供父列表的数据 Map<String, String> maps = new
		 * HashMap<String, String>(); // maps.put("images", images[i]);
		 * maps.put("names", names[i]); group.add(maps); // 提供当前父列的子列数据
		 * List<Map<String, String>> child = new ArrayList<Map<String,
		 * String>>(); for (int j = 0; j < 8; j++) { Map<String, String> mapsj =
		 * new HashMap<String, String>(); if (childnames[i][j] != "") {
		 * mapsj.put("tengxun", childnames[i][j]); child.add(mapsj); } }
		 * ss.add(child); }
		 */

	}
}
