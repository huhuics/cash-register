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
            <div class="col-xs-5">
            	<div class="input-group">
            	    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="checkout_all_cash">全部</button></span>
            		<span class="input-group-addon">现金</span>
                    <input class="form-control" type="text" v-model="payChenals.payChenal_cash.amount">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
            <div class="col-xs-5 col-xs-offset-2">
            	<div class="input-group">
            	    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="checkout_all_alipay">全部</button></span>
            		<span class="input-group-addon">支付宝</span>
                    <input class="form-control" type="text" v-model="payChenals.payChenal_alipay.amount">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-5">
            	<div class="input-group">
            	    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="checkout_all_unionpay">全部</button></span>
            		<span class="input-group-addon">银联</span>
                    <input class="form-control" type="text" v-model="payChenals.payChenal_unionpay.amount">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
            <div class="col-xs-5 col-xs-offset-2">
            	<div class="input-group">
            	    <span class="input-group-btn"><button class="btn btn-primary" type="button" @click="checkout_all_wcpay">全部</button></span>
            		<span class="input-group-addon">微&emsp;信</span>
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
    </form>
</div>
<!-- /收银信息 -->