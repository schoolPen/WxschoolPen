package org.stan.yxgz.service;

import java.util.Calendar;

import org.stan.yxgz.util.HttpCommonUtil;
import org.stan.yxgz.util.MessageUtil;

public class a {

	
	public static void main(String[]args) throws Exception{
		//HttpCommonUtil.transMyCourseForJson("");
		String time="2015-04-30 08:00:00";
		String date=time.substring(0,time.indexOf(" "));
		String hour=time.substring(time.indexOf(" "),time.length()-3);
		System.out.println(date+"----------"+hour);
		//calutate();
		
		Calendar now = Calendar.getInstance();  
	     String currentArea=now.get(Calendar.HOUR_OF_DAY)+"";
	     System.out.println(currentArea);
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void calutate(){
		double currentHight=0.00;    //当前最高
		double currentClose=24.21;   //第9日的收盘价
		double currentLower=23.38;   //9日内最低
		double beforHight=25.65;   //N日最高  n=9   为最高计算方法
		
		double rsv=((currentClose-currentLower)/(beforHight-currentLower))*100;
		System.out.println("第9日的RSV值"+rsv);
		double bbeforK=50.71;   //前一日k值
		double bbeforD=41.75;   //前一日d值    第8日
		if(bbeforK==0.00)
			bbeforK=50.00;
		if(bbeforD==0.00)
			bbeforD=50.00;
		
		//K值=2/3×第8日K值+1/3×第9日RSV
			//	D值=2/3×第8日D值+1/3×第9日K值
				//J值=3*第9日K值-2*第9日D值
				//若无前一日K值与D值，则可以分别用50代替。
		System.out.println(2%3);
		
		double k = (float)2/3*bbeforK+(float)1/3*rsv;
		System.out.println("k:"+k);
		double d=(float)2/3*bbeforD+(float)1/3*k;
		System.out.println("d:"+d);
		double j=(float)3*k-(float)2*d;
		System.out.println("j:"+j);
		if(d>=70){
			System.out.println("超买区，需要卖掉");
		}
		if(d<=30){
			System.out.println("超卖区，需要进仓");
		}
	}
}
