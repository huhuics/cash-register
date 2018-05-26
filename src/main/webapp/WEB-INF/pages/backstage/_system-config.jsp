<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>系统设置</title>
</head>

<body style="width: 600px; margin-left:100px; font-family: 'Microsoft YaHei';">
    <div id="systemConfigDiv" v-cloak>
        <h3>系统设置<small>（{{authInfo}}）</small></h3>
        <div>
            <form class="form-horizontal layerForm">
            	<div class="form-group col-xs-12">
            		<div class="input-group">
	                    <span class="input-group-addon">商店名称</span>
	                    <input type="text" class="form-control" v-model="shopName" readonly>
	                </div>
                </div>
            	<div class="form-group col-xs-12">
            		<div class="input-group">
	                    <span class="input-group-addon">注册时间</span>
	                    <input type="text" class="form-control" v-model="registerTime" readonly>
	                </div>
                </div>
            	<div class="form-group col-xs-12">
            		<div class="input-group">
	                    <span class="input-group-addon">有效期截止时间</span>
	                    <input type="text" class="form-control" v-model="invalidTime" readonly>
	                </div>
                </div>
            	<div class="form-group col-xs-12">
            		<div class="input-group">
	                    <span class="input-group-addon">绑定邮箱</span>
	                    <input type="text" class="form-control" v-model="relatedEmail">
	                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="updateRelatedEmail">确定</button></span>
	                </div>
                </div>
            	<div class="form-group col-xs-12">
            		<div class="input-group">
	                    <span class="input-group-addon">联系电话</span>
	                    <input type="text" class="form-control" v-model="phone">
	                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="updatePhone">确定</button></span>
	                </div>
                </div>
            	<div class="form-group col-xs-12">
            		<div class="input-group">
	                    <span class="input-group-addon">联系地址</span>
	                    <input type="text" class="form-control" v-model="address">
	                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="updateAddress">确定</button></span>
	                </div>
                </div>
            	<div class="form-group col-xs-12">
            		<div class="input-group">
	                    <span class="input-group-addon">备用金</span>
	                    <span class="input-group-addon">
	                    	<input type="radio" v-model="pettyAmount" value="true">启用
	                    </span>
	                    <span class="input-group-addon">
	                    	<input type="radio" v-model="pettyAmount" value="false">不启用
	                    </span>
	                    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="updatePettyAmount">确定</button></span>
	                </div>
                </div>
            </form>
        </div>
        <h3>系统重新初始化</h3>
        <div>
        	<form class="form-horizontal layerForm">
        		<div class="form-group col-xs-12">
        			<button type="button" class="btn btn-danger btn-lg" @click="truncateAllTables">清空所有数据</button>
        		</div>
        	</form>
        </div>
    </div>
    <script src="${ctx}/static/js/backstage/_system-config.js"></script>
</body>

</html>