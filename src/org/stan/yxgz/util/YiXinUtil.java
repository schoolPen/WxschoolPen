package org.stan.yxgz.util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;



import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stan.yxgz.pojo.AccessToken;
import org.stan.yxgz.pojo.Menu;




public class YiXinUtil {
	private static Logger log = LoggerFactory.getLogger(YiXinUtil.class);

	public static JSONObject httpRequested(String requestUrl,
			String requestMethod, String outputStr) {

		JSONObject jsonObject = new JSONObject();
		StringBuffer buffer = new StringBuffer();

		try {

			TrustManager[] tm = { new MyTrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());

			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoInput(true);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();

			}

			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {

				buffer.append(str);
			}

			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());

			// log.info("jsonObject IS:"+jsonObject);

		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		}

		catch (Exception e) {
			// TODO: handle exception

			log.error("https request error:{}", e);
		}
		System.out.println("------------------------------"+requestUrl);
		System.out.println(jsonObject);
		return jsonObject;

	}

	//public final static String ACCESS_TOKEN_URL = "https://api.yixin.im/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static AccessToken getAccessToken(String appid, String appSecret) {
		AccessToken accessToken = null;

		String requestUr = ACCESS_TOKEN_URL.replace("APPID", appid).replace(
				"APPSECRET", appSecret);
		JSONObject jsonObject = YiXinUtil.httpRequested(requestUr, "GET", null);

		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				System.out.println("获取全局token:"+accessToken.getToken());
			} catch (JSONException e) {
				accessToken = null;

				log.error("获取token失败", jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	//public final static String MENU_CREATE_URL = "https://api.yixin.im/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;
		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		String jsonMenu = JSONObject.fromObject(menu).toString();
		JSONObject jsonObject = httpRequested(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败", jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));

			}

		}
		return result;

	}
	
	
	//public final static String MENU_DELETE_URL = "https://api.yixin.im/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public static int deleteMenu(String accessToken) {
		int result = 0;
		String url = MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequested(url, "GET", null);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("删除菜单失败", jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));

			}

		}
		return result;

	}
	
	//上次图片到服务端
	public final static String IMAGE_URL="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public static int uploadFile(String accessToken,String type){
		int result=0;
		String url=IMAGE_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		
		return result;
		
	}
	//获取详细信息
	public final static String USER_INFO="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	public static Map<String,Object> getUserInfo(String accessToken,String openId){
		Map<String,Object> map=new HashedMap();
		String url = USER_INFO.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		JSONObject jsonObject = httpRequested(url, "GET", null);
		if (null != jsonObject) {
			
			String nickname = jsonObject.getString("nickname");
			String sex=jsonObject.getString("sex");
			String city=jsonObject.getString("city");
			String province=jsonObject.getString("province");
			String country=jsonObject.getString("country");
			String headimgurl=jsonObject.getString("headimgurl");
			//String unionid=jsonObject.getString("unionid");
			String remark=jsonObject.getString("remark");
			String groupid=jsonObject.getString("groupid");
			map.put("openid", openId);
			map.put("nickname", nickname);
			map.put("sex", sex);
			map.put("country", country);
			map.put("province", province);
			map.put("city", city);
			map.put("headimgurl", headimgurl);
			//map.put("unionid", unionid);
			map.put("remark", remark);
			map.put("groupid", groupid);
			log.error("获取个人信息：subscribe（用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。）--openId:"+openId, jsonObject.getInt("subscribe"));

		}
		return map;
	}
	//发送模板信息
	public final static String messageUrl=PropertyUtils.getWebServiceProperty("messageUrl");
	public final static String TEMPLATE_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	public static int templateMessage(String openId,String courseName,String jine,String courseSid){
		//VTkCS3D45Icj9_67lRf1mMIANNGQ0i9yP7WskLQVBNY
		String json="{"
           +"\"touser\":\""+openId+"\","
           +"\"template_id\":\"VTkCS3D45Icj9_67lRf1mMIANNGQ0i9yP7WskLQVBNY\","
           +"\"url\":\""+messageUrl+"?courseSid="+courseSid+"\"," 
           +"\"data\":{"
			+"\"first\": {"
				+"\"value\":\"恭喜你购买成功！\","
				+"\"color\":\"#173177\""
						+"},"
						+"\"orderProductName\":{"
					+"\"value\":\""+courseName+"\","
					+"\"color\":\"#173177\""
							+"},"
							+"\"orderMoneySum\": {"
						+"\"value\":\""+jine+" RMB\","
						+"\"color\":\"#173177\""
								+"},"
									+"\"Remark\":{"
									+"\"value\":\"欢迎再次购买！\","
									+"\"color\":\"#17317\" }}}";
		
		
		String	appid =PropertyUtils.getWebServiceProperty("appid");
		String	appSecret =PropertyUtils.getWebServiceProperty("appSecret");
		AccessToken at = YiXinUtil.getAccessToken(appid, appSecret);
		int result=0;
		String url=TEMPLATE_URL.replace("ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequested(url, "POST", json);

		if (null != jsonObject) {
			System.out.println("------------TEMPLATE_URL----------"+jsonObject);

			}
		return result;

	}
	
	public static int templateManagerMessage(String templateId,String openId,String title,String courseName,String jine,String courseSid){
		//VTkCS3D45Icj9_67lRf1mMIANNGQ0i9yP7WskLQVBNY
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.ff");
		String json="{"
           +"\"touser\":\""+openId+"\","
           +"\"template_id\":\""+templateId+"\","
           +"\"url\":\""+messageUrl+"?courseSid="+courseSid+"\"," 
           +"\"data\":{"
			+"\"first\": {"
				+"\"value\":\""+title+"\","
				+"\"color\":\"#173177\""
						+"},"
						+"\"tradeDateTime\":{"
					+"\"value\":\""+sdf.format(new Date())+"\","
					+"\"color\":\"#173177\""
							+"},"
							+"\"tradeType\": {"
						+"\"value\":\"购买课程《"+courseName+"》\","
						+"\"color\":\"#173177\""
								+"},"
								+"\"curAmount\": {"
								+"\"value\":\""+jine+" RMB\","
								+"\"color\":\"#173177\""
										+"},"
									+"\"Remark\":{"
									+"\"value\":\"欢迎再次购买！\","
									+"\"color\":\"#17317\" }}}";
		
		
		String	appid =PropertyUtils.getWebServiceProperty("appid");
		String	appSecret =PropertyUtils.getWebServiceProperty("appSecret");
		AccessToken at = YiXinUtil.getAccessToken(appid, appSecret);
		int result=0;
		String url=TEMPLATE_URL.replace("ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequested(url, "POST", json);

		if (null != jsonObject) {
			System.out.println("------------TEMPLATE_URL----------"+jsonObject);

			}
		return result;

	}
	/**
	 * 获取行业模板list
	 */
	public final static String template_list="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
	public static int templateList(String json){
		String	appid =PropertyUtils.getWebServiceProperty("appid");
		String	appSecret =PropertyUtils.getWebServiceProperty("appSecret");
		AccessToken at = YiXinUtil.getAccessToken(appid, appSecret);
		int result=0;
		String url=template_list.replace("ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequested(url, "GET", "");

		if (null != jsonObject) {
			System.out.println("------------templateList----------"+jsonObject);

			}
		return result;

	}

	/**
	 * 获取模板Id
	 */
	public final static String templateId="https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
	public static int templateId(String json){
		json=" {\"template_id_short\":\"1\"}";
		String	appid =PropertyUtils.getWebServiceProperty("appid");
		String	appSecret =PropertyUtils.getWebServiceProperty("appSecret");
		AccessToken at = YiXinUtil.getAccessToken(appid, appSecret);
		int result=0;
		String url=templateId.replace("ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequested(url, "POST", json);

		if (null != jsonObject) {
			System.out.println("------------templateList----------"+jsonObject);

			}
		return result;

	}

	
	
	
	
	public final static String template_set="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
	public static int templateSett(String json){
		json=" {\"industry_id1\":\"1\","
          +"\"industry_id2\":\"2\"}";
		String	appid =PropertyUtils.getWebServiceProperty("appid");
		String	appSecret =PropertyUtils.getWebServiceProperty("appSecret");
		AccessToken at = YiXinUtil.getAccessToken(appid, appSecret);
		int result=0;
		String url=template_set.replace("ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequested(url, "POST", json);

		if (null != jsonObject) {
			System.out.println("------------templateList----------"+jsonObject);

			}
		return result;

	}
	//发送客户消息
	public final static String CUSTOMER_URL="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	public static int send(String json){
		json="{\"touser\": \"oO-NhuAcmuY_0z-f-921RUkGFglc\","
				+"\"msgtype\": \"text\","
				+"\"text\": {"
				+"\"content\": \"Hello World\""
				+"}}\"";
		String	appid =PropertyUtils.getWebServiceProperty("appid");
		String	appSecret =PropertyUtils.getWebServiceProperty("appSecret");
		AccessToken at = YiXinUtil.getAccessToken(appid, appSecret);
		int result=0;
		String url=CUSTOMER_URL.replace("ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = httpRequested(url, "POST", json);

		if (null != jsonObject) {
			System.out.println("----------------------"+jsonObject);

			}
		return result;
	}
}