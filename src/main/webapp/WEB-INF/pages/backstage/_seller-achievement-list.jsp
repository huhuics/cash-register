<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>收银员业绩查询</title>
</head>

<body>
    <div id="sellerAchievementListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.bizNo">
                        <option value="">全部收银员</option>
                        <option v-for="seller in sellers" :value="seller.sellerNo">{{seller.sellerNo}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.categoryName" data-live-search="true">
                        <option value="">全部分类</option>
                        <option v-for="category in goods_categorys" :value="category.name">{{category.prefix+category.name}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-6">
					<div class="input-group">
	                    <input type="text" class="form-control" v-model="q.tradeTimeUp"
						id="datetimepickerAfter" placeholder="起始时间 yyyy-MM-dd hh:mm:ss">
						<span class="input-group-addon">~</span>
						<input type="text" class="form-control" v-model="q.tradeTimeDown"
						id="datetimepickerBefore" placeholder="截止时间 yyyy-MM-dd hh:mm:ss">
	                </div>
				</div>
                <div class="form-group col-xs-2">
                    <a class="btn btn-primary" @click="search"><i class="fa fa-search"></i>&nbsp;查询</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="jqGrid_wrapper">
                <table id="jqGrid"></table>
                <div id="jqGridPager" style="height: 50px;"></div>
            </div>
        </div>
        <!-- /.分页表格 -->
    </div>
    
    <script src="${ctx}/static/js/backstage/_seller-achievement-list.js"></script>
    
</body>

</html>