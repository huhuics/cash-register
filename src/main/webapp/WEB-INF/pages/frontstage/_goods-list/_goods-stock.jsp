<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 编辑库存 -->
<div id="goodsStockDiv" style="display: none;">
	<!-- 不设置颜色尺码 -->
    <form class="form-horizontal layerForm" v-if="!switches.colorSize">
        <div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group">
                    <span class="input-group-addon">库存</span>
                    <input type="text" class="form-control" v-model="goods.goodsStock">
                </div>
            </div>
        </div>
    </form>
    <!-- 设置颜色尺码 -->
    <form class="form-horizontal layerForm" v-if="switches.colorSize">
    	<div class="form-group">
            <div class="col-xs-4">
                <span class="label label-primary">颜色</span>
            </div>
            <div class="col-xs-4">
                <span class="label label-primary">尺码</span>
            </div>
            <div class="col-xs-4">
                <span class="label label-default">库存</span>
            </div>
        </div>
    	<div class="form-group" v-for="css in color_size_stock">
            <div class="col-xs-4">
            	<input type="text" class="form-control" v-model="css.color" readonly>
            </div>
            <div class="col-xs-4">
                <input type="text" class="form-control" v-model="css.size" readonly>
            </div>
            <div class="col-xs-4">
                <input type="text" class="form-control" v-model="css.stock" place-holder="库存">
            </div>
        </div>
    </form>
</div>
<!-- /编辑库存 -->