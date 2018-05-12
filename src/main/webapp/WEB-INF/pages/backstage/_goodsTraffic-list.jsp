<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>货流列表</title>
</head>

<body>
    <div id="goodsTrafficListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-8">
                    <div class="input-group">
                        <input type="text" class="form-control" v-model="q.createTimeDown" id="datetimepickerAfter" placeholder="起始时间 yyyy-MM-dd hh:mm:ss">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="form-control" v-model="q.createTimeUp" id="datetimepickerBefore" placeholder="截止时间 yyyy-MM-dd hh:mm:ss">
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
                <div class="clearfix"></div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.trafficType">
                        <option value="">全部货流类型</option>
                        <option value="in">进货</option>
                        <option value="ordinaryOut">普通出库</option>
                        <option value="supplierOut">退货给供货商</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.trafficNo" @keyup.enter="search" placeholder="货流编号">
                </div>
                
                <div class="form-group col-xs-4">
                    <a class="btn btn-default" @click="search"><i class="fa fa-search"></i>&nbsp;搜索</a>
                    <a class="btn btn-default" @click="resetSearch"><i class="fa fa-undo"></i>&nbsp;刷新</a>
                </div>
                <div class="clearfix"></div>
                <div class="pull-right">
                    <a class="btn btn-primary" @click="addInTraffic"><i class="fa fa-download"></i>&nbsp;进货</a>
                    <a class="btn btn-primary" @click="addOutTraffic"><i class="fa fa-upload"></i>&nbsp;出库</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="jqGrid_wrapper">
                <table id="jqGrid"></table>
                <div id="jqGridPager" style="height: 50px;"></div>
            </div>
        </div>
        <!-- /分页表格 -->
        <!-- 添加进货货流 -->
        <div id="addInTrafficDiv" style="display: none;">
            <form class="form-horizontal layerForm">
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">编号</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsId">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品名称</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsName">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品条码</span>
		                    <input type="text" class="form-control" v-model="inTraffic.barCode">
		                    <span class="input-group-addon">%</span>
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品颜色</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsColor">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品尺寸</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsSize">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">供货商名称</span>
		                    <input type="text" class="form-control" v-model="inTraffic.supplierName">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品库存</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsStock">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">进货量</span>
		                    <input type="text" class="form-control" v-model="inTraffic.inCount">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">进货价</span>
		                    <input type="text" class="form-control" v-model="inTraffic.inAmount">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">进货赠送量</span>
		                    <input type="text" class="form-control" v-model="inTraffic.freeCount">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">单位</span>
		                    <input type="text" class="form-control" v-model="inTraffic.quantityUnit">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">出库价</span>
		                    <input type="text" class="form-control" v-model="inTraffic.totalAmount">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">预付款</span>
		                    <input type="text" class="form-control" v-model="inTraffic.advancePaymentAmount">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">操作员编号</span>
		                    <input type="text" class="form-control" v-model="inTraffic.operatorNo">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">备注</span>
		                    <input type="text" class="form-control" v-model="inTraffic.remark">
		                </div>
                    </div>
                </div>
                <div class="form-group">
		            <div class="col-xs-12">
		            	<div class="input-group">
		                    <span class="input-group-addon">状态</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="inTraffic.status" value="true">已完成
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="inTraffic.status" value="false">处理中
		                    </span>
		                </div>
		            </div>
		        </div>
            </form>
        </div>
        <!-- /添加进货货流 -->
        <!-- 添加出库货流 -->
        <div id="addOutTrafficDiv" style="display: none;">
            <form class="form-horizontal layerForm">
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">编号</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsId">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品名称</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsName">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品条码</span>
		                    <input type="text" class="form-control" v-model="outTraffic.barCode">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品颜色</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsColor">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品尺寸</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsSize">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">供货商名称</span>
		                    <input type="text" class="form-control" v-model="outTraffic.supplierName">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品库存</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsStock">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">单位</span>
		                    <input type="text" class="form-control" v-model="outTraffic.quantityUnit">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">出库价</span>
		                    <input type="text" class="form-control" v-model="outTraffic.totalAmount">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">操作员编号</span>
		                    <input type="text" class="form-control" v-model="outTraffic.operatorNo">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">备注</span>
		                    <input type="text" class="form-control" v-model="outTraffic.remark">
		                </div>
                    </div>
                </div>
                <div class="form-group">
		            <div class="col-xs-12">
		            	<div class="input-group">
		                    <span class="input-group-addon">类型</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="outTraffic.trafficType" value="ordinaryOut">普通出库
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="outTraffic.trafficType" value="supplierOut">退货给供货商
		                    </span>
		                </div>
		            </div>
		        </div>
            </form>
        </div>
        <!-- /添加出库货流 -->
    </div>
    <script src="${ctx}/static/js/backstage/_goodsTraffic-list.js"></script>
</body>

</html>