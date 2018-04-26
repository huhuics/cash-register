<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 选择单位 -->
<div id="goodsUnitDiv" style="display: none;">
    <form class="form-horizontal layerForm">
    	<div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group" v-for="unit in goods_units">
                    <span class="input-group-addon"><input type="radio" :value="unit.unitName" v-model="goods.quantityUnit"></span>
                    <input type="text" class="form-control" :value="unit.unitName" readonly>
                    <span class="input-group-btn"><button class="btn btn-danger" type="button" @click="deleteGoodsUnitById(unit.id)">删除</button></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group">
                    <span class="input-group-addon">添加单位</span>
                    <input type="text" class="form-control" v-model="goodsUnit.unitName">
                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="addGoodsUnit">添加</button></span>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /选择单位 -->
