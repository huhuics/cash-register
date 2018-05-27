<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<link rel="stylesheet" href="${ctx}/static/css/login.css">
<link rel="stylesheet" href="${ctx}/static/plugins/elegant-icons-style/css/elegant-icons-style.css">
<title>密钥生成</title>
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
					<input type="text" class="form-control" placeholder="用户名" v-model="auth_user" autofocus>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" class="form-control" placeholder="密码" v-model="auth_password" @keyup.enter="gen">
				</div>
				<button class="btn btn-primary btn-lg btn-block" type="button" @click="gen">生成密钥</button>
			</div>
		</form>
	</div>

	<script src="${ctx}/static/js/backstage/secretKey-gen.js"></script>
</body>
</html>