<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商品资料</title>
</head>

<body>
    <div id="goodsListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.goodsStatus" data-live-search="true">
                        <option value="">全部状态</option>
                        <option value="true">启用</option>
                        <option value="false">禁用</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.goodsBrand" data-live-search="true">
                        <option value="">全部品牌</option>
                        <option v-for="brand in goods_brands" :value="brand.brandName">{{brand.brandName}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.categoryName" data-live-search="true">
                        <option value="">全部种类</option>
                        <option v-for="category in goods_categorys" :value="category.id">{{category.name}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.goodsTag" data-live-search="true">
                        <option value="">全部标签</option>
                        <option v-for="tag in goods_tags" :value="tag.tagName">{{tag.tagName}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.supplierName" data-live-search="true">
                        <option value="">供货商</option>
                        <option v-for="supplier in goods_suppliers" :value="supplier">{{supplier}}</option>
                    </select>
                </div>
                <div class="clearfix"></div>
                <div class="form-group col-xs-3">
                    <input type="text" class="form-control" v-model="q.keyword" @keyup.enter="search" placeholder="条码/拼音码/商品名">
                </div>
                <div class="form-group col-xs-4">
                    <a class="btn btn-default" @click="search"><i class="fa fa-search"></i>&nbsp;搜索</a>
                    <a class="btn btn-default" @click="resetSearch"><i class="fa fa-undo"></i>&nbsp;刷新</a>
                </div>
                <div class="clearfix"></div>
                <div class="pull-right">
                    <a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
                    <a class="btn btn-success" @click="update"><i class="fa fa-edit"></i>&nbsp;编辑</a>
                    <a class="btn btn-warning" @click="batchUpdate"><i class="fa fa-edit"></i>&nbsp;批量编辑</a>
                    <a class="btn btn-danger" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
					<a class="btn btn-info" @click="importGoods"><i class="fa fa-download"></i>&nbsp;导入</a>
                    <a class="btn btn-info" @click="exportGoods"><i class="fa fa-upload"></i>&nbsp;导出</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <table id="jqGrid"></table>
            <div id="jqGridPager" style="height: 50px;"></div>
        </div>
        <!-- /.分页表格 -->
        
        <%@ include file="./_goods-list/_goods-add.jsp"%>
        <%@ include file="./_goods-list/_goods-batchEdit.jsp"%>
    </div>
    
    <script src="${ctx}/static/js/backstage/_goods-list/var-jqGrid-option.js"></script>
    <script src="${ctx}/static/js/backstage/_goods-list/var-goods-entity.js"></script>
    <script src="${ctx}/static/js/backstage/_goods-list/var-vue-data.js"></script>
    <script src="${ctx}/static/js/backstage/_goods-list.js"></script>
    
</body>

</html>