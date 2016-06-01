package org.stan.yxgz.zhifu;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.HttpRequest;
import org.stan.yxgz.service.WXinterfaceService;
import org.stan.yxgz.util.PropertyUtils;
import org.stan.yxgz.zhifu.util.GetWxOrderno;
import org.stan.yxgz.zhifu.util.RequestHandler;
import org.stan.yxgz.zhifu.util.Sha1Util;
import org.stan.yxgz.zhifu.util.TenpayUtil;

import net.sf.json.JSONObject;

public class MainWx extends HttpServlet {
	//public static Map main(Map<String ,String> map){
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				request.setCharacterEncoding("GBK");
				response.setCharacterEncoding("GBK");
				response.setContentType("text/html;charset=GBK");
				String openId=request.getParameter("openId")+"";
				String shId=request.getParameter("shId")+"";
				//商户订单号
				String courseId=request.getParameter("courseId");
				String out_trade_no=request.getParameter("courseSId");
				
				
				Map<String, Object> map=WXinterfaceService.findStId(shId, out_trade_no);
				String courseName=map.get("courseName")+"";
				courseName=java.net.URLEncoder.encode(courseName, "GBK");
				String teacherName=map.get("teacherName")+"";
				teacherName=java.net.URLEncoder.encode(teacherName, "GBK");
				
				String teachAddress=map.get("teachAddress")+"";
				teachAddress=java.net.URLEncoder.encode(teachAddress, "GBK");
				String courseInfo=map.get("teachAddress")+"";
				courseInfo=java.net.URLEncoder.encode(courseInfo, "GBK");
				
				String params="&courseSid="+map.get("courseSid")+"&courseName="+courseName+"&courseId="+map.get("courseId")+"&year="+map.get("year")+"&date="+map.get("startDate")+"-"+map.get("endDate")+"&teacherName="+teacherName+"&teachTime="+map.get("teachTime")+"&teachAddress="+teachAddress+"&courseInfo="+courseInfo+"&courseJe="+map.get("courseJe")+"&totalCourse="+map.get("totalCourse")+"&userId="+map.get("userId");
				String orderId=map.get("payId")+"";
				System.out.println("findStId------------------"+orderId);
				//网页授权后获取传递的参数
				
				
				//商户相关资料 
				
				String appid=PropertyUtils.getWebServiceProperty("appid");
				String partner=PropertyUtils.getWebServiceProperty("WxPayConfig.MCHID");
				String notifyurl=PropertyUtils.getWebServiceProperty("WxPayConfig.NOTIFY_URL");
				String device=PropertyUtils.getWebServiceProperty("WxPayConfig.DEVICE");
				String partnerkey=PropertyUtils.getWebServiceProperty("WxPayConfig.KEY");
				String appsecret=PropertyUtils.getWebServiceProperty("appSecret");
				
				
				
				//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
				String currTime = TenpayUtil.getCurrTime();
				//8位日期
				String strTime = currTime.substring(8, currTime.length());
				//四位随机数
				String strRandom = TenpayUtil.buildRandom(4) + "";
				//10位序列号,可以自行调整。
				String strReq = strTime + strRandom;
				
				
				//商户号
				String mch_id = partner;
				//子商户号  非必输
//						String sub_mch_id="";
				//设备号   非必输
				String device_info="";
				//随机数 
				String nonce_str = strReq;
				//商品描述
//						String body = describe;
				
				//商品描述根据情况修改
				String body = map.get("courseName")+"";
				//附加数据
				String attach = map.get("userId")+strRandom;
				attach=strRandom;
				String app = PropertyUtils.getWebServiceProperty("isTest");
				String money=map.get("courseJe")+"";
				System.out.println("is tesing :"+app+"-----------je----------:"+money);
				if(app.equals("true")){
					money="0.01";
				}
				//金额转化为分为单位
				String finalmoney="1";
				if(money.startsWith("0")){
					float sessionmoney=Float.parseFloat(money)*100;
					finalmoney=(int)sessionmoney+"";
						
				}else{
					float sessionmoney = Float.parseFloat(money);
					finalmoney = String.format("%.2f", sessionmoney);
					finalmoney = finalmoney.replace(".", "");
				}
				
				int intMoney = Integer.parseInt(finalmoney);
				
				//总金额以分为单位，不带小数点
				int total_fee = intMoney;
				//订单生成的机器 IP
				String spbill_create_ip =request.getRemoteAddr()+"";
				//String spbill_create_ip="163.177.94.114";
				//订 单 生 成 时 间   非必输
//						String time_start ="";
				//订单失效时间      非必输
//						String time_expire = "";
				//商品标记   非必输
//				String goods_tag = "";
		
				//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
				//String notify_url ="http://www.oepp.cn/WxschoolPen/zhifu1/success.jsp";
				
				
				String trade_type = "JSAPI";
				String openid = openId;
				String prepay_id="";
				
				RequestHandler reqHandler = new RequestHandler(request, response);
				reqHandler.init(appid, appsecret, partnerkey);
				
				//if(orderId.equals("{}")){
					//非必输
//						String product_id = "";
					SortedMap<String, String> packageParams = new TreeMap<String, String>();
					packageParams.put("appid", appid);  
					packageParams.put("mch_id", mch_id);  
					packageParams.put("nonce_str", nonce_str);  
					packageParams.put("body", body);  
					packageParams.put("attach", attach);  
					out_trade_no=excuteString(out_trade_no);
					packageParams.put("out_trade_no", out_trade_no);  
					
					
					//这里写的金额为1 分到时修改
				//	packageParams.put("total_fee", "1");  
					packageParams.put("total_fee", finalmoney);  
					packageParams.put("spbill_create_ip", spbill_create_ip);  
					packageParams.put("notify_url", notifyurl);  
					
					packageParams.put("trade_type", trade_type);  
					packageParams.put("openid", openid);  

					
					
					
					
					String sign = reqHandler.createSign(packageParams);
					String xml="<xml>"+
							"<appid>"+appid+"</appid>"+
							"<mch_id>"+mch_id+"</mch_id>"+
							"<nonce_str>"+nonce_str+"</nonce_str>"+
							"<sign>"+sign+"</sign>"+
							"<body><![CDATA["+body+"]]></body>"+
							"<attach>"+attach+"</attach>"+
							"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
							"<attach>"+attach+"</attach>"+
		//金额，这里写的1 分到时修改
//							"<total_fee>"+1+"</total_fee>"+
								"<total_fee>"+finalmoney+"</total_fee>"+
							"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
							"<notify_url>"+notifyurl+"</notify_url>"+
							"<trade_type>"+trade_type+"</trade_type>"+
							"<openid>"+openid+"</openid>"+
							"</xml>";
					System.out.println(xml);
					String allParameters = "";
					try {
						allParameters =  reqHandler.genPackage(packageParams);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
					Map<String, Object> dataMap2 = new HashMap<String, Object>();
					
					try {
						System.out.println("-------------begin------------createOrderURL-------------");
						prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
						System.out.println("------------------------"+prepay_id);
						/*if(StringUtils.isNotBlank(prepay_id)){
							WXinterfaceService.isZhifuOut(out_trade_no,prepay_id);
						}*/
						System.out.println("-------------------------createOrderURL-------------"+prepay_id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			/*}else{
				prepay_id=orderId;
			}*/
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String appid2 = appid;
			String timestamp = Sha1Util.getTimeStamp();
			String nonceStr2 = nonce_str;
			String prepay_id2 = "prepay_id="+prepay_id;
			String packages = prepay_id2;
			finalpackage.put("appId", appid2);  
			finalpackage.put("timeStamp", timestamp);  
			finalpackage.put("nonceStr", nonceStr2);  
			finalpackage.put("package", packages);  
			finalpackage.put("signType", "MD5");
			String finalsign = reqHandler.createSign(finalpackage);
			finalpackage.put("finalpackage", finalsign);
			System.out.println("--------------finalsign-------------------"+finalsign);
			response.sendRedirect("/WxschoolPen/zhifu/buy.jsp?openId="+openId+"&appid="+appid2+"&timeStamp="+timestamp+"&nonceStr="+nonceStr2+"&package="+packages+"&sign="+finalsign+"&orderId="+out_trade_no+params);
	}
	public String excuteString(String out_trade_no){
		char [] ch=out_trade_no.toCharArray();
		int a=RandomUtils.nextInt(30);
		int i=0;
		StringBuffer b=new StringBuffer();
		for(char c:ch){
			if(i==a){
				b.append("8");
			}else{
				b.append(c);
			}
			i++;
		}
		System.out.println(out_trade_no+"---"+b.toString());
	return b.toString();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		doGet(request, response);
	}

}
