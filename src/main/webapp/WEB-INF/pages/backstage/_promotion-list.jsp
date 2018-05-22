<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>促销活动设置</title>
</head>

<body>
    <div id="promotionListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.promotionType">
                        <option value="">全部类型</option>
                        <option value="discount">打折促销</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.status">
                        <option value="">全部状态</option>
                        <option value="true">未过期</option>
                        <option value="false">已结束</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.promotionName" @keyup.enter="search" placeholder="促销名称">
                </div>
                <div class="form-group col-xs-4">
                    <a class="btn btn-default" @click="search"><i class="fa fa-search"></i>&nbsp;搜索</a>
                    <a class="btn btn-default" @click="resetSearch"><i class="fa fa-undo"></i>&nbsp;刷新</a>
                </div>
                <div class="clearfix"></div>
                <div class="pull-right">
                    <a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
                    <a class="btn btn-success" @click="update"><i class="fa fa-edit"></i>&nbsp;编辑</a>
                    <a class="btn btn-warning" @click="updatePromotionGoods"><i class="fa fa-edit"></i>&nbsp;编辑促销商品</a>
                    <a class="btn btn-danger" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <table id="jqGrid"></table>
            <div id="jqGridPager" style="height: 50px;"></div>
        </div>
        <!-- /.分页表格 -->
        <%@ include file="./_promotion-list/_promotion-add.jsp" %>
        <%@ include file="./_promotion-list/_promotion-goods-list.jsp" %>
    </div>
    <script src="${ctx}/static/js/backstage/_promotion-list/var-jqGrid-option.js"></script>
    <script src="${ctx}/static/js/backstage/_promotion-list/var-promotion-entity.js"></script>
    <script src="${ctx}/static/js/backstage/_promotion-list/var-vue-data.js"></script>
    <script src="${ctx}/static/js/backstage/_promotion-list.js"></script>
</body>

</html>