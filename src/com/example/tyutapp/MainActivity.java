package com.example.tyutapp;

import TYUTservice.data.msgdata.KccxMsg;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button fanganchengji_func;
	private Button peiyangfangan_func;
	private Button kechengchaxun_func;
	private Button gerenguanli_func;
	private Button kaoshiguanli_func;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// button
		fanganchengji_func = (Button) findViewById(R.id.fanganchengji_func);
		peiyangfangan_func=(Button) findViewById(R.id.peiyangfangan_func);
		kechengchaxun_func=(Button)findViewById(R.id.kechengchaxun_func);
		gerenguanli_func=(Button)findViewById(R.id.gerenguanli_func);
		kaoshiguanli_func=(Button)findViewById(R.id.kaoshiguanli_func);
		// listen
		fanganchengji_func.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						Facj_Activity.class);
				// 启动活动
				startActivity(intent);
			}

		});
		peiyangfangan_func.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						Pyfa_Activity.class);
				// 启动活动
				startActivity(intent);
			}

		});
		kechengchaxun_func.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						KccxActivity.class);
				// 启动活动
				startActivity(intent);
			}

		});
		gerenguanli_func.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						GrglActivity.class);
				// 启动活动
				startActivity(intent);
			}

		});
		kaoshiguanli_func.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						KsglActivity.class);
				// 启动活动
				startActivity(intent);
			}

		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}
}
