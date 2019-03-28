package com.example.phonewall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class FirstBroadcastReceiver extends BroadcastReceiver {
	
	private int i=0;//仅仅是一个过程中要用的变量
	public static int j=0;//表示有垃圾短信多少条//////////////////
	private int flag=0;
	

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		SharedPreferences dxhc=context.getSharedPreferences("短信黑名单", Context.MODE_PRIVATE);
		SharedPreferences dxbc=context.getSharedPreferences("短信白名单", Context.MODE_PRIVATE);
		SharedPreferences dxscjl=context.getSharedPreferences("短信删除记录", Context.MODE_PRIVATE);
		
		
		Object[] objs=(Object[]) intent.getExtras().get("pdus");
		for(Object obj:objs){
			SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) obj);
			String body=smsMessage.getMessageBody();
			String sender=smsMessage.getOriginatingAddress();
			
			
			/*if(不在白名单中){
				if(在黑名单中){
					需要废除广播
				}
				else{
					根据内容判断
				}
			}*/
			
			for(i=0;i<MainActivity.j;i++){//////////////////////////////???????????//?????
				if(     (  "1555521"+dxbc.getString(MainActivity.phonenumber[i], "")  ).equals(sender)      ){//若执行此句则在白名单中
					this.flag=1;
					Log.i("在sms白名单中","");
					break;
				}
			}
			if(this.flag==0){										//不再白名单中
				for(i=0;i<MainActivity.i;i++){//////////////////////???????????????????
					if(("1555521"+dxhc.getString(MainActivity.phonenumber[i], "")).equals(sender)){//若执行此句则在黑名单中
						Log.i("在sms黑名单中","");
						this.flag=1;

						break;
					}
				}
				if(this.flag==1){									//此时说明在黑名单中
					Log.i("FirstBroadcastReceiver","垃圾sms号码，立刻终止");
																						/*此处存入必要信息给sharedpreferences*/
					
					Editor editor=dxscjl.edit();
					editor.putString(MainActivity.phonenumber[j],sender+"黑名单号码删除短信");//这种写法可以吗？/////
					j++;
					flag=0;
					abortBroadcast();
				}
				else{
					if(BF(body,"fuck",1)!=0){										//此时短信内容有问题
						/**/
						Log.i("FirstBroadcastReceiver","垃圾sms内容，立刻终止");
						
						Editor editor=dxscjl.edit();
						editor.putString(MainActivity.phonenumber[j], sender+"违规内容删除短信");//////
						j++;
						flag=0;
						abortBroadcast();
					}
				}
			}
			
			
			//Log.i("FirstBroadcastReceiver", "body:"+body);
			//Log.i("FirstBroadcastReceiver", "sender:"+sender);
			//if(/*""*/.equals(sender)){
				//Log.i("FirstBroadcastReceiver","垃圾短信，立刻终止");
				//abortBroadcast();
			//}
		}
	}
																			//以下为bf算法
	public int BF(String S,String T,int pos){								//以后传入时pos为1
		int m,n;
		if(1<=pos&&pos<=S.length()){
			m=pos;
			n=1;
			while(m<=S.length()&&n<=T.length()){
				if(S.toCharArray()[m-1]==T.toCharArray()[n-1]){
				/*if(S[m-1]==T[n-1]){*/
					++m;
					++n;	
				}
				else{
					m=m-n+2;
					n=1;
				}
			}
			if(n>T.length())
				return m-T.length();
			else
				return 0;
		}
		else
			return 0;	
	}																		//若不存在则返回值为0
	
	
}
