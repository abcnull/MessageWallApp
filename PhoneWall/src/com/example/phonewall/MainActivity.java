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

public class MainActivity extends Activity {										//��activity
	public static int i=0;//���ź���������				////////////////////////////////////ע���ǹ��еģ�������������������
	public static int j=0;//���Ű���������
	public static int k=0;//�绰����������														//�������ƴ�����ǵڼ�����������±�
	
	private EditText number;
	private Button dxh;
	private Button dxb;
	private Button dhh;
	private Button rz;
	private Button ck;																//�ȶ����˽�пؼ��ı���
	
	public static SharedPreferences dxhc;
	public static SharedPreferences dxbc;
	public static SharedPreferences dhhc;										//�ȶ��������洢��sharedpreferences
	public static SharedPreferences dxscjl;										//ȷ��Ҫ˽����
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
		ck=(Button) findViewById(R.id.button5);										//�����xml�е��⼸���ؼ��������Ѿ�����õı���
		
		dxhc=getSharedPreferences("���ź�����",MODE_PRIVATE);
		dxbc=getSharedPreferences("���Ű�����",MODE_PRIVATE);
		dhhc=getSharedPreferences("�绰������",MODE_PRIVATE);							//��ʼ������sharedpreferences����
		dxscjl=getSharedPreferences("����ɾ����¼",MODE_PRIVATE);
		dhscjl=getSharedPreferences("�绰ɾ����¼",MODE_PRIVATE);
		
		number.setText(dxhc.getString(phonenumber[i], ""));
		number.setText(dxbc.getString(phonenumber[j], ""));
		number.setText(dhhc.getString(phonenumber[k], ""));						//�˴�ʹ��textedit�ؼ�����ʾ�����Ҫ��ĺڱ��������� ��	
	}
	
	public void click1(View view){

		String phonenumberr=number.getText().toString().trim();
		Editor editor=dxhc.edit();
		editor.putString(phonenumber[i], phonenumberr);							//����ط��Ƕ������ ��������ôŪ����Ҫ��˫������//��֤�Ѿ����ȥ��
		i++;
		editor.commit();
		Toast.makeText(this, /*"��"+i+"��"+"sms����������ɹ�"+*/"��"+i+"��sms����������"+dxhc.getString(phonenumber[i], ""), 0).show();/////////////////////////////////
		Log.i("�洢�õ�sms����������",dxhc.getString(phonenumber[i], ""));
	}
	
	public void click2(View view){
		String phonenumberr=number.getText().toString().trim();
		Editor editor=dxbc.edit();
		editor.putString(phonenumber[j], phonenumberr);
		j++;
		editor.commit();
		Toast.makeText(this, "��"+j+"��sms����������"+dxbc.getString(phonenumber[j], ""), 0).show();								//���Ű����������ı��������ݱ�������Ӧ��sharedpreferences��
		Log.i("�洢�õ�sms����������",dxbc.getString(phonenumber[j], ""));
	}
	
	public void click3(View view){
		String phonenumberr=number.getText().toString().trim();
		Editor editor=dhhc.edit();
		editor.putString(phonenumber[k], phonenumberr);
		k++;
		editor.commit();
		Toast.makeText(this, "��"+k+"��tel����������"+dhhc.getString(phonenumber[k], ""), 0).show();								//�绰�����������ı����е����ݱ�������Ӧ��sharedpreferences��
		Log.i("�洢�õ�tel����������",dhhc.getString(phonenumber[k], ""));
	}																		
	
	public void click4(View view){													//�˴��Ƿ���ǽ��־���ȱ�д���㲥��������д
		Intent intent=new Intent(this,SecondActivity.class);
		startActivity(intent);														//�����ڶ���activityҳ��
		Log.i("�ɹ�����־����","");
	}
	
	public void click5(View view){
		Intent intent=new Intent(this,ThirdActivity.class);
		startActivity(intent);														//����������activityҳ��
		Log.i("�ɹ��򿪺������鿴����","");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
