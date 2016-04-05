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
<title>我的学习历程</title>
<link rel="stylesheet" type="text/css" href="${root1 }/css/common.css" />
<script type="text/javascript" src="${root1 }/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${root1 }/js/center.js"></script>
<script type="text/javascript">
$(function(){
	$('.tjCen dd .bInfo:odd').css('background-color','#f9fefa')
	$('.top10 tr').find('td').slice(0,10).addClass('tdCol');
	$('#ckBox .panel div:first').css('min-width','600px');
})
</script>
</head>
<body>
<div class="ifrRight">
	<!-- main -->
	<div class="yjBox">
		
		<div class="yjMain">
				
				<div id="tTab">
					<!--今日bengin-->
					<div class="sjMain active">
						<span class="tjTip">统计区间：<em>2015年07月27日-2016-04-12</em></span>
						<div class="tjBox">
							<div class="tjTop">截至当天<em>23：50</em>为止，共有学习历程 <b>3</b> 件</div>
							<div class="tjCen">
								<dl id="yzCls">
									<dd>
										<img class="dTip" src="../image/icon_tj_m.gif"/>
										<!-- <span>2015-04-12</span> -->
										<div class="bInfo">
											<p class="border_corr"></p>
											<dl>
												<dt>2015-04-12&nbsp;&nbsp;毛笔硬笔初级学习<em>10课时</em><i></i></dt>
												<dd>
													<p>
														学习奖项
													</p>
													<a href="glgd.html" target="_iframe">查看信息</a>
												</dd>
											</dl>
										</div>
									</dd>
									<dd>
										<div class="bInfo">
											<p class="border_corr"></p>
											<dl>
												<dt>2015-06-28&nbsp;&nbsp;毛笔硬笔初级中级<em>8课时</em><i></i></dt>
												<dd>
													<p>
														学习奖项
													</p>
													<a href="glgd.html" target="_iframe">查看信息</a>
												</dd>
											</dl>
										
										</div>
									</dd>
									<dd>
										<div class="bInfo">
											<p class="border_corr"></p>
											<dl>
												<dt>2015-12-12&nbsp;&nbsp;毛笔硬笔高级<em>8课时</em><i></i></dt>
												<dd>
													<p>
														学习奖项
													</p>
													<a href="glgd.html" target="_iframe">查看信息</a>
												</dd>
											</dl>
											
										</div>
									</dd>
									<!-- 
									<dd>
										<span>0:00</span>
										<div class="bInfo"></div>
									</dd> -->
								</dl>
							</div>
							<div class="tjBot"><img src="${root1 }/image/icon_tj_bot.png"/></div>
						</div>
					</div>
					<!--今日end-->
				
				</div>
			<!--时间纬度end-->
		</div>
	</div>
	<!--main end-->
</div>
</body>
</html>