package com.example.phonewall;

import android.app.Activity;											//import包还要增加？
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ThirdActivity extends MainActivity {
	private ListView list1;
	private ListView list2;
	private ListView list3;
	private int i;
	private String[] a;
	private String[] b;
	private String[] c;
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_third);						
		list1=(ListView) findViewById(R.id.listView1);
		list2=(ListView) findViewById(R.id.listView2);
		list3=(ListView) findViewById(R.id.listView3);
		
		for(i=0;i<MainActivity.i;i++){///
			a[i]=MainActivity.dxhc.getString("phonenumber[i]", "");
		}
		
		for(i=0;i<MainActivity.j;i++){
			b[i]=MainActivity.dxbc.getString("phonenumber[i]", "");
		}
		
		for(i=0;i<MainActivity.k;i++){
			c[i]=MainActivity.dhhc.getString("phonenumber[i]", "");		//分别将三个sharedpreferences的存书内容赋给了三个数组abc
		}
		
		BaseAdapter1 ba1=new BaseAdapter1();
		list1.setAdapter(ba1);
		
		BaseAdapter2 ba2=new BaseAdapter2();
		list2.setAdapter(ba2);
		
		BaseAdapter3 ba3=new BaseAdapter3();
		list3.setAdapter(ba3);
	}
	
	class BaseAdapter1 extends BaseAdapter{								//第一个适配器登场
		public int getCount(){
			//返回listview的item的条目总数
			return a.length;
		}
		public Object getItem(int position){
			//返回listview的item条目代表的对象
			return a[position];
		}
		public long getItemId(int position){
			return position;															//返回listview的item的id
		}
		public View getView(int position,View convertView,ViewGroup parent){
			View view1=View.inflate(ThirdActivity.this,R.layout.list_item,null);
			TextView text1=(TextView) view1.findViewById(R.id.textView1);
			text1.setText("短信黑名单");
			TextView text2=(TextView) view1.findViewById(R.id.textView2);
			text2.setText(a[position]);														//注意这里要加东西？
			return view1;
		}
	}
	
	class BaseAdapter2 extends BaseAdapter{
		public int getCount(){
			return b.length;
		}
		public Object getItem(int position){
			return b[position];
		}
		public long getItemId(int position){
			return position;
		}
		public View getView(int position,View convertView,ViewGroup parent){
			View view2=View.inflate(ThirdActivity.this,R.layout.list_item2,null);
			TextView text1=(TextView) view2.findViewById(R.id.textView3);
			text1.setText("短信白名单");
			TextView text2=(TextView) view2.findViewById(R.id.textView4);
			text2.setText(b[position]);
			return view2;
		}
	}
	
	class BaseAdapter3 extends BaseAdapter{
		public int getCount(){
			return c.length;
		}
		public Object getItem(int position){
			return c[position];
		}
		public long getItemId(int position){
			return position;
		}
		public View getView(int position,View convertView,ViewGroup parent){
			View view3=View.inflate(ThirdActivity.this,R.layout.list_item3,null);
			TextView text1=(TextView) view3.findViewById(R.id.textView5);
			text1.setText("电话黑名单");
			TextView text2=(TextView) view3.findViewById(R.id.textView6);
			text2.setText(c[position]);
			return view3;
		}
	}
}
