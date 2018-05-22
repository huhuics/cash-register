<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 选择品牌 -->
<div id="goodsBrandDiv" style="display: none;">
    <form class="form-horizontal layerForm">
    	<div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group" v-for="brand in goods_brands">
                    <span class="input-group-addon"><input type="radio" :value="brand.brandName" v-model="goods.goodsBrand"></span>
                    <input type="text" class="form-control" :value="brand.brandName" readonly>
                    <span class="input-group-btn"><button class="btn btn-danger" type="button" @click="deleteGoodsBrandById(brand.id)">删除</button></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group">
                    <span class="input-group-addon">添加品牌</span>
                    <input type="text" class="form-control" v-model="goodsBrand.brandName">
                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="addGoodsBrand">添加</button></span>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /选择品牌 -->
