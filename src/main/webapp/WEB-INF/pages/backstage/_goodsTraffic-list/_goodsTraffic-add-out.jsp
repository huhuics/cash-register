<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 添加出库货流 -->
<div id="addOutTrafficDiv" style="display: none;">
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
                    <input type="text" class="form-control" v-model="outTraffic.barCode" readonly>
                </div>
            </div>
            <div class="col-xs-5">
                <div class="input-group">
                    <span class="input-group-addon">商品名称</span>
                    <input type="text" class="form-control" v-model="outTraffic.goodsName" readonly>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-7">
                <div class="input-group">
                    <span class="input-group-addon">商品颜色</span>
                    <input type="text" class="form-control" v-model="outTraffic.goodsColor" readonly>
                    <span class="input-group-addon">商品尺寸</span>
                    <input type="text" class="form-control" v-model="outTraffic.goodsSize" readonly>
                </div>
            </div>
            <div class="col-xs-5">
                <div class="input-group">
                    <span class="input-group-addon">商品库存</span>
                    <input type="text" class="form-control" v-model="outTraffic.goodsStock" readonly>
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