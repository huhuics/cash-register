<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>会员列表</title>
</head>

<body>
    <div id="memberListDiv" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-2">
                	<select class="form-control" v-model="q.memberRank">
                        <option value="">全部等级</option>
                        <option v-for="rank in memberRanks" :value="rank.rankTitle">{{rank.rankTitle}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                	<select class="form-control" v-model="q.shopperName">
                        <option value="">全部导购员</option>
                        <option v-for="shopper in shoppers" :value="shopper.name">{{shopper.name}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.status">
                        <option value="">全部状态</option>
                        <option value="true">启用</option>
                        <option value="false">禁用</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.keyword" @keyup.enter="search" placeholder="卡号/姓名/电话">
                </div>
                <div class="clearfix"></div>
                <div class="form-group col-xs-4">
                    <a class="btn btn-default" @click="search"><i class="fa fa-search"></i>&nbsp;搜索</a>
                    <a class="btn btn-default" @click="resetSearch"><i class="fa fa-undo"></i>&nbsp;刷新</a>
                </div>
                <div class="pull-right">
                    <a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
                    <a class="btn btn-success" @click="update"><i class="fa fa-edit"></i>&nbsp;编辑</a>
                    <a class="btn btn-danger" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
                    <a class="btn btn-info" @click="importMember"><i class="fa fa-download"></i>&nbsp;导入</a>
                    <a class="btn btn-info" @click="exportMember"><i class="fa fa-upload"></i>&nbsp;导出</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="jqGrid_wrapper">
                <table id="jqGrid"></table>
                <div id="jqGridPager" style="height: 50px;"></div>
            </div>
        </div>
        <!-- /分页表格 -->
        <!-- 添加或更新会员 -->
        <div id="memberDiv" style="display: none;">
            <form class="form-horizontal layerForm">
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;编号</span>
		                    <input type="text" class="form-control" v-model="member.memberNo">
		                </div>
                    </div>
                </div>
            	<div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;姓名</span>
		                    <input type="text" class="form-control" v-model="member.memberName">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;折扣</span>
		                    <input type="text" class="form-control" v-model="member.memberDiscount">
		                    <span class="input-group-addon">%</span>
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<select class="form-control" v-model="member.memberRank">
	                        <option value="">--请选择等级--</option>
	                        <option v-for="rank in memberRanks" :value="rank.rankTitle">{{rank.rankTitle}}</option>
	                    </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;联系电话</span>
		                    <input type="password" class="form-control" v-model="member.phone">
		                </div>
                    </div>
                    <div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;积分</span>
		                    <input type="text" class="form-control" v-model="member.memberIntegral">
		                    <span class="input-group-addon">分</span>
		                </div>
                    </div>
                </div>
                <div class="form-group">
		            <div class="col-xs-7">
		            	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;状态</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="member.status" value="true">启用
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="member.status" value="false">禁用
		                    </span>
		                </div>
		            </div>
		            <div class="col-xs-5">
		            	<div class="input-group">
		                    <span class="input-group-addon"><i class="fa fa-exclamation" style="color:red;"></i>&nbsp;允许赊账</span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="member.isOnCredit" value="true">是
		                    </span>
		                    <span class="input-group-addon">
		                    	<input type="radio" v-model="member.isOnCredit" value="false">否
		                    </span>
		                </div>
		            </div>
		        </div>
                <h5 class="page-header"></h5>
                <div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">密码</span>
		                    <input type="text" class="form-control" v-model="member.password">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">生日</span>
		                    <input type="text" class="form-control" v-model="member.birthday">
		                </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-12">
                    	<div class="input-group">
		                    <span class="input-group-addon">开卡日期</span>
		                    <input type="text" class="form-control" :value="member.gmtCreate" placeholder="默认为创建日期" readonly>
		                </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-7">
                    	<div class="input-group">
		                    <span class="input-group-addon">邮箱地址</span>
		                    <input type="text" class="form-control" v-model="member.email">
		                </div>
                    </div>
                	<div class="col-xs-5">
                    	<div class="input-group">
		                    <span class="input-group-addon">QQ</span>
		                    <input type="text" class="form-control" v-model="member.qqNo">
		                </div>
                    </div>
                </div>
                <div class="form-group">
		            <div class="col-xs-12">
		            	<textarea class="form-control" rows="2" placeholder="地址" v-model="member.address"></textarea>
		            </div>
		        </div>
                <div class="form-group">
		            <div class="col-xs-12">
		            	<textarea class="form-control" rows="3" placeholder="备注" v-model="member.remark"></textarea>
		            </div>
		        </div>
            </form>
        </div>
        <!-- /添加或更新会员 -->
    </div>
    <script src="${ctx}/static/js/backstage/_member-list.js"></script>
</body>

</html>