package com.example.tyutapp;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import vo.StudentInfo;
import vo.StudentStatus;
import TYUT.network.ConnectTYUT;
import TYUT.network.LoginAgain;
import TYUT.tmp.Tmp;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class XjxxActivity extends Activity {
	private TextView xjxx_idstudent;
	private TextView xjxx_name;
	private TextView xjxx_idcard;
	private TextView xjxx_sex;
	private TextView xjxx_mz;
	private TextView xjxx_jg;
	private TextView xjxx_csrq;
	private TextView xjxx_zz;
	private TextView xjxx_rxrq;
	private TextView xjxx_xs;
	private TextView xjxx_zy;
	private TextView xjxx_nj;
	private TextView xjxx_bj;
	private TextView xjxx_sfxj;
	private TextView xjxx_gjxj;
	private TextView xjxx_xq;
	private TextView xjxx_yd;

	private StudentStatus info;
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.obj != null) {
				info = (StudentStatus) msg.obj;
				xjxx_idstudent.setText(info.getIdstudent());
				xjxx_name.setText(info.getName());
				xjxx_idcard.setText(info.getIdcard());
				xjxx_sex.setText(info.getSex());
				xjxx_mz.setText(info.getMz());
				xjxx_jg.setText(info.getJg());
				xjxx_csrq.setText(info.getCsrq());
				xjxx_zz.setText(info.getZz());
				xjxx_rxrq.setText(info.getRxrq());
				xjxx_xs.setText(info.getXs());
				xjxx_zy.setText(info.getZy());
				xjxx_nj.setText(info.getNj());
				xjxx_bj.setText(info.getBj());
				xjxx_sfxj.setText(info.getSfxj());
				xjxx_gjxj.setText(info.getGjxj());
				xjxx_xq.setText(info.getXq());
				xjxx_yd.setText(info.getYd());
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xjxx);
		Tmp.contexts.add(this);
		xjxx_idstudent = (TextView) findViewById(R.id.xjxx_idstudent);
		xjxx_name = (TextView) findViewById

				
		(R.id.xjxx_name);
		xjxx_idcard = (TextView) findViewById

		(R.id.xjxx_idcard);
		xjxx_sex = (TextView) findViewById(R.id.xjxx_sex);
		xjxx_mz = (TextView) findViewById(R.id.xjxx_mz);
		xjxx_jg = (TextView) findViewById(R.id.xjxx_jg);
		xjxx_csrq = (TextView) findViewById

		(R.id.xjxx_csrq);
		xjxx_zz = (TextView) findViewById(R.id.xjxx_zz);
		xjxx_rxrq = (TextView) findViewById

		(R.id.xjxx_rxrq);
		xjxx_xs = (TextView) findViewById(R.id.xjxx_xs);
		xjxx_zy = (TextView) findViewById(R.id.xjxx_zy);
		xjxx_nj = (TextView) findViewById(R.id.xjxx_nj);
		xjxx_bj = (TextView) findViewById(R.id.xjxx_bj);
		xjxx_sfxj = (TextView) findViewById

		(R.id.xjxx_sfxj);
		xjxx_gjxj = (TextView) findViewById

		(R.id.xjxx_gjxj);
		xjxx_xq = (TextView) findViewById(R.id.xjxx_xq);
		xjxx_yd = (TextView) findViewById(R.id.xjxx_yd);
		
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
				StudentStatus info = new StudentStatus();
				// 参数对象
				ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
				//params.add(new BasicNameValuePair("address", address));
				// 连接
				String grglStatus = connectTYUT.getByPost(
						"http://" + Tmp.getServerIp() + "/xjxx", params);
				String cookielock=Tmp.getCookies();
				Log.i("facj数据1", grglStatus + " ");
				try {
					if(grglStatus.indexOf("响应吗50") != -1||(new JSONObject(grglStatus).getInt("status"))==2) {
						LoginAgain again = new LoginAgain();
						again.loginAgain(XjxxActivity.this);
						
						while(Tmp.getCookies()==cookielock){
							
						}
						params.remove(0);
						params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
						grglStatus = connectTYUT.getByPost(
								"http://" + Tmp.getServerIp() + "/xjxx", params);
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
						info.setIdstudent(key.getString("idstudent"));
						info.setName(key.getString("name"));
						info.setIdcard(key.getString("idcard"));
						info.setSex(key.getString("sex"));
						info.setMz(key.getString("mz"));
						info.setJg(key.getString("jg"));
						info.setCsrq(key.getString("csrq"));
						info.setZz(key.getString("zz"));
						info.setRxrq(key.getString("rxrq"));
						info.setXs(key.getString("xs"));
						info.setZy(key.getString("zy"));
						info.setNj(key.getString("nj"));
						info.setBj(key.getString("bj"));
						info.setSfxj(key.getString("sfxj"));
						info.setGjxj(key.getString("gjxj"));
						info.setXq(key.getString("xq"));
						info.setYd(key.getString("yd"));
						
						
						
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
