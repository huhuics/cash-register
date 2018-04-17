<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>收银</title>
</head>
<body style="height: 100%;">
	<div id="app" v-cloak>
		<div class="tableDiv">
			<table class="table table-bordered table-hover largeTable">
				<thead>
					<tr>
						<th>商品条码</th>
						<th>商品名称</th>
						<th>原价</th>
						<th>折扣</th>
						<th>数量</th>
						<th>小计</th>
						<th>现价</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="summaryDiv">
			<p class="pull-left">共计0件商品</p>
			<div class="pull-right">
				<button type="button" class="btn btn-primary">新增商品</button>
				<button type="button" class="btn btn-primary">网店订单</button>
				<button type="button" class="btn btn-primary">删除</button>
			</div>
		</div>
		<div class="operationDiv">
			<div class="pull-left"></div>
			<div class="pull-left"></div>
			<div class="pull-left"></div>
		</div>

	</div>

	<script src="${ctx}/static/js/frontstage/cashRegister.js"></script>
</body>
</html>