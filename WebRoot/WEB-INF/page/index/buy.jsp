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
	$('.tcont dd .bfo:odd').css('background-color','#f9fefa');
	$('.top10 tr').find('td').slice(0,10).addClass('tdCol');
	$('#ckBox .panel div:first').css('min-width','600px');
	
});
var ti;
function clock()
  {
	var t;
	  
	  t=parseInt(document.getElementById("clock").value)+1;
	  document.getElementById("clock").value = t;
	  t=formatSeconds(t);
	  document.getElementById("clocktime").innerHTML=t;
  } 
 
function formatSeconds(value) {
var ctime = Number(value);
        var ctime1 = 0;
        var ctime2 = 0;
        if(ctime > 60) {
        	ctime1 = Number(ctime/60);
        	ctime = Number(ctime%60);
        	if(ctime1 > 60) {
        		ctime2 = Number(ctime1/60);
        		ctime1 = Number(ctime%60);
        	}
        }
        var result = ""+ctime+"秒";
        console.log(parseInt(ctime1));
        if(parseInt(ctime1)==15){
        	clearTimeout(ti);
        }
        if(ctime1 > 0) {
        	result = ""+parseInt(ctime1)+"分"+result;
        }
        if(ctime2 > 0) {
        	result = ""+parseInt(ctime2)+"小时"+result;
        }
        return result;
}
ti=setInterval("clock()",1000);

function pay(openId,courseId,payje,sId){
	  var url="${root1}/index/payOk.do";
	  $.post(url,{"openId":openId,"courseId":courseId,"payJe":payje,"courseSid":sId}, function(data) {
		 	if(  data.state == "false"){
				alert("网络异常.请稍候再试.");
			}else{
				//微信端提示用户付款成功.
				
				alert("付款成功.");
				window.location="${root1}/index/minCourstList.do?openId="+openId;
				WeixinJSBridge.call('closeWindow');
			}

		}, 'json');  
}



function callpay(openId,courseId,payje,sId){
	var url="${root1}/index/isZhifuOut.do";
	  $.post(url,{"courseSid":sId,"orderId":"${package}"}, function(data) {
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
			<div class="tjBox">
			<span>您本次报班的信息，已锁定本次名额，请尽快付款(请在15分钟内付款，否则课课程将无效):</span><span style="color:red;font-size: 23px" id="clocktime"></span>
					<dl class="tcont">
						<dd>
							<div class="bfo">
								<dl>
									<dt>${data.year}-${data.courseName }<em>${data.totalCourse }课时</em>
									<br/>
									${data.teacherName }<em>￥:${data.courseJe }</em>
									<br/>
									${data.startDate } – ${data.endDate } 
									<br/>
									${data.teachTime } <em></em>
									<br/>
									${data.teachAddress }
									<br/>
									课程介绍：</br>
									${data.courseInfo }
									<!-- <i></i> -->
									</dt>
									
						</dl>
				</div>
			
			</dd>						
			</dl>
			</div>
			<div class="btnBtm">
				<button onclick="callpay('${openId}','${courseId }','${data.courseJe }','${courseSid }')" >确认付款</button>
			</div>
			
			
		</div>
				
				</div>
</body>
</html>