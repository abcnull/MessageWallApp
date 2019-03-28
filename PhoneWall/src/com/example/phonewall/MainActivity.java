package com.example.phonewall;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {										//主activity
	public static int i=0;//短信黑名单数量				////////////////////////////////////注意是共有的？？？？？？？？？？
	public static int j=0;//短信白名单数量
	public static int k=0;//电话黑名单数量														//三个控制存入的是第几个的数组的下表
	
	private EditText number;
	private Button dxh;
	private Button dxb;
	private Button dhh;
	private Button rz;
	private Button ck;																//先定义的私有控件的变量
	
	public static SharedPreferences dxhc;
	public static SharedPreferences dxbc;
	public static SharedPreferences dhhc;										//先定义三个存储的sharedpreferences
	public static SharedPreferences dxscjl;										//确定要私有吗？
	public static SharedPreferences dhscjl;
	
	public static String []phonenumber = new String[100];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		number=(EditText) findViewById(R.id.editText1);
		dxh=(Button) findViewById(R.id.button1);
		dxb=(Button) findViewById(R.id.button2);
		dhh=(Button) findViewById(R.id.button3);
		rz=(Button) findViewById(R.id.button4);
		ck=(Button) findViewById(R.id.button5);										//查出主xml中的这几个控件并赋给已经定义好的变量
		
		dxhc=getSharedPreferences("短信黑名单",MODE_PRIVATE);
		dxbc=getSharedPreferences("短信白名单",MODE_PRIVATE);
		dhhc=getSharedPreferences("电话黑名单",MODE_PRIVATE);							//初始化三个sharedpreferences对象
		dxscjl=getSharedPreferences("短信删除记录",MODE_PRIVATE);
		dhscjl=getSharedPreferences("电话删除记录",MODE_PRIVATE);
		
		number.setText(dxhc.getString(phonenumber[i], ""));
		number.setText(dxbc.getString(phonenumber[j], ""));
		number.setText(dhhc.getString(phonenumber[k], ""));						//此处使得textedit控件上显示输入的要存的黑边名单号码 ？	
	}
	
	public void click1(View view){

		String phonenumberr=number.getText().toString().trim();
		Editor editor=dxhc.edit();
		editor.putString(phonenumber[i], phonenumberr);							//这个地方是定义变量 吗？数组怎么弄？非要加双引号吗？//验证已经存进去了
		i++;
		editor.commit();
		Toast.makeText(this, /*"第"+i+"个"+"sms黑名单保存成功"+*/"第"+i+"个sms黑名单号码"+dxhc.getString(phonenumber[i], ""), 0).show();/////////////////////////////////
		Log.i("存储好的sms黑名单数据",dxhc.getString(phonenumber[i], ""));
	}
	
	public void click2(View view){
		String phonenumberr=number.getText().toString().trim();
		Editor editor=dxbc.edit();
		editor.putString(phonenumber[j], phonenumberr);
		j++;
		editor.commit();
		Toast.makeText(this, "第"+j+"个sms白名单号码"+dxbc.getString(phonenumber[j], ""), 0).show();								//短信白名单，将文本框中内容保存至相应的sharedpreferences中
		Log.i("存储好的sms白名单数据",dxbc.getString(phonenumber[j], ""));
	}
	
	public void click3(View view){
		String phonenumberr=number.getText().toString().trim();
		Editor editor=dhhc.edit();
		editor.putString(phonenumber[k], phonenumberr);
		k++;
		editor.commit();
		Toast.makeText(this, "第"+k+"个tel黑名单号码"+dhhc.getString(phonenumber[k], ""), 0).show();								//电话黑名单，将文本框中的内容保存至相应的sharedpreferences中
		Log.i("存储好的tel黑名单数据",dhhc.getString(phonenumber[k], ""));
	}																		
	
	public void click4(View view){													//此处是防火墙日志，等编写到广播，再来编写
		Intent intent=new Intent(this,SecondActivity.class);
		startActivity(intent);														//开启第二个activity页面
		Log.i("成功打开日志界面","");
	}
	
	public void click5(View view){
		Intent intent=new Intent(this,ThirdActivity.class);
		startActivity(intent);														//开启第三个activity页面
		Log.i("成功打开黑名单查看界面","");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
