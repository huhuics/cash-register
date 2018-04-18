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
		<div>
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
			<table class="table table-bordered" style="background-color: #EEE;">
				<tbody>
					<tr>
						<td class="col-xs-3"><div>
								<div class="col-xs-12">
									<div class="input-group">
										<input type="text" class="form-control"
											placeholder="条码/拼音码/品名"> <span
											class="input-group-btn">
											<button class="btn btn-default" type="button">确定</button>
										</span>
									</div>
								</div>
								<div class="col-xs-12 div-height-5"></div>
								<div class="col-xs-12">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="输入价格无码收银">
										<span class="input-group-btn">
											<button class="btn btn-default" type="button">确定</button>
										</span>
									</div>
								</div>
							</div></td>
						<td class="col-xs-3"><div>
								<div class="col-xs-12">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="会员号/手机号">
										<span class="input-group-btn">
											<button class="btn btn-default" type="button">确定</button>
										</span>
									</div>
								</div>
								<div class="col-xs-12 div-height-5"></div>
								<div class="col-xs-12">
									<button class="btn btn-info" type="button">
										姓名 <span class="badge">老胡</span>
									</button>
									<button class="btn btn-info" type="button">
										余额 <span class="badge">￥0</span>
									</button>
									<button class="btn btn-info" type="button">
										积分 <span class="badge">0</span>
									</button>
								</div>
							</div></td>
						<td class="col-xs-4" style="vertical-align: middle;"><div>
								<button type="button" class="btn btn-danger btn-lg btn-block">收款&nbsp;&nbsp;￥0.00</button>
							</div></td>
					</tr>
				</tbody>
			</table>
		</div>

	</div>

	<script src="${ctx}/static/js/frontstage/cashRegister.js"></script>
</body>
</html>