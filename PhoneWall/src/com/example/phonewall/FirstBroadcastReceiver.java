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
	
	private int i=0;//������һ��������Ҫ�õı���
	public static int j=0;//��ʾ���������Ŷ�����//////////////////
	private int flag=0;
	

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		SharedPreferences dxhc=context.getSharedPreferences("���ź�����", Context.MODE_PRIVATE);
		SharedPreferences dxbc=context.getSharedPreferences("���Ű�����", Context.MODE_PRIVATE);
		SharedPreferences dxscjl=context.getSharedPreferences("����ɾ����¼", Context.MODE_PRIVATE);
		
		
		Object[] objs=(Object[]) intent.getExtras().get("pdus");
		for(Object obj:objs){
			SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) obj);
			String body=smsMessage.getMessageBody();
			String sender=smsMessage.getOriginatingAddress();
			
			
			/*if(���ڰ�������){
				if(�ں�������){
					��Ҫ�ϳ��㲥
				}
				else{
					���������ж�
				}
			}*/
			
			for(i=0;i<MainActivity.j;i++){//////////////////////////////???????????//?????
				if(     (  "1555521"+dxbc.getString(MainActivity.phonenumber[i], "")  ).equals(sender)      ){//��ִ�д˾����ڰ�������
					this.flag=1;
					Log.i("��sms��������","");
					break;
				}
			}
			if(this.flag==0){										//���ٰ�������
				for(i=0;i<MainActivity.i;i++){//////////////////////???????????????????
					if(("1555521"+dxhc.getString(MainActivity.phonenumber[i], "")).equals(sender)){//��ִ�д˾����ں�������
						Log.i("��sms��������","");
						this.flag=1;

						break;
					}
				}
				if(this.flag==1){									//��ʱ˵���ں�������
					Log.i("FirstBroadcastReceiver","����sms���룬������ֹ");
																						/*�˴������Ҫ��Ϣ��sharedpreferences*/
					
					Editor editor=dxscjl.edit();
					editor.putString(MainActivity.phonenumber[j],sender+"����������ɾ������");//����д��������/////
					j++;
					flag=0;
					abortBroadcast();
				}
				else{
					if(BF(body,"fuck",1)!=0){										//��ʱ��������������
						/**/
						Log.i("FirstBroadcastReceiver","����sms���ݣ�������ֹ");
						
						Editor editor=dxscjl.edit();
						editor.putString(MainActivity.phonenumber[j], sender+"Υ������ɾ������");//////
						j++;
						flag=0;
						abortBroadcast();
					}
				}
			}
			
			
			//Log.i("FirstBroadcastReceiver", "body:"+body);
			//Log.i("FirstBroadcastReceiver", "sender:"+sender);
			//if(/*""*/.equals(sender)){
				//Log.i("FirstBroadcastReceiver","�������ţ�������ֹ");
				//abortBroadcast();
			//}
		}
	}
																			//����Ϊbf�㷨
	public int BF(String S,String T,int pos){								//�Ժ���ʱposΪ1
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
	}																		//���������򷵻�ֵΪ0
	
	
}
