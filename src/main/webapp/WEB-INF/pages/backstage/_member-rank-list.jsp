<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>会员等级</title>
</head>

<body>
    <div id="memberRankListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div>
                    <a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
                    <a class="btn btn-success" @click="update"><i class="fa fa-edit"></i>&nbsp;编辑</a>
                    <a class="btn btn-danger" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
                </div>
            </div>
            <div class="jqGrid_wrapper">
                <table id="jqGrid"></table>
                <div id="jqGridPager" style="height: 50px;"></div>
            </div>
        </div>
        <!-- /分页表格 -->
        <!-- 添加或更新会员等级 -->
        <div id="memberRankDiv" style="display: none;">
            <form class="form-horizontal layerForm">
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;等级名称</span>
		                    <input type="text" class="form-control" v-model="memberRank.rankTitle">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;优惠折扣</span>
		                    <input type="text" class="form-control" v-model="memberRank.discount">
		                </div>
                    </div>
                </div>
                <div class="form-group">
		            <div class="col-xs-12">
		            	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;是否积分</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="memberRank.isIntegral" value="true">启用
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="memberRank.isIntegral" value="false">禁用
		                    </span>
		                </div>
		            </div>
		        </div>
                <h5 class="page-header"></h5>
                <div class="form-group">
		            <div class="col-xs-12">
		            	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;是否自动升级</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="memberRank.isAutoUpgrade" value="true">是
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="memberRank.isAutoUpgrade" value="false">否
		                    </span>
		                </div>
		            </div>
		        </div>
                <div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">当会员积分达到</span>
		                    <input type="text" class="form-control" v-model="memberRank.integralToUpgrade">
		                    <span class="input-group-addon">分时，自动升级到该等级。</span>
		                </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">该等级有效期</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="memberRank.rankPeriod" value="0">永久
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="memberRank.rankPeriod" value="1">1年
		                    </span>
		                </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- /添加或更新会员等级 -->
    </div>
    <script src="${ctx}/static/js/backstage/_member-rank-list.js"></script>
</body>

</html>