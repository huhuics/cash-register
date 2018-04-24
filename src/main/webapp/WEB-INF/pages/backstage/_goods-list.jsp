<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>商品资料</title>
    <link rel="stylesheet" href="${ctx}/static/plugins/bootstrap-fileinput/css/fileinput.min.css">
</head>

<body>
    <div id="app" v-cloak>
        <!-- 分页表格 -->
        <div>
            <div class="grid-btn">
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.goodsStatus" data-live-search="true">
                        <option value="true">启用</option>
                        <option value="false">禁用</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.goodsBrand" data-live-search="true">
                        <option value="">全部品牌</option>
                        <option v-for="brand in goods_brands" :value="brand.brandName">{{brand.brandName}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.categoryName" data-live-search="true">
                        <option value="">全部种类</option>
                        <option v-for="category in goods_categorys" :value="category.id">{{category.name}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.goodsTag" data-live-search="true">
                        <option value="">全部标签</option>
                        <option v-for="tag in goods_tags" :value="tag.tagName">{{tag.tagName}}</option>
                    </select>
                </div>
                <div class="form-group col-xs-2">
                    <select class="form-control" v-model="q.supplierName" data-live-search="true">
                        <option value="">供货商</option>
                        <option v-for="supplier in goods_suppliers" :value="supplier.name">{{supplier.name}}</option>
                    </select>
                </div>
                <div class="clearfix"></div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.barCode" @keyup.enter="search" placeholder="条码">
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.goodsName" @keyup.enter="search" placeholder="名称">
                </div>
                <div class="form-group col-xs-2">
                    <input type="text" class="form-control" v-model="q.pinyinCode" @keyup.enter="search" placeholder="拼音码">
                </div>
                <div class="form-group col-xs-3 pull-right">
                    <a class="btn btn-default pull-right" @click="search"><i class="fa fa-search"></i>&nbsp;搜索</a>
                    <a class="btn btn-default pull-right" @click="resetSearch"><i class="fa fa-undo"></i>&nbsp;刷新</a>
                </div>
                <div class="clearfix"></div>
                <div class="pull-right">
                    <a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
                    <a class="btn btn-success" @click="update"><i class="fa fa-edit"></i>&nbsp;编辑</a>
                    <a class="btn btn-danger" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
                    <a class="btn btn-info" @click="importGoods"><i class="fa fa-download"></i>&nbsp;导入</a>
                    <a class="btn btn-info" @click="exportGoods"><i class="fa fa-upload"></i>&nbsp;导出</a>
                </div>
                <div class="clearfix"></div>
            </div>
            <table id="jqGrid"></table>
            <div id="jqGridPager" style="height: 50px;"></div>
        </div>
        <!-- /.分页表格 -->
        <!-- 添加或更新商品 -->
        <div id="goodsDiv" style="display: none;">
            <form class="form-horizontal layerForm">
                <div class="form-group">
                    <div class="col-xs-6">
                        <div class="btn-group" role="group">
                        	<div class="btn-group" data-toggle="buttons">
							  <label class="btn btn-default active">
							    <input type="radio" name="options" id="option1" autocomplete="off" checked>启用
							  </label>
							  <label class="btn btn-default">
							    <input type="radio" name="options" id="option2" autocomplete="off">禁用
							  </label>
							</div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                    	<input v-if="displayImageUpload" type="file" name="image" class="form-control" value=""/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                    	<div class="btn-group" role="group">
                        	<div class="btn-group" data-toggle="buttons">
							  <label class="btn btn-default active">
							    <input type="radio" name="options" id="option1" autocomplete="off" checked>颜色尺码开启
							  </label>
							  <label class="btn btn-default">
							    <input type="radio" name="options" id="option2" autocomplete="off">颜色尺码关闭
							  </label>
							</div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">*条码</span>
                            <input type="text" class="form-control" placeholder="">
                            <span class="input-group-btn">
     <button class="btn btn-default" type="button">生成</button>
   </span>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">*品名</span>
                            <input type="text" class="form-control" placeholder="">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <select class="form-control">
                            <option value="">分类</option>
                            <option></option>
                        </select>
                    </div>
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">*货号</span>
                            <input type="text" class="form-control" placeholder="">
                            <span class="input-group-addon">
     <input type="checkbox">和条码一致
   </span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                        <div class="btn-group btn-group-justified" role="group">
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default">颜色尺码</button>
                            </div>
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default">库存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">*售价</span>
                            <input type="text" class="form-control" placeholder="0.00">
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">进价</span>
                            <input type="text" class="form-control" placeholder="0.00">
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                </div>
                <h5 class="page-header"></h5>
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    完善下列商品拓展信息，有助于日常的经营管理哦！
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">
     <input type="checkbox">会员折扣
   </span>
                            <span class="input-group-addon">会员价</span>
                            <input type="text" class="form-control" placeholder="0.00">
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">批发价</span>
                            <input type="text" class="form-control" placeholder="0.00">
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <div class="btn-group btn-group-justified">
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    商品品牌<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="btn-group btn-group-justified">
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    供货商<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">生产日期</span>
                            <input type="text" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">保质期</span>
                            <input type="text" class="form-control" placeholder="">
                            <span class="input-group-addon">天</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">库存上限</span>
                            <input type="text" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="input-group">
                            <span class="input-group-addon">库存下限</span>
                            <input type="text" class="form-control" placeholder="">
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- /添加或更新商品 -->
    </div>
    <script src="${ctx}/static/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
    <script src="${ctx}/static/js/backstage/_goods-list.js"></script>
</body>

</html>