<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${ctx}/static/plugins/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${ctx}/static/css/login.css">
<link rel="stylesheet"
	href="${ctx}/static/plugins/elegant-icons-style/css/elegant-icons-style.css">
<link rel="stylesheet"
	href="${ctx}/static/plugins/adminlte/css/font-awesome.min.css">
<title>Cash Register Login</title>

</head>

<body class="login-img3-body">
	<div class="container" id="app">
		<form class="login-form" role="form">
			<div class="login-wrap">
				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" class="form-control" placeholder="用户名"
						v-model="loginName" required alphanumeric="true" autofocus>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" class="form-control" placeholder="密码"
						v-model="loginPassword" required alphanumeric="true">
				</div>
				<button class="btn btn-primary btn-lg btn-block" type="button" @click="login">登录</button>
			</div>
		</form>
	</div>
	<script src="${ctx}/static/plugins/jquery/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/vue.min.js"></script>
    <script src="${ctx}/static/plugins/layer/layer.js"></script>
    <script src="${ctx}/static/js/common.js"></script>
	<script src="${ctx}/static/js/frontstage/login.js"></script>
</body>
</html>