<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>收银员列表</title>
</head>
<body>
	<div id="app" v-cloak>
		<!-- 分页表格 -->
		<div>
			<div class="grid-btn">
				<div class="form-group col-xs-2">
					<input type="text" class="form-control" v-model="q.sellerNo"
						@keyup.enter="query" placeholder="编号">
				</div>
				<div class="form-group col-xs-2">
					<input type="text" class="form-control" v-model="q.name"
						@keyup.enter="query" placeholder="姓名">
				</div>
				<div class="form-group col-xs-2">
					<input type="text" class="form-control" v-model="q.phone"
						@keyup.enter="query" placeholder="电话">
				</div>
				<div class="form-group col-xs-2">
					<select class="selectpicker" v-model="q.status"
						data-live-search="true">
						<option value="1">启用</option>
						<option value="0">禁用</option>
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
						<a class="btn btn-success" @click=""><i
							class="fa fa-check"></i>&nbsp;新增收银员</a>
				</div>
				<div class="clearfix"></div>
			</div>

			<table id="jqGrid"></table>
			<div id="jqGridPager" style="height: 50px;"></div>
		</div>
		<!-- /.分页表格 -->
	</div>
	
	<script src="${ctx}/static/js/backstage/_seller-list.js"></script>
</body>
</html>