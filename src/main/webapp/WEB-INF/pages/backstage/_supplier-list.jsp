<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>供货商列表</title>
</head>

<body>
    <div id="supplierListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.status">
                        <option value="">全部状态</option>
                        <option value="true">启用</option>
                        <option value="false">禁用</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.supplierName" @keyup.enter="search" placeholder="姓名">
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
                    <a class="btn btn-info" @click="importSupplier"><i class="fa fa-download"></i>&nbsp;导入</a>
                    <a class="btn btn-info" @click="exportSupplier"><i class="fa fa-upload"></i>&nbsp;导出</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="jqGrid_wrapper">
                <table id="jqGrid"></table>
                <div id="jqGridPager" style="height: 50px;"></div>
            </div>
        </div>
        <!-- /分页表格 -->
        <!-- 添加或更新供货商 -->
        <div id="supplierDiv" style="display: none;">
            <form class="form-horizontal layerForm">
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;编号</span>
		                    <input type="text" class="form-control" v-model="supplier.supplierCode">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;名称</span>
		                    <input type="text" class="form-control" v-model="supplier.supplierName">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">拼音码</span>
		                    <input type="text" class="form-control" v-model="supplier.pinyinCode">
		                    <span class="input-group-btn"><button class="btn btn-success" type="button" @click="getPinyinCode">生成</button></span>
		                </div>
                    </div>
                </div>
                <h5 class="page-header"></h5>
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">联系人</span>
		                    <input type="text" class="form-control" v-model="supplier.contactName">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">联系电话</span>
		                    <input type="text" class="form-control" v-model="supplier.contactPhone">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">联系邮箱</span>
		                    <input type="text" class="form-control" v-model="supplier.contactEmail">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">配送费返点</span>
		                    <input type="text" class="form-control" v-model="supplier.deliveryRebate">
		                    <span class="input-group-addon">%</span>
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">固定返利点</span>
		                    <input type="text" class="form-control" v-model="supplier.regularRebate">
		                    <span class="input-group-addon">%</span>
		                </div>
                    </div>
                </div>
                <div class="form-group">
		            <div class="col-xs-7">
		            	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;状态</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="supplier.status" value="true">启用
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="supplier.status" value="false">禁用
		                    </span>
		                </div>
		            </div>
		        </div>
                <div class="form-group">
		            <div class="col-xs-12">
		            	<textarea class="form-control" rows="3" placeholder="备注" v-model="supplier.remark"></textarea>
		            </div>
		        </div>
            </form>
        </div>
        <!-- /添加或更新供货商 -->
    </div>
    <script src="${ctx}/static/js/backstage/_supplier-list.js"></script>
</body>

</html>