<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>销售单据列表</title>
</head>

<body>
    <div id="salesTradeDetailListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
            	<div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.sellerNo">
                        <option value="">全部收银员</option>
                        <option v-for="seller in sellers" :value="seller.sellerNo">{{seller.name}}</option>
                    </select>
                </div>
            	<div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.payChenal">
                        <option value="">全部支付方式</option>
                        <option value="cash">现金</option>
                        <option value="balance">储值卡</option>
                        <option value="unionpay">银联卡</option>
                        <option value="alipay">支付宝支付</option>
                        <option value="wcpay">微信支付</option>
                    </select>
                </div>
            	<div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.tradeType">
                        <option value="">全部交易类型</option>
                        <option value="SALES">销售</option>
                        <option value="REFUND">退款</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.tradeNo" @keyup.enter="search" placeholder="流水号">
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
                <div class="pull-right">
                    <a class="btn btn-info" @click="cancel"><i class="fa fa-trash-o"></i>&nbsp;反结账</a>
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
    <script src="${ctx}/static/js/frontstage/_sales-tradeDetail-list.js"></script>
</body>

</html>