<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 收银信息 -->
<div id="checkoutDiv" style="display: none;">
	<form class="form-horizontal layerForm">
        <div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group">
            		<span class="input-group-addon">总额</span>
                    <input class="form-control" type="text" :value="summary_price" readonly>
                    <span class="input-group-addon">元</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-3">
            	<div class="input-group">
            		<span class="input-group-addon">现金</span>
                    <input class="form-control" type="text" v-model="payChenals.payChenal_cash.amount">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
            <div class="col-xs-3">
            	<div class="input-group">
            		<span class="input-group-addon">银联</span>
                    <input class="form-control" type="text" v-model="payChenals.payChenal_unionpay.amount">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
            <div class="col-xs-3">
            	<div class="input-group">
            		<span class="input-group-addon">支付宝</span>
                    <input class="form-control" type="text" v-model="payChenals.payChenal_alipay.amount">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
            <div class="col-xs-3">
            	<div class="input-group">
            		<span class="input-group-addon">微信</span>
                    <input class="form-control" type="text" v-model="payChenals.payChenal_wcpay.amount">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group">
            		<span class="input-group-addon">找零</span>
                    <input class="form-control" type="text" :value="change" readonly>
                    <span class="input-group-addon">元</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
            	<button type="button" class="btn btn-danger btn-lg btn-block" @click="checkout">确定</button>
            </div>
        </div>
    </form>
</div>
<!-- /收银信息 -->