<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 添加进货货流 -->
<div id="addInTrafficDiv" style="display: none;">
    <form class="form-horizontal layerForm">
        <div class="form-group col-xs-12">
            <label>商品信息</label>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <div class="input-group">
                    <input type="text" class="form-control toFocus" v-model="goods_keyword" @keyup.enter="searchGoods" placeholder="条码/拼音码/品名">
                    <span class="input-group-btn"><button class="btn btn-default" type="button" @click="searchGoods">搜索商品</button></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-7">
                <div class="input-group">
                    <span class="input-group-addon">商品条码</span>
                    <input type="text" class="form-control" v-model="inTraffic.barCode" readonly>
                </div>
            </div>
            <div class="col-xs-5">
                <div class="input-group">
                    <span class="input-group-addon">商品名称</span>
                    <input type="text" class="form-control" v-model="inTraffic.goodsName" readonly>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-7">
                <div class="input-group">
                    <span class="input-group-addon">商品颜色</span>
                    <input type="text" class="form-control" v-model="inTraffic.goodsColor" readonly>
                    <span class="input-group-addon">商品尺寸</span>
                    <input type="text" class="form-control" v-model="inTraffic.goodsSize" readonly>
                </div>
            </div>
            <div class="col-xs-5">
                <div class="input-group">
                    <span class="input-group-addon">商品库存</span>
                    <input type="text" class="form-control" v-model="inTraffic.goodsStock" readonly>
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