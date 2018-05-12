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
<<<<<<< HEAD
            	<div class="form-group col-xs-12">
				    <label>商品信息</label>
				</div>
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品ID(这个不需要传吧？)</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsId">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-7">
                		<div class="input-group">
		                    <span class="input-group-addon">商品条码</span>
		                    <input type="text" class="form-control" v-model="inTraffic.barCode">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品名称</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsName">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品颜色</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsColor">
		                    <span class="input-group-addon">商品尺寸</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsSize">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品库存</span>
		                    <input type="text" class="form-control" v-model="inTraffic.goodsStock">
		                </div>
                    </div>
                </div>
                <div class="form-group col-xs-12">
				    <label>货流信息</label>
				</div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">供货商</span>
		                    <input type="text" class="form-control" v-model="inTraffic.supplierName">
		                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="_editSupplier('in')">选择</button></span>
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">进货价</span>
		                    <input type="text" class="form-control" v-model="inTraffic.inAmount">
		                    <span class="input-group-addon">元</span>
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">进货量</span>
		                    <input type="text" class="form-control" v-model="inTraffic.inCount">
		                    <span class="input-group-addon">赠送量</span>
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
		                    <span class="input-group-addon">总价</span>
		                    <input type="text" class="form-control" v-model="inTraffic.totalAmount">
		                    <span class="input-group-addon">元</span>
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">预付款</span>
		                    <input type="text" class="form-control" v-model="inTraffic.advancePaymentAmount">
		                    <span class="input-group-addon">元</span>
		                </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-7">
                	</div>
		            <div class="col-xs-5">
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
                <div class="form-group col-xs-12">
				    <label>其它信息</label>
				</div>
                <div class="form-group">
                    <div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">操作员编号</span>
		                    <input type="text" class="form-control" v-model="inTraffic.operatorNo">
		                </div>
                    </div>
                </div>
                <div class="form-group">
		            <div class="col-xs-12">
		            	<textarea class="form-control" rows="3" placeholder="备注" v-model="inTraffic.remark"></textarea>
		            </div>
		        </div>
            </form>
        </div>
        <!-- /添加进货货流 -->
        <!-- 添加出库货流 -->
        <div id="addOutTrafficDiv" style="display: none;">
            <form class="form-horizontal layerForm">
            	<div class="form-group col-xs-12">
				    <label>商品信息</label>
				</div>
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品ID(这个不需要传吧？)</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsId">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-7">
                		<div class="input-group">
		                    <span class="input-group-addon">商品条码</span>
		                    <input type="text" class="form-control" v-model="outTraffic.barCode">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品名称</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsName">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品颜色</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsColor">
		                    <span class="input-group-addon">商品尺寸</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsSize">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">商品库存</span>
		                    <input type="text" class="form-control" v-model="outTraffic.goodsStock">
		                </div>
                    </div>
                </div>
                <div class="form-group col-xs-12">
				    <label>货流信息</label>
				</div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">供货商</span>
		                    <input type="text" class="form-control" v-model="outTraffic.supplierName">
		                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="_editSupplier('out')">选择</button></span>
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<select class="form-control" v-model="outTraffic.trafficType">
                   			<option value="">---选择出库类型---</option>
                   			<option value="ordinaryOut">普通出库</option>
                   			<option value="supplierOut">退货给供货商</option>
                   		</select>
		            </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                   		<select class="form-control" v-model="outTraffic.outPriceType">
                   			<option value="">---选择出库价格类型---</option>
                   			<option value="last_import_price">以最近进货价出库</option>
                   			<option value="average_import_price">以平均进货价出库</option>
                   			<option value="sales_price">以商品销售价</option>
                   			<option value="trade_price">以商品批发价</option>
                   		</select>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">出库价</span>
		                    <input type="text" class="form-control" v-model="outTraffic.outAmount">
		                    <span class="input-group-addon">元</span>
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">出库量</span>
		                    <input type="text" class="form-control" v-model="outTraffic.outCount">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">单位</span>
		                    <input type="text" class="form-control" v-model="outTraffic.quantityUnit">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">小计</span>
		                    <input type="text" class="form-control" v-model="outTraffic.totalAmount">
		                </div>
                    </div>
                </div>
                <div class="form-group col-xs-12">
				    <label>其它信息</label>
				</div>
                <div class="form-group">
                    <div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">操作员编号</span>
		                    <input type="text" class="form-control" v-model="outTraffic.operatorNo">
		                </div>
                    </div>
                </div>
                <div class="form-group">
		            <div class="col-xs-12">
		            	<textarea class="form-control" rows="3" placeholder="备注" v-model="outTraffic.remark"></textarea>
		            </div>
		        </div>
            </form>
        </div>
        <!-- /添加出库货流 -->
        <!-- 选择供货商 -->
		<div id="supplierDiv" style="display: none;">
		    <form class="form-horizontal layerForm">
		    	<div class="form-group">
		            <div class="col-xs-12">
		            	<div class="input-group" v-for="supplier in goods_suppliers">
		                    <span class="input-group-addon"><input type="radio" :value="supplier" v-model="select_supplier_name"></span>
		                    <input type="text" class="form-control" :value="supplier" readonly>
		                </div>
		            </div>
		        </div>
		    </form>
		</div>
		<!-- /选择供货商 -->
=======
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
>>>>>>> branch 'master' of https://github.com/Sunxiai51/cash-register.git
    </div>
    <script src="${ctx}/static/js/backstage/_goodsTraffic-list.js"></script>
</body>

</html>