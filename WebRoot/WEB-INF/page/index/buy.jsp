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
<title>购买课程详细信息</title>
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
			<div class="tjBox">
			<span>您本次报班的相信信息，请你再次确认:</span>
					<dl class="tcont">
						<dd>
							<div class="bfo">
								<dl>
									<dt>2016年春季硬笔入门一班<em>10课时</em>
									<br/>
									蔡毅华老师<em>￥:1098</em>
									<br/>
									2016/03/01 – 2016/06/06 
									<br/>
									周一晚上18:30 – 20:30 <em><a>缴费</a></em>
									<br/>
									美林花园
									
									<i></i>
									</dt>
									<dd>
										<p>
											课程详细介绍
										</p>
										
									</dd>
								</dl>
							</div>
						</dd>						
					</dl>
			</div>
			
			
			<div class="btnBtm">
				<button >确认付款</button>
			</div>
			
			
		</div>
				
				</div>

</body>
</html>