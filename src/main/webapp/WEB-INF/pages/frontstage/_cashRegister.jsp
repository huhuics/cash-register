<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>收银</title>
</head>

<body style="overflow: hidden;">
    <div id="cashRegisterDiv" v-cloak>
        <div>
            <table class="table table-bordered table-hover" style="font-family: 'Microsoft YaHei';">
                <thead>
                    <tr>
                        <th>商品条码</th>
                        <th>商品名称</th>
                        <th>原价</th>
                        <th>折扣</th>
                        <th>现价</th>
                        <th>数量</th>
                        <th>小计</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in goods_list" style="height: 30px; line-height: 30px;">
                        <td>{{item.barCode}}</td>
                        <td>{{item.goodsName}}</td>
                        <td>{{item.totalAmount}}</td>
                        <td>
                            <input class="form-control" type="text" v-model="item.goodsDiscount" @keyup.enter="editItemDiscountById(item.goodsId,item.goodsDiscount)">
                        </td>
                        <td>
                            <input class="form-control" type="text" v-model="item.totalActualAmount" @keyup.enter="editItemPriceById(item.goodsId,item.totalActualAmount)">
                        </td>
                        <td>
                            <input class="form-control" type="text" v-model="item.goodsCount" @keyup.enter="editItemCountById(item.goodsId,item.goodsCount)">
                        </td>
                        <td>{{item.priceTotal}}</td>
                        <td>
                            <button class="btn btn-danger" type="button" @click="deleteItemById(item.goodsId)">删除</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="summaryDiv" style="width: 100%;height: 60px;padding: 10px;">
            <div class="pull-left">
                <button class="btn btn-primary" type="button" @click="summary">
                    商品数量 <span class="badge">{{summary_count}}</span>
                </button>
            </div>
        </div>
        <div class="operationDiv">
            <table class="table table-bordered" style="background-color: #EEE;">
                <tbody>
                    <tr>
                        <td class="col-xs-3">
                            <div>
                                <div class="col-xs-12">
                                    <div class="input-group">
                                        <input type="text" class="form-control" v-model="goods_keyword" placeholder="条码/拼音码/品名">
                                        <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" @click="searchGoods">确定</button>
                                </span>
                                    </div>
                                </div>
                                <div class="col-xs-12 div-height-5"></div>
                                <div class="col-xs-12">
                                    <div class="input-group">
                                        <input type="text" class="form-control" v-model="price_without_barcode" placeholder="输入价格无码收银">
                                        <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" @click="addNoBarcodeItem">确定</button>
                                </span>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td class="col-xs-3">
                            <div>
                                <div class="col-xs-12">
                                    <div class="input-group">
                                        <input type="text" class="form-control" v-model="vip_keyword" placeholder="会员号/手机号">
                                        <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" @click="searchVipInfo">确定</button>
                                </span>
                                    </div>
                                </div>
                                <div class="col-xs-12 div-height-5"></div>
                                <div class="col-xs-12">
                                    <button class="btn btn-info" type="button">
                                        姓名 <span class="badge">{{vip_info.name}}</span>
                                    </button>
                                    <button class="btn btn-info" type="button">
                                        会员折扣 <span class="badge">{{vip_info.discount}}%</span>
                                    </button>
                                    <button class="btn btn-info" type="button">
                                        积分 <span class="badge">{{vip_info.score}}</span>
                                    </button>
                                </div>
                            </div>
                        </td>
                        <td class="col-xs-4" style="vertical-align: middle;">
                            <div>
                                <button type="button" class="btn btn-danger btn-lg btn-block" @click="toCheckout">收款&nbsp;&nbsp;￥{{summary_price}}</button>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <%@ include file="./_cashRegister/_goods-select.jsp"%>
        <%@ include file="./_cashRegister/_vip-select.jsp"%>
        <%@ include file="./_cashRegister/_checkout.jsp"%>
    </div>
    <script src="${ctx}/static/js/frontstage/_cashRegister.js"></script>
</body>

</html>