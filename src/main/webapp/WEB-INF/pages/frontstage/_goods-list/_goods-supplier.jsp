<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 选择供货商 -->
<div id="goodsSupplierDiv" style="display: none;">
    <form class="form-horizontal layerForm">
    	<div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group" v-for="supplier in goods_suppliers">
                    <span class="input-group-addon"><input type="radio" :value="supplier" v-model="goods.supplierName"></span>
                    <input type="text" class="form-control" :value="supplier" readonly>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /选择供货商 -->
