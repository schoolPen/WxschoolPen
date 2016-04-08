<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width,user-scalable=no;initial-scale=1, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<!-- <meta name="viewport" content="target-densitydpi=device-dpi,width=device-width, minimum-scale=1.0, maximum-scale=1.0"> -->
<title>驾校注册</title>
<link href="/css/base.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>

<style type="text/css">
* {
	padding: 0;
	margin: 0;
	padding: 0;
	font: normal 16px Microsoft Yahei
}
a{color:white; text-decoration:none;cursor:pointer;}
.fromLine {
	height: 38px;
	width: 360px;
	line-height: 38px;
}

.fromlineError {
	color: #cc6600;
	border: 1px solid #e19824;
	background: #faefd4 url(../image/ico_erro.png) 10px 7px no-repeat;
}

.logoBox {
	margin-left: 10px;
	position: relative;
}

.marT10 {
	margin-top: 10px;
}

.marT20 {
	margin-top: 20px;
}
/*这里是登录页*/
header {
	height: 60px;
	line-height: 60px;
	background-color: #2090ff;
}

.jx_banner {
	background: url(/image/bj2013.gif) no-repeat;
	width: 100%;
	height: 200px;
	margin-top: 1px;
}

.logoBox {
	margin-left: 10px;
	position: relative;
}

.logotext {
	position: absolute;
	left: 124px;
	top: 14px;
	font-size: 24px;
	color: white;
}

.content {
	width: 80%;
	margin: 0 auto;
}
/*.user_right{width:34px;height:34px; background:url(/image/ico_right.png) no-repeat;position:absolute;right:-44px;top:9px;}
.user_erro{width:34px;height:34px; background:url(/image/ico_erro.png) no-repeat;position:absolute;right:-44px;top:9px;}*/
.password_pic {
	width: 100%;
	height: 58px;
	display: block;
	background-color: red;
	background: url(/image/ico_password.png) no-repeat left center;
	position: relative;
}

.password {
	width: 100%;
	height: 58px;
	display: block;
	background-color: red;
	background: url(/image/ico_username.png) no-repeat left center;
	position: relative;
}

.password_input {
	border: none;
	width: 80%;
	line-height: 58px;
	position: absolute;
	left: 62px;
	top: 0;
	font-size: 24px;
	height: 58px;
	display: block;
	border: solid 2px #3099ff;
	-moz-border-radius: 0 16px 16px 0;
	-webkit-border-radius: 0 16px 16px 0;
	border-radius: 0 16px 16px 0;
}

.labeltext {
	color: #2090ff;
	font-size: 18px;
}

.btn_login {
	display: block;
	width: 100%;
	height: 58px;
	font-size: 24px;
	line-height: 58px;
	color: white;
	border: none;
	text-align: center;
	-moz-border-radius: 16px;
	-webkit-border-radius: 16px;
	border-radius: 16px;
	background-color: #219fff;
	font-size: 24px;
}

.btn_login:hover {
	background-color: #0d88e6;
}
/*这里是登录页结束*/
/*这里是注册页开始*/
.input_text {
	border: solid 2px #3099ff
}
/*这里是账号退出*/
</style>
<!-- 出生年月-->
<script type="text/javascript">
	/*加载年份*/
	function years(obj, Cyear) {
		var len = 134; // select长度,即option数量
		var selObj = document.getElementById(obj);
		var selIndex = len - 1;
		var newOpt; // OPTION对象

		// 循环添加OPION元素到年份select对象中
		for (i = 1; i <= len; i++) {
			if (selObj.options.length != len) { // 如果目标对象下拉框升度不等于设定的长度则执行以下代码
				newOpt = window.document.createElement("OPTION"); // 新建一个OPTION对象
				newOpt.text = Cyear - len + i; // 设置OPTION对象的内容
				newOpt.value = Cyear - len + i; // 设置OPTION对象的值
				selObj.options.add(newOpt, i); // 添加到目标对象中
			}

		}
	}

	function months() {
		var month = document.getElementById("month");
		month.length = 0;
		for (i = 1; i < 13; i++) {
			month.options.add(new Option(i, i));
		}

	}

	function change_date() {
		// var birthday = document.birthday;  
		var year = document.getElementById("year");
		var month = document.getElementById("month");
		var day = document.getElementById("date");
		vYear = parseInt(year.value);
		vMonth = parseInt(month.value);
		date.length = 0;

		//根据年月获取天数  
		var max = (new Date(vYear, vMonth, 0)).getDate();
		for (var i = 1; i <= max; i++) {
			date.options.add(new Option(i, i));
		}
	}
</script>
<body>
	<header>
		<div class="logoBox">
			<img height="60" src="../image/logo.png" /> <span class="logotext">广东数据超市</span>
		</div>
	</header>
	<div class="content">
		<!--  姓名：性别：电话号码：家庭住址：年龄：学年级（下拉框）：角色（下拉框，超级管理员/教师/学员）-->

		<div class="marT20">
			<lable>姓名：</lable>
			<input class="input_text" name="regName" id="regName" type="text"
				required="required" placeholder="请输入姓名" />
		</div>
		<div class="marT10">
			<lable>手机：</lable>
			<input class="input_text" maxlength="11" name="regPhone"
				required="required"
				onkeyup="this.value=this.value.replace(/\D/g,'')" id="regPhone"
				type=text placeholder="请输入手机号码" />
		</div>
		<div class="marT10">
			<lable>住址：</lable>
			<input class="input_text" name="regAddress" id="regAddress"
				type="text" required="required" placeholder="请输入家庭地址" />
		</div>
		
		<div class="marT10">
			<lable>出生年月：</lable>
			<select size="1" name="year" id="year" style="width: 70px"
				onfocus="years('year',new Date().getFullYear()),change_date()"
				onchange="change_date()"></select>&nbsp; <select size="1"
				name="month" id="month" style="width: 70px"
				onfocus="months(),change_date()" onchange="change_date()"></select>
				<!-- &nbsp;<select size="1" name="date" id="date" style="width: 50px"></select> -->
		</div>
		<div class="marT10">
			<lable>性别：</lable>
			<label> <input name="sex" type="radio" value="男" checked />男
			</label> <label><input name="sex" type="radio" value="女" />女 </label>
		</div>
		<div class="marT10">
			<lable>学年级：</lable>
			<select class="input_text">
				<option value="1年">1年级</option>
				<option value="2年">2年级</option>
				<option value="3年">3年级</option>
				<option value="4年">4年级</option>
			</select>
		</div>
		<div class="marT10">
			<lable>角色：</lable>
			<select class="input_text">
				<option value="学员">学员</option>
				<option value="教师">教师</option>
				<option value="超级管理员">超级管理员</option>
			</select>
		</div>

		<div class="marT20">
			<a class="btn_login" href="#" id="btnReg">注册</a>
		</div>
	</div>
</body>

</html>
