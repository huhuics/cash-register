<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Cash Register Index</title>
<link rel="stylesheet"
	href="${ctx}/static/plugins/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${ctx}/static/plugins/adminlte/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${ctx}/static/plugins/adminlte/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="${ctx}/static/plugins/adminlte/css/all-skins.min.css">

</head>

<!-- ADD THE CLASS layout-boxed TO GET A BOXED LAYOUT -->
<body class="hold-transition skin-green sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper" id="app" v-cloak></div>

	<script src="${ctx}/static/plugins/jquery/jquery-3.2.1.min.js"></script>
	<script src="${ctx}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/plugins/adminlte/app.js"></script>
	<script src="${ctx}/static/js/vue.min.js"></script>
	<script src="${ctx}/static/js/router.js"></script>
	<script src="${ctx}/static/plugins/layer/layer.js"></script>
</body>
</html>