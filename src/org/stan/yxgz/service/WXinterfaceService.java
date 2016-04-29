package org.stan.yxgz.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.stan.yxgz.pojo.User;
import org.stan.yxgz.util.PropertyUtils;
import org.stan.yxgz.util.UrlUtil;
import org.stan.yxgz.util.UrlUtil.HttpRequestData;

import com.dt.dtpt.mybatis.model.sijiao.EduCourse;
import com.dt.dtpt.mybatis.model.sijiao.EduStudent;
import com.dt.dtpt.util.Result;

public class WXinterfaceService {

	/**
	 * 是否为管理员
	 * 
	 * @param shId 管理员用户编号，不能为空
	 * @param userOpenID 当前操作用户微信OPENID，不能为空
	 * @return 是返回对象的success属性为true，否则为false public Result isWxManerger(String shId,String userOpenID);
	 */
	public static Map<String, Object> isWxManerger(User user)
			throws UnsupportedEncodingException {
		// 绑定
		String url = PropertyUtils.getWebServiceProperty("sijiao.isWxManerger");

		String json = "";
		String app = PropertyUtils.getWebServiceProperty("app");
		try {
			if (app.equals("true")) {
				HttpRequestData data = UrlUtil.sendGet(url);
				json = data.getResult();

			} else
				json = "{\"success\":true,\"title\":\"操作成功\",\"reason\":\"操作成功\",\"result\":{studentId:0bd22f1f-2aee-4e74-8ee0-144ffedd3822}}";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(json);
		boolean state = Boolean.valueOf(obj.get("success").toString());
		result.put("state", state);
		result.put("reason", obj.get("reason"));
		result.put("title", obj.get("title"));
		if (state) {
			JSONObject reObj = JSONObject.fromObject(obj.get("result"));
			result.put("userId", reObj.get("studentId"));
		}

		System.out.println(result.get("state").toString()
				+ result.get("reason").toString()
				+ result.get("title").toString());
		return result;
	}

	/**
	 * 用户获取课程列表
	 * 
	 * @param shId 管理员用户编号，不能为空
	 * @param eduCourse 精准匹配的课程条件对象
	 * @param pageNumber  页码，不能为空
	 * @param pageSize  每页数据行数，不能为空
	 * @return 返回对象的success为true时，result属性为List<EduCourse>
	 * @GET
	 * @Path("/findWxCourses/{shId /{pageNumber}/{pageSize}")
	 */
	public static Map<String, Object> findWxCourses(EduCourse eduCourse) {
		String url = PropertyUtils.getWebServiceProperty("sijiao.findWxCourses");
		//url=url.replace("eduCourse", eduCourse);
		EduCourse eC = new EduCourse();
		JSONObject json1 = JSONObject.fromObject(eC);
		String str = json1.toString();
		//System.out.println(str);
		String json = "";
		String app = PropertyUtils.getWebServiceProperty("app");
		try {
			if (app.equals("true")) {
				//HttpRequestData data = UrlUtil.sendGet(url);
				HttpRequestData data = UrlUtil.sendPost(url, "aa","{}");
				json = data.getResult();
			} else
				json = "{\"success\":true,\"title\":\"操作成功\",\"reason\":\"操作成功\",\"result\":{studentId:0bd22f1f-2aee-4e74-8ee0-144ffedd3822}}";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();

		JSONObject obj = JSONObject.fromObject(json);
		boolean state = Boolean.valueOf(obj.get("success").toString());
		result.put("state", state);
		result.put("reason", obj.get("reason"));
		result.put("title", obj.get("title"));

		System.out.println(result.get("state").toString()+ result.get("reason").toString()+ result.get("title").toString()+"interface");
		return result;

	}

	/**
	 * 新增课程
	 * @param shId 管理员用户编号，不能为空
	 * @param userOpenID 当前操作用户微信OPENID，不能为空
	 * @param course  需要添加的课程信息：课程名称、课程类型、老师姓名、学年、学期、开始日期、结束日期、上课时间、上课地点、节数、限制人数、 课程内容
	 * @return 返回对象的success属性为true是，操作成功；否则操作失败
	 * String shId, String userOpenID, 
	 */
	public static Map<String, Object> addCourseByWx(EduCourse course) {
		String url = PropertyUtils.getWebServiceProperty("sijiao.addCourseByWx");

		String json = "";
		String app = PropertyUtils.getWebServiceProperty("app");
		try {
			if (app.equals("true")) {
				//HttpRequestData data = UrlUtil.sendGet(url);
				HttpRequestData data = UrlUtil.sendPost(url, "aa","{}");
				json = data.getResult();
			} else
				json = "{\"success\":true,\"title\":\"操作成功\",\"reason\":\"操作成功\",\"result\":{studentId:0bd22f1f-2aee-4e74-8ee0-144ffedd3822}}";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();

		JSONObject obj = JSONObject.fromObject(json);
		boolean state = Boolean.valueOf(obj.get("success").toString());
		result.put("state", state);
		result.put("reason", obj.get("reason"));
		result.put("title", obj.get("title"));

		System.out.println("新增课程:"+result.get("state").toString()+ result.get("reason").toString()+ result.get("title").toString());
		return result;
	}

	/**
	 * 获取用户下已添加学员
	 * 
	 * @param userOpenID  当前操作用户微信OPENID，不能为空
	 * @return 返回对象的success属性为true时，result属性为List<EduStudent>
	 * String userOpenID
	 */
	public static Map<String, Object> findEduStudents() {
		String url = PropertyUtils.getWebServiceProperty("sijiao.findEduStudents");
		String json = "";
		String app = PropertyUtils.getWebServiceProperty("app");
		try {
			if (app.equals("true")) {
				HttpRequestData data = UrlUtil.sendGet(url);
				json = data.getResult();
			} else
				json = "{\"success\":true,\"title\":\"操作成功\",\"reason\":\"操作成功\",\"result\":{studentId:0bd22f1f-2aee-4e74-8ee0-144ffedd3822}}";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(json);
		boolean state = Boolean.valueOf(obj.get("success").toString());
		result.put("state", state);
		result.put("reason", obj.get("reason"));
		result.put("title", obj.get("title"));
		

		System.out.println("获取用户下已添加学员:"+result.get("state").toString() 
				+ result.get("reason").toString() + result.get("title").toString());
		
		return result;
	}

	/**
	 * 添加学员
	 * @param shId  管理员用户编号，不能为空
	 * @param userOpenID  当前操作用户微信OPENID，不能为空
	 * @param eduStudent  需要添加的学员信息：姓名、手机、住址、出生年月、性别、学年级
	 * @return 返回对象的success属性值为true时，添加成功，result值为EduStudent对象；否则添加失败
	 * String shId, String userOpenID,
	 */
	public static Map<String, Object> addStudentByWx(EduStudent eduStudent) {
		String url = PropertyUtils.getWebServiceProperty("sijiao.addStudentByWx");

		String json = "";
		String app = PropertyUtils.getWebServiceProperty("app");
		try {
			if (app.equals("true")) {
				//HttpRequestData data = UrlUtil.sendGet(url);
				//json = data.getResult();
				HttpRequestData data = UrlUtil.sendPost(url, "aa","{}");
				json = data.getResult();
			} else
				json = "{\"success\":true,\"title\":\"操作成功\",\"reason\":\"操作成功\",\"result\":{studentId:0bd22f1f-2aee-4e74-8ee0-144ffedd3822}}";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();

		JSONObject obj = JSONObject.fromObject(json);
		boolean state = Boolean.valueOf(obj.get("success").toString());
		result.put("state", state);
		result.put("reason", obj.get("reason"));
		result.put("title", obj.get("title"));
		if (state) {
			JSONObject reObj = JSONObject.fromObject(obj.get("result"));
			result.put("userId", reObj.get("studentId"));
		}

		System.out.println("添加学员："+result.get("state").toString()
				+ result.get("reason").toString()
				+ result.get("title").toString());
		return result;
	}

	/**
	 * 学员添加课程
	 * 
	 * @param userOpenID  当前操作用户微信OPENID，不能为空
	 * @param courseId    需要添加的课程编号，不能为空
	 * @return 返回对象的success属性值为true时，添加成功；否则添加失败
	 * String userOpenID, String courseId
	 */
	public static Map<String, Object> addCourseByWx() {
		String url = PropertyUtils.getWebServiceProperty("sijiao.findEduStudents");
		String json = "";
		String app = PropertyUtils.getWebServiceProperty("app");
		try {
			if (app.equals("true")) {
				HttpRequestData data = UrlUtil.sendGet(url);
				json = data.getResult();
			} else
				json = "{\"success\":true,\"title\":\"操作成功\",\"reason\":\"操作成功\",\"result\":{studentId:0bd22f1f-2aee-4e74-8ee0-144ffedd3822}}";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(json);
		boolean state = Boolean.valueOf(obj.get("success").toString());
		result.put("state", state);
		result.put("reason", obj.get("reason"));
		result.put("title", obj.get("title"));

		System.out.println("获取用户下已添加学员:"+result.get("state").toString() 
				+ result.get("reason").toString() + result.get("title").toString());
		
		return result;
	}

	/**
	 * 预付款确认课程详细信息
	 * 
	 * @param courseId   课程编号，不能为空
	 * @return 返回对象的success属性值为true时，result属性为EduCourse
	 */
	public static Map<String, Object> getCourse(String courseId) {
		String url = PropertyUtils.getWebServiceProperty("sijiao.getCourse");
		String json = "";
		String app = PropertyUtils.getWebServiceProperty("app");
		try {
			if (app.equals("true")) {
				HttpRequestData data = UrlUtil.sendGet(url);
				json = data.getResult();
			} else
				json = "{\"success\":true,\"title\":\"操作成功\",\"reason\":\"操作成功\",\"result\":{studentId:0bd22f1f-2aee-4e74-8ee0-144ffedd3822}}";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(json);
		boolean state = Boolean.valueOf(obj.get("success").toString());
		result.put("state", state);
		result.put("reason", obj.get("reason"));
		result.put("title", obj.get("title"));

		System.out.println("预付款确认课程详细信息:"+result.get("state").toString() 
				+ result.get("reason").toString() + result.get("title").toString());
		
		return result;
	}

	/**
	 * 预付款确认学员信息
	 * 
	 * @param studentId   学员编号，不能为空
	 * @return 返回对象的success属性值为true时，result属性为EduStudent
	 */
	public static Map<String, Object> geStudent(String studentId) {
		String url = PropertyUtils.getWebServiceProperty("sijiao.getCourse");
		String json = "";
		String app = PropertyUtils.getWebServiceProperty("app");
		try {
			if (app.equals("true")) {
				HttpRequestData data = UrlUtil.sendGet(url);
				json = data.getResult();
			} else
				json = "{\"success\":true,\"title\":\"操作成功\",\"reason\":\"操作成功\",\"result\":{studentId:0bd22f1f-2aee-4e74-8ee0-144ffedd3822}}";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(json);
		boolean state = Boolean.valueOf(obj.get("success").toString());
		result.put("state", state);
		result.put("reason", obj.get("reason"));
		result.put("title", obj.get("title"));

		System.out.println("预付款确认学员信息:"+result.get("state").toString() 
				+ result.get("reason").toString() + result.get("title").toString());
		
		return result;
	}

	/**
	 * 课程付款完成
	 * 
	 * @param courseSid 学员选课编号(订单编号)，不能为空
	 * @param payJe 实际付款金额
	 * @return 返回对象的success属性值为true时，操作成功；否则操作失败
	 */
	public static Map<String, Object> payOk(String courseSid, String payJe) {
		String url = PropertyUtils.getWebServiceProperty("sijiao.payOk");
		String json = "";
		String app = PropertyUtils.getWebServiceProperty("app");
		try {
			if (app.equals("true")) {
				HttpRequestData data = UrlUtil.sendGet(url);
				json = data.getResult();
			} else
				json = "{\"success\":true,\"title\":\"操作成功\",\"reason\":\"操作成功\",\"result\":{studentId:0bd22f1f-2aee-4e74-8ee0-144ffedd3822}}";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(json);
		boolean state = Boolean.valueOf(obj.get("success").toString());
		result.put("state", state);
		result.put("reason", obj.get("reason"));
		result.put("title", obj.get("title"));
		

		System.out.println("预付款确认学员信息:"+result.get("state").toString() 
				+ result.get("reason").toString() + result.get("title").toString());
		
		return result;
	}

}
