package com.example.phonewall;

import android.app.Activity;											
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SecondActivity extends MainActivity {
	private ListView list1;
	private ListView list2;
	private int i;
	private String[] a;
	private String[] b;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);						
		list1=(ListView) findViewById(R.id.listView4);
		list2=(ListView) findViewById(R.id.listView5);
		
		for(i=0;i<FirstBroadcastReceiver.j;i++){///////////????????
			a[i]=MainActivity.dxscjl.getString("phonenumber[i]", "");
		}
		for(i=0;i<SecondBroadcastReceiver.j;i++){//////////////??????????
			b[i]=MainActivity.dhscjl.getString("phonenumber[i]", "");
		}
		BaseAdapter1 mAdapter1=new BaseAdapter1();
		BaseAdapter2 mAdapter2=new BaseAdapter2();
		list1.setAdapter(mAdapter1);
		list2.setAdapter(mAdapter2);
	}
	
	class BaseAdapter1 extends BaseAdapter{
			public int getCount(){
				return a.length;/////
			}
			
			public Object getItem(int position){
				return a[position];
				
			}
			public long getItemId(int position){
				return position;
			}
			
			public View getView(int position,View convertView,ViewGroup parent){
				View view1=View.inflate(SecondActivity.this,R.layout.list_item4,null);
				TextView mTextView=(TextView)view1.findViewById(R.id.textView7);
				mTextView.setText(a[position]);
				return view1;
			}
	}
	
	
	class BaseAdapter2 extends BaseAdapter{
		public int getCount(){
			return b.length;/////
		}
		
		public Object getItem(int position){
			return b[position];
			
		}
		public long getItemId(int position){
			return position;
		}
		
		public View getView(int position,View convertView,ViewGroup parent){
			View view2=View.inflate(SecondActivity.this,R.layout.list_item5,null);
			TextView mTextView=(TextView)view2.findViewById(R.id.textView8);
			mTextView.setText(b[position]);
			return view2;
		}
	}
}


