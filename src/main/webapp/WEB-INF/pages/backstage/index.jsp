<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <title>后台管理</title>
    <link rel="stylesheet" href="${ctx}/static/plugins/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${ctx}/static/plugins/adminlte/css/all-skins.min.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper" id="app" v-cloak>
        <header class="main-header">
            <a href="javascript:void(0);" style="padding-left: 20px;" class="logo"><img class="logo-mini" src="${ctx}/static/img/logo-mini.png" style="height: 40px; margin-top: 5px;" alt="logo"> <img class="logo-lg" src="${ctx}/static/img/logo.png" style="height: 40px; margin-top: 5px;" alt="logo"></a>
            <nav class="navbar navbar-static-top" role="navigation">
                <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"> <span class="sr-only">Toggle navigation</span></a>
                <div style="float: left; color: #fff; padding: 15px 10px;">欢迎您，${ sessionScope.admin.name }</div>
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="javascript:void(0);" @click="logout"><i class="fa fa-sign-out"></i>&nbsp;退出系统</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        <aside class="main-sidebar">
            <section class="sidebar" style="height: auto;">
                <!-- 菜单ul开始 -->
                <ul class="sidebar-menu tree" data-widget="tree">
                    <li class="header">导航</li>
                    <li class="treeview active"><a href="#"><i class="fa fa-shopping-cart"></i><span>销售</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li  class="active"><a href="#sales/queryBasicFacts" @click="menuClick"><i class="fa fa-bar-chart"></i>营业概况</a></li>
                            <li><a href="#sales/tradeDetail" @click="menuClick"><i class="fa fa-file-text"></i>销售单据</a></li>
                            <li><a href="#sales/goodsSaleStatistics" @click="menuClick"><i class="fa fa-file-text-o"></i>销售统计</a></li>
                            <li><a href="#sales/queryExchangeJobs" @click="menuClick"><i class="fa fa-exchange"></i>交接班记录</a></li>
                            <li><a href="#sales/goodsSaleAmountByTime" @click="menuClick"><i class="fa fa-file-text-o"></i>营业报表</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"><i class="fa fa-shopping-bag"></i><span>商品</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#goods" @click="menuClick"><i class="fa fa-gift"></i>商品资料</a></li>
                            <li><a href="#goods-category" @click="menuClick"><i class="fa fa-list"></i>商品分类</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"><i class="fa fa-battery-3"></i><span>库存</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#stock" @click="menuClick"><i class="fa fa-search"></i>库存查询</a></li>
                            <li><a href="#stock/warning" @click="menuClick"><i class="fa fa-hourglass-half"></i>库存预警</a></li>
                            <li><a href="#stock/flow" @click="menuClick"><i class="fa fa-file-text-o"></i>变动明细</a></li>
                            <li><a href="#stock/check" @click="menuClick"><i class="fa fa-history"></i>盘点历史</a></li>
                            <li><a href="#goodsLose" @click="menuClick"><i class="fa fa-chain-broken"></i>商品报损</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"><i class="fa fa-vcard"></i><span>会员</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#member" @click="menuClick"><i class="fa fa-user-circle"></i>会员资料</a></li>
                            <li><a href="#member/rank" @click="menuClick"><i class="fa fa-signal"></i>会员等级</a></li>
                            <li><a href="#member/integral" @click="menuClick"><i class="fa fa-sliders"></i>会员制度</a></li>
                            <li><a href="#member/recharge" @click="menuClick"><i class="fa fa-credit-card"></i>会员充值明细</a></li>
                            <li><a href="#member/recharge/check/list" @click="menuClick"><i class="fa fa-file-text-o"></i>储值卡对账</a></li>
                            <li><a href="#member/analysis" @click="menuClick"><i class="fa fa-pie-chart"></i>会员分析</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"> <i class="fa fa-flag"></i><span>营销</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#promotion" @click="menuClick"><i class="fa fa-fire"></i>促销活动设置</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"> <i class="fa fa-users"></i><span>员工</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#seller" @click="menuClick"><i class="fa fa-user"></i>收银员资料</a></li>
                            <li><a href="#seller/achievement" @click="menuClick"><i class="fa fa-bar-chart"></i>收银员业绩</a></li>
                            <li><a href="#shopper" @click="menuClick"><i class="fa fa-user-o"></i>导购员资料</a></li>
                            <li><a href="#shopper/achievement" @click="menuClick"><i class="fa fa-line-chart"></i>导购明细</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"> <i class="fa fa-truck"></i><span>货流</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                        	<li><a href="#traffic" @click="menuClick"><i class="fa fa-info-circle"></i>货流信息</a></li>
                            <li><a href="#supplier" @click="menuClick"><i class="fa fa-drivers-license-o"></i>供货商</a></li>
                        </ul>
                    </li>
                    <li>
					    <a href="#systemConfig" @click="menuClick"><i class="fa fa-cogs"></i><span>系统设置</span></a>
					</li>
                </ul>
                <!-- 菜单ul结束 -->
            </section>
            <!-- /.sidebar -->
        </aside>
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <ol class="breadcrumb" id="nav_title" style="position: static; float: none;">
                    <li class="active"><i class="fa fa-home" style="font-size: 20px; position: relative; top: 2px; left: -3px;"></i> &nbsp;首页</li>
                    <li class="active">{{navTitle}}</li>
                </ol>
            </section>
            <!-- Main content -->
            <section class="content" style="background:#fff;">
                <iframe scrolling="yes" frameborder="0" style="width: 100%; min-height: 200px; overflow: visible; background: #fff;" :src="iframeSrc"></iframe>
            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->
        <footer class="main-footer"> Copyright &copy; 2018 All Rights Reserved </footer>
        <div class="control-sidebar-bg"></div>
    </div>
    <script src="${ctx}/static/js/backstage/index.js"></script>
</body>

</html>