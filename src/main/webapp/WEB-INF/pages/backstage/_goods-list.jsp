<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品资料</title>
</head>
<body>
	<div id="app" v-cloak>
		<!-- 分页表格 -->
		<div>
			<div class="grid-btn">
				<div class="form-group col-xs-2">
					<input type="text" class="form-control" v-model="q.tradeNo"
						@keyup.enter="query" placeholder="订单号">
				</div>
				<div class="form-group col-xs-2">
					<input type="text" class="form-control" v-model="q.customerNo"
						@keyup.enter="query" placeholder="客户号">
				</div>
				<div class="form-group col-xs-2">
					<input type="text" class="form-control" v-model="q.accountNo"
						@keyup.enter="query" placeholder="账户号">
				</div>
				<div class="form-group col-xs-2">
					<select class="selectpicker" v-model="q.status"
						data-live-search="true">
						<option value="">全部状态</option>
						<option value="PROCESSING">处理中</option>
						<option value="SUCCESS">处理成功</option>
						<option value="FAIL">处理失败</option>
					</select>
				</div>
				<div class="form-group col-xs-1 pull-right">
					<a class="btn btn-default" @click="refresh"><i
						class="fa fa-undo"></i>&nbsp;刷新</a>
				</div>
				<div class="form-group col-xs-1 pull-right">
					<a class="btn btn-default" @click="query"><i
						class="fa fa-search"></i>&nbsp;搜索</a>
				</div>
				<div class="clearfix"></div>
				<div class="pull-right">
					<shiro:hasPermission name="order:withdraw2bank:success">
						<a class="btn btn-success" @click="successWithdrawConfirm"><i
							class="fa fa-check"></i>&nbsp;成功提现</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="order:withdraw2bank:deny">
						<a class="btn btn-danger" @click="denyWithdrawConfirm"><i
							class="fa fa-close"></i>&nbsp;不予提现</a>
					</shiro:hasPermission>
				</div>
				<div class="clearfix"></div>
			</div>

			<table id="jqGrid"></table>
			<div id="jqGridPager"></div>
		</div>
		<!-- /.分页表格 -->
	</div>
	
	<script src="${ctx}/static/js/backstage/_goods-list.js"></script>
</body>
</html>