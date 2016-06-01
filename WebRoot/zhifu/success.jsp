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
<title>支付成功</title>
<link rel="stylesheet" type="text/css" href="${root1 }/css/common.css" />
<script type="text/javascript" src="${root1 }/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${root1 }/js/center.js"></script>
<script type="text/javascript">
$(function(){
	$('.tcont dd .bfo:odd').css('background-color','#f9fefa');
	$('.top10 tr').find('td').slice(0,10).addClass('tdCol');
	$('#ckBox .panel div:first').css('min-width','600px');
	
});
function callpay(openId,courseId,payje,sId){
	var url="${root1}/index/isZhifuOut.do";
	  $.post(url,{"courseSid":sId}, function(data) {
		 	if(  data.state == "false"){
				alert("您的提交的课程超过期限已被限制，请重新购买课程.");
			}else{
				//微信端提示用户付款成功.
				WeixinJSBridge.invoke('getBrandWCPayRequest',{
		 	"appId" : "${appid}","timeStamp" : "${timeStamp}", "nonceStr" : "${nonceStr}", "package" : "${package}","signType" : "MD5", "paySign" : "${sign}" 
			},function(res){
			WeixinJSBridge.log(res.err_msg);
			alert(res.err_code + res.err_desc + res.err_msg);
           if(res.err_msg == "get_brand_wcpay_request:ok"){  
        	     var url="${root1}/index/message.do";
        		  $.post(url,{"openId":openId,"courseName":"${data.courseName }","payJe":payje}, function(data) {
        			 	
        			}, 'json');  
        	   
        	   
               alert("微信支付成功!");  
           }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
               alert("用户取消支付!");  
           }else{  
              alert("支付失败!");  
           }  
		})
			}

		}, 'json');  
	 
	}
</script>
</head>
<body>
<div class="ifrRight">
	<!-- main -->
	<input type="hidden" id="clock" value="0">
	<div class="yjBox">
			
			<div class="btnBtm">
				<button onclick="callpay()" >确认付款</button>
			</div>
			
			
		</div>
				
				</div>
</body>
</html>