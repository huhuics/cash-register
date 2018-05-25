<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>系统信息</title>
</head>

<body style="font-family: 'Microsoft YaHei'; max-height: 600px;overflow: hidden;">
	<div class="jumbotron" id="systemConfigDiv" style="height: 100%; padding-left: 100px;" v-cloak>
		<h1>{{shopName}}</h1>
		<p>联系地址：{{address}}</p>
		<p>客服电话：{{phone}}</p>
		<p>备用金：<span v-if="pettyAmount">启用</span><span v-else>不启用</span></p>
	</div>
	<script src="${ctx}/static/js/frontstage/_system-config.js"></script>
</body>

</html>