<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 批量编辑商品 -->
<div id="goodsbatchEditDiv" style="display: none;">
    <form class="form-horizontal layerForm">
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-primary">商品提成</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<select class="form-control" v-model="batchEdit.royaltyType">
                    <option value="">选择提成方式</option>
                    <option value="0">不提成</option>
                    <option value="1">销售价 x 导购员提成百分比</option>
                    <option value="2">利润 x 导购员提成百分比</option>
                    <option value="3">固定金额</option>
                    <option value="4">销售价 x 自定义百分比</option>
                    <option value="5">利润 x 自定义百分比</option>
                </select>
                <input type="text" class="form-control" v-model="batchEdit.barCode">
            </div>
        </div>
    </form>
</div>
<!-- /批量编辑商品 -->
