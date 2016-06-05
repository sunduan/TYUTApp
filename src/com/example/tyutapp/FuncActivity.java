package com.example.tyutapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import TYUT.tmp.Tmp;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FuncActivity extends Activity {
	private ListView facjlist;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bs_main);

		Tmp.contexts.add(this);
		// 修改标题栏颜色
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ad82dd")));

		facjlist = (ListView) findViewById(R.id.facjlist);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData());
		
		facjlist.setAdapter(adapter);
	}

	private List<String> getData() {
		
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
		
		String[] names = { "方  案    成  绩", "课程属性成绩", "不 及 格 成 绩", "本 学 期 成 绩",
				"自主实践成绩" };

		String[][] childnames = {
				{ "软工方案", "", "", "", "", "", "", "" },
				{ "任        选", "综合必修", "专业必修", "学科必修", "专业选修", "学科选修", "实践环节",
						"综合任选" },

				{ "曾不及格", "尚不及格", "", "", "", "", "", "" },
				{ "本学期成绩查询", "", "", "", "", "", "", "" },
				{ "自主实践", "", "", "", "", "", "", "" } };
		group = new ArrayList<Map<String, String>>();
		// 定义子列表项List数据集合
		ss = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < names.length; i++) {
			// 提供父列表的数据
			Map<String, String> maps = new HashMap<String, String>();
			// maps.put("images", images[i]);
			maps.put("names", names[i]);
			group.add(maps);
			// 提供当前父列的子列数据
			List<Map<String, String>> child = new ArrayList<Map<String, String>>();
			for (int j = 0; j < 8; j++) {
				Map<String, String> mapsj = new HashMap<String, String>();
				if (childnames[i][j] != "") {
					mapsj.put("tengxun", childnames[i][j]);
					child.add(mapsj);
				}
			}
			ss.add(child);
		}*/

	}
}
