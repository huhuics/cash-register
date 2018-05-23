<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 选择商品 -->
<div id="goodsSelectDiv" style="display: none;">
	<form class="form-horizontal layerForm">
        <div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group">
                    <input class="form-control" type="text" v-model="goods_keyword" placeholder="按条码/拼音码/品名搜索">
                    <span class="input-group-btn">
                    	<button class="btn btn-default" type="button" @click="searchGoodsInBox"><i class="fa fa-search"></i>&nbsp;搜索</button>
                    </span>
                </div>
            </div>
        </div>
        <div class="form-group">
        	<div class="col-xs-12">
        		<table class="table table-condensed" style="font-family: 'Microsoft YaHei';">
        			<thead>
        				<th></th>
        				<th>商品条码</th>
        				<th>商品名称</th>
        				<th>价格</th>
        				<th>颜色</th>
        				<th>尺码</th>
        				<th>货号</th>
        			</thead>
        			<tbody>
        				<tr v-for="goods in keyword_search_goods_list">
        					<td><input type="radio" v-model="select_goods_id" :value="goods.id"></td>
        					<td>{{goods.barCode}}</td>
        					<td>{{goods.goodsName}}</td>
        					<td>{{goods.salesPrice.amount}}</td>
        					<td>{{goods.goodsColor}}</td>
        					<td>{{goods.goodsSize}}</td>
        					<td>{{goods.productNumber}}</td>
        				</tr>
        			</tbody>
        		</table>
        	</div>
        </div>
    </form>
</div>
<!-- /选择商品 -->