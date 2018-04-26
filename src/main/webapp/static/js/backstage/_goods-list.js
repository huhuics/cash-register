/*
 * 本文件依赖：
 * var-jqGrid-option.js
 * var-goods-entity.js
 * 请在文件同页面之前引用
 */
$(function() {
    $("#jqGrid").jqGrid(option);
});

var vm = new Vue({
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
    	select_goods_tags: [], // 所选择的标签
    	select_goods_colors: [], // 所选择的颜色
    	select_goods_sizes: [], // 所选择的尺码
        switches: {
        	displayImageUpload: false, // 显示图片上传框开关(仅编辑时显示)
            colorSize: false, // 颜色尺码开关
            prodNumSame: false, // 货号和条码一致开关
        },
		goods: cloneJsonObj(goods_entity), // 商品实体
        goodsColor: cloneJsonObj(goodsColor_entity), // 颜色实体
        goodsSize: cloneJsonObj(goodsSize_entity), // 尺码实体
        goodsUnit: cloneJsonObj(goodsUnit_entity), // 单位实体
        goodsBrand: cloneJsonObj(goodsBrand_entity), // 品牌实体
        goodsTag: cloneJsonObj(goodsTag_entity), // 标签实体
    },
    computed: {
    	goods_barCode() {
    		return this.goods.barCode;
    	},
    	goods_isVipDiscount() {
    		return this.goods.isVipDiscount;
    	},
    	goods_lastImportPrice() {
    		return this.goods.lastImportPrice;
    	},
    	switches_colorSize() {
    		return this.switches.colorSize;
    	},
    	switches_prodNumSame() {
    		return this.switches.prodNumSame;
    	},
    	select_color_size() {
    		if(this.switches.colorSize) {
    			return this.select_goods_colors + '-' + this.select_goods_sizes;
    		} else {
    			return null;
    		}
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
    	},
    	select_goods_tags: function() { // 选择标签变化时更新商品标签
    		this.goods.goodsTag = this.select_goods_tags.toString();
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
                type: 1, skin: 'layui-layer-lan', title: "新增商品", area: '650px', shadeClose: false,
                content: jQuery("#goodsAddDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        url: basePath + "/admin/goods/addGoodsInfo",
                        data: vm.goods,
                        success: function(result) {
                            if (result.code == "00") {
                                layer.alert('添加成功');
                                layer.close(index);
                                vm.resetGoods();
                                vm.select_goods_colors = [];
                                vm.select_goods_sizes = [];
                            } else {
                                layer.alert(result.msg);
                            }
                            vm.reloadPage();
                        }
                    });
                }
            });
        },
        update: function() {
        	var goodsId = getSelectedRow();
        	if (isBlank(goodsId)) {
                return;
            }
        	this.resetGoods();
        	$.ajax({
                url: basePath + "/admin/goods/queryGoodsInfoById",
                data: { 'goodsInfoId': goodsId },
                success: function(result) {
                    if (result.code == "00") {
                        vm.goods = result.goodsInfo;
                        vm.goods.salesPrice = result.goodsInfo.salesPrice.amount;
                        vm.goods.lastImportPrice = result.goodsInfo.lastImportPrice.amount;
                        vm.goods.vipPrice = result.goodsInfo.vipPrice==null?null:result.goodsInfo.vipPrice.amount;
                        vm.goods.tradePrice = result.goodsInfo.tradePrice.amount;
                        if(!isBlank(vm.goods.goodsTag)) {
                        	vm.select_goods_tags = vm.goods.goodsTag.split(',');
                        }
                        layer.open({
                        	type: 1, skin: 'layui-layer-lan', title: "编辑商品", area: '650px', shadeClose: false,
                            content: jQuery("#goodsAddDiv"),
                            btn: ['提交', '取消'],
                            btn1: function(index) {
                                $.ajax({
                                    url: basePath + "/admin/goods/updateGoodsInfo",
                                    data: vm.goods,
                                    success: function(result) {
                                        if (result.code == "00") {
                                            layer.alert('操作成功');
                                            layer.close(index);
                                        } else {
                                            layer.alert(result.msg);
                                        }
                                        vm.reloadPage();
                                    }
                                });
                            }
                        });
                    } else {
                        layer.alert("获取商品信息失败:" + result.msg);
                    }
                }
            });

        },
        del: function() {
        	var goodsIds = getSelectedRows();
        	if (isBlank(goodsIds) || goodsIds.length < 1) {
                return;
            }
        	confirm("确定删除这" + goodsIds.length + "个商品吗?", function() {
                $.ajax({
                    url: basePath + "/admin/goods/deleteGoodsInfo",
                    data: { 'ids': goodsIds },
                    success: function(result) {
                        if (result.code == "00") {
                            layer.alert('删除成功');
                            vm.reload();
                        } else {
                            layer.alert(result.msg);
                        }
                    }
                });
            });
        },
        importGoods: function() {},
        exportGoods: function() {},
        getBarCode: function() {
        	$.ajax({
                type: "GET",
                url: basePath + "/admin/goods/getBarCode",
                success: function(result) {
                    if (result.code == "00") {
                    	vm.goods.barCode = result.barCode;
                    } else {
                        layer.alert("条码生成失败：" + result.msg);
                    }
                }
            });
        },
        getPinyinCode: function() {
        	$.ajax({
        		url: basePath + "/admin/goods/genePinyinShort",
        		data: {'goodsName': vm.goods.goodsName},
        		success: function(result) {
        			if (result.code == "00") {
        				vm.goods.pinyinCode = result.pinyin;
        			} else {
        				layer.alert("拼音码生成失败：" + result.msg);
        			}
        		}
        	});
        },
        _editGoodsStock: function() { // 编辑库存
        	layer.open({
                type: 1, skin: 'layui-layer-lan', title: "编辑库存", area: '650px', shadeClose: false,
                content: jQuery("#goodsStockDiv"),
                btn: ['确定']
            });
        },
        _editGoodsColorSize: function() {
        	layer.open({
                type: 1, skin: 'layui-layer-lan', title: "编辑颜色尺码", area: '650px', shadeClose: false,
                content: jQuery("#goodsColorSizeDiv"),
                btn: ['确定']
            });
        },
        _editGoodsUnit: function() {
        	layer.open({
        		type: 1, skin: 'layui-layer-lan', title: "选择单位", area: '350px', shadeClose: false,
        		content: jQuery("#goodsUnitDiv"),
        		btn: ['确定']
        	});
        },
        _editGoodsBrand: function() {
        	layer.open({
        		type: 1, skin: 'layui-layer-lan', title: "选择品牌", area: '350px', shadeClose: false,
        		content: jQuery("#goodsBrandDiv"),
        		btn: ['确定']
        	});
        },
        _editGoodsTag: function() {
        	layer.open({
        		type: 1, skin: 'layui-layer-lan', title: "选择标签", area: '350px', shadeClose: false,
        		content: jQuery("#goodsTagDiv"),
        		btn: ['确定']
        	});
        },
        _editGoodsSupplier: function() {
        	layer.open({
        		type: 1, skin: 'layui-layer-lan', title: "选择供货商", area: '350px', shadeClose: false,
        		content: jQuery("#goodsSupplierDiv"),
        		btn: ['确定']
        	});
        },
        _editGoodsCategory: function() {
        	layer.alert('暂不支持分类管理，请手动输入分类');
        },
        addGoodsColor: function() { // 添加颜色
        	$.ajax({
                url: basePath + "/admin/goods/addGoodsColor",
                data: { 'colorName': vm.goodsColor.color },
                success: function(result) {
                    if (result.code == "00") {
                    	vm.loadGoodsColors();
                    	vm.resetGoodsColor();
                    } else {
                        layer.alert("添加颜色异常：" + result.msg);
                    }
                }
            });
        },
        deleteGoodsColorById: function(id) { // 根据id删除颜色
        	$.ajax({
                url: basePath + "/admin/goods/deleteGoodsColor",
                data: { 'id': id },
                success: function(result) {
                    if (result.code == "00") {
                    	vm.loadGoodsColors();
                    	vm.select_goods_colors = []; // 删除颜色成功后清空选择
                    } else {
                        layer.alert("删除颜色失败：" + result.msg);
                    }
                }
            });
        },
        addGoodsSize: function() { // 添加尺码
        	$.ajax({
                url: basePath + "/admin/goods/addGoodsSize",
                data: { 'sizeName': vm.goodsSize.sizeName },
                success: function(result) {
                    if (result.code == "00") {
                    	vm.loadGoodsSizes();
                    	vm.resetGoodsSize();
                    } else {
                        layer.alert("添加尺码异常：" + result.msg);
                    }
                }
            });
        },
        deleteGoodsSizeById: function(id) { // 根据id删除尺码
        	$.ajax({
                url: basePath + "/admin/goods/deleteGoodsSize",
                data: { 'id': id },
                success: function(result) {
                    if (result.code == "00") {
                    	vm.loadGoodsSizes();
                    	vm.select_goods_sizes = []; // 删除尺码成功后清空选择
                    } else {
                        layer.alert("删除尺码失败：" + result.msg);
                    }
                }
            });
        },
        addGoodsUnit: function() { // 添加单位
        	$.ajax({
        		url: basePath + "/admin/goods/addGoodsUnit",
        		data: { 'unitName': vm.goodsUnit.unitName },
        		success: function(result) {
        			if (result.code == "00") {
        				vm.loadGoodsUnits();
        				vm.resetGoodsUnit();
        			} else {
        				layer.alert("添加单位异常：" + result.msg);
        			}
        		}
        	});
        },
        deleteGoodsUnitById: function(id) { // 根据id删除单位
        	$.ajax({
        		url: basePath + "/admin/goods/deleteGoodsUnit",
        		data: { 'id': id },
        		success: function(result) {
        			if (result.code == "00") {
        				vm.loadGoodsUnits();
        				vm.goods.quantityUnit = null; // 删除单位成功后清空选择
        			} else {
        				layer.alert("删除单位失败：" + result.msg);
        			}
        		}
        	});
        },
        addGoodsBrand: function() { // 添加品牌
        	$.ajax({
        		url: basePath + "/admin/goods/addGoodsBrand",
        		data: { 'brandName': vm.goodsBrand.brandName },
        		success: function(result) {
        			if (result.code == "00") {
        				vm.loadGoodsBrands();
        				vm.resetGoodsBrand();
        			} else {
        				layer.alert("添加品牌异常：" + result.msg);
        			}
        		}
        	});
        },
        deleteGoodsBrandById: function(id) { // 根据id删除品牌
        	$.ajax({
        		url: basePath + "/admin/goods/deleteGoodsBrand",
        		data: { 'id': id },
        		success: function(result) {
        			if (result.code == "00") {
        				vm.loadGoodsBrands();
        				vm.goods.goodsBrand = null; // 删除品牌成功后清空选择
        			} else {
        				layer.alert("删除品牌失败：" + result.msg);
        			}
        		}
        	});
        },
        addGoodsTag: function() { // 添加标签
        	$.ajax({
                url: basePath + "/admin/goods/addGoodsTag",
                data: { 'tagName': vm.goodsTag.tagName },
                success: function(result) {
                    if (result.code == "00") {
                    	vm.loadGoodsTags();
                    	vm.resetGoodsTag();
                    } else {
                        layer.alert("添加标签异常：" + result.msg);
                    }
                }
            });
        },
        deleteGoodsTagById: function(id) { // 根据id删除标签
        	$.ajax({
                url: basePath + "/admin/goods/deleteGoodsTag",
                data: { 'id': id },
                success: function(result) {
                    if (result.code == "00") {
                    	vm.loadGoodsTags();
                    	vm.select_goods_tags = []; // 删除标签成功后清空选择
                    } else {
                        layer.alert("删除标签失败：" + result.msg);
                    }
                }
            });
        },
        resetGoods: function() {
        	this.goods = cloneJsonObj(goods_entity);
        },
        resetGoodsColor: function() {
        	this.goodsColor = cloneJsonObj(goodsColor_entity);
        },
        resetGoodsSize: function() {
        	this.goodsSize = cloneJsonObj(goodsSize_entity);
        },
        resetGoodsUnit: function() {
        	this.goodsUnit = cloneJsonObj(goodsUnit_entity);
        },
        resetGoodsBrand: function() {
        	this.goodsBrand = cloneJsonObj(goodsBrand_entity);
        },
        resetGoodsTag: function() {
        	this.goodsTag = cloneJsonObj(goodsTag_entity);
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
        	// 加载所有供货商列表
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/supplier/queryAllSupplierNames",
        		success: function(result) {
        			if (result.code == "00") {
        				vm.goods_suppliers = result.names;
        			} else {
        				layer.alert("加载供货商列表出错" + result.msg);
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
        loadGoodsColors: function() {
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
        	// 加载所有商品尺码列表
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsSize",
        		success: function(result) {
        			if (result.code == "00") {
        				vm.goods_sizes = result.sizes;
        			} else {
        				layer.alert("加载商品尺码列表出错" + result.msg);
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
    	this.loadGoodsUnits();
    	this.loadGoodsColors();
    	this.loadGoodsSizes();
    }
});