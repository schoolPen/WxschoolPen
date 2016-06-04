<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/contextPath.jsp"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>课程列表</title>
<link rel="stylesheet" type="text/css" href="${root1 }/css/common.css" />
<script type="text/javascript" src="${root1 }/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${root1 }/js/center.js"></script>
<script type="text/javascript">
$(function(){
	$('.tcont dd .bfo:odd').css('background-color','#f9fefa');
	$('.top10 tr').find('td').slice(0,10).addClass('tdCol');
	$('#ckBox .panel div:first').css('min-width','600px');
});

function isLogin(openId,shId,courseId){
	var url="${root1 }/index/isLogin.do";
	
	 $.post(url,{"openId":openId,"shId":shId}, function(data) {
		 	if(  data.state == "false"){
				alert("网络异常.请稍候再试.");
			}else{
				var studentId=data.studentId;
				if(studentId==''){//注册
					window.location='${root1}/index/register.do?openId='+openId+'&shId='+shId;
				}else{
					//1、判断该用户是否已经购买过课程
					
					$.post("${root1 }/index/isBuy.do",{"openId":openId,"shId":shId,"courseId":courseId}, function(data) {
						if(data=='true'){
							alert("已经购买不能重复");
						}else{
							//2、添加到我的课程表里面ajax
							
							$.post("${root1 }/index/addMineCourse.do",{"openId":openId,"shId":shId,"courseId":courseId}, function(data) {
								window.location='${root1}/mainWX?openId='+openId+'&shId='+shId+"&courseId="+courseId+"&courseSId="+data.data;
					
							}, 'json'); 
						}
			
					}, 'json'); 
					
					
					
					
					
				}
			}

		}, 'json'); 
	
	/*  $.ajax({
         url:url,
         data:{"openId":openId,"shId":shId},
         type:'post',
         cache : false,
         dataType : 'json',
         success:function(data){
        	 if(  data.state == "false"){
 				alert("网络异常.请稍候再试.");
 			}else{
 				var studentId=data.studentId;
 				if(studentId==''){//注册
 					window.location='${root1}/index/register.do?openId='+openId+'&shId='+shId;
 				}else{
 					window.location='${root1}/index/courseBuy.do?openId='+openId+'&shId='+shId;
 				}
 			}
         }
         }) ;
	 */
	
}
</script>

</head>
<body>
<input type="hidden" id="openId" name="openId" value="${openId }"/> 
<!-- <input type="hidden" id="openId" name="openId" value="oUDNMwBwAOoiYp0JrqHOx6BFiupo"/> -->

<div class="ifrRight">
	<!-- main -->
	<div class="yjBox">
			<div><span class="tjTip">课程列表<em>welcome!</em></span><c:if test="${isAdmin=='true' }"><a href="${root1}/index/preAddCource.do?openId=${openId}">发布课程</a></c:if></div>
			<div class="tjBox">
					<dl class="tcont">
						<%-- <dd>
							<div class="bfo">
								<dl>
									<dt>2016年春季硬笔入门一班<em>10课时</em>
									<br/>
									蔡毅华老师<em>￥:1098</em>
									<br/>
									2016/03/01 – 2016/06/06 
									<br/>
									周一晚上18:30 – 20:30 <em><a href="javascript:isLogin('${openId }','${shId }','')">缴费</a></em>
									<br/>
									美林花园-这是测试数据，不要点击，下面的可以点
									
									<i></i>
									</dt>
									<dd>
										<p>
											课程详细介绍
										</p>
										<a href="glgd.html" target="_iframe">查看信息</a>
									</dd>
								</dl>
							</div>
						</dd> --%>
						 <c:forEach var="obj" items="${dataList.data}" varStatus="stat">
		  					<dd>
							<div class="bfo">
								<dl>
									<dt>${obj.year}-${obj.courseName }<em>${obj.totalCourse }课时</em>
									<br/>
									${obj.teacherName }<em>￥:${obj.courseJe }</em>
									<br/>
									${obj.startDate } – ${obj.endDate } <em>剩余:${obj.maxStudents-obj.payStudents }</em>
									<br/>
									${obj.teachTime } <em><c:if test="${(obj.maxStudents-obj.payStudents)!=0}"><a href="javascript:isLogin('${openId }','${shId }','${obj.courseId }')">缴费</a></c:if></em>
									<br/>
									${obj.teachAddress }
									
									<i></i>
									</dt>
									<dd>
										<p>
											课程详细介绍：
											${obj.courseInfo }
											</br>
											可报人数${obj.maxStudents }：人/已报：${obj.payStudents}
										</p>
										<!-- <a href="glgd.html" target="_iframe">查看信息</a> -->
									</dd>
								</dl>
							</div>
						</dd>
			  			</c:forEach>
						
					</dl>
			</div>
		</div>
				
				</div>

</body>
<script type="text/javascript">

</script>
</html>