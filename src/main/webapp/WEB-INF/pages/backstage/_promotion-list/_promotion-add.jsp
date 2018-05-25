<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- 添加或更新促销信息 -->
<div id="promotionDiv" style="display: none;">
    <form class="form-horizontal layerForm">
        <div class="form-group">
            <div class="col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;促销名称</span>
                    <input type="text" class="form-control" v-model="promotion.promotionName">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <select class="form-control" v-model="promotion.promotionType">
                    <option value="">---请选择促销类型---</option>
                    <option value="discount">打折促销</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-5">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;开始时间</span>
                    <input type="text" class="form-control" v-model="promotion.startTime" id="datetimepickerAfter" placeholder="yyyy-MM-dd" readonly>
                </div>
            </div>
            <div class="col-xs-5 col-xs-offset-2">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;结束时间</span>
                    <input type="text" class="form-control" v-model="promotion.endTime" id="datetimepickerBefore" placeholder="yyyy-MM-dd" readonly>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;是否会员专享</span>
                    <span class="input-group-addon"><input type="radio" v-model="promotion.isMemberOnly" value="true">是</span>
                    <span class="input-group-addon"><input type="radio" v-model="promotion.isMemberOnly" value="false">否</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;是否会员折上折</span>
                    <span class="input-group-addon"><input type="radio" v-model="promotion.isMemberDiscountTwice" value="true">是</span>
                    <span class="input-group-addon"><input type="radio" v-model="promotion.isMemberDiscountTwice" value="false">否</span>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /添加或更新促销信息 -->