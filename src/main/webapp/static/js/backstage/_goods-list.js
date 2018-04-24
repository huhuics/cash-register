$(function() {
    $("#jqGrid")
        .jqGrid({
            url: basePath + '/admin/goods/goodsInfoList',
            datatype: "json",
            colModel: [{
                    label: '商品ID',
                    name: 'id',
                    hidden: true,
                    key: true
                },
                {
                    label: '商品名称',
                    name: 'goodsName',
                    index: 'goods_Name',
                    width: 150
                },
                {
                    label: '条码',
                    name: 'barCode',
                    index: 'bar_Code',
                    width: 100
                },
                {
                    label: '货号',
                    name: 'productNumber',
                    index: 'product_Number',
                    width: 80
                },
                {
                    label: '拼音码',
                    name: 'pinyinCode',
                    index: 'pinyin_Code',
                    width: 80
                },
                {
                    label: '分类',
                    name: 'categoryName',
                    index: 'category_Name',
                    width: 80
                },
                {
                    label: '库存',
                    name: 'goodsStock',
                    index: 'goods_Stock',
                    width: 60
                },
                {
                    label: '主单位',
                    name: 'quantityUnit',
                    index: 'quantity_Unit',
                    width: 60
                },
                {
                    label: '进货价',
                    name: 'importPrice',
                    index: 'import_Price',
                    width: 80
                },
                {
                    label: '销售价',
                    name: 'salesPrice',
                    index: 'sales_Price',
                    width: 80
                },
                {
                    label: '批发价',
                    name: 'tradePrice',
                    index: 'trade_Price',
                    width: 80
                },
                {
                    label: '会员价',
                    name: 'vipPrice',
                    index: 'vip_Price',
                    width: 80
                },
                {
                    label: '会员折扣',
                    name: 'isVipDiscount',
                    index: 'is_Vip_Discount',
                    width: 80
                },
                {
                    label: '供货商',
                    name: 'supplierName',
                    index: 'supplier_Name',
                    width: 100
                },
                {
                    label: '生产日期',
                    name: 'productionDate',
                    index: 'production_Date',
                    width: 100
                },
                {
                    label: '保质期',
                    name: 'qualityGuaranteePeriod',
                    index: 'quality_Guarantee_Period',
                    width: 100
                },
                {
                    label: '创建日期',
                    name: 'gmtCreate',
                    index: 'gmt_Create',
                    width: 100
                }
            ],
            viewrecords: true,
            height: "500",
            width: "100%",
            shrinkToFit: false,
            rowNum: 10,
            rowList: [10, 30, 50],
            rownumbers: true,
            rownumWidth: 45,
            autowidth: true,
            multiselect: true,
            sortname: "gmt_Update",
            sortorder: "desc",
            pager: "#jqGridPager",
            jsonReader: {
                root: "page.list",
                page: "page.pageNum",
                total: "page.pages",
                records: "page.total"
            },
            prmNames: {
                page: "pageNum",
                rows: "pageSize",
                order: "order"
            }
        });
});

var vm = new Vue({
    el: '#app',
    data: {
        q: {
            barCode: null,
            goodsName: null,
            pinyinCode: null,
            goodsStatus: true,
            categoryName: '',
            goodsBrand: '',
            supplierName: '',
            goodsTag: ''
        },
        goods_categorys: [], // 全部分类
        goods_brands: [], // 全部品牌
        goods_suppliers: [], // 全部供货商
        goods_tags: [], // 全部标签
        goods_units: [], // 全部单位
        goods_colors: [], // 全部颜色
        goods_sizes: [], // 全部尺码
        displayImageUpload: false, // 是否显示图片上传框(仅编辑时显示)
        goods: {
            id: null,
            goodsImageId: null,
            goodsName: null,
            barCode: null,
            productNumber: null,
            pinyinCode: null,
            categoryName: null,
            goodsStatus: null,
            goodsBrand: null,
            goodsColor: null,
            goodsSize: null,
            goodsTag: null,
            goodsStock: null,
            quantityUnit: null,
            stockUpperLimit: null,
            stockLowerLimit: null,
            importPrice: null,
            averageImportPrice: null,
            salesPrice: null,
            tradePrice: null,
            vipPrice: null,
            isVipDiscount: null,
            supplierName: null,
            productionDate: null,
            qualityGuaranteePeriod: null,
            isIntegral: null,
            royaltyType: null,
            isBooked: null,
            isGift: null,
            isWeigh: null,
            isFixedPrice: null,
            isTimeingPrice: null,
            isHidden: null,
            remark: null,
            gmtUpdate: null,
            gmtCreate: null
        }
    },
    methods: {
        search: function() {
            vm.reload();
        },
        resetSearch: function() {
            vm.reload();
        },
        add: function() {
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: "新增商品",
                area: '600px',
                shadeClose: false,
                content: jQuery("#goodsDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        type: "POST",
                        url: basePath + "/admin/goods/addOrUpdate",
                        data: {

                        },
                        dataType: "json",
                        success: function(result) {
                            if (result.code == "00") {
                                layer.alert('添加成功');
                                layer.close(index);
                            } else {
                                layer.alert(result.msg);
                            }
                            vm.reload();
                        }
                    });
                }
            });
        },
        update: function() {

        },
        del: function() {

        },
        importGoods: function() {

        },
        exportGoods: function() {

        },
        reload: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'barCode': vm.q.barCode,
                    'goodsName': vm.q.goodsName,
                    'pinyinCode': vm.q.pinyinCode,
                    'goodsStatus': vm.q.goodsStatus,
                    'categoryName': vm.q.categoryName,
                    'goodsBrand': vm.q.goodsBrand,
                    'supplierName': vm.q.supplierName,
                    'goodsTag': vm.q.goodsTag
                },
                page: page
            }).trigger("reloadGrid");
        },
        loadGoodsCategorys: function() {
        	// 加载所有商品分类列表
            $.ajax({
                type: "GET",
                url: basePath + "/admin/goods/getGoodsCategoryTree",
                success: function(result) {
                    if (result.code == "00") {
                        vm.goods_categorys = result.tree;
                    } else {
                        layer.alert("加载商品分类列表出错" + result.msg);
                    }
                }
            });
        },
        loadGoodsBrands: function() {
        	// 加载所有商品品牌列表
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsBrand",
        		success: function(result) {
        			if (result.code == "00") {
        				vm.goods_brands = result.brands;
        			} else {
        				layer.alert("加载商品品牌列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadGoodsSuppliers: function() {
        	// 加载所有商品供货商列表
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsBrand",
        		success: function(result) {
        			if (result.code == "00") {
        				vm.goods_suppliers = result.suppliers;
        			} else {
        				layer.alert("加载商品供货商列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadGoodsTags: function() {
        	// 加载所有商品标签列表
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsTag",
        		success: function(result) {
        			if (result.code == "00") {
        				vm.goods_tags = result.tags;
        			} else {
        				layer.alert("加载商品标签列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadGoodsUnits: function() {
        	// 加载所有商品单位列表
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsUnit",
        		success: function(result) {
        			if (result.code == "00") {
        				vm.goods_units = result.units;
        			} else {
        				layer.alert("加载商品单位列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadGoodscolors: function() {
        	// 加载所有商品颜色列表
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsColor",
        		success: function(result) {
        			if (result.code == "00") {
        				vm.goods_colors = result.colors;
        			} else {
        				layer.alert("加载商品颜色列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadGoodsSizes: function() {
        	// 加载所有商品尺寸列表
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsSize",
        		success: function(result) {
        			if (result.code == "00") {
        				vm.goods_sizes = result.sizes;
        			} else {
        				layer.alert("加载商品尺寸列表出错" + result.msg);
        			}
        		}
        	});
        }
    },
    mounted: function() {
//    	this.loadGoodsCategorys();
//    	this.loadGoodsBrands();
//    	this.loadGoodsSuppliers();
//    	this.loadGoodsTags();
//    	this.loadGoodsUnits();
//    	this.loadGoodscolors();
//    	this.loadGoodsSizes();
    }
});