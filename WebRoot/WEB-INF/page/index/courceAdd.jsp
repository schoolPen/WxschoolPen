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
<title>开班计划</title>
<link rel="stylesheet" type="text/css" href="${root1 }/css/common.css" />
<script type="text/javascript" src="${root1 }/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${root1 }/js/center.js"></script>
<style type="text/css">
.btnBtm button{margin-left:10px;vertical-align:middle;background-color:#a0d62c;color:#FFF;font-size:14px;font-family:'MicroSoft YaHei';border-top:1px solid #afe969;border-bottom:1px solid #5d8a33;border-left:1px solid #afe969;border-right:1px solid #5d8a33;display:inline-block;text-align:center;width:82px;cursor:pointer;height:28px;}
.btnBtm button:hover{background-color:#dcedfd;color:#FFF;}
</style>
<script type="text/javascript">
</script>
</head>
<body>
<div class="ifrRight">
	<!-- main -->
	<div class="yjBox">
			<div id="addS" class="easyui-dialog" style="padding:10px 6px;width:50%;">
			<form action="#">
			<div class="addBox">
				<ul>
					<li>
						<span>课程名称</span>
						<input type="text" required="required" name="courceName" id="courceName"/>
					</li>
					<li>
						<span>课程类型</span>
						<select id="courceType" name="courceType">
							<option value="1">软笔</option>
							<option value="2">硬笔</option>
						</select>
					</li>
					<li>
						<span>老师姓名</span>
						<input type="text" required="required" name="teacherName" id="teacherName"/>
					</li>
					<li>
						<span>学期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<select id="period" name="period">
							<option value="1">春季</option>
							<option value="2">暑假</option>
							<option value="3">秋季</option>
							<option value="4">寒假</option>
						</select>
					</li>
					<li>
						<span>开始时间&nbsp;&nbsp;</span>
						<input type="date" required="required" name="btime" id="btime"/>
					</li>
					<li>
						<span>结束时间&nbsp;&nbsp;</span>
						<input type="date" required="required" name="etime" id="etime"/>
					</li>
					
					
					<li>
						<span>课程时间</span>
						<input type="text" required="required" name="ptime" id="ptime"/>
					</li>
					<li>
						<span>上课地点</span>
						<input type="text" required="required" name="address" id="paddress"/>
					</li>
					<li>
						<span>节数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<input type="text" name="pcount" required="required" id="pcount"/>
					</li>
					<li>
						<span>限制人数</span>
						<input type="text" name="ptotal" required="required" id="ptotal"/>
					</li>
					<li>
						<span>课程内容</span>
						<input type="text" name="common" id="common" />
					</li>
					<!-- <li>
						<span>词条状态</span>
						<label><input type="radio"/>有效</label>
						<label><input type="radio"/>停用</label>
					</li> -->
				</ul>
			</div>
			<div class="btnBtm">
			<button>保存</button>
			</div>
			</form>
		</div>
			
	</div>
				
</div>

</body>
</html>