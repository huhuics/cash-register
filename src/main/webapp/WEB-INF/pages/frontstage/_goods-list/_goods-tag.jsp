<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 选择标签 -->
<div id="goodsTagDiv" style="display: none;">
    <form class="form-horizontal layerForm">
    	<div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group" v-for="tag in goods_tags">
                    <span class="input-group-addon"><input type="checkbox" :value="tag.tagName" v-model="select_goods_tags"></span>
                    <input type="text" class="form-control" :value="tag.tagName" readonly>
                    <span class="input-group-btn"><button class="btn btn-danger" type="button" @click="deleteGoodsTagById(tag.id)">删除</button></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group">
                    <span class="input-group-addon">添加标签</span>
                    <input type="text" class="form-control" v-model="goodsTag.tagName">
                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="addGoodsTag">添加</button></span>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /选择标签 -->
