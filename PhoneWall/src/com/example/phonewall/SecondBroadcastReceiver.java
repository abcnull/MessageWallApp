package com.example.phonewall;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SecondBroadcastReceiver extends BroadcastReceiver {
	 private static final String TAG = "message";
	 private static boolean mIncomingFlag = false;
	 private static String mIncomingNumber = null;
	 private int i=0;
	 public static int j=0;//telɾ������Ŀ��

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("�ڶ����㲥ִ����","");
		// TODO Auto-generated method stub
		SharedPreferences dhhc=context.getSharedPreferences("�绰������", Context.MODE_PRIVATE);
		SharedPreferences dhscjl=context.getSharedPreferences("�绰ɾ����¼",Context.MODE_PRIVATE);
		
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
		Log.i("switch����ִ����","");
		switch (tManager.getCallState()){
    		case TelephonyManager.CALL_STATE_RINGING:{						//����
    			mIncomingNumber = intent.getStringExtra("incoming_number");
    			Log.i("��ȡ�Ľӵ����",mIncomingNumber);
    			for(i=0;i<MainActivity.k;i++){//////////////////////////����������������
	    			if(mIncomingNumber.equals("1555521"+dhhc.getString(MainActivity.phonenumber[i], ""))){
	        			Log.i(TAG, "RINGING :" + mIncomingNumber+"tel�����ں�������");
	        			/*��Ϣ����洢����*/////////////
	        			/*
	        			 * SharedPreferences dxscjl=context.getSharedPreferences("����ɾ����¼", Context.MODE_PRIVATE);
					Editor editor=dxscjl.edit();
					editor.putString(MainActivity.phonenumber[this.j], sender+"����������ɾ������");//����д��������/////
					this.j++;
	        			 * */
	        			
	        			Editor editor=dhscjl.edit();
	        			editor.putString(MainActivity.phonenumber[j], "��ɾ���磺"+mIncomingNumber+"��"+"new Time().setToNow().hour.toString()"+":"+"new Time().setToNow().minute.toString()"+":"+"new Time().setToNow().second.toString()");//û��ʱ��
	        			j++;
	    				abortBroadcast();
	    				break;
	    			}
    			}
           	 	break;
    		}
		}
		
	}

}

