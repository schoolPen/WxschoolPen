<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/contextPath.jsp"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>课程列表</title>
<link rel="stylesheet" type="text/css" href="${root1 }/css/common.css" />
<script type="text/javascript" src="${root1 }/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${root1 }/js/center.js"></script>
<script type="text/javascript">
$(function(){
	$('.tcont dd .bfo:odd').css('background-color','#f9fefa')
	$('.top10 tr').find('td').slice(0,10).addClass('tdCol');
	$('#ckBox .panel div:first').css('min-width','600px');
})
</script>
</head>
<body>
<div class="ifrRight">
	<!-- main -->
	<div class="yjBox">
			<span class="tjTip">课程列表<em>welcome!</em></span>
			<div class="tjBox">
					<dl class="tcont">
						<dd>
							<div class="bfo">
								<dl>
									<dt>2015-04-12&nbsp;&nbsp;毛笔硬笔初级学习<em>10课时</em>
									<br/>
									地点：美林花园     时间：2016-04-05
									<em><a>价格：1088元&nbsp;&nbsp;缴费</a></em>
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
						</dd>
						<dd>
							<div class="bfo">
								<dl>
									<dt>2015-06-28&nbsp;&nbsp;毛笔硬笔初级中级<em>8课时</em>
									<br/>
									地点：美林花园     时间：2016-04-05
									<em><a>价格：1088元&nbsp;&nbsp;缴费</a></em>
									<i></i></dt>
									<dd>
										<p>
											课程详细介绍
										</p>
										<a href="glgd.html" target="_iframe">查看信息</a>
									</dd>
								</dl>
							
							</div>
						</dd>
						<dd>
							<div class="bfo">
								<dl>
									<dt>2015-12-12&nbsp;&nbsp;毛笔硬笔高级<em>8课时</em>
									<br/>
									地点：美林花园     时间：2016-04-05
									<em><a>价格：1088元&nbsp;&nbsp;缴费</a></em>
									<i></i></dt>
									<dd>
										<p>
											课程详细介绍
										</p>
										<a href="glgd.html" target="_iframe">查看信息</a>
									</dd>
								</dl>
								
							</div>
						</dd>
						
					</dl>
			</div>
		</div>
				
				</div>

</body>
</html>