/*
 * 本文件依赖：
 * 1. var-jqGrid-option.js
 * 2. var-promotion-entity.js
 * 3. var-vue-data.js
 * 请按照上述顺序，在文件同页面之前引用
 */
$(function() {
    $("#jqGrid").jqGrid(option);
});

var vm = new Vue({
    el: '#promotionListDiv',
    data: vue_data,
    methods: {
        add: function() {
            this.resetPromotion();
            this.promotion.promotionType = 'discount';
            this.promotion.isMemberOnly = false;
            this.promotion.isMemberDiscountTwice = true;
            this.promotion.status = true;
            var _self = this;
            layer.open({
                type: 1, skin: 'layui-layer-lan', title: "新增促销活动", area: '650px', shadeClose: false,
                content: jQuery("#promotionDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        url: basePath + "/admin/promotion/addPromotionDetail",
                        data: _self.promotion,
                        success: function(result) {
                            if (result.code == "00") {
                                layer.msg('添加成功');
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
        update: function() {
            var promotionId = getSelectedRow();
            if (isBlank(promotionId)) {
                return;
            }
            this.resetPromotion();
            var _self = this;
            $.ajax({
                url: basePath + "/admin/promotion/queryPromotionById",
                data: { 'id': promotionId },
                success: function(result) {
                    if (result.code == "00") {
                        _self.promotion = result.ret;
                        layer.open({
                            type: 1, skin: 'layui-layer-lan', title: "编辑促销活动信息", area: '650px', shadeClose: false,
                            content: jQuery("#promotionDiv"),
                            btn: ['提交', '取消'],
                            btn1: function(index) {
                            	delete _self.promotion.gmtCreate;
                            	delete _self.promotion.gmtUpdate;
                                $.ajax({
                                    url: basePath + "/admin/promotion/updatePromotion",
                                    data: {
                                    	'item': _self.promotion,
                                    },
                                    success: function(result) {
                                        if (result.code == "00") {
                                            layer.msg('编辑成功');
                                            layer.close(index);
                                            _self.reloadPage();
                                        } else {
                                            layer.alert(result.msg);
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        layer.alert("获取促销活动信息失败:" + result.msg);
                    }
                }
            });
        },
        del: function() {
            var promotionId = getSelectedRow();
            if (isBlank(promotionId)) {
                return;
            }
            var _self = this;
            confirm("确定删除这个促销吗?", function() {
                $.ajax({
                    url: basePath + "/admin/promotion/deletePromotion",
                    data: { 'promotionId': promotionId },
                    success: function(result) {
                        if (result.code == "00") {
                            layer.msg('删除成功');
                            _self.reloadPage();
                        } else {
                            layer.alert(result.msg);
                        }
                    }
                });
            });
        },
        updatePromotionGoods: function() { // 编辑促销商品
        	var promotionId = getSelectedRow();
        	if (isBlank(promotionId)) {
        		return;
        	}
        	this.resetPromotion();
        	this.promotion.id = promotionId;
        	this.goods_keyword = null;
        	this.keyword_search_goods_list = [];
        	this.select_goods_id_list = [];
        	var _self = this;
        	$.ajax({
                url: basePath + "/admin/promotion/queryByPromotionId",
                data: { 'promotionId': _self.promotion.id },
                success: function(result) {
                    if (result.code == "00") {
                        _self.promotion_goods_list_commit = result.promotionGoods;
                        _self.transferPromotionGoodsList1to2();
                        layer.msg("查找到该促销活动有"+_self.promotion_goods_list_commit.length+"个促销商品");
                    } else {
                        layer.alert("查询该促销活动商品列表失败：" + result.msg);
                        return;
                    }
                }
            });
        	layer.open({
                type: 1, skin: 'layui-layer-lan', title: "编辑促销商品", area: '900px', shadeClose: false,
                content: jQuery("#promotionGoodsListDiv"),
                btn: ['确定'],
                btn1: function(index) {
                    _self.transferPromotionGoodsList2to1();
                    $.ajax({
                        url: basePath + "/admin/promotion/addPromotionGoodsDetail",
                        data: {
                        	'promotionId': promotionId,
                        	'promotionGoodsList': _self.promotion_goods_list_commit
                        },
                        success: function(result) {
                            if (result.code == "00") {
                            	layer.msg("编辑促销商品成功");
                            } else {
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            });
        },
        searchGoods: function() { // 根据关键字查找商品加入清单
            if (isBlank(this.goods_keyword)) {
                layer.msg("请输入：条码/拼音码/品名");
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + "/cashier/trade/searchGoodsInfo",
                data: { 'keyword': _self.goods_keyword },
                success: function(result) {
                    if (result.code == "00") {
                        if (result.size == 0) {
                            layer.msg("没有找到相关商品");
                            return;
                        } else if (result.size == 1) { // 查到唯一商品，直接加入
                            _self.transferGoodsToItem(result.goodsInfos[0], _self.promotion.id, 100);
                            _self.addItemToList();
                            return;
                        } else if (result.size > 1) { // 查到多个商品，选择加入
                            _self.keyword_search_goods_list = result.goodsInfos;
                            _self.select_goods_id_list = []; // 打开窗口前清空选择列表
                            layer.open({
                                type: 1,
                                skin: 'layui-layer-lan',
                                title: "搜索并选择商品",
                                area: '800px',
                                shadeClose: false,
                                content: jQuery("#goodsSelectDiv"),
                                btn: ['加入', '取消'],
                                btn1: function(index) {
                                    var _goodsList = _self.keyword_search_goods_list;
                                    for (var i = 0; i < _self.select_goods_id_list.length; i++) {
                                        _id = _self.select_goods_id_list[i];
                                        for (var j = 0; j < _goodsList.length; j++) {
                                            if (_goodsList[j].id == _id) {
                                                _self.transferGoodsToItem(_goodsList[j], _self.promotion.id, 100);
                                                _self.addItemToList();
                                                break;
                                            }
                                        }
                                    }
                                    layer.close(index);
                                }
                            });
                        }
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        transferGoodsToItem: function(goods, promotionId, discount) {
            this.resetPromotionGoodsItem(); // 重置

            this.promotion_goods_item.id = null;
            this.promotion_goods_item.promotionId = promotionId;
            this.promotion_goods_item.goodsId = goods.id;
            this.promotion_goods_item.barCode = goods.barCode;
            this.promotion_goods_item.categoryName = goods.categoryName;
            this.promotion_goods_item.discount = discount.toFixed(2);
            this.promotion_goods_item.goodsName = goods.goodsName;
            this.promotion_goods_item.salesPrice = goods.salesPrice.amount.toFixed(2);
            this.promotion_goods_item.priceWithDiscount = (1 * discount * goods.salesPrice.amount / 100).toFixed(2);
        },
        addItemToList: function() { // 将item加入list
            for (var i = 0; i < this.promotion_goods_list_show.length; i++) {
                if (this.promotion_goods_list_show[i].goodsId == this.promotion_goods_item.goodsId) {
                    // 商品已经存在于列表中时，直接返回
                    return;
                }
            }
            // 商品不存在于列表中时，添加进列表
            this.promotion_goods_list_show.push(cloneJsonObj(this.promotion_goods_item)); 
        },
        editItemDiscountById: function(goodsId, discount) { // 修改折扣
            for (var i = 0; i < this.promotion_goods_list_show.length; i++) {
                if (this.promotion_goods_list_show[i].goodsId == goodsId) {
                    this.promotion_goods_list_show[i].discount = discount.toFixed(2);
                    this.promotion_goods_list_show[i].priceWithDiscount = (this.promotion_goods_list_show[i].salesPrice * this.promotion_goods_list_show[i].discount / 100).toFixed(2);
                    return;
                }
            }
            layer.alert("系统错误");
        },
        editItemPriceById: function(goodsId, priceWithDiscount) { // 修改实际价格
            for (var i = 0; i < this.promotion_goods_list_show.length; i++) {
                if (this.promotion_goods_list_show[i].goodsId == goodsId) {
                    this.promotion_goods_list_show[i].priceWithDiscount = priceWithDiscount.toFixed(2);
                    this.promotion_goods_list_show[i].discount = (this.promotion_goods_list_show[i].priceWithDiscount / this.promotion_goods_list_show[i].salesPrice * 100).toFixed(2);
                    return;
                }
            }
            layer.alert("系统错误");
        },
        deleteItemById: function(goodsId) { // 删除item
            for (var i = 0; i < this.promotion_goods_list_show.length; i++) {
                if (this.promotion_goods_list_show[i].goodsId == goodsId) {
                	if(isBlank(this.promotion_goods_list_show[i].id)) {
                		// 如果是临时添加，直接从列表中删除
                		this.promotion_goods_list_show.splice(i, 1);
                        return;
                	} else {
                		// 如果已经存在在数据库中，从数据库中删除
                		confirm("该操作将删除已经保存的促销商品，确定要删除吗？", function() {
                			$.ajax({
                                url: basePath + "/admin/promotion/deletePromotionGoodsDetail",
                                data: { 'promotionGoodsId': _self.promotion_goods_list_show[i].id },
                                success: function(result) {
                                    if (result.code == "00") {
                                    	layer.msg("删除成功");
                                    	_self.promotion_goods_list_show.splice(i, 1); // 数据库删除成功后将其从列表中删除
                                    } else {
                                        layer.alert(result.msg);
                                    }
                                }
                            });
                        });
                		return;
                	}
                }
            }
            layer.alert("系统错误");
        },
        searchGoodsInBox: function() { // 根据关键字查找商品列表显示在待加入界面
            this.select_goods_id_list = [];
            if (isBlank(this.goods_keyword)) {
                this.keyword_search_goods_list = [];
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + "/cashier/trade/searchGoodsInfo",
                data: { 'keyword': _self.goods_keyword },
                success: function(result) {
                    if (result.code == "00") {
                        _self.keyword_search_goods_list = result.goodsInfos;
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        transferPromotionGoodsList1to2: function() { // 转换列表commit→show
        	this.promotion_goods_list_show = [];
        	for (var i = 0; i < this.promotion_goods_list_commit.length; i++) {
        		var _pg = this.promotion_goods_list_commit[i];
        		this.resetPromotionGoodsItem();
        		this.promotion_goods_item.id = _pg.id;
        		this.promotion_goods_item.promotionId = _pg.promotionId;
        		this.promotion_goods_item.goodsId = _pg.goodsId;
        		this.promotion_goods_item.barCode = _pg.barCode;
        		this.promotion_goods_item.categoryName = _pg.categoryName;
        		this.promotion_goods_item.discount = _pg.discount;
        		this.promotion_goods_list_show.push(cloneJsonObj(_self.promotion_goods_item));
        	}
        	var _self = this;
        	for (var i = 0; i < this.promotion_goods_list_show.length; i++) {
        		$.ajax({
        			url: basePath + "/admin/goods/queryGoodsInfoById",
        			data: { 'goodsInfoId': _self.promotion_goods_list_show[i].goodsId },
        			success: function(result) {
        				if (result.code == "00") {
        					_self.promotion_goods_list_show[i].goodsName = result.goodsInfo.goodsName;
        					_self.promotion_goods_list_show[i].salesPrice = result.goodsInfo.salesPrice.amount;
        					_self.promotion_goods_list_show[i].priceWithDiscount = (1 * _self.promotion_goods_item.salesPrice * _self.promotion_goods_item.discount / 100).toFixed(2);
        				} else {
        					layer.alert("获取商品信息失败:" + result.msg);
        				}
        			}
        		});
        	}
        },
        transferPromotionGoodsList2to1: function() { // 转换列表show→commit
        	this.promotion_goods_list_commit = [];
        	for (var i = 0; i < this.promotion_goods_list_show.length; i++) {
        		this.resetPromotionGoods();
        		this.promotion_goods.id = this.promotion_goods_list_show[i].id;
        		this.promotion_goods.promotionId = this.promotion_goods_list_show[i].promotionId;
        		this.promotion_goods.goodsId = this.promotion_goods_list_show[i].goodsId;
        		this.promotion_goods.barCode = this.promotion_goods_list_show[i].barCode;
        		this.promotion_goods.categoryName = this.promotion_goods_list_show[i].categoryName;
        		this.promotion_goods.discount = this.promotion_goods_list_show[i].discount;
        		this.promotion_goods_list_commit.push(cloneJsonObj(this.promotion_goods));
            }
        },
        resetPromotion: function() {
        	this.promotion = cloneJsonObj(promotion_entity);
        	this.promotion_goods_list = [];
        },
        resetPromotionGoodsItem: function() {
        	this.promotion_goods_item = cloneJsonObj(promotion_goods_item);
        },
        resetPromotionGoods: function() {
        	this.promotion_goods = cloneJsonObj(promotion_goods_entity);
        },
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.promotionType = '';
            this.q.status = '';
            this.q.promotionName = null;
            this.reloadPage();
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            var _self = this;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'promotionType': _self.q.promotionType,
                    'status': _self.q.status,
                    'promotionName': _self.q.promotionName,
                },
                page: page
            }).trigger("reloadGrid");
        },
    }
});