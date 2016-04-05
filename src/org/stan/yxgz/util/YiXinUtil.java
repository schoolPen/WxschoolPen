package org.stan.yxgz.util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;



import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

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
				// log.info("获取token成功！"+accessToken);
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