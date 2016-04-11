package org.stan.yxgz.action;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.stan.yxgz.pojo.User;
import org.stan.yxgz.util.KanqActionSupport;

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
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@RequestMapping(value = "mineClass", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView mineClass() throws Exception {
		ModelAndView view=new ModelAndView("/index/mine-process");
		return view;
	}
	
	@RequestMapping(value = "courceList", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView courceList() throws Exception {
		ModelAndView view=new ModelAndView("/index/courceList");
		return view;
	}
	@RequestMapping(value = "addCource", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView addCource() throws Exception {
		ModelAndView view=new ModelAndView("/index/courceAdd");
		return view;
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET )
	@ResponseBody
	public ModelAndView register() throws Exception {
		ModelAndView view=new ModelAndView("/index/register");
		return view;
	}
	
	
	@RequestMapping(value = "subUser", method = RequestMethod.POST )
	@ResponseBody
	public ModelAndView subUser(){
		ModelAndView view=new ModelAndView("/index/buy");
		String studentName=request.getParameter("studentName");
		String phone=request.getParameter("phone");
		String sex=request.getParameter("sex");
		String address=request.getParameter("address");
		String bornYear=request.getParameter("bornYear");
		String bornMonth=request.getParameter("bornMonth");
		String inSchool=request.getParameter("inSchool");
		user.setAddress(address);
		user.setSex(sex);
		user.setBornMonth(bornMonth);
		user.setBornYear(bornYear);
		user.setInSchool(inSchool);
		user.setPhone(phone);
		user.setStudentName(studentName);
		System.out.println(user.getAddress());
		return view;
	}
	
	@RequestMapping(value = "subCourse", method = RequestMethod.POST )
	@ResponseBody
	public ModelAndView subCourse(){
		ModelAndView view=new ModelAndView("/index/courceList");
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
