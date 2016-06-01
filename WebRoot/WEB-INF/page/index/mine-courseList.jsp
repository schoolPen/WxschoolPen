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
<title>我的课程列表</title>
<link rel="stylesheet" type="text/css" href="${root1 }/css/common.css" />
<script type="text/javascript" src="${root1 }/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${root1 }/js/center.js"></script>
<script type="text/javascript">
$(function(){
	$('.tcont dd .bfo:odd').css('background-color','#f9fefa');
	$('.top10 tr').find('td').slice(0,10).addClass('tdCol');
	$('#ckBox .panel div:first').css('min-width','600px');
});


</script>

</head>
<body>
<input type="hidden" id="openId" name="openId" value="${openId }"/> 
<!-- <input type="hidden" id="openId" name="openId" value="oUDNMwBwAOoiYp0JrqHOx6BFiupo"/> -->

<div class="ifrRight">
	<!-- main -->
	<div class="yjBox">
			<div><span class="tjTip">我的课程列表<em></em></span></div>
			<div class="tjBox">
					<dl class="tcont">
						 <c:forEach var="obj" items="${dataList.data}" varStatus="stat">
		  					<dd>
							<div class="bfo">
								<dl>
									<dt>${obj.year}-${obj.courseName }<em>${obj.totalCourse }课时</em>
									<br/>
									${obj.teacherName }<em><c:if test="${obj.isPayed==0 }">未缴费</c:if><c:if test="${obj.isPayed==1 }">已缴费:￥:${obj.courseJe }</c:if></em>
									<br/>
									${obj.startDate } – ${obj.endDate } 
									<br/>
									${obj.teachTime } <c:if test="${obj.isPayed==0 || obj.isPayed==2}"><em><a href="${root1}/mainWX?openId=${openId }&shId=${shId }&courseId=${obj.courseId}&courseSId=${obj.courseSid}">缴费</a></em></c:if>
									<c:if test="${obj.isPayed==3 }"><em><a href="${root1}/mainWX?openId=${openId }&shId=${shId }&courseId=${obj.courseId}&courseSid=${obj.courseSid}">已失效</a></em></c:if>
									<br/>
									购买日期<em>${obj.pay_date }</em>
									<br/>
									${obj.courseInfo }
									
									<i></i>
									</dt>
									<dd>
										<p>
											课程详细介绍：
											${obj.courseInfo }
										</p>
										<!-- <a href="glgd.html" target="_iframe">查看信息</a> -->
									</dd>
								</dl>
							</div>
						</dd>
			  			</c:forEach>
			  			<c:if test="${dataList==null }">
			  			赞无课程！
			  			</c:if>
						
					</dl>
			</div>
		</div>
				
				</div>

</body>
<script type="text/javascript">

</script>
</html>