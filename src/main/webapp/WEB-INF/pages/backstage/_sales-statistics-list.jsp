<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>销售统计</title>
</head>

<body>
    <div id="saleStatisticsListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.goodsBrand" data-live-search="true">
                        <option value="">全部品牌</option>
                        <option v-for="brand in goods_brands" :value="brand.brandName">{{brand.brandName}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.categoryName" data-live-search="true">
                        <option value="">全部分类</option>
                        <option v-for="category in goods_categorys" :value="category.name">{{category.prefix+category.name}}</option>
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
                        <option value="">全部供货商</option>
                        <option v-for="supplier in goods_suppliers" :value="supplier">{{supplier}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.barCode" @keyup.enter="search" placeholder="条码">
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.goodsName" @keyup.enter="search" placeholder="商品名称">
                </div>
                <div class="clearfix"></div>
                <div class="form-group col-xs-8">
                    <div class="input-group">
                        <input type="text" class="form-control" v-model="q.tradeTimeDown" id="datetimepickerAfter" placeholder="起始时间 yyyy-MM-dd hh:mm:ss">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="form-control" v-model="q.tradeTimeUp" id="datetimepickerBefore" placeholder="截止时间 yyyy-MM-dd hh:mm:ss">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">时间范围&nbsp;<span class="caret"></span></button>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a href="javascript:void(0);" @click="rangeToday">今天</a></li>
                                <li><a href="javascript:void(0);" @click="rangeYesterday">昨天</a></li>
                                <li><a href="javascript:void(0);" @click="rangeDayBeforeYesterday">前天</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="javascript:void(0);" @click="rangeWeek">本周</a></li>
                                <li><a href="javascript:void(0);" @click="rangeLastWeek">上周</a></li>
                                <li><a href="javascript:void(0);" @click="range7Days">最近7天</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="javascript:void(0);" @click="rangeMonth">本月</a></li>
                                <li><a href="javascript:void(0);" @click="rangeLastMonth">上月</a></li>
                            </ul>
                        </div>
                        <!-- /btn-group -->
                    </div>
                </div>
                <div class="form-group col-xs-4">
                    <a class="btn btn-default" @click="search"><i class="fa fa-search"></i>&nbsp;搜索</a>
                    <a class="btn btn-default" @click="resetSearch"><i class="fa fa-undo"></i>&nbsp;刷新</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="jqGrid_wrapper">
                <table id="jqGrid"></table>
                <div id="jqGridPager" style="height: 50px;"></div>
            </div>
        </div>
        <!-- /分页表格 -->
    </div>
    <script src="${ctx}/static/js/backstage/_sales-statistics-list.js"></script>
</body>

</html>