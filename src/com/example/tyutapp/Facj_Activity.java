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

		// �޸ı�������ɫ
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ad82dd")));

		facjlist = (ListView) findViewById(R.id.facjlist);
		main_listview=(ListView)findViewById(R.id.main_listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData());

		facjlist.setAdapter(adapter);
		
		// �¼�����
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
				//��Ϣ
				Message message = null;
				// ���Ӷ���
				ConnectTYUT connectTYUT = new ConnectTYUT();
				// ���ݽ��ն���
				int id = 0;
				int status = 0;
				MessageFacj facj = new MessageFacj();
				// ��������
				ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
				params.add(new BasicNameValuePair("address", address));
				// ����
				String facjStatus = connectTYUT.getByPost(
						"http://" + Tmp.getServerIp() + "/facj", params);
				String cookielock=Tmp.getCookies();
				Log.i("facj����1", facjStatus + " ");
				if(facjStatus.indexOf("��Ӧ��50") != -1) {
					LoginAgain again = new LoginAgain();
					again.loginAgain();
					facjStatus = connectTYUT.getByPost(
							"http://" + Tmp.getServerIp() + "/facj", params);
					while(Tmp.getCookies()==cookielock){
						
					}
				}
				
				Log.i("facj����2", facjStatus + " ");
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
						// �ֲ�facjmsg
						
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
				//Log.i("msg����",facj.getFacjMsgs().size()+" ");

			}
		}.start();
	}

	public List<String> getData() {

		List<String> data = new ArrayList<String>();
		data.add("������");
		data.add("��        ѡ");
		data.add("�ۺϱ���");
		data.add("רҵ����");
		data.add("ѧ�Ʊ���");
		data.add("רҵѡ��");
		data.add("ѧ��ѡ��");
		data.add("ʵ������");
		data.add("�ۺ���ѡ");
		data.add("��������");
		data.add("�в�����");
		data.add("��ѧ�ڳɼ���ѯ");
		data.add("����ʵ��");
		return data;

		/*
		 * 
		 * String[] names = { "��  ��    ��  ��", "�γ����Գɼ�", "�� �� �� �� ��",
		 * "�� ѧ �� �� ��", "����ʵ���ɼ�" };
		 * 
		 * String[][] childnames = { { "������", "", "", "", "", "", "", "" }, {
		 * "��        ѡ", "�ۺϱ���", "רҵ����", "ѧ�Ʊ���", "רҵѡ��", "ѧ��ѡ��", "ʵ������", "�ۺ���ѡ"
		 * },
		 * 
		 * { "��������", "�в�����", "", "", "", "", "", "" }, { "��ѧ�ڳɼ���ѯ", "", "", "",
		 * "", "", "", "" }, { "����ʵ��", "", "", "", "", "", "", "" } }; group =
		 * new ArrayList<Map<String, String>>(); // �������б���List���ݼ��� ss = new
		 * ArrayList<List<Map<String, String>>>(); for (int i = 0; i <
		 * names.length; i++) { // �ṩ���б������ Map<String, String> maps = new
		 * HashMap<String, String>(); // maps.put("images", images[i]);
		 * maps.put("names", names[i]); group.add(maps); // �ṩ��ǰ���е���������
		 * List<Map<String, String>> child = new ArrayList<Map<String,
		 * String>>(); for (int j = 0; j < 8; j++) { Map<String, String> mapsj =
		 * new HashMap<String, String>(); if (childnames[i][j] != "") {
		 * mapsj.put("tengxun", childnames[i][j]); child.add(mapsj); } }
		 * ss.add(child); }
		 */

	}
}
