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
	 public static int j=0;//tel删掉的数目数

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("第二个广播执行了","");
		// TODO Auto-generated method stub
		SharedPreferences dhhc=context.getSharedPreferences("电话黑名单", Context.MODE_PRIVATE);
		SharedPreferences dhscjl=context.getSharedPreferences("电话删除记录",Context.MODE_PRIVATE);
		
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
		Log.i("switch上面执行了","");
		switch (tManager.getCallState()){
    		case TelephonyManager.CALL_STATE_RINGING:{						//响铃
    			mIncomingNumber = intent.getStringExtra("incoming_number");
    			Log.i("获取的接电号码",mIncomingNumber);
    			for(i=0;i<MainActivity.k;i++){//////////////////////////？？？？？？？？
	    			if(mIncomingNumber.equals("1555521"+dhhc.getString(MainActivity.phonenumber[i], ""))){
	        			Log.i(TAG, "RINGING :" + mIncomingNumber+"tel号码在黑名单中");
	        			/*信息存入存储类中*/////////////
	        			/*
	        			 * SharedPreferences dxscjl=context.getSharedPreferences("短信删除记录", Context.MODE_PRIVATE);
					Editor editor=dxscjl.edit();
					editor.putString(MainActivity.phonenumber[this.j], sender+"黑名单号码删除短信");//这种写法可以吗？/////
					this.j++;
	        			 * */
	        			
	        			Editor editor=dhscjl.edit();
	        			editor.putString(MainActivity.phonenumber[j], "被删来电："+mIncomingNumber+"于"+"new Time().setToNow().hour.toString()"+":"+"new Time().setToNow().minute.toString()"+":"+"new Time().setToNow().second.toString()");//没有时间
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

