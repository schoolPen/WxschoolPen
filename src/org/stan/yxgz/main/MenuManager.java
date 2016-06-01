package org.stan.yxgz.main;



import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stan.yxgz.pojo.AccessToken;
import org.stan.yxgz.pojo.Button;
import org.stan.yxgz.pojo.CommonButton;
import org.stan.yxgz.pojo.ComplexButton;
import org.stan.yxgz.pojo.Menu;
import org.stan.yxgz.pojo.ViewButton;
import org.stan.yxgz.util.PropertyUtils;
import org.stan.yxgz.util.YiXinUtil;

public class MenuManager {
	private static Logger logger = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//String appid = "wx2a74f8196b2017de";
		//String appSecret = "eb6581ea26d85189dab9d7881fa30431";
		String	appid =PropertyUtils.getWebServiceProperty("appid");
		String	appSecret =PropertyUtils.getWebServiceProperty("appSecret");
		AccessToken at = YiXinUtil.getAccessToken(appid, appSecret);

		if (null != at) {
			int det = YiXinUtil.deleteMenu(at.getToken());
			if (0 == det) {
				logger.info("删除菜单成功！");
			} else {
				logger.info("菜单删除失败，错误码：" +det);
			}
			
			int res = YiXinUtil.createMenu(getMenu(), at.getToken());

			if (0 == res) {
				logger.info("创建菜单成功！");
			} else {
				logger.info("菜单创建失败，错误码：" + res);
			}

		}

	}

	public static Menu getMenu() {
		//shId=6827b4cf-0eff-4545-9e4f-da8510351fca

		ViewButton btn11 = new ViewButton();
		btn11.setName("课程列表");
		btn11.setType("view");
		btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa76644985975ad93&redirect_uri=http%3a%2f%2foepp.cn%2fWxschoolPen%2findex%2ftempMinCourse.do&response_type=code&scope=snsapi_base&state=2343#wechat_redirect");
		
		ViewButton btn12 = new ViewButton();
		btn12.setName("学习历程");
		btn12.setType("view");
		btn12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa76644985975ad93&redirect_uri=http%3a%2f%2foepp.cn%2fWxschoolPen%2findex%2ftempProcess.do&response_type=code&scope=snsapi_base&state=2343#wechat_redirect");
		
		
		ViewButton btn21 = new ViewButton();
		btn21.setName("班级介绍");
		btn21.setType("view");
		btn21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa76644985975ad93&redirect_uri=http%3a%2f%2foepp.cn%2fWxschoolPen%2findex%2ftempCourse.do&response_type=code&scope=snsapi_base&state=2343#wechat_redirect");
		
		CommonButton btn22 = new CommonButton();
		btn22.setKey("a2");
		btn22.setName("老师介绍");
		btn22.setType("click");
		
		
		
		ViewButton btn31 = new ViewButton();
		btn31.setName("调课通知");
		btn31.setType("view");
		btn31.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa76644985975ad93&redirect_uri=http%3A%2F%2Foepp.cn%2FweChatpay%2FtopayServlet%3FuserId%3Db83w45345088801%26orderNo%3D1355444812%26describe%3D%3D%E8%A5%BF%E7%93%9C%26money%3D1780.00&response_type=code&scope=snsapi_base&state=123#wechat_redirect");

		ViewButton btn32 = new ViewButton();
		btn32.setName("赛事公告");
		btn32.setType("view");
		btn32.setUrl("http://www.oepp.cn/weChatpay/mainServlet");

		
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("我的课程");
		mainBtn1.setSub_button(new Button[] {btn11,btn12});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("书法之家 ");
		mainBtn2.setSub_button(new Button[] {btn21, btn22});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("书艺风采 ");
		mainBtn3.setSub_button(new Button[] {btn31, btn32});


		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2 ,mainBtn3});

		return menu;

	}

}




