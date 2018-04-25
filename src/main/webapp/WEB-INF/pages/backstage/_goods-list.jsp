<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商品资料</title>
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
                    <div class="col-xs-7">
                    	<div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-exclamation"></i>&nbsp;状态</span>
                            <span class="input-group-addon">
                            	<input type="radio" v-model="goods.goodsStatus" value="true">启用
                            </span>
                            <span class="input-group-addon">
                            	<input type="radio" v-model="goods.goodsStatus" value="false">禁用
                            </span>
                        </div>
                    </div>
                    <div class="col-xs-5">
                    	<input v-if="switches.displayImageUpload" type="file" name="image" class="form-control" value=""/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-exclamation"></i>&nbsp;条码</span>
                            <input type="text" class="form-control" v-model="goods.barCode">
                            <span class="input-group-btn"><button class="btn btn-success" type="button" @click="getBarCode">生成</button></span>
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-exclamation"></i>&nbsp;品名</span>
                            <input type="text" class="form-control" v-model="goods.goodsName">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-exclamation"></i>&nbsp;货号</span>
                            <input type="text" class="form-control" v-model="goods.productNumber" id="productNumberInput">
                            <span class="input-group-addon"><input type="checkbox" v-model="switches.prodNumSame">和条码一致</span>
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-exclamation"></i>&nbsp;分类</span>
                            <input type="text" class="form-control" v-model="goods.categoryName">
                            <span class="input-group-btn"><button class="btn btn-primary" type="button">选择分类</button></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-xs-7">
                    	<div class="input-group">
                            <span class="input-group-addon"><input type="checkbox" v-model="switches.colorSize"></span>
                            <input type="text" class="form-control" readonly placeholder="未选择任何颜色尺码">
                            <span class="input-group-btn"><button id="colorSizeBtn" class="btn btn-primary" type="button" disabled>选择颜色尺码</button></span>
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <div class="btn-group btn-group-justified" role="group">
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default" @click="_editGoodsStock"><i class="fa fa-exclamation"></i>&nbsp;输入库存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-exclamation"></i>&nbsp;售价</span>
                            <input type="text" class="form-control" v-model="goods.salesPrice" placeholder="0.00">
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-exclamation"></i>&nbsp;进价</span>
                            <input type="text" class="form-control" v-model="goods.lastImportPrice" placeholder="0.00">
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
                    <div class="col-xs-7">
                        <div class="input-group">
                            <span class="input-group-addon"><input type="checkbox" v-model="goods.isVipDiscount">会员折扣</span>
                            <span class="input-group-addon">会员价</span>
                            <input type="text" class="form-control" id="goodsVipPriceInput" v-model="goods.vipPrice" placeholder="未设置" readonly>
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <div class="input-group">
                            <span class="input-group-addon">批发价</span>
                            <input type="text" class="form-control" v-model="goods.tradePrice" placeholder="0.00">
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                        <div class="input-group">
                            <span class="input-group-addon">主单位</span>
                            <input type="text" class="form-control" v-model="goods.quantityUnit" readonly>
                            <span class="input-group-btn"><button class="btn btn-primary" type="button">选择单位</button></span>
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <div class="input-group">
                            <span class="input-group-addon">拼音码</span>
                            <input type="text" class="form-control" v-model="goods.pinyinCode">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                        <div class="input-group">
                            <span class="input-group-addon">商品品牌</span>
                            <input type="text" class="form-control" v-model="goods.goodsBrand" readonly>
                            <span class="input-group-btn"><button class="btn btn-primary" type="button">选择品牌</button></span>
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <div class="input-group">
                            <span class="input-group-addon">供货商</span>
                            <input type="text" class="form-control" v-model="goods.supplierName" readonly>
                            <span class="input-group-btn"><button class="btn btn-primary" type="button">选择供货商</button></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                        <div class="input-group">
                            <span class="input-group-addon">生产日期</span>
                            <input type="text" class="form-control" v-model="goods.productionDate">
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <div class="input-group">
                            <span class="input-group-addon">保质期</span>
                            <input type="text" class="form-control" v-model="goods.qualityGuaranteePeriod">
                            <span class="input-group-addon">天</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-7">
                        <div class="input-group">
                            <span class="input-group-addon">库存上限</span>
                            <input type="text" class="form-control" v-model="goods.stockUpperLimit">
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <div class="input-group">
                            <span class="input-group-addon">库存下限</span>
                            <input type="text" class="form-control" v-model="goods.stockLowerLimit">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon">标签</span>
                            <input type="text" class="form-control" readonly v-model="goods.goodsTag">
                            <span class="input-group-btn"><button class="btn btn-primary" type="button">选择标签</button></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                    	<textarea class="form-control" rows="3" placeholder="备注" v-model="goods.remark"></textarea>
                    </div>
                </div>
            </form>
        </div>
        <!-- /添加或更新商品 -->
    </div>
    
    <script src="${ctx}/static/js/backstage/_goods-list.js"></script>
</body>

</html>