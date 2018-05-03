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

<body class="skin-green layout-top-nav">
	<div class="wrapper" id="app" v-cloak>
		<!-- header -->
		<header class="main-header"> <nav
			class="navbar navbar-static-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="javascript:void(0);" class="navbar-brand">老胡收银系统</a>
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar-collapse">
					<i class="fa fa-bars"></i>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#"><i class="fa fa-cny"></i>&nbsp;收银</a></li>
					<li><a href="#"><i class="fa fa-cog"></i>&nbsp;新增商品</a></li>
					<li><a href="#"><i class="fa fa-cog"></i>&nbsp;新增会员</a></li>
					<li><a href="#"><i class="fa fa-cog"></i>&nbsp;销售单据</a></li>
					<li><a href="#"><i class="fa fa-cog"></i>&nbsp;商品编辑</a></li>
					<li><a href="#"><i class="fa fa-cog"></i>&nbsp;帮助</a></li>
					<li><a href="#"><i class="fa fa-cog"></i>&nbsp;系统设置</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><i class="fa fa-cog"></i>&nbsp;货物货流<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;退货</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;盘点</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;调货</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;进货</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;订货</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;货流通知</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><i class="fa fa-cog"></i>&nbsp;交接班</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><i class="fa fa-cog"></i>&nbsp;所有功能<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;交接班</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;新增会员</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;销售单据</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;退货</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;盘点</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;货流通知</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;调货</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;进货</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;订货</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;商品编辑</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;系统设置</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;打开钱箱</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;报损</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;半成品制作</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;沽清</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;帮助</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;通知</a></li>
							<li><a href="#"><i class="fa fa-cog"></i>&nbsp;预付卡</a></li>
						</ul></li>
				</ul>
			</div>

		</div>
		</nav> </header>
		<!-- ./header -->

		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content"> <iframe
				scrolling="yes" frameborder="0"
				style="width: 100%; min-height: 200px; overflow: visible; background: #fff;"
				:src="cashRegisterPage"></iframe> </section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer"> Copyright &copy; 2018 All Rights
		Reserved </footer>

	</div>

	<script src="${ctx}/static/plugins/jquery/jquery-3.2.1.min.js"></script>
	<script src="${ctx}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/plugins/adminlte/js/adminlte.min.js"></script>
	<script src="${ctx}/static/js/vue.min.js"></script>
	<script src="${ctx}/static/plugins/layer/layer.js"></script>
	<script src="${ctx}/static/js/frontstage/index.js"></script>
</body>
</html>