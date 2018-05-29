<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>盘点</title>
</head>

<body>
    <div id="stockCheckListDiv" v-cloak>
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-8">
                    <div class="input-group">
                        <input type="text" class="form-control" v-model="q.checkDateDown" id="datetimepickerAfter" placeholder="起始时间 yyyy-MM-dd hh:mm:ss">
                        <span class="input-group-addon">~</span>
                        <input type="text" class="form-control" v-model="q.checkDateUp" id="datetimepickerBefore" placeholder="截止时间 yyyy-MM-dd hh:mm:ss">
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
                <div class="pull-right">
                    <a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;盘点</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div style="width: 100%">
                <table class="table table-bordered tableStyle">
                    <thead>
                        <th>序号</th>
                        <th>日期</th>
                        <th>盘点人</th>
                        <th>备注</th>
                    </thead>
                    <tbody>
                        <tr v-for="(goodsStockCheck, index) in goodsStockCheckList">
                            <td>{{index + 1}}</td>
                            <td>{{goodsStockCheck.checkDate}}</td>
                            <td>{{goodsStockCheck.sellerNo}}</td>
                            <td>{{goodsStockCheck.remark}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- 添加盘点页面 -->
        <div id="stockCheckAddDiv" style="display: none">
	        <div>
	            <table class="table table-bordered table-hover" style="font-family: 'Microsoft YaHei';">
	                <thead>
	                    <tr>
	                        <th>序号</th>
	                        <th>条码</th>
	                        <th>名称</th>
	                        <th>颜色</th>
	                        <th>尺码</th>
	                        <th>盘前库存</th>
	                        <th>差异库存</th>
	                        <th>盘后库存</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr v-for="(item, index) in check_list" style="height: 30px; line-height: 30px;">
	                        <td>{{index+1}}</td>
	                        <td>{{item.barCode}}</td>
	                        <td>{{item.goodsName}}</td>
	                        <td>{{item.goodsColor}}</td>
	                        <td>{{item.goodsSize}}</td>
	                        <td>{{item.primaryGoodsStock}}</td>
	                        <td>{{item.stockDiff}}</td>
	                        <td>
	                            <input class="form-control" type="text" v-model="item.checkedGoodsStock" @blur="editItemCheckedGoodsStockByBarCode(item.barCode,item.checkedGoodsStock)" @keyup.enter="editItemCheckedGoodsStockByBarCode(item.barCode,item.checkedGoodsStock)">
	                        </td>
	                        <td>
	                            <button class="btn btn-danger" type="button" @click="deleteItemByBarCode(item.barCode)">删除</button>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	        </div>
	        <div class="operationDiv">
	            <table class="table table-bordered" style="background-color: #EEE;">
	                <tbody>
	                    <tr>
	                        <td class="col-xs-3">
	                            <div>
	                                <div class="col-xs-12">
	                                    <div class="input-group">
	                                        <input type="text" class="form-control toFocus" v-model="goods_keyword" @keyup.enter="searchGoods" placeholder="条码/拼音码/品名">
	                                        <span class="input-group-btn">
	                                    <button class="btn btn-default" type="button" @click="searchGoods">确定</button>
	                                </span>
	                                    </div>
	                                </div>
	                            </div>
	                        </td>
	                        <td class="col-xs-6">
	                            <div>
	                                <div class="col-xs-12">
	                                    <button class="btn btn-info" type="button">
	                                        总数 <span class="badge">{{all_count}}&nbsp;</span>
	                                    </button>
	                                    <button class="btn btn-info" type="button">
	                                        已盘点 <span class="badge">{{checked_count}}</span>
	                                    </button>
	                                    <button class="btn btn-info" type="button">
	                                        未盘点 <span class="badge">{{all_count-checked_count}}</span>
	                                    </button>
	                                </div>
	                            </div>
	                        </td>
	                        <td class="col-xs-3">
	                            <div>
	                                <div class="col-xs-12">
	                                    <textarea class="form-control" rows="3" placeholder="备注" v-model="remark"></textarea>
	                                </span>
	                                    </div>
	                                </div>
	                            </div>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	        </div>
	    </div>
	    <!-- /.添加盘点页面 -->
	    <%@ include file="./_goods-check-list/_goods-select.jsp"%>
    </div>
    <script src="${ctx}/static/js/frontstage/_stock-check-list.js"></script>
</body>

</html>