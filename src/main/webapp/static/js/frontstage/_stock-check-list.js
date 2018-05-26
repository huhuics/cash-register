var check_item = { // 盘点元素
	id: null,
	checkId: null,
	goodsName: null,
	barCode: null,
	goodsColor: null,
	goodsSize: null,
	productNumber: null,
	primaryGoodsStock: null,
	checkedGoodsStock: null,
	stockDiff: null,
	profitLossAmountStr: null,
	// 以下元素提交前需要删掉
	averageImportPrice: null, // 用于计算profitLossAmountStr
};

var vm = new Vue({
    el: '#stockCheckListDiv',
    data: {
        q: {
        	checkDateUp: dateFormater(getDayEnd()),
        	checkDateDown: dateFormater(getDayStart()),
        },
        goodsStockCheckList: [],
        // 以下为增加盘点页面数据
        check_list: [], // 盘点商品清单，check_item的数组
        check_item: cloneJsonObj(check_item),
        goods_keyword: null,
        keyword_search_goods_list: [], // 搜索商品清单
        select_goods_id_list: [], // 选择要加入的商品列表
        all_count: null, // 所有商品总数
        checked_count: 0, // 已盘点商品数
        remark: null, // 备注
    },
    methods: {
    	add: function() {
    		this.check_list = [];
    		this.reset_check_item();
    		this.goods_keyword = null;
    		this.keyword_search_goods_list = [];
    		this.select_goods_id_list = [];
    		this.checked_count = 0;
    		var _self = this;
    		$.ajax({
    			type: 'GET',
                url: basePath + "/cashier/stockCheck/queryGoodsCount",
                success: function(result) {
                    if (result.code == "00") {
                    	_self.all_count = result.goodsCount;
                    } else {
                        layer.alert('加载所有商品数量出错'+result.msg);
                    }
                }
            });
    		layer.open({
                type: 1, skin: 'layui-layer-lan', title: "添加盘点", area: '1000px', shadeClose: false,
                content: jQuery("#stockCheckAddDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                	$.ajax({
                        url: basePath + "/cashier/stockCheck/addCheck",
                        data: {
                        	'detailsStr': JSON.stringify(_self.getRequestList()),
                        	'remark': _self.remark
                        },
                        success: function(result) {
                            if (result.code == "00") {
                                layer.msg("成功盘点");
                                layer.close(index);
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
                            _self.transferGoodsToItem(result.goodsInfos[0]);
                            _self.addItemToGoodsList(1);
                            return;
                        } else if (result.size > 1) { // 查到多个商品，选择加入
                            _self.keyword_search_goods_list = result.goodsInfos;
                            _self.select_goods_id_list = []; // 打开窗口前清空选择列表
                            layer.open({
                                type: 1, skin: 'layui-layer-lan', title: "搜索并选择商品", area: '800px', shadeClose: false,
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
            this.reset_check_item(); // 重置

            this.check_item.barCode = goods.barCode;
            this.check_item.goodsName = goods.goodsName;
            this.check_item.goodsColor = goods.goodsColor;
            this.check_item.goodsSize = goods.goodsSize;
            this.check_item.productNumber = goods.productNumber;
            this.check_item.averageImportPrice = goods.averageImportPrice.amount;
            this.check_item.primaryGoodsStock = goods.goodsStock;
        },
        addItemToGoodsList: function(count) { // 将指定数量的item加入list
            for (var i = 0; i < this.check_list.length; i++) {
                if (this.check_list[i].barCode == this.check_item.barCode) {
                    this.check_list[i].checkedGoodsStock = 1 * this.check_list[i].checkedGoodsStock + count * 1; // 商品已经存在于列表中时
                    this.summary();
                    return;
                }
            }
            this.check_item.checkedGoodsStock = count;
            this.check_list.push(cloneJsonObj(this.check_item)); // 商品不存在于列表中时，添加进列表
            this.summary();
        },
        getItemByBarCode: function(barCode) {
            for (var i = 0; i < this.check_list.length; i++) {
                if (this.check_list[i].barCode == barCode) {
                    this.check_item = this.check_list[i];
                    return;
                }
            }
            layer.alert("系统错误");
        },
        editItemCheckedGoodsStockByBarCode: function(barCode, count) { // 修改数量
            this.getItemByBarCode(barCode);
            this.addItemToGoodsList(1 * count - 1 * this.check_item.checkedGoodsStock);
        },
        deleteItemByBarCode: function(barCode) { // 删除item
            for (var i = 0; i < this.check_list.length; i++) {
                if (this.check_list[i].barCode == barCode) {
                    this.check_list.splice(i, 1);
                    this.summary();
                    return;
                }
            }
            layer.alert("系统错误");
        },
        getRequestList: function() {
        	var requestList = cloneJsonObj(this.check_list);
        	for (var i = 0; i < requestList.length; i++) {
        		delete requestList[i].averageImportPrice;
        	}
        	return requestList;
        },
        reset_check_item: function() {
            this.check_item = cloneJsonObj(check_item);
        },
        summary: function() { // 计算统计数据
            for (var i = 0; i < this.check_list.length; i++) {
            	this.check_list[i].stockDiff = 1 * this.check_list[i].checkedGoodsStock - 1 * this.check_list[i].primaryGoodsStock;
            	this.check_list[i].profitLossAmountStr = 1 * this.check_list[i].stockDiff * this.check_list[i].averageImportPrice;
            }
            this.checked_count = this.check_list.length;
        },
        search: function() {
            var _self = this;
            $.ajax({
                url: basePath + '/cashier/stockCheck/queryCheck',
                data: _self.q,
                success: function(result) {
                    if (result.code == "00") {
                        _self.goodsStockCheckList = result.checks;
                        layer.msg('查询成功');
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        datetimepickerLoad: function() {
        	var _self = this;
			$('#datetimepickerAfter').datetimepicker({
				language : 'zh-CN',
				todayHighlight : 1,
				format: 'yyyy-mm-dd hh:ii:ss',
			    autoclose: true,
			    minView: 0,
			    minuteStep: 1
			});
			$('#datetimepickerAfter').datetimepicker().on('hide', function(ev) {
				var value = $("#datetimepickerAfter").val();
				_self.q.checkDateDown = value;
			});
			$('#datetimepickerBefore').datetimepicker({
				language : 'zh-CN',
				todayHighlight : 1,
				format: 'yyyy-mm-dd hh:ii:ss',
			    autoclose: true,
			    minView: 0,
			    minuteStep: 1
			});
			$('#datetimepickerBefore').datetimepicker().on('hide', function(ev) {
				var value = $("#datetimepickerBefore").val();
				_self.q.checkDateUp = value;
			});
		},
		rangeToday: function() {
			var _now = new Date();
			this.q.checkDateDown = dateFormater(getDayStart(_now));
			this.q.checkDateUp = dateFormater(getDayEnd(_now));
		},
		rangeYesterday: function() {
			var _now = new Date();
			var _yesterday = getLastDay(_now);
			this.q.checkDateDown = dateFormater(getDayStart(_yesterday));
			this.q.checkDateUp = dateFormater(getDayEnd(_yesterday));
		},
		rangeDayBeforeYesterday: function() {
			var _now = new Date();
			var _dayBeforeYesterday = getLastDay(getLastDay(_now));
			this.q.checkDateDown = dateFormater(getDayStart(_dayBeforeYesterday));
			this.q.checkDateUp = dateFormater(getDayEnd(_dayBeforeYesterday));
		},
		rangeWeek: function() {
			var _now = new Date();
			this.q.checkDateDown = dateFormater(getWeekStart(_now));
			this.q.checkDateUp = dateFormater(getWeekEnd(_now));
		},
		rangeLastWeek: function() {
			var _now = new Date();
			this.q.checkDateDown = dateFormater(getLastWeekStart(_now));
			this.q.checkDateUp = dateFormater(getLastWeekEnd(_now));
		},
		range7Days: function() {
			var _now = new Date();
			this.q.checkDateDown = dateFormater(get7DaysBefore(_now));
			this.q.checkDateUp = dateFormater(getDayEnd(_now));
		},
		rangeMonth: function() {
			var _now = new Date();
			this.q.checkDateDown = dateFormater(getMonthStart(_now));
			this.q.checkDateUp = dateFormater(getMonthEnd(_now));
		},
		rangeLastMonth: function() {
			var _now = new Date();
			this.q.checkDateDown = dateFormater(getMonthStart(getLastMonthEnd(_now)));
			this.q.checkDateUp = dateFormater(getLastMonthEnd(_now));
		},
    },
    mounted: function() {
	    this.datetimepickerLoad();
	}
});