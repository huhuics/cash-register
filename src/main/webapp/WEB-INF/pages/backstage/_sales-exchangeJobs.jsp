<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>交接班记录</title>
</head>

<body>
    <div id="salesExchangeJobsDiv" v-cloak>
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-8">
                    <div class="input-group">
                        <input type="text" class="form-control" v-model="q.gmtCreateDown" id="datetimepickerAfter" placeholder="起始时间 yyyy-MM-dd hh:mm:ss">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="form-control" v-model="q.gmtCreateUp" id="datetimepickerBefore" placeholder="截止时间 yyyy-MM-dd hh:mm:ss">
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
                <div class="form-group col-xs-2">
                    <a class="btn btn-primary" @click="search"><i class="fa fa-search"></i>&nbsp;查询</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div style="width: 100%">
                <table class="table table-bordered tableStyle">
                    <thead>
                        <th>开始时间</th>
                        <th>结束时间</th>
                        <th>收银员</th>
                        <th>收银总额</th>
                        <th>现金支付</th>
                        <th>储值卡支付</th>
                        <th>银联支付</th>
                        <th>支付宝支付</th>
                        <th>微信支付</th>
                        <th>pettyCashAmount</th>
                        <th>paidAmount</th>
                        <th>isfinished</th>
                    </thead>
                    <tbody>
                        <tr v-for="exchangeJobDetail in exchangeJobDetailList">
                            <td>{{exchangeJobDetail.startTime}}</td>
                            <td>{{exchangeJobDetail.endTime}}</td>
                            <td>{{exchangeJobDetail.sellerNo}}</td>
                            <td>{{exchangeJobDetail.checkoutTotalAmount}}</td>
                            <td>{{exchangeJobDetail.cashAmount}}</td>
                            <td>{{exchangeJobDetail.balanceAmount}}</td>
                            <td>{{exchangeJobDetail.unionpayAmount}}</td>
                            <td>{{exchangeJobDetail.alipayAmount}}</td>
                            <td>{{exchangeJobDetail.wcpayAmount}}</td>
                            <td>{{exchangeJobDetail.pettyCashAmount}}</td>
                            <td>{{exchangeJobDetail.paidAmount}}</td>
                            <td>{{exchangeJobDetail.isfinished}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="${ctx}/static/js/backstage/_sales-exchangeJobs.js"></script>
</body>

</html>