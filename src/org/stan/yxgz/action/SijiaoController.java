package org.stan.yxgz.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.dtpt.mybatis.model.sijiao.EduCourse;
import com.dt.dtpt.service.sijiao.SijiaoService;
import com.dt.dtpt.util.Result;

@Controller
@RequestMapping("/sijiaoController")
public class SijiaoController {

	@Autowired
	SijiaoService sijiaoService;
	
	@RequestMapping("test.do")
	public void test(){
		Result rs = sijiaoService.findWxCourses("6827b4cf-0eff-4545-9e4f-da8510351fca", new EduCourse(), 1, 10);
		List<EduCourse> es = (List<EduCourse>) rs.getResult();
		if(es != null){
			for(EduCourse ec:es){
				System.out.println(ec.getCourseInfo() + "   " + ec.getCourseName() + "    " + ec.getMaxStudents());
			}
		}
	}
}
