<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理</title>
    <link rel="stylesheet" href="${ctx}/static/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/static/plugins/adminlte/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/static/plugins/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${ctx}/static/plugins/adminlte/css/all-skins.min.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper" id="app" v-cloak>
        <header class="main-header">
            <a href="javascript:void(0);" class="logo"><span class="logo-mini"><b>CS</b></span> <span class="logo-lg">CashRegister</span></a>
            <nav class="navbar navbar-static-top" role="navigation">
                <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"> <span class="sr-only">Toggle navigation</span></a>
                <div style="float: left; color: #fff; padding: 15px 10px;">小胡自行车行(huhui2018)</div>
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="javascript:void(0);"><i class="fa fa-lock"></i> &nbsp;修改密码</a></li>
                        <li><a href="javascript:void(0);"><i class="fa fa-sign-out"></i>&nbsp;退出系统</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        <aside class="main-sidebar">
            <section class="sidebar" style="height: auto;">
                <!-- 菜单ul开始 -->
                <ul class="sidebar-menu tree" data-widget="tree">
                    <li class="header">导航</li>
                    <li class="active">
                        <a href="#dashboard" @click="menuClick"><i class="fa fa-dashboard"></i><span>Dashboard</span></a>
                    </li>
                    <li class="treeview"><a href="#"><i class="fa fa-circle-o"></i><span>销售</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>营业概况</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>销售单据</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>日结记录</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>销售统计</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>交接班记录</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>现金收支明细</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>营业报表</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"><i class="fa fa-shopping-cart"></i><span>商品</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#goods" @click="menuClick"><i class="fa fa-shopping-bag"></i>商品资料</a></li>
                            <li><a href="#goods-category" @click="menuClick"><i class="fa fa-list"></i>商品分类</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"><i class="fa fa-battery-3"></i><span>库存</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#stock" @click="menuClick"><i class="fa fa-search"></i>库存查询</a></li>
                            <li><a href="#stock/warning" @click="menuClick"><i class="fa fa-warning"></i>库存预警</a></li>
                            <li><a href="#stock/flow" @click="menuClick"><i class="fa fa-file-text-o"></i>变动明细</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>盘点历史</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>商品报损</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"><i class="fa fa-vcard"></i><span>会员</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#member" @click="menuClick"><i class="fa fa-user-circle"></i>会员资料</a></li>
                            <li><a href="#member/rank" @click="menuClick"><i class="fa fa-signal"></i>会员等级</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>会员制度</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>会员卡报表</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>会员分析</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"> <i class="fa fa-circle-o"></i><span>营销</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>促销活动设置</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>充值赠送活动设置</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"> <i class="fa fa-users"></i><span>员工</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#seller" @click="menuClick"><i class="fa fa-user"></i>收银员资料</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>收银员业绩</a></li>
                            <li><a href="#shopper" @click="menuClick"><i class="fa fa-user-o"></i>导购员资料</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>导购员业绩</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>导购明细</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"> <i class="fa fa-circle-o"></i><span>货流</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>供货商</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>进货</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>出库</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"> <i class="fa fa-circle-o"></i><span>设置</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>账户设置</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>系统设置</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>支付方式</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>操作日志</a></li>
                        </ul>
                    </li>
                    <li class="treeview"><a href="#"> <i class="fa fa-circle-o"></i><span>其它</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>文件上传</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>报表打印</a></li>
                            <li><a href="#" @click="menuClick"><i class="fa fa-circle-o"></i>缓存</a></li>
                        </ul>
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
    <script src="${ctx}/static/plugins/jquery/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/plugins/adminlte/js/adminlte.min.js"></script>
    <script src="${ctx}/static/js/vue.min.js"></script>
    <script src="${ctx}/static/plugins/layer/layer.js"></script>
    <script src="${ctx}/static/js/backstage/index.js"></script>
</body>

</html>