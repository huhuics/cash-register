<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 参与促销商品列表 -->
<div id="promotionGoodsListDiv" style="display: none;">
	<div class="div-height-15"></div>
	<div class="clearfix"></div>
	<div class="col-xs-12">
		<div class="input-group">
			<input type="text" class="form-control toFocus"
				v-model="goods_keyword" @keyup.enter="searchGoods"
				placeholder="条码/拼音码/品名"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" @keyup.enter="searchGoods" @click="searchGoods">确定</button>
			</span>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="div-height-15"></div>
	<table class="table table-bordered tableStyle" style="margin-bottom: 200px;">
		<thead>
			<th>商品名称</th>
			<th>条码</th>
			<th>分类</th>
			<th>原价</th>
			<th>折扣</th>
			<th>折后价</th>
			<th>操作</th>
		</thead>
		<tbody>
			<tr v-for="item in promotion_goods_list_show">
				<td>{{item.goodsName}}</td>
				<td>{{item.barCode}}</td>
				<td>{{item.categoryName}}</td>
				<td>{{item.salesPrice}}</td>
				<td><input class="form-control" type="text"
					v-model="item.discount"
					@blur="editItemDiscountById(item.goodsId,item.discount)"
					@keyup.enter="editItemDiscountById(item.goodsId,item.discount)"></td>
				<td><input class="form-control" type="text"
					v-model="item.priceWithDiscount"
					@blur="editItemPriceById(item.goodsId,item.priceWithDiscount)"
					@keyup.enter="editItemPriceById(item.goodsId,item.priceWithDiscount)"></td>
				<td><button class="btn btn-danger" type="button"
						@click="deleteItemById(item.goodsId)">删除</button></td>
			</tr>
		</tbody>
	</table>

</div>
<!-- /参与促销商品列表 -->