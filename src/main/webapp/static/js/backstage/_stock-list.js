/*
 * 本文件依赖：
 * 1. var-jqGrid-option.js
 * 请按照上述顺序，在文件同页面之前引用
 */
$(function() {
    $("#jqGrid").jqGrid(option);
});

var vm = new Vue({
    el: '#goodsListDiv',
    data: {
    	q: {
        	keyword: null,
            goodsStatus: '',
            categoryName: '',
            goodsBrand: '',
            supplierName: '',
            goodsTag: ''
        },
    	goods_categorys: [], // 全部分类
        goods_brands: [], // 全部品牌
        goods_suppliers: [], // 全部供货商
        goods_tags: [], // 全部标签
    },
    methods: {
        search: function() {
        	this.reloadPage();
        },
        resetSearch: function() {
        	this.q.goodsStatus = '';
        	this.q.categoryName = '';
        	this.q.goodsBrand = '';
        	this.q.supplierName = '';
        	this.q.goodsTag = '';
        	this.q.keyword = null;
        	this.reloadPage();
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'keyword': this.q.keyword,
                    'goodsStatus': this.q.goodsStatus,
                    'categoryName': this.q.categoryName,
                    'goodsBrand': this.q.goodsBrand,
                    'supplierName': this.q.supplierName,
                    'goodsTag': this.q.goodsTag
                },
                page: page
            }).trigger("reloadGrid");
        },
        loadGoodsCategorys: function() { // 加载所有商品分类列表
        	var _self = this;
            $.ajax({
                url: basePath + "/admin/goods/getGoodsCategoryList",
                data:  { 'parentCategoryId': 0 },
                success: function(result) {
                    if (result.code == "00") {
                    	_self.goods_categorys = result.list;
                    } else {
                        layer.alert("加载商品分类列表出错" + result.msg);
                    }
                }
            });
        },
        loadGoodsBrands: function() { // 加载所有商品品牌列表
        	var _self = this;
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsBrand",
        		success: function(result) {
        			if (result.code == "00") {
        				_self.goods_brands = result.brands;
        			} else {
        				layer.alert("加载商品品牌列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadGoodsSuppliers: function() { // 加载所有供货商列表
        	var _self = this;
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/supplier/queryAllSupplierNames",
        		success: function(result) {
        			if (result.code == "00") {
        				_self.goods_suppliers = result.names;
        			} else {
        				layer.alert("加载供货商列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadGoodsTags: function() { // 加载所有商品标签列表
        	var _self = this;
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsTag",
        		success: function(result) {
        			if (result.code == "00") {
        				_self.goods_tags = result.tags;
        			} else {
        				layer.alert("加载商品标签列表出错" + result.msg);
        			}
        		}
        	});
        },
    },
    mounted: function() {
    	this.loadGoodsCategorys();
    	this.loadGoodsBrands();
    	this.loadGoodsSuppliers();
    	this.loadGoodsTags();
    }
});

