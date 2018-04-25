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

var vm_goodsListDiv = new Vue({
    el: '#goodsListDiv',
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
        // --------add---------
        selected: { // 所勾选的
        	goods_category: [], // 分类
            goods_brand: [], // 品牌
            goods_supplier: [], // 供货商
            goods_tags: [], // 标签
            goods_units: [], // 单位
            goods_colors: [], // 颜色
            goods_sizes: [], // 尺码
        },
        switches: {
        	displayImageUpload: false, // 显示图片上传框开关(仅编辑时显示)
            colorSize: false, // 颜色尺码开关
            prodNumSame: false, // 货号和条码一致开关
        },
		goods: { // 商品实体
        	id: null,
        	goodsImageId: null,
        	goodsName: null,
        	barCode: null,
        	productNumber: null,
        	pinyinCode: null,
        	categoryName: null,
        	goodsStatus: true, // 默认启用
        	goodsBrand: null,
        	goodsColor: null,
        	goodsSize: null,
        	goodsTag: null,
        	goodsStock: null,
        	quantityUnit: null,
        	stockUpperLimit: null,
        	stockLowerLimit: null,
        	lastImportPrice: null,
        	averageImportPrice: null,
        	salesPrice: null,
        	tradePrice: null,
        	vipPrice: null,
        	isVipDiscount: true, // 默认开启会员折扣
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
        },
        DEFAULT_GOODS: { // 默认初始商品实体，用于重置goods
        	id: null,
        	goodsImageId: null,
        	goodsName: null,
        	barCode: null,
        	productNumber: null,
        	pinyinCode: null,
        	categoryName: null,
        	goodsStatus: true, // 默认启用
        	goodsBrand: null,
        	goodsColor: null,
        	goodsSize: null,
        	goodsTag: null,
        	goodsStock: null,
        	quantityUnit: null,
        	stockUpperLimit: null,
        	stockLowerLimit: null,
        	lastImportPrice: null,
        	averageImportPrice: null,
        	salesPrice: null,
        	tradePrice: null,
        	vipPrice: null,
        	isVipDiscount: true, // 默认开启会员折扣
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
        },
        goodsColor: { // 颜色实体
        	id: null,
        	color: null,
        	gmtUpdate: null,
        	gmtCreate: null
        },
        DEFAULT_GOODSCOLOR: { // 默认颜色实体
        	id: null,
        	color: null,
        	gmtUpdate: null,
        	gmtCreate: null
        },
        goodsSize: { // 尺寸实体
        	id: null,
        	sizeName: null,
        	gmtUpdate: null,
        	gmtCreate: null
        },
        DEFAULT_GOODSSIZE: { // 默认尺寸实体
        	id: null,
        	sizeName: null,
        	gmtUpdate: null,
        	gmtCreate: null
        },
    },
    computed: {
    	goods_barCode() {
    		return this.goods.barCode;
    	},
    	switches_colorSize() {
    		return this.switches.colorSize;
    	},
    	switches_prodNumSame() {
    		return this.switches.prodNumSame;
    	},
    	goods_isVipDiscount() {
    		return this.goods.isVipDiscount;
    	},
    	goods_lastImportPrice() {
    		return this.goods.lastImportPrice;
    	}
    },
    watch: {
    	switches_prodNumSame: function() { // 货号与条码一致开关
    		if(this.switches.prodNumSame) {
    			this.goods.productNumber = this.goods.barCode;
        		$("#productNumberInput").attr("readOnly","true");
    		} else {
    			$("#productNumberInput").removeAttr("readOnly");
    		}
    	},
    	goods_barCode: function() { // 条码变化时可能需要更新货号
    		if(this.switches.prodNumSame) {
    			this.goods.productNumber = this.goods.barCode;
    		}
    	},
    	switches_colorSize: function() { // 颜色尺码开关切换
    		if(this.switches.colorSize) {
    			$("#colorSizeBtn").removeAttr("disabled");
    		} else {
    			$("#colorSizeBtn").attr("disabled","true");
    		}
    	},
    	goods_isVipDiscount: function() { // 会员折扣
    		if(this.goods.isVipDiscount) {
    			this.goods.vipPrice = null;
        		$("#goodsVipPriceInput").attr("readOnly","true");
    		} else {
    			$("#goodsVipPriceInput").removeAttr("readOnly");
    		}
    	},
    	goods_lastImportPrice: function() { // 最后一次进价
    		this.goods.averageImportPrice = this.goods.lastImportPrice;
    	}
    },
    methods: {
        search: function() {
        	this.reloadPage();
        },
        resetSearch: function() {
        	this.reloadPage();
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'barCode': this.q.barCode,
                    'goodsName': this.q.goodsName,
                    'pinyinCode': this.q.pinyinCode,
                    'goodsStatus': this.q.goodsStatus,
                    'categoryName': this.q.categoryName,
                    'goodsBrand': this.q.goodsBrand,
                    'supplierName': this.q.supplierName,
                    'goodsTag': this.q.goodsTag
                },
                page: page
            }).trigger("reloadGrid");
        },
        add: function() {
        	this.resetGoods();
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: "新增商品",
                area: '650px',
                shadeClose: false,
                content: jQuery("#goodsAddDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        type: "POST",
                        url: basePath + "/admin/goods/addGoodsInfo",
                        data: vm_goodsListDiv.goods,
                        success: function(result) {
                            if (result.code == "00") {
                                layer.alert('添加成功');
                                layer.close(index);
                                vm_goodsListDiv.resetGoods();
                            } else {
                                layer.alert(result.msg);
                            }
                            vm_goodsListDiv.reloadPage();
                        }
                    });
                }
            });
        },
        update: function() {
        	this.resetGoods();

        },
        del: function() {
        	this.resetGoods();
        	
        },
        importGoods: function() {

        },
        exportGoods: function() {

        },
        loadGoodsCategorys: function() {
        	// 加载所有商品分类列表
            $.ajax({
                type: "POST",
                url: basePath + "/admin/goods/getGoodsCategoryTree",
                data: {
                	'categoryId': 1
                },
                success: function(result) {
                    if (result.code == "00") {
                        this.goods_categorys = result.tree;
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
        				vm_goodsListDiv.goods_brands = result.brands;
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
        				vm_goodsListDiv.goods_suppliers = result.suppliers;
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
        				vm_goodsListDiv.goods_tags = result.tags;
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
        				vm_goodsListDiv.goods_units = result.units;
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
        				vm_goodsListDiv.goods_colors = result.colors;
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
        				vm_goodsListDiv.goods_sizes = result.sizes;
        			} else {
        				layer.alert("加载商品尺寸列表出错" + result.msg);
        			}
        		}
        	});
        },
        resetGoods: function() {
        	this.goods = cloneJsonObj(this.DEFAULT_GOODS);
        },
        getBarCode: function() {
        	$.ajax({
                type: "GET",
                url: basePath + "/admin/goods/getBarCode",
                success: function(result) {
                    if (result.code == "00") {
                    	vm_goodsListDiv.goods.barCode = result.barCode;
                    } else {
                        layer.alert("条码生成失败：" + result.msg);
                    }
                }
            });
        },
        _editGoodsStock: function() { // 编辑库存
        	layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: "编辑库存",
                area: '650px',
                shadeClose: false,
                content: jQuery("#goodsStockDiv"),
                btn: ['确定']
            });
        },
        addGoodsColor: function() { // 添加颜色
        	$.ajax({
                type: "POST",
                url: basePath + "/admin/goods/addGoodsColor",
                data: {
                	'colorName': vm_goodsListDiv.goodsColor.color
                },
                success: function(result) {
                    if (result.code == "00") {
                    	vm_goodsListDiv.goodsColor.id = result.id;
                    	vm_goodsListDiv.goods_colors.push(cloneJsonObj(vm_goodsListDiv.goodsColor));
                    	vm_goodsListDiv.resetGoodsColor();
                    } else {
                        layer.alert("添加颜色失败：" + result.msg);
                    }
                }
            });
        },
        addGoodsSize: function() { // 添加尺寸
        	
        },
        resetGoodsColor: function() {
        	this.goodsColor = cloneJsonObj(this.DEFAULT_GOODSCOLOR);
        },
        resetGoodsSize: function() {
        	this.goodsSize = cloneJsonObj(this.DEFAULT_GOODSSIZE);
        },
    },
    mounted: function() {
    	this.loadGoodsCategorys();
    	this.loadGoodsBrands();
    	this.loadGoodsSuppliers();
    	this.loadGoodsTags();
    	this.loadGoodsUnits();
    	this.loadGoodscolors();
    	this.loadGoodsSizes();
    }
});