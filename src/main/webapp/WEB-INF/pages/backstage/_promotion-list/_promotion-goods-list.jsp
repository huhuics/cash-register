<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 参与促销商品列表 -->
<div id="promotionGoodsDiv" style="display: none;">
	<div class="col-xs-12">
        <div class="input-group">
            <input type="text" class="form-control toFocus" v-model="goods_keyword" @keyup.enter="searchGoods" placeholder="条码/拼音码/品名">
            <span class="input-group-btn">
        <button class="btn btn-default" type="button" @click="searchGoods">确定</button>
    </span>
        </div>
    </div>
	<table class="table table-bordered tableStyle">
        <thead>
            <th>商品名称</th>
            <th>条码</th>
            <th>分类</th>
            <th>原价</th>
            <th>折扣</th>
            <th>操作</th>
        </thead>
        <tbody>
            <tr v-for="item in goods_list">
                <td>{{item.barCode}}</td>
                <td>{{item.goodsName}}</td>
                <td>{{item.salesPrice}}</td>
                <td>
                    <input class="form-control" type="text" v-model="item.goodsDiscount" @blur="editItemDiscountById(item.goodsId,item.goodsDiscount)" @keyup.enter="editItemDiscountById(item.goodsId,item.goodsDiscount)">
                </td>
                <td>
                    <input class="form-control" type="text" v-model="item.actualAmount" @blur="editItemPriceById(item.goodsId,item.actualAmount)" @keyup.enter="editItemPriceById(item.goodsId,item.actualAmount)">
                </td>
                <td>
                    <input class="form-control" type="text" v-model="item.goodsCount" @blur="editItemCountById(item.goodsId,item.goodsCount)" @keyup.enter="editItemCountById(item.goodsId,item.goodsCount)">
                </td>
                <td>{{item.totalActualAmount}}</td>
                <td>
                    <button class="btn btn-danger" type="button" @click="deleteItemById(item.goodsId)">删除</button>
                </td>
            </tr>
        </tbody>
    </table>
    <%@ include file="./_goods-select.jsp"%>
</div>
<!-- /参与促销商品列表 -->