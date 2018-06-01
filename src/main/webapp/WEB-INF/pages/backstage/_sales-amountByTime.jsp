<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>营业报表</title>
</head>

<body>
    <div id="salesAmountByTimeDiv" v-cloak>
        <div>
            <div class="grid-btn">
            	<div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.timePeriod">
                        <option value="hour">小时</option>
                        <option value="day">天</option>
                        <option value="month">月</option>
                    </select>
                </div>
                <div class="form-group col-xs-8">
                    <div class="input-group">
                        <input type="text" class="form-control" v-model="q.timeDown" id="datetimepickerAfter" placeholder="起始时间 yyyy-MM-dd hh:mm:ss">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="form-control" v-model="q.timeUp" id="datetimepickerBefore" placeholder="截止时间 yyyy-MM-dd hh:mm:ss">
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
                        <th>时间</th>
                        <th>实收总金额</th>
                        <th>利润</th>
                        <th>商品数量</th>
                        <th>利润率</th>
                    </thead>
                    <tbody>
                        <tr v-for="item in salesAmountByTimeArray">
                            <td>{{item.time}}</td>
                            <td><span v-if="item.totalActualAmount != null">{{item.totalActualAmount.amount}}</span></td>
                            <td><span v-if="item.profitAmount != null">{{item.profitAmount.amount}}</span></td>
                            <td>{{item.goodsCount}}</td>
                            <td>{{item.profitRate}}&nbsp;%</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="${ctx}/static/js/backstage/_sales-amountByTime.js"></script>
</body>

</html>