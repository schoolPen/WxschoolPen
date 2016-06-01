<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="org.stan.yxgz.util.PropertyUtils"%>
<%@page import="org.stan.yxgz.util.UrlUtil"%>
<%@page import="org.stan.yxgz.util.UrlUtil.HttpRequestData"%>
<%
	String path1 = request.getContextPath();
    String root1=request.getScheme()+"://"+request.getServerName()+path1;
    
	
	
	
	
	 String	appid =PropertyUtils.getWebServiceProperty("appid");
	String	appSecret =PropertyUtils.getWebServiceProperty("appSecret");

	String code=request.getParameter("code");
	String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	url=url.replace("APPID", appid).replace("SECRET", appSecret).replace("CODE", code);
	HttpRequestData data = UrlUtil.sendGet(url);
	String json=data.getResult();
	JSONObject obj = JSONObject.fromObject(json);
	String openid =obj.get("openid").toString();  
	String urls=root1+"/index/minCourstList.do?openId="+openid;
	System.out.println("-----------minCourstList-----------------"+urls);

	%>
	
	
	

<script language="javascript">
	function process(url){
		self.location = url;
	}
</script>
<html>
	<head>
		<title></title>
	</head>
	<body onload="javascript: process('<%=urls%>');">
	</body>
</html>
