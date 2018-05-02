/*
 * 本文件依赖：
 * 1. var-jqGrid-option.js
 * 2. var-goods-entity.js
 * 3. var-vue-data.js
 * 请按照上述顺序，在文件同页面之前引用
 */
$(function() {
    $("#jqGrid").jqGrid(option);
});

var vm = new Vue({
    el: '#goodsListDiv',
    data: vue_data,
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
    	},
    	color_size_stock() {
    		var ret = [];
    		var colors = this.select_goods_colors;
    		var sizes = this.select_goods_sizes;
    		if (colors.length>0 && sizes.length>0) {
    			for (var i = 0; i < colors.length; i++) {
                    for (var j = 0; j < sizes.length; j++) {
                        ret.push({'color':colors[i], 'size':sizes[j], 'stock':'0'});
                    }
                }
    		} else if (colors.length>0) {
    			for (var i = 0; i < colors.length; i++) {
                    ret.push({'color':colors[i], 'size':'', 'stock':'0'});
                }
    		} else if (sizes.length>0) {
    			for (var i = 0; i < sizes.length; i++) {
                    ret.push({'color':'', 'size':sizes[i], 'stock':'0'});
                }
    		} else {
    			
    		}
            return ret;
    	},
    	batchEditParam_royaltyType() {
    		return this.batchEditParam.royaltyType;
    	},
    	batchEditParam_isVipPrice() {
    		return this.batchEditParam.isVipPrice;
    	},
    	
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
    		switch(this.select_goods_tags_usefor) {
    		case 'addOrEdit':
    			this.goods.goodsTag = this.select_goods_tags.toString();
    			break;
    		case 'batchAdd':
    			this.batchEditParam.goodsTagAdd = this.select_goods_tags.toString();
    			break;
    		case 'batchRemove':
    			this.batchEditParam.goodsTagRemove = this.select_goods_tags.toString();
    			break;
    		default:
    			layer.alert('参数异常！选择标签将不会生效');
    		}
    	},
    	batchEditParam_royaltyType: function() { // 批量编辑提成方式
    		if (this.batchEditParam.royaltyType==3){
    			$("#batchEditRoyaltyValueInput").removeAttr("readonly");
    			$("#batchEditRoyaltyValueSpan").text("固定金额");
    		} else if (this.batchEditParam.royaltyType==4 || this.batchEditParam.royaltyType==5){
    			$("#batchEditRoyaltyValueInput").removeAttr("readonly");
    			$("#batchEditRoyaltyValueSpan").text("百分比");
    		} else {
    			this.batchEditParam.royaltyValue = 0;
    			$("#batchEditRoyaltyValueInput").attr("readonly","true");
    			$("#batchEditRoyaltyValueSpan").text("无相关值");
    		}
    	},
    	batchEditParam_isVipPrice: function() { // 批量编辑会员优惠
    		if(this.batchEditParam.isVipPrice!='no') {
    			this.batchEditParam.vipPricePercent = null;
        		$("#batchEditVipPricePercentInput").attr("readonly","true");
    		} else {
    			$("#batchEditVipPricePercentInput").removeAttr("readonly");
    		}
    	},
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
        add: function() {
        	this.select_goods_tags_usefor = 'addOrEdit';
        	this.resetGoods();
        	this.goods.categoryName = '';
        	this.goods.royaltyType = '{type:"0",value:"0"}';
        	var _self = this;
            layer.open({
                type: 1, skin: 'layui-layer-lan', title: "新增商品", area: '650px', shadeClose: false,
                content: jQuery("#goodsAddDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                	if(!_self.switches.colorSize) { // 未选择颜色尺码
                		$.ajax({
                            url: basePath + "/admin/goods/addGoodsInfo",
                            data: _self.goods,
                            success: function(result) {
                                if (result.code == "00") {
                                    layer.alert('添加成功');
                                    layer.close(index);
                                    _self.resetGoods();
                                    _self.select_goods_colors = [];
                                    _self.select_goods_sizes = [];
                                } else {
                                    layer.alert(result.msg);
                                }
                                _self.reloadPage();
                            }
                        });
                	} else { // 选择颜色尺码
                		var num_success = 0;
                		var num_failed = 0;
                		var err_msg = '';
                		var css = _self.color_size_stock;
                		var barCodePrefix = _self.goods.barCode + '-';
                		for(var i=0; i<css.length; i++) {
                			_self.goods.barCode = barCodePrefix + PrefixInteger(i+1,3);
                			_self.goods.goodsColor = css[i].color;
                			_self.goods.goodsSize = css[i].size;
                			_self.goods.goodsStock = css[i].stock;
                			$.ajax({
                				async: false,
                                url: basePath + "/admin/goods/addGoodsInfo",
                                data: _self.goods,
                                success: function(result) {
                                    if (result.code == "00") {
                                    	num_success++;
                                    } else {
                                    	num_failed++;
                                    	err_msg += vm.goods.goodsName + '创建失败[' + result.msg + ']';
                                    }
                                    
                                }
                            });
                		}
                		_self.reloadPage();
                		layer.close(index);
                		layer.alert(num_success+'个商品创建成功,'+num_failed+'个商品创建失败.'+err_msg);
                	}
                }
            });
        },
        update: function() {
        	var goodsId = getSelectedRow();
        	if (isBlank(goodsId)) {
                return;
            }
        	this.select_goods_tags_usefor = 'addOrEdit';
        	this.resetGoods();
        	var _self = this;
        	$.ajax({
                url: basePath + "/admin/goods/queryGoodsInfoById",
                data: { 'goodsInfoId': goodsId },
                success: function(result) {
                    if (result.code == "00") {
                    	_self.goods = result.goodsInfo;
                    	_self.goods.salesPrice = result.goodsInfo.salesPrice.amount;
                    	_self.goods.lastImportPrice = result.goodsInfo.lastImportPrice==null?null:result.goodsInfo.lastImportPrice.amount;
                    	_self.goods.vipPrice = result.goodsInfo.vipPrice==null?null:result.goodsInfo.vipPrice.amount;
                    	_self.goods.tradePrice = result.goodsInfo.tradePrice==null?null:result.goodsInfo.tradePrice.amount;
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
        batchUpdate: function() { // 批量编辑
        	var goodsIds = getSelectedRows();
        	if (isBlank(goodsIds) || goodsIds.length < 1) {
                return;
            }
        	this.resetBatchEditParam();
        	this.batchEditParam.targetIds = goodsIds.toString();
        	var _self = this;
        	layer.open({
                type: 1, skin: 'layui-layer-lan', title: "批量编辑"+goodsIds.length+"个商品", area: '600px', shadeClose: false,
                content: jQuery("#goodsbatchEditDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                	$.ajax({
                        url: basePath + "/admin/goods/batchUpdate",
                        data: _self.batchEditParam,
                        success: function(result) {
                            if (result.code == "00") {
                                layer.alert('批量编辑成功');
                                layer.close(index);
                                _self.reloadPage();
                            } else {
                                layer.alert(result.msg);
                            }
                        }
                    });
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
                    data: { 'idStr': goodsIds + '' },
                    success: function(result) {
                        if (result.code == "00") {
                            layer.alert('删除成功');
                            vm.reloadPage();
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
        _editGoodsStock: function() { layerOpen_skinlan_nobtn("编辑库存",'650px',"#goodsStockDiv"); },
        _editGoodsColorSize: function() { layerOpen_skinlan_nobtn("编辑颜色尺码",'650px',"#goodsColorSizeDiv"); },
        _editGoodsUnit: function() { layerOpen_skinlan_nobtn("选择单位",'350px',"#goodsUnitDiv"); },
        _editGoodsBrand: function() { layerOpen_skinlan_nobtn("选择品牌",'350px',"#goodsBrandDiv"); },
        _editGoodsTag: function(usefor) {
        	this.select_goods_tags_usefor = usefor;
        	layerOpen_skinlan_nobtn("选择标签",'350px',"#goodsTagDiv"); 
        },
        _editGoodsSupplier: function() { layerOpen_skinlan_nobtn("选择供货商",'350px',"#goodsSupplierDiv"); },
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
        	this.select_goods_tags = [];
        	this.select_goods_colors = [];
        	this.select_goods_sizes = [];
        	this.switches.displayImageUpload = false;
        	this.switches.colorSize = false;
        	this.switches.prodNumSame = false;
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
        resetBatchEditParam: function() {
        	this.batchEditParam = cloneJsonObj(vue_batchEditParam);
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
        loadGoodsUnits: function() { // 加载所有商品单位列表
        	var _self = this;
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsUnit",
        		success: function(result) {
        			if (result.code == "00") {
        				_self.goods_units = result.units;
        			} else {
        				layer.alert("加载商品单位列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadGoodsColors: function() { // 加载所有商品颜色列表
        	var _self = this;
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsColor",
        		success: function(result) {
        			if (result.code == "00") {
        				_self.goods_colors = result.colors;
        			} else {
        				layer.alert("加载商品颜色列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadGoodsSizes: function() { // 加载所有商品尺码列表
        	var _self = this;
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/goods/queryAllGoodsSize",
        		success: function(result) {
        			if (result.code == "00") {
        				_self.goods_sizes = result.sizes;
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

