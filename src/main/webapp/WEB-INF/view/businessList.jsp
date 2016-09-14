<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
			<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
				<title>欢迎访问待办平台</title>
				<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui-1.11.4/jquery-ui.css" type="text/css">
				<link rel="stylesheet"
						href="${pageContext.request.contextPath}/js/jqGrid/css/jquery-ui-1.9.2.custom.css"
						type="text/css" media="screen"/>
				<link rel="stylesheet"
						href="${pageContext.request.contextPath}/js/jqGrid/css/ui.jqgrid.css"
						type="text/css" media="screen" />
						
				<script type="text/javascript"
                        src="${pageContext.request.contextPath}/js/jqGrid/jquery-1.7.2.min.js"></script>		
				<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/jqGrid/js/jquery-ui-1.9.2.custom.min.js"></script>
				<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/jqGrid/js/i18n/grid.locale-cn.js"></script>
				<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/jqGrid/js/jquery.jqGrid.min.js"></script>
               <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui-1.11.4/jquery-ui.css" type="text/css"> --%>
               <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.11.4/jquery-ui.js"></script>
			   <script type="text/javascript"
						src="${pageContext.request.contextPath}/js/syslib/common.js"></script>
</head>

<body background="${pageContext.request.contextPath}/images/login/login_bg.png?<%=new Date().getTime()%>">
	<div style="text-align: center;padding-top: 50px;" >
		<a href="#"><img
			src="${pageContext.request.contextPath}/images/login/logo.gif?<%=new Date().getTime()%>"
			id="logoimage" /></a>
	</div>

	<div style="width: 1372px;margin: 0 auto;padding-top: 0px;clear: both;zoom: 1;" >
		<div id="Databox">
		            <div id="accordion">
		               <h3>FAM系统</h3>
	                   <div style="width: 1300px;"> 
	                         <table id="list1"></table>
	                         <div id="pager1"></div>
	                   </div>
	                   
	                   <!-- <h3>测试1</h3>
	                   <div style="width: 1300px;"> 
	                         <table id="list2"></table>
	                         <div id="pager2"></div>
	                   </div>
	                   <h3>测试2</h3>
	                   <div style="width: 1300px;"> 
	                         <table id="list3"></table>
	                         <div id="pager3"></div>
	                   </div> -->
	                   
	                   <c:forEach var="type" items="${typeList}" varStatus="s">
                          <h3><c:out value="${type}"/></h3>
                          <div style="width: 1300px;"> 
	                         <table id="dynamic_list_${s.index}" title="${type}"></table>
	                         <div id="dynamic_pager_${s.index}"></div>
	                      </div>
                       </c:forEach>
                    </div>
		</div>
	</div>
</body>
</html>