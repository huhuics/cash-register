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
	<div class="container">

		<form class="login-form" role="form" action="${ctx}/login"
			method="post" id="myForm">
			<!-- error tip -->

			<div class="login-wrap">
				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" class="form-control" placeholder="用户名"
						name="username" required alphanumeric="true" autofocus>
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<input type="password" class="form-control" placeholder="密码"
						name="password" required alphanumeric="true">
				</div>

				<button class="btn btn-primary btn-lg btn-block" type="submit">登陆</button>

				<c:if test="${not empty errorMsg }">
					<div class="alert alert-danger alert-dismissible center-block"
						role="alert" style="max-width: 600px; margin-top: 15px;">
						${errorMsg }</div>
				</c:if>
			</div>
		</form>
	</div>
</body>
</html>