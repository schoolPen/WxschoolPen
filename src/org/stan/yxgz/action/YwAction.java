package org.stan.yxgz.action;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.stan.yxgz.pojo.User;
import org.stan.yxgz.service.CommonService;
import org.stan.yxgz.service.WXinterfaceService;
import org.stan.yxgz.util.KanqActionSupport;
import org.stan.yxgz.util.PropertyUtils;
import org.stan.yxgz.util.YiXinUtil;
import org.stan.yxgz.zhifu.util.GetWxOrderno;
import org.stan.yxgz.zhifu.util.RequestHandler;
import org.stan.yxgz.zhifu.util.Sha1Util;
import org.stan.yxgz.zhifu.util.TenpayUtil;


import com.dt.dtpt.mybatis.model.sijiao.EduCourse;
import com.dt.dtpt.mybatis.model.sijiao.EduStudent;
import com.dt.dtpt.service.sijiao.SijiaoService;
/*
*<P>维权信息查询</P>
*
*消费维权设计方法
*
*@author  chend
*/

@Controller
@RequestMapping("index")
public class YwAction extends KanqActionSupport {

/*	@Resource
	private IOperatorService operatorService;
*/
	private User user=new User();
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	

	private SijiaoService sijiao;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@RequestMapping(value = "mineClass", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView mineClass() throws Exception {
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/mine-process");
		return view;
	}
	
	@RequestMapping(value = "tempCourse", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView tempCourse() throws Exception {
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/temp-course");
		return view;
	}
	@RequestMapping(value = "tempMinCourse", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView tempMinCourse() throws Exception {
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/temp-minecourse");
		return view;
	}
	@RequestMapping(value = "tempProcess", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView tempProcess() throws Exception {
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/temp-process");
		return view;
	}
	
	//SijiaoService sijiao1;
	@RequestMapping(value = "courceList", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView courceList() throws Exception {
		String shId=request.getParameter("shId");
		shId=StringUtils.isBlank(shId)?PropertyUtils.getWebServiceProperty("shId"):shId;
		String openId=request.getParameter("openId");
		String pageSize=request.getParameter("pageSize");
		String pageNuber=request.getParameter("pageNuber");
		pageSize=StringUtils.isBlank(pageSize)?"20":pageSize;
		pageNuber=StringUtils.isBlank(pageNuber)?"1":pageNuber;
		String results=CommonService.isWxManerger(shId, openId);
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/courceList");
		
		
		Map<String, Object> result = WXinterfaceService.findWxCourses(null, shId, pageNuber, pageSize);
		
		view.addObject("dataList", result);
		view.addObject("shId", shId);
		view.addObject("openId", openId);
		view.addObject("isAdmin", results);
		return view;
	}
	
	//添加课程
	@RequestMapping(value = "addCource")
	@ResponseBody
	public Object addCource() throws Exception {
		Map<String,Object> map =new HashMap<String, Object>();
		String shId=request.getParameter("shId");
		String openId=request.getParameter("openId");
		
		String courceName=request.getParameter("courceName");
		String courceType=request.getParameter("courceType");
		String teacherName=request.getParameter("teacherName");
		String btime=request.getParameter("btime");
		String etime=request.getParameter("etime");
		String ptime=request.getParameter("ptime");
		String period=request.getParameter("period");
		String money=request.getParameter("money");
		String paddress=request.getParameter("paddress");
		String pcount=request.getParameter("pcount");
		String ptotal=request.getParameter("ptotal");
		
		String common=request.getParameter("common");
		
		EduCourse course=new EduCourse();
		course.setCourseName(courceName);
		course.setTeacherName(teacherName);
		course.setCommon(common);
		course.setSubject(courceType);
		course.setMaxStudents(Integer.parseInt(ptotal));
		//course.setYear(period);
		course.setCourseInfo(common);
		course.setSemester(Integer.parseInt(period));
		course.setSubjectSub(courceType);
		course.setStartDate(sdf.parse(btime));
		course.setEndDate(sdf.parse(etime));
		course.setTeachTime(ptime);
		course.setTeachAddress(paddress);
		course.setTotalCourse(Integer.parseInt(pcount));
		course.setCourseJe(BigDecimal.valueOf(Double.parseDouble(money)));
		Map<String, Object> resultC = WXinterfaceService.addCourseByWx(course, openId, shId);
		System.out.println("添加课程"+resultC);
		System.out.println("添加课程状态："+resultC.get("state").toString());
		Object state = resultC.get("state");
		
		map.put("state", state);
		map.put("shId", shId);
		map.put("openId", openId);
		return JSONObject.fromObject(map);
	}
	
	
	@RequestMapping(value = "preAddCource", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView preAddCource() throws Exception {
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/courceAdd");
		String shId=request.getParameter("shId");
		shId=StringUtils.isBlank(shId)?PropertyUtils.getWebServiceProperty("shId"):shId;
		String openId=request.getParameter("openId");
		view.addObject("shId", shId);
		view.addObject("openId", openId);
		return view;
	}
	
	
	
	@RequestMapping(value = "register", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView register() throws Exception {
		String openId=request.getParameter("openId");
		String shId=request.getParameter("shId");
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/register");
		view.addObject("shId", shId);
		view.addObject("openId", openId);
		return view;
	}
	@RequestMapping(value = "isLogin" )
	@ResponseBody
	public Object isLogin(){
		String openId=request.getParameter("openId");
		Map map=WXinterfaceService.findStudentByOpenId(openId);
		return JSONObject.fromObject(map);
	}
	@RequestMapping(value = "subUser", method = RequestMethod.POST )
	@ResponseBody
	public ModelAndView subUser() throws ParseException{
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/buy");
		String shId=request.getParameter("shId");
		String studentName=request.getParameter("studentName");
		String phone=request.getParameter("phone");
		String openId=request.getParameter("openId");
		String sex=request.getParameter("sex");
		String address=request.getParameter("address");
		String bornYear=request.getParameter("bornYear");
		String inSchool=request.getParameter("inSchool");
		Date bronDate=sdf.parse(bornYear);
		EduStudent stu=new EduStudent();
		stu.setAddress(address);
		stu.setSex(Integer.parseInt(sex));
		stu.setBirthday(bronDate);
		//stu.setCommon(common);
		stu.setIsInschool(Integer.parseInt(inSchool));
		stu.setStudentName(studentName);
		stu.setPhone(phone);
		//stu.setUserId(openId);
		stu.setWxOpenid(openId);
		Map<String,Object> map = WXinterfaceService.addStudentByWx(stu,openId,shId);
			
		return view;
	}
	
	
	@RequestMapping(value = "courseBuy" )
	@ResponseBody
	public ModelAndView courseBuy() throws ParseException{
		ModelAndView view=new ModelAndView("/zhifu1/buy");
		String shId=request.getParameter("shId");
		String openId=request.getParameter("openId");
		String courseId=request.getParameter("courseId");
		String courseSId=request.getParameter("courseSid");
		Map map=WXinterfaceService.getCourse(courseId);
		if(StringUtils.isBlank(courseSId)){
			Map cmap=WXinterfaceService.addCourseByWx(openId, courseId);
			courseSId=cmap.get("courseSid")+"";
		}
		Map course=(Map)map.get("data");
		//调用微信支付功能  统一订单
		view.addObject("shId", shId);
		view.addObject("openId", openId);
		view.addObject("courseId",courseId);
		view.addObject("courseSid",courseSId);
		view.addAllObjects(map);
		return view;
	}
	
	@RequestMapping(value = "addMineCourse" )
	@ResponseBody
	public Object addMineCourse() throws ParseException{
		String openId=request.getParameter("openId");
		String courseId=request.getParameter("courseId");
		Map cmap=WXinterfaceService.addCourseByWx(openId, courseId);
		//return cmap.get("courseSid");
		Map m=new HashMap<String, String>();
		m.put("data", cmap.get("courseSid"));
		return JSONObject.fromObject(m);
	}
	
	@RequestMapping(value = "courseZhiFu" )
	@ResponseBody
	public ModelAndView courseZhiFu() throws ParseException{
		ModelAndView view=new ModelAndView("/zhifu1/buy");
		String shId=request.getParameter("shId");
		String openId=request.getParameter("openId");
		String courseId=request.getParameter("courseId");
		Map map=WXinterfaceService.getCourse(courseId);
		Map cmap=WXinterfaceService.addCourseByWx(openId, courseId);
		Map course=(Map)map.get("data");
		
		view.addObject("shId", shId);
		view.addObject("openId", openId);
		view.addObject("courseId",courseId);
		view.addObject("courseSid",cmap.get("courseSid"));
		view.addAllObjects(map);
		return view;
	}
	
	public Object orderInfo(){
		Map<String,String> data=new HashMap<String, String>();
		String shId=request.getParameter("shId");
		String openId=request.getParameter("openId");
		String out_trade_no=request.getParameter("courseSId");
		String userId=request.getParameter("userId");
		String jine=request.getParameter("courseJe");
		String courseName=request.getParameter("courseName");
		//调用微信支付功能  统一订单
		String ip=request.getRemoteAddr();
		String strRandom = TenpayUtil.buildRandom(4) + "";

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
		//10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		
		
		//商户号
		String mch_id = partner;
		//子商户号  非必输
//				String sub_mch_id="";
		//设备号   非必输
		String device_info="";
		//随机数 
		String nonce_str = strReq;
		//商品描述
//				String body = describe;
		
		//商品描述根据情况修改
		String body = courseName;
		//附加数据
		String attach = userId+strRandom;
		attach=strRandom;
		String app = PropertyUtils.getWebServiceProperty("isTest");
		String money=jine;
		System.out.println("is tesing :"+app+"-----------je----------:"+money);
		if(app.equals("true")){
			money="1";
		}
		//金额转化为分为单位
		float sessionmoney = Float.parseFloat(money);
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		
		int intMoney = Integer.parseInt(finalmoney);
		
		//总金额以分为单位，不带小数点
		int total_fee = intMoney;
		//订单生成的机器 IP
		String spbill_create_ip =request.getRemoteAddr()+"";
		//String spbill_create_ip="163.177.94.114";
		//订 单 生 成 时 间   非必输
//				String time_start ="";
		//订单失效时间      非必输
//				String time_expire = "";
		//商品标记   非必输
//		String goods_tag = "";

		//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		//String notify_url ="http://www.oepp.cn/WxschoolPen/zhifu1/success.jsp";
		
		
		String trade_type = "JSAPI";
		String openid = openId;
		String prepay_id="";
		
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(appid, appsecret, partnerkey);
//				String product_id = "";
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", appid);  
			packageParams.put("mch_id", mch_id);  
			packageParams.put("nonce_str", nonce_str);  
			packageParams.put("body", body);  
			packageParams.put("attach", attach);  
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
//					"<total_fee>"+1+"</total_fee>"+
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
	finalpackage.put("shId", shId);
	finalpackage.put("openId", openId);
		return finalpackage;
	}
	
	
	@RequestMapping(value = "isZhifuOut" )
	@ResponseBody
	public Object isZhifuOut() throws ParseException{
		String courseSid=request.getParameter("courseSid");
		String orderId=request.getParameter("orderId");
		Map map=WXinterfaceService.isZhifuOut(courseSid,orderId);
		return map;
	}
	@RequestMapping(value = "message" )
	@ResponseBody
	public Object message() throws ParseException, UnsupportedEncodingException{
		String openId=request.getParameter("openId");
		String courseName=request.getParameter("courseName");
		String courseSid=request.getParameter("courseSid");
		courseName=java.net.URLDecoder.decode(courseName, "UTF-8");
		String jine=request.getParameter("payJe");
		YiXinUtil.templateMessage(openId, courseName, jine,courseSid);
		//是否发送管理员
		WXinterfaceService.sendMessageToManager(openId, courseName, jine,courseSid);
		
		return "true";
	}
	
	@RequestMapping(value = "payOk" )
	@ResponseBody
	public Object payOk() throws ParseException{
		String openId=request.getParameter("openId");
		String courseSid=request.getParameter("courseSid");
		String payJe=request.getParameter("payJe");
		Map map=WXinterfaceService.payOk(courseSid,payJe);
		return map;
	}
	//user.min.course.list
	@RequestMapping(value = "minCourstList" )
	@ResponseBody
	public ModelAndView minCourstList() throws ParseException{
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/mine-courseList");
		String shId=request.getParameter("shId");
		shId=StringUtils.isBlank(shId)?PropertyUtils.getWebServiceProperty("shId"):shId;
		String openId=request.getParameter("openId");
		Map map=WXinterfaceService.findMineCourses(shId,openId);
		
		view.addObject("shId", shId);
		view.addObject("openId", openId);
		view.addObject("dataList", map);
		return view;
	}
	
	@RequestMapping(value = "minDetailCourse" )
	@ResponseBody
	public ModelAndView minDetailCourse() throws ParseException{
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/courseDetail");
		String shId=request.getParameter("shId");
		shId=StringUtils.isBlank(shId)?PropertyUtils.getWebServiceProperty("shId"):shId;
		String courseSid=request.getParameter("courseSid");
		Map map=WXinterfaceService.findStId(shId,courseSid);
		view.addObject("shId", shId);
		view.addObject("dataList", map);
		return view;
	}
	
	@RequestMapping(value = "minCourstLCList" )
	@ResponseBody
	public ModelAndView minCourstLCList() throws ParseException{
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/mine-process");
		String shId=request.getParameter("shId");
		shId=StringUtils.isBlank(shId)?PropertyUtils.getWebServiceProperty("shId"):shId;
		String openId=request.getParameter("openId");
		Map map=WXinterfaceService.findMineCoursesProcess(shId,openId);
		
		view.addObject("shId", shId);
		view.addObject("openId", openId);
		view.addObject("dataList", map);
		return view;
	}
	
	@RequestMapping(value = "subCourse", method = RequestMethod.POST )
	@ResponseBody
	public ModelAndView subCourse(){
		ModelAndView view=new ModelAndView("/WEB-INF/page/index/courceList");
		String courceName=request.getParameter("courceName");
		String courceType=request.getParameter("courceType");
		String teacherName=request.getParameter("teacherName");
		String period=request.getParameter("period");
		String btime=request.getParameter("btime");
		String etime=request.getParameter("etime");
		String ptime=request.getParameter("ptime");
		String address=request.getParameter("address");
		String pcount=request.getParameter("pcount");
		String ptotal=request.getParameter("ptotal");
		String common=request.getParameter("common");
		
		return view;
	}
	
	
	@RequestMapping(value = "isExist", method = RequestMethod.GET )
	@ResponseBody
	public String isExist(){
		String rspData = "false";
	
		return rspData;
	}
	
	/*@RequestMapping(value = "addOperator", method = RequestMethod.POST)
	@ResponseBody
	public Map addOperator(@ModelAttribute OperatorEntity user, HttpServletRequest request) {
		Map jsonMap = new HashMap();
		String orgName=Utils.objectToNoEmpty(request.getParameter("orgName"),"");
		String orgCode=Utils.objectToNoEmpty(request.getParameter("orgCode"),"");
		String moreOrgName=Utils.objectToNoEmpty(request.getParameter("moreOrgName"),"");
		String moreOrgCode=Utils.objectToNoEmpty(request.getParameter("moreOrgCode"),"");
		//String createAuthor=Utils.objectToNoEmpty(OperatorUtils.getOperator().getAccount(),"");
		String password=user.getPassword();
		MD5 theMD5 = new MD5();
		password = theMD5.getMD5ofStr(password);
		try {
			user.setPassword(password);
			user.setOperator_id(UUID.randomUUID().toString());
			user.setOrg_code(orgCode);
			user.setOrg_name(orgName);
			user.setMore_org_code(moreOrgCode);
			user.setMore_org_name(moreOrgName);
			user.setCreate_author("");
			user.setCreate_time(new Date());
			
			operatorService.addOperator(user);
			jsonMap.put("result", "0");
			jsonMap.put("reason", "成功");
		} catch (Exception e) {
			jsonMap.put("result", "1");
			jsonMap.put("reason", "失败");
			System.out.println("testUserAction json data is error:::::::"
					+ e.getMessage());
		} finally {
			return jsonMap;
		}
	}
*/
	
	
}
