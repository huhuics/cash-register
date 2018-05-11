<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>库存预警查询</title>
</head>

<body>
    <div id="goodsListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.categoryName" data-live-search="true">
                        <option value="">全部分类</option>
                        <option v-for="category in goods_categorys" :value="category.name">{{category.prefix+category.name}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.supplierName" data-live-search="true">
                        <option value="">全部供货商</option>
                        <option v-for="supplier in goods_suppliers" :value="supplier">{{supplier}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.warningType" data-live-search="true">
                        <option value="less">库存不足</option>
                        <option value="over">库存过剩</option>
                    </select>
                </div>
                <div class="form-group col-xs-4">
                    <a class="btn btn-default" @click="search"><i class="fa fa-search"></i>&nbsp;搜索</a>
                    <a class="btn btn-default" @click="resetSearch"><i class="fa fa-undo"></i>&nbsp;刷新</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <table id="jqGrid"></table>
            <div id="jqGridPager" style="height: 50px;"></div>
        </div>
        <!-- /.分页表格 -->
    </div>
    
    <script src="${ctx}/static/js/backstage/_stock-warning-list.js"></script>
    
</body>

</html>