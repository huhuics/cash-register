<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 批量编辑商品 -->
<div id="goodsbatchEditDiv" style="display: none;">
    <form class="form-horizontal layerForm">
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">商品分类</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<select class="form-control" v-model="batchEditParam.categoryName" data-live-search="true">
                    <option value="">选择商品分类</option>
                    <option v-for="category in goods_categorys" :value="category.name">{{category.prefix+category.name}}</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">商品品牌</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<select class="form-control" v-model="batchEditParam.goodsBrand">
                    <option value="">选择商品品牌</option>
                    <option v-for="brand in goods_brands" :value="brand.brandName">{{brand.brandName}}</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">供货商</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<select class="form-control" v-model="batchEditParam.supplierName">
                    <option value="">选择供货商</option>
                    <option v-for="supplier in goods_suppliers" :value="supplier">{{supplier}}</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">商品标签</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">添加标签</span>
                    <input type="text" class="form-control" readonly v-model="batchEditParam.goodsTagAdd">
                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="_editGoodsTag('batchAdd')">选择</button></span>
                </div>
            	<div class="input-group">
                    <span class="input-group-addon">删除标签</span>
                    <input type="text" class="form-control" readonly v-model="batchEditParam.goodsTagRemove">
                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="_editGoodsTag('batchRemove')">选择</button></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">商品提成</span>
                </div>
            </div>
            <div class="col-xs-4">
            	<select class="form-control" v-model="batchEditParam.royaltyType">
                    <option value="">选择提成方式</option>
                    <option value="0">不提成</option>
                    <option value="1">销售价 x 导购员提成百分比</option>
                    <option value="2">利润 x 导购员提成百分比</option>
                    <option value="3">固定金额</option>
                    <option value="4">销售价 x 自定义百分比</option>
                    <option value="5">利润 x 自定义百分比</option>
                </select>
            </div>
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="input-group-addon" id="batchEditRoyaltyValueSpan">无相关值</span>
                    <input type="text" class="form-control" id="batchEditRoyaltyValueInput" v-model="batchEditParam.royaltyValue" readonly>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">会员优惠</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isVipPrice" value="">未设置
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isVipPrice" value="yes">使用会员折扣
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isVipPrice" value="no">使用会员价
                    </span>
                </div>
            	<div class="input-group">
                    <span class="input-group-addon">会员价：售价&nbsp;x</span>
                    <input type="text" class="form-control" id="batchEditVipPricePercentInput" v-model="batchEditParam.vipPricePercent" placeholder="未设置" readonly>
                    <span class="input-group-addon">%</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">启用禁用</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">设置状态</span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.goodsStatus" value="">未设置
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.goodsStatus" value="yes">启用
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.goodsStatus" value="no">禁用
                    </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">积分设置</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">是否积分</span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isIntegral" value="">未设置
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isIntegral" value="yes">是
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isIntegral" value="no">否
                    </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">是否赠品</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">是否赠品</span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isGift" value="">未设置
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isGift" value="yes">是
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isGift" value="no">否
                    </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">是否隐藏</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">是否隐藏</span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isHidden" value="">未设置
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isHidden" value="yes">是
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isHidden" value="no">否
                    </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">能否预约</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">能否预约</span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isBooked" value="">未设置
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isBooked" value="yes">是
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isBooked" value="no">否
                    </span>
                </div>
            </div>
        </div>
        <!-- <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">是否称重</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">是否称重</span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isWeigh" value="">未设置
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isWeigh" value="yes">是
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isWeigh" value="no">否
                    </span>
                </div>
            </div>
        </div> -->
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">是否时价</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">是否时价</span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isTimeingPrice" value="">未设置
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isTimeingPrice" value="yes">是
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isTimeingPrice" value="no">否
                    </span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-4">
            	<div class="input-group">
                    <span class="label label-warning">是否固价</span>
                </div>
            </div>
            <div class="col-xs-8">
            	<div class="input-group">
                    <span class="input-group-addon">是否固价</span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isFixedPrice" value="">未设置
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isFixedPrice" value="yes">是
                    </span>
                    <span class="input-group-addon">
                    	<input type="radio" v-model="batchEditParam.isFixedPrice" value="no">否
                    </span>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /批量编辑商品 -->
