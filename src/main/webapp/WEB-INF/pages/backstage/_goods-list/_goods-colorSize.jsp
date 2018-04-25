<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 编辑颜色尺码 -->
<div id="goodsColorSizeDiv" style="display: none;">
    <form class="form-horizontal layerForm">
    	<div class="form-group">
            <div class="col-xs-7">
            	<div class="checkbox" v-for="color in goods_colors">
				  <label>
				    <input type="checkbox" :value="color.color" v-model="selected.goods_colors">
				    {{color.color}}
				  </label>
				</div>
            </div>
            <div class="col-xs-5">
            	<div class="checkbox" v-for="size in goods_sizes">
				  <label>
				    <input type="checkbox" :value="size.sizeName" v-model="selected.goods_sizes">
				    {{size.sizeName}}
				  </label>
				</div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-7">
            	<div class="input-group">
                    <span class="input-group-addon">添加颜色</span>
                    <input type="text" class="form-control" v-model="goodsColor.color">
                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="addGoodsColor">添加</button></span>
                </div>
            </div>
            <div class="col-xs-5">
            	<div class="input-group">
                    <span class="input-group-addon">添加尺寸</span>
                    <input type="text" class="form-control" v-model="goodsSize.sizeName">
                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="addGoodsSize">添加</button></span>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /编辑库存 -->
