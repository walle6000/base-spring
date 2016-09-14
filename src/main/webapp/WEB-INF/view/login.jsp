<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
<title>欢迎访问待办平台</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default/base1.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login.css" type="text/css" />
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui-1.11.4/jquery-ui.css" type="text/css"> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.11.4/external/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/syslib/common.js"></script>
</head>
<body>
<div class="big_bg"><img id="loginbg" src="${pageContext.request.contextPath}/images/login/login_bg.png?<%=new Date().getTime()%>" width="100%" height="100%"></div>
<div id="logo">
<a href="${pageContext.request.contextPath}/home/"><img src="${pageContext.request.contextPath}/images/login/logo.gif?<%=new Date().getTime()%>" ng-bind="window.title" id="logoimage" /></a>
</div>

<div class="w1154 clearfix">
<form action="${pageContext.request.contextPath}/login.do?method=login" method="post" id="form_login">
<div id="loginbox">
	<div id="logintable">
	<p class="login-box-title">待办平台登录</p>
		<label>用户名</label>
		<div class="text"><input id="userName" path="userid" name="userName" value="${user.userName}"/ placeholder="xxx@cn.ibm.com"></div>
		<label>密码</label>
		<div class="text"><input id="password" path="password" name="password" type="password"/ vlaue="${user.password}"></div>
		<div style="margin-top:25px;"><font color="red">${errorMessage }</font></div>
		<div style="margin-top:25px;">
		<button id="button_login" style="width: 280px;height: 34px;background-color:#00AAE7;">登录</button>
		</div>
	</div>
	</div>
</form>
</div>

<div id="dialog" title="登录信息">
	<font color="red">请填写用户名和密码！</font>
</div>
</body>
</html>