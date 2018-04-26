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
    	<h1>多颜色尺码库存还在开发中</h1>
    	<table>
    		<thead>
    			<!-- <th v-for=""></th> -->
    		</thead>
    		<tbody>
    		
    		</tbody>
    	</table>
        
    </form>
</div>
<!-- /编辑库存 -->