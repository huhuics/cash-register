var goods_item = { // 商品元素
    goodsId: null,
    barCode: null,
    goodsName: null,
    productNumber: null,
    categoryName: null,
    goodsColor: null,
    goodsSize: null,
    lastImportPrice: null, // 商品进货价
    salesPrice: null, // 商品售价
    supplierName: null, // 商品供货商
    goodsStock: null,
    //--- 以上为商品表中对应数据
    goodsCount: null, // 进货量
};

var entity_inTrafficRequest = {
	goodsId: null,
	goodsName: null,
	barCode: null,
	goodsColor: null,
	goodsSize: null,
	goodsStock: null,
	supplierName: null,
	inAmount: null,
	inCount: null,
	freeCount: null,
	quantityUnit: null,
	totalAmount: null,
	advancePaymentAmount: null,
	operatorNo: null,
	status: null,
	remark: null
};

var vm = new Vue({
    el: '#goodsStockAddDiv',
    data: {
        goods_list: [], // 进货商品清单，goods_item的数组
        goods_item: cloneJsonObj(goods_item),
        goods_keyword: null,
        keyword_search_goods_list: [], // 搜索商品清单
        select_goods_id_list: [], // 选择要加入的商品列表
        summary_count: 0,
        summary_lastImportPrice: 0,
        summary_salesPrice: 0,
        inTraffic: cloneJsonObj(entity_inTrafficRequest),
    },
    methods: {
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
                            _self.transferGoodsToItem(result.goodsInfos[0]);
                            _self.addItemToGoodsList(1);
                            _self.goods_keyword = null; // 加入商品后清空搜索值
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
                                                _self.transferGoodsToItem(_goodsList[j]);
                                                _self.addItemToGoodsList(1);
                                                _self.goods_keyword = null; // 加入商品后清空搜索值
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
        transferGoodsToItem: function(goods) { // 将goods转换为item
            this.reset_goods_item(); // 重置

            this.goods_item.goodsId = goods.id;
            this.goods_item.barCode = goods.barCode;
            this.goods_item.goodsName = goods.goodsName;
            this.goods_item.productNumber = goods.productNumber;
            this.goods_item.categoryName = goods.categoryName;
            this.goods_item.goodsColor = goods.goodsColor;
            this.goods_item.goodsSize = goods.goodsSize;
            this.goods_item.lastImportPrice = goods.lastImportPrice.amount;
            this.goods_item.salesPrice = goods.salesPrice.amount;
            this.goods_item.supplierName = goods.supplierName;
            this.goods_item.goodsStock = goods.goodsStock;

        },
        transferItemToInTraffic: function() { // 将item转换为inTraffic
        	this.inTraffic.goodsId = this.goods_item.goodsId;
        	this.inTraffic.goodsName = this.goods_item.goodsName;
        	this.inTraffic.barCode = this.goods_item.barCode;
        	this.inTraffic.goodsColor = this.goods_item.goodsColor;
        	this.inTraffic.goodsSize = this.goods_item.goodsSize;
        	this.inTraffic.goodsStock = this.goods_item.goodsStock;
        	this.inTraffic.supplierName = this.goods_item.supplierName;
        	this.inTraffic.inAmount = this.goods_item.lastImportPrice;
        	this.inTraffic.inCount = this.goods_item.goodsCount;
        	this.inTraffic.freeCount = 0;
        	this.inTraffic.quantityUnit = null;
        	this.inTraffic.totalAmount = 1 * this.inTraffic.inCount * this.inTraffic.inAmount;
        	this.inTraffic.advancePaymentAmount = 0;
        	this.inTraffic.operatorNo = null;
        	this.inTraffic.status = true;
        	this.inTraffic.remark = null;
        },
        addItemToGoodsList: function(count) { // 将指定数量的item加入list
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == this.goods_item.goodsId) {
                    this.goods_list[i].goodsCount = this.goods_list[i].goodsCount * 1 + count * 1; // 商品已经存在于列表中时
                    this.summary();
                    return;
                }
            }
            this.goods_item.goodsCount = 1;
            this.goods_list.push(cloneJsonObj(this.goods_item)); // 商品不存在于列表中时，添加进列表
            this.summary();
        },
        getItemById: function(id) {
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == this.goods_item.goodsId) {
                    this.goods_item = this.goods_list[i];
                    return;
                }
            }
            layer.alert("系统错误");
        },
        editItemCountById: function(id, count) { // 修改数量
            this.getItemById();
            this.addItemToGoodsList(count * 1 - this.goods_item.goodsCount * 1);
        },
        deleteItemById: function(id) { // 删除item
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == id) {
                    this.goods_list.splice(i, 1);
                    this.summary();
                    return;
                }
            }
            layer.alert("系统错误");
        },
        submit: function() { // 提交
        	var _self = this;
        	// 循环列表，转换为inTraffic请求，循环异步提交
        	for (var i = 0; i < this.goods_list.length; i++) {
                this.goods_item = this.goods_list[i];
                this.transferItemToInTraffic();
                $.ajax({
                    url: basePath + "/cashier/traffic/addInTraffic",
                    data: cloneJsonObj(_self.inTraffic),
                    success: function(result) {
                        if (result.code == "00") {
                            layer.msg('进货成功');
                        } else {
                            layer.alert(result.msg);
                        }
                    }
                });
            }
        	_self.reload();
        	
        },
        reset_goods_item: function() {
            this.goods_item = cloneJsonObj(goods_item);
        },
        summary: function() { // 计算统计数据
            var _totalCount = 0;
            var _totalLastImportPrice = 0;
            var _totalSalesPrice = 0;
            for (var i = 0; i < this.goods_list.length; i++) {
                _totalCount += this.goods_list[i].goodsCount;
                _totalLastImportPrice += 1 * this.goods_list[i].lastImportPrice * this.goods_list[i].goodsCount;
                _totalSalesPrice += 1 * this.goods_list[i].salesPrice * this.goods_list[i].goodsCount;
            }
            this.summary_count = _totalCount;
            this.summary_lastImportPrice = _totalLastImportPrice;
            this.summary_salesPrice = _totalSalesPrice;
            this.focus();
        },
        reload: function() {
            this.goods_list = [];
            this.goods_item = cloneJsonObj(goods_item);
            this.goods_keyword = null;
            this.summary_count = 0;
            this.summary_lastImportPrice = 0;
            this.summary_salesPrice = 0;
            this.focus();
        },
        focus: function() { // 设置页面焦点
        	$(".toFocus").focus();
        }
    },
    mounted: function() {
    	this.focus();
    }
});