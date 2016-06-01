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
<title>我的课程详情</title>
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
			<div><span class="tjTip">我的课程详情<em></em></span></div>
			<div class="tjBox">
					<dl class="tcont">
		  					<dd>
							<div class="bfo">
								<dl>
									<dt>${dataList.year}-${dataList.courseName }<em>${dataList.totalCourse }课时</em>
									<br/>
									${dataList.teacherName }<em><c:if test="${dataList.isPayed==0 }">未缴费</c:if><c:if test="${dataList.isPayed==1 }">已缴费:￥:${dataList.courseJe }</c:if></em>
									<br/>
									${dataList.startDate } – ${dataList.endDate } 
									<br/>
									${dataList.teachTime } <em>购买日期:${dataList.payDate }</em>
									<br/>
									${dataList.teachAddress }
									
									<i></i>
									</dt>
									<dd>
										<p>
											课程详细介绍：
											${dataList.courseInfo }
										</p>
										<!-- <a href="glgd.html" target="_iframe">查看信息</a> -->
									</dd>
								</dl>
							</div>
						</dd>
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