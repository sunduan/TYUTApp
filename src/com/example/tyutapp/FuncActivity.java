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
		// �޸ı�������ɫ
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ad82dd")));

		facjlist = (ListView) findViewById(R.id.facjlist);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData());
		
		facjlist.setAdapter(adapter);
	}

	private List<String> getData() {
		
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
		
		String[] names = { "��  ��    ��  ��", "�γ����Գɼ�", "�� �� �� �� ��", "�� ѧ �� �� ��",
				"����ʵ���ɼ�" };

		String[][] childnames = {
				{ "������", "", "", "", "", "", "", "" },
				{ "��        ѡ", "�ۺϱ���", "רҵ����", "ѧ�Ʊ���", "רҵѡ��", "ѧ��ѡ��", "ʵ������",
						"�ۺ���ѡ" },

				{ "��������", "�в�����", "", "", "", "", "", "" },
				{ "��ѧ�ڳɼ���ѯ", "", "", "", "", "", "", "" },
				{ "����ʵ��", "", "", "", "", "", "", "" } };
		group = new ArrayList<Map<String, String>>();
		// �������б���List���ݼ���
		ss = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < names.length; i++) {
			// �ṩ���б������
			Map<String, String> maps = new HashMap<String, String>();
			// maps.put("images", images[i]);
			maps.put("names", names[i]);
			group.add(maps);
			// �ṩ��ǰ���е���������
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
