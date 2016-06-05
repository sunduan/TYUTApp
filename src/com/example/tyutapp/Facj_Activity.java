package com.example.tyutapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import TYUT.adapter.FacjAdapter;
import TYUT.adapter.KcxqAdapter;
import TYUT.cuslayout.SlidingMenu;
import TYUT.network.ConnectTYUT;
import TYUT.network.LoginAgain;
import TYUT.tmp.Tmp;
import TYUTservice.data.MessageFacj;
import TYUTservice.data.MessageKccx;
import TYUTservice.data.msgdata.FacjMsg;
import TYUTservice.data.msgdata.KccxMsg;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Facj_Activity extends Activity {
	private ListView facjlist;
	private ListView main_listview;
	private List<FacjMsg> facjMsgs;
	private SlidingMenu slidingMenu;
	private List<KccxMsg> kccxMsgs;
	
	//kcxq
	private EditText kcxq_kcm;
	private EditText kcxq_jsm;
	private EditText kcxq_zy;
	private Button	 kcxq_cx;
	
	private Button nextpage;
	private TextView numberpage;
	private EditText editpage;
	
	private int mMenuWidth;
	private int mScreenHeight;
	//屏宽
	private int mHalfMenuWidth;
	//
	private int mMenuRightPadding;
	private DisplayMetrics outMetrics;
	private int mScreenWidth;
	
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what==1) {
				
				FacjAdapter adapter=new FacjAdapter(Facj_Activity.this,(List<FacjMsg>)msg.obj);
				main_listview=(ListView)findViewById(R.id.main_listview);
				
				main_listview.setAdapter(adapter);
			}else{
				
				
				if(numberpage.getText().toString().equals("")){
					editpage.setVisibility(View.VISIBLE);
					if(((List<KccxMsg>)msg.obj).isEmpty()){
						numberpage.setText("0/0"+"页");
					}else{
				numberpage.setText("1/"+((List<KccxMsg>)msg.obj).get(0).getSun()+"页");
					}
				}
				KcxqAdapter adapter=new KcxqAdapter(Facj_Activity.this,(List<KccxMsg>)msg.obj);
				main_listview=(ListView)findViewById(R.id.main_listview);
				main_listview.setAdapter(adapter);
			}
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bs_main);
		
		Tmp.contexts.add(this);
		//屏幕大小
		WindowManager wm=(WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
		outMetrics=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenHeight=outMetrics.heightPixels;
		
		mScreenWidth=outMetrics.widthPixels;
		Log.i("屏款",mScreenWidth+"");
		//dp转px
		mMenuRightPadding=mScreenWidth/5;
		
		
		// 修改标题栏颜色
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ad82dd")));
		
		slidingMenu=(SlidingMenu)findViewById(R.id.slide_menu);
		facjlist = (ListView) findViewById(R.id.facjlist);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData());

		facjlist.setAdapter(adapter);
		
		setView(R.layout.kcxq_form);
		onclick_kcxq();
		// 事件监听
		facjlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					
					setView(R.layout.kcxq_form);
					onclick_kcxq();
					break;			
				case 1:
					setView(R.layout.activity_func);
					setMainView("2");
					break;
				case 2:
					setView(R.layout.activity_func);
					setMainView("3");
					break;
				case 3:
					setView(R.layout.activity_func);
					setMainView("4");
					break;
				case 4:
					setView(R.layout.activity_func);
					setMainView("5");
					break;
				case 5:
					setView(R.layout.activity_func);
					setMainView("6");
					break;
				case 6:
					setView(R.layout.activity_func);
					setMainView("7");
					break;
				case 7:
					setView(R.layout.activity_func);
					setMainView("8");
					break;
				case 8:
					setMainView("9");
					break;
				case 9:
					setView(R.layout.activity_func);
					setMainView("10");
					break;
				case 10:
					setView(R.layout.activity_func);
					setMainView("11");
					break;
				case 11:
					setView(R.layout.activity_func);
					setMainView("12");
					break;
				case 12:
					setView(R.layout.activity_func);
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
				facjMsgs=new ArrayList<FacjMsg>();
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
				try {
					if(facjStatus.indexOf("响应吗50") != -1||(new JSONObject(facjStatus).getInt("status"))==2) {
						LoginAgain again = new LoginAgain();
						again.loginAgain(Facj_Activity.this);
						
						while(Tmp.getCookies()==cookielock){
							
						}
						params.remove(0);
						params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
						facjStatus = connectTYUT.getByPost(
								"http://" + Tmp.getServerIp() + "/facj", params);
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Log.i("facj数据2", facjStatus + " ");
				// json
				JSONObject jsonObject = null;
				JSONArray key = new JSONArray();
				try {
					jsonObject = new JSONObject(facjStatus);
					status = jsonObject.getInt("status");
					Log.i("状态吗",status+"");
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
						Log.i("接受qianqian","接受"+facjMsgs.size());
						//facj.setFacjMsgs(facjMsgs);
						message = new Message();
						message.what =1;
						message.obj=facjMsgs;
						Log.i("发送前","发送");
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
		data.add("本学期课程查询");
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

	}
	public void  setView(int view){
		LinearLayout layout=(LinearLayout)slidingMenu.getChildAt(0);
		if(layout.getChildCount()==3){
			layout.removeView(layout.getChildAt(2));
			
		}
		layout.removeView(layout.getChildAt(1));
	
		layout.addView(LayoutInflater.from(Facj_Activity.this).inflate(
		view, null));
		
		
		ViewGroup menu = (ViewGroup) layout.getChildAt(0);
		ViewGroup content = (ViewGroup) layout.getChildAt(1);

		mMenuWidth = mScreenWidth - mMenuRightPadding*2;
		//mHalfMenuWidth = mMenuWidth / 10;
		android.widget.LinearLayout.LayoutParams menulay=(android.widget.LinearLayout.LayoutParams) menu.getLayoutParams();
		menulay.width = mMenuWidth;
		android.widget.LinearLayout.LayoutParams contentlay=(android.widget.LinearLayout.LayoutParams) content.getLayoutParams();
		contentlay.width = mScreenWidth;
		
		menu.setLayoutParams(menulay);
		content.setLayoutParams(contentlay);
	}
	public void  setView3(int view){
		LinearLayout layout=(LinearLayout)slidingMenu.getChildAt(0);
		//layout.removeView(layout.getChildAt(1));
		layout.addView(LayoutInflater.from(Facj_Activity.this).inflate(
		view, null));
		
		
		ViewGroup menu = (ViewGroup) layout.getChildAt(0);
		ViewGroup content = (ViewGroup) layout.getChildAt(1);

		mMenuWidth = mScreenWidth - mMenuRightPadding*2;
		//mHalfMenuWidth = mMenuWidth / 10;
		android.widget.LinearLayout.LayoutParams menulay=(android.widget.LinearLayout.LayoutParams) menu.getLayoutParams();
		menulay.width = mMenuWidth;
		android.widget.LinearLayout.LayoutParams contentlay=(android.widget.LinearLayout.LayoutParams) content.getLayoutParams();
		contentlay.width = mScreenWidth;
		
		menu.setLayoutParams(menulay);
		content.setLayoutParams(contentlay);
		
		ViewGroup hmenu=(ViewGroup) layout.getChildAt(1);
		ViewGroup hcontent=(ViewGroup) layout.getChildAt(2);
		
		android.widget.LinearLayout.LayoutParams hmenulay=(android.widget.LinearLayout.LayoutParams) hmenu.getLayoutParams();
		hmenulay.width =0;
		android.widget.LinearLayout.LayoutParams hcontentlay=(android.widget.LinearLayout.LayoutParams) hcontent.getLayoutParams();
		hcontentlay.width = mScreenWidth;
		
		hmenu.setLayoutParams(hmenulay);
		hcontent.setLayoutParams(hcontentlay);
	}

	public void  setView2(int view){
		LinearLayout layout=(LinearLayout)slidingMenu.getChildAt(0);
		layout.removeView(((LinearLayout)layout.getChildAt(1)).getChildAt(0));
		
		((LinearLayout)layout.getChildAt(1)).addView(LayoutInflater.from(Facj_Activity.this).inflate(
		view, null));
		
		
		ViewGroup menu = (ViewGroup) layout.getChildAt(0);
		ViewGroup content = (ViewGroup) layout.getChildAt(1);

		mMenuWidth = mScreenWidth - mMenuRightPadding*2;
		//mHalfMenuWidth = mMenuWidth / 10;
		android.widget.LinearLayout.LayoutParams menulay=(android.widget.LinearLayout.LayoutParams) menu.getLayoutParams();
		menulay.width = mMenuWidth;
		android.widget.LinearLayout.LayoutParams contentlay=(android.widget.LinearLayout.LayoutParams) content.getLayoutParams();
		contentlay.width = mScreenWidth;
		
		menu.setLayoutParams(menulay);
		content.setLayoutParams(contentlay);
		
		/*ViewGroup hmenu=(ViewGroup) ((LinearLayout)(ViewGroup)layout.getChildAt(1)).getChildAt(1);
		ViewGroup hcontent=(ViewGroup) ((LinearLayout)(ViewGroup)layout.getChildAt(1)).getChildAt(2);
		
		android.widget.LinearLayout.LayoutParams hmenulay=(android.widget.LinearLayout.LayoutParams) hmenu.getLayoutParams();
		hmenulay.height = mScreenHeight/10;
		android.widget.LinearLayout.LayoutParams hcontentlay=(android.widget.LinearLayout.LayoutParams) hcontent.getLayoutParams();
		hcontentlay.height = mScreenHeight/10*9;
		
		hmenu.setLayoutParams(hmenulay);
		hcontent.setLayoutParams(hcontentlay);*/
	}
	public void onclick_kcxq(){
		kcxq_jsm=(EditText)findViewById(R.id.kcxq_jsm);
		kcxq_kcm=(EditText)findViewById(R.id.kcxq_kcm);
		kcxq_zy=(EditText)findViewById(R.id.kcxq_zy);
		kcxq_cx=(Button)findViewById(R.id.kcxq_cx);
		
		
		kcxq_cx.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				String jsm=kcxq_jsm.getText().toString();
				String kcm=kcxq_kcm.getText().toString();
				String zy=kcxq_zy.getText().toString();
				setView3(R.layout.activity_func);
				
				LinearLayout l=(LinearLayout)findViewById(R.id.main_top);
				l.setVisibility(View.VISIBLE);
				nextpage=(Button)findViewById(R.id.nextpage);
				numberpage=(TextView)findViewById(R.id.numberpage);
				editpage=(EditText)findViewById(R.id.editpage);
				
				getKcxq(1+"",0+"",kcm,jsm,zy);
				nextpage.setOnClickListener(new OnClickListener() {
					
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String number=numberpage.getText().toString();
						String ednu=editpage.getText().toString();
						Log.i("number",number+"");
						String page=number.split("/")[0];
						String co=number.split("/")[1].split("页")[0];
						if(ednu.equals("")){
							if(Integer.parseInt(page)<Integer.parseInt(co)){
									
									int page1=Integer.parseInt(page)+1;
									numberpage.setText(page1+"/"+co+"页");
									getKcxq(2+"",page1+"","","","");
									}
						}else{
							if(Integer.parseInt(ednu)>Integer.parseInt(co)){
								
							}else{
								numberpage.setText(ednu+"/"+co+"页");
								getKcxq(2+"",ednu+"","","","");
							}
						}
					}
				});
				
					
				}
				
			
		});
		
	}
	public void getKcxq(String actionType,String pageNumber, String kcm,String jsm,String zy){
		
		final String actionType_1=actionType;
		final String pageNumber_1=pageNumber;
		final String kcm_1=kcm;
		final String jsm_1=jsm;
		final String zy_1=zy;
		new Thread() {
			public void run() {
				//消息
				kccxMsgs=new ArrayList<KccxMsg>();
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
				params.add(new BasicNameValuePair("actionType",actionType_1));
				params.add(new BasicNameValuePair("pageNumber",pageNumber_1));
				
				params.add(new BasicNameValuePair("kcm", kcm_1));
				Log.i("kcm课程名",kcm_1+","+Tmp.getCookies());
				
				params.add(new BasicNameValuePair("jsm", jsm_1));
				params.add(new BasicNameValuePair("xsjc", zy_1));
				params.add(new BasicNameValuePair("skjc",""));
				params.add(new BasicNameValuePair("xaqh",""));
				params.add(new BasicNameValuePair("jxlh",""));
				params.add(new BasicNameValuePair("jash",""));
				// 连接
				String kcxqStatus = connectTYUT.getByPost(
						"http://" + Tmp.getServerIp() + "/kcxq", params);
				String cookielock=Tmp.getCookies();
				Log.i("facj数据1", kcxqStatus + " ");
				try {
					if(kcxqStatus.indexOf("响应吗50") != -1||(new JSONObject(kcxqStatus).getInt("status"))==2) {
						LoginAgain again = new LoginAgain();
						again.loginAgain(Facj_Activity.this);
						
						while(Tmp.getCookies()==cookielock){
							
						}
						params.remove(0);
						params.add(new BasicNameValuePair("cookie", Tmp.getCookies()));
						kcxqStatus = connectTYUT.getByPost(
								"http://" + Tmp.getServerIp() + "/kcxq", params);
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Log.i("facj数据2", kcxqStatus + " ");
				// json
				JSONObject jsonObject = null;
				JSONArray key = new JSONArray();
				try {
					jsonObject = new JSONObject(kcxqStatus);
					status = jsonObject.getInt("status");
					if (status == 0) {

					} else if (status == 3) {
						kccx.setStatus(status);
						key = jsonObject.getJSONArray("kcxqMsgs");
						int l = key.length();
						// 局部facjmsg
						
						List<FacjMsg> facjMsgs = new ArrayList<FacjMsg>();
						for (int i = 0; i < l; i++) {
							JSONObject jsonObject2 = key.getJSONObject(i);
							KccxMsg kccxMsg = new KccxMsg();
							kccxMsg.setMon(jsonObject2.getString("kcm"));
							kccxMsg.setTue(jsonObject2.getString("xf"));
							kccxMsg.setWed(jsonObject2.getString("js"));
							kccxMsg.setThu(jsonObject2.getString("jc"));
							kccxMsg.setFri(jsonObject2.getString("xq"));
							kccxMsg.setSat(jsonObject2.getString("zc"));
							kccxMsg.setSun(jsonObject2.getString("con"));
							
							kccxMsgs.add(kccxMsg);
						}
						
						//facj.setFacjMsgs(facjMsgs);
						message = new Message();
						message.what =2;
						message.obj=kccxMsgs;
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
