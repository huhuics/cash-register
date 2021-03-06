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
                    <input type="text" class="form-control" v-model="q.sellerNo" @keyup.enter="query" placeholder="编号">
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.name" @keyup.enter="query" placeholder="姓名">
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.phone" @keyup.enter="query" placeholder="电话">
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.status">
                        <option value="">全部状态</option>
                        <option value="true">启用</option>
                        <option value="false">禁用</option>
                    </select>
                </div>
                <div class="form-group col-xs-4">
                    <a class="btn btn-default" @click="search"><i class="fa fa-search"></i>&nbsp;搜索</a>
                    <a class="btn btn-default" @click="resetSearch"><i class="fa fa-undo"></i>&nbsp;刷新</a>
                </div>
                <div class="clearfix"></div>
                <div class="pull-right">
                    <a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
                    <a class="btn btn-success" @click="update"><i class="fa fa-edit"></i>&nbsp;编辑</a>
                    <a class="btn btn-danger" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="jqGrid_wrapper">
                <table id="jqGrid"></table>
                <div id="jqGridPager" style="height: 50px;"></div>
            </div>
        </div>
        <!-- /分页表格 -->
        <!-- 添加或更新收银员 -->
        <div id="sellerDiv" style="display: none;">
            <form class="form-horizontal layerForm">
                <div class="form-group">
                    <div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;编号</span>
		                    <input type="text" class="form-control" v-model="seller.sellerNo">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;姓名</span>
		                    <input type="text" class="form-control" v-model="seller.name">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;密码</span>
		                    <input type="password" class="form-control" v-model="seller.password">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;电话</span>
		                    <input type="text" class="form-control" v-model="seller.phone">
		                </div>
                    </div>
                </div>
                <!-- <div class="form-group">
                	<div class="col-xs-12">
		            	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;角色</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="seller.role" value="seller" readonly> 收银员
		                    </span>
		                </div>
		            </div>
                </div> -->
                <div class="form-group">
                	<div class="col-xs-12">
		            	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;状态</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="seller.status" value="true">启用
		                    </span>
		                    <span class="input-group-addon" v-if="seller.role=='seller'">
		                    	<input type="radio" v-model="seller.status" value="false">禁用
		                    </span>
		                </div>
		            </div>
                </div>
                <!-- <div class="form-group">
                	<div class="col-xs-12">
		            	<div class="input-group">
		                    <span class="input-group-addon">收银端权限</span>
		                    <span class="input-group-addon">
		                    	<input type="checkbox" v-model="seller.cashPermission" value="option1"> 权限1
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="checkbox" v-model="seller.cashPermission" value="option2"> 权限2
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="checkbox" v-model="seller.cashPermission" value="option3"> 权限3
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="checkbox" v-model="seller.cashPermission" value="option4"> 权限4
		                    </span>
		                </div>
		            </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-12">
		            	<div class="input-group">
		                    <span class="input-group-addon">后台权限</span>
		                    <span class="input-group-addon">
		                    	<input type="checkbox" v-model="seller.backgroundPermission" value="option1"> 权限1
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="checkbox" v-model="seller.backgroundPermission" value="option2"> 权限2
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="checkbox" v-model="seller.backgroundPermission" value="option3"> 权限3
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="checkbox" v-model="seller.backgroundPermission" value="option4"> 权限4
		                    </span>
		                </div>
		            </div>
                </div> -->
            </form>
        </div>
        <!-- /添加或更新收银员 -->
    </div>
    <script src="${ctx}/static/js/backstage/_seller-list.js"></script>
</body>

</html>