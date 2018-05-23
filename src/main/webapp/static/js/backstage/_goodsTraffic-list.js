$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/traffic/queryPage',
        datatype: "json",
        colModel: [
        	{ label: '货流ID', name: 'id', hidden: true, key: true },
            { label: '货流单号', name: 'trafficNo', index: 'traffic_No', width: 80 },
            { label: '货流类型', name: 'trafficType', index: 'traffic_Type', width: 80,
            	formatter: function(value, options, row) {
                    if (value == 'in') {
                        return '进货';
                    }
                    if (value == 'ordinaryOut') {
                        return '普通出库';
                    }
                    if (value == 'supplierOut') {
                    	return '退货给供货商';
                    }
                    return '未知类型:' + value;
                }
            },
            { label: '商品名称', name: 'goodsName', index: 'goods_Name', width: 80 },
            { label: '商品条码', name: 'barCode', index: 'bar_Code', width: 80 },
            { label: '商品颜色', name: 'goodsColor', index: 'goods_Color', width: 80 },
            { label: '商品尺寸', name: 'goodsSize', index: 'goods_Size', width: 80 },
            { label: '供货商名称', name: 'supplierName', index: 'supplier_Name', width: 80 },
            { label: '商品库存', name: 'goodsStock', index: 'goods_Stock', width: 80 },
            { label: '进货量', name: 'inCount', index: 'in_Count', width: 80 },
            { label: '进货价', name: 'inAmount.amount', index: 'in_Amount', width: 80 },
            { label: '进货赠送量', name: 'freeCount', index: 'free_Count', width: 80 },
            { label: '预付款', name: 'advancePaymentAmount.amount', index: 'advance_Payment_Amount', width: 80 },
            { label: '单位', name: 'quantityUnit', index: 'quantity_Unit', width: 80 },
            { label: '出库价格类型', name: 'outPriceType', index: 'out_Price_Type', width: 80,
            	formatter: function(value, options, row) {
                    if (value == 'last_import_price') {
                        return '以最近进货价出库';
                    }
                    if (value == 'average_import_price') {
                        return '以平均进货价出库';
                    }
                    if (value == 'sales_price') {
                    	return '以商品销售价出库';
                    }
                    if (value == 'trade_price') {
                    	return '以商品批发价出库';
                    }
                    return '未知类型:' + value;
                }
            },
            { label: '出库价', name: 'outAmount.amount', index: 'out_Amount', width: 80 },
            { label: '出库量', name: 'outCount', index: 'out_Count', width: 80 },
            { label: '小计', name: 'totalAmount.amount', index: 'total_Amount', width: 80 },
            { label: '操作员编号', name: 'operatorNo', index: 'operator_No', width: 80 },
            { label: '备注', name: 'remark', index: 'remark', width: 80 },
            {
                label: '状态',
                name: 'status',
                index: 'status',
                sortable: false,
                width: 80,
                formatter: function(value, options, row) {
                    if (value == '1') {
                        return '<span class="label label-success">已完成</span>';
                    }
                    if (value == '0') {
                        return '<span class="label label-info">处理中</span>';
                    }
                    return '未知状态:' + value;
                }
            }
        ],
        viewrecords: true, height: "auto", width: "100%",
        rowNum: 10, rowList: [10, 30, 50], 
        rownumbers: true, rownumWidth: 45,
        shrinkToFit: true, autowidth: true,
        multiselect: false,
        sortname: "gmt_Update", sortorder: "desc",
        pager: "#jqGridPager",
        jsonReader: { root: "page.list", page: "page.pageNum", total: "page.pages", records: "page.total" },
        prmNames: { page: "pageNum", rows: "pageSize", order: "order" },
        postData: { createTimeUp: dateFormater(getDayEnd()), createTimeDown: dateFormater(getDayStart()) }
    });
});

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
var entity_outTrafficRequest = {
	goodsId: null,
	goodsName: null,
	barCode: null,
	goodsColor: null,
	goodsSize: null,
	goodsStock: null,
	supplierName: null,
	trafficType: '',
	outPriceType: '',
	outAmount: null,
	outCount: null,
	quantityUnit: null,
	totalAmount: null,
	operatorNo: null,
	remark: null
};

var vm = new Vue({
    el: '#goodsTrafficListDiv',
    data: {
        q: {
        	trafficType: '',
        	trafficNo: null,
        	createTimeUp: dateFormater(getDayEnd()),
        	createTimeDown: dateFormater(getDayStart()),
        },
        inTraffic: cloneJsonObj(entity_inTrafficRequest),
        outTraffic: cloneJsonObj(entity_outTrafficRequest),
        goods_suppliers: [],
        select_supplier_name: null,
        
        goods_keyword: null,
        keyword_search_goods_list: [], // 搜索商品清单
        select_goods_id: null, // 选择要加入的商品id
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
                            return;
                        } else if (result.size > 1) { // 查到多个商品，选择加入
                            _self.keyword_search_goods_list = result.goodsInfos;
                            _self.select_goods_id = null; // 打开窗口前清空选择列表
                            layer.open({
                                type: 1,
                                skin: 'layui-layer-lan',
                                title: "搜索并选择商品",
                                area: '800px',
                                shadeClose: false,
                                content: jQuery("#goodsSelectDiv"),
                                btn: ['选择', '取消'],
                                btn1: function(index) {
                                	if(isBlank(_self.select_goods_id)) {
                                		layer.msg("尚未选择商品");
                                		return;
                                	}
                                    var _goodsList = _self.keyword_search_goods_list;
                                    for (var j = 0; j < _goodsList.length; j++) {
                                        if (_goodsList[j].id == _self.select_goods_id) {
                                            _self.transferGoodsToItem(_goodsList[j]);
                                            break;
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
        transferGoodsToItem: function(goods) {
        	this.inTraffic.goodsId = goods.id;
        	this.inTraffic.goodsName = goods.goodsName;
        	this.inTraffic.barCode = goods.barCode;
        	this.inTraffic.goodsColor = goods.goodsColor;
        	this.inTraffic.goodsSize = goods.goodsSize;
        	this.inTraffic.goodsStock = goods.goodsStock;
        	this.inTraffic.supplierName = goods.supplierName;
        	this.inTraffic.inAmount = goods.lastImportPrice.amount;
        	this.inTraffic.inCount = 0;
        	this.inTraffic.freeCount = 0;
        	this.inTraffic.totalAmount = 0;
        	this.inTraffic.advancePaymentAmount = 0;
        	this.inTraffic.quantityUnit = goods.quantityUnit;
        	//---
        	this.outTraffic.goodsId = goods.id;
        	this.outTraffic.goodsName = goods.goodsName;
        	this.outTraffic.barCode = goods.barCode;
        	this.outTraffic.goodsColor = goods.goodsColor;
        	this.outTraffic.goodsSize = goods.goodsSize;
        	this.outTraffic.goodsStock = goods.goodsStock;
        	this.outTraffic.supplierName = goods.supplierName;
        	this.outTraffic.outAmount = goods.lastImportPrice.amount;
        	this.outTraffic.outCount = 0;
        	this.outTraffic.quantityUnit = goods.quantityUnit;
        	this.outTraffic.totalAmount = 0;
        },
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.trafficType = '';
            this.q.trafficNo = null;
            this.q.createTimeUp = dateFormater(getDayEnd());
            this.q.createTimeDown = dateFormater(getDayStart());
            this.reloadPage();
        },
        addInTraffic: function() {
            this.resetInTraffic();
            this.select_supplier_name = null;
            this.inTraffic.status = true;
            var _self = this;
            layer.open({
                type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "进货", area: '800px',
                content: jQuery("#addInTrafficDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        url: basePath + "/admin/traffic/addInTraffic",
                        data: _self.inTraffic,
                        success: function(result) {
                            if (result.code == "00") {
                                layer.msg('进货成功');
                                layer.close(index);
                            } else {
                                layer.alert(result.msg);
                            }
                            _self.reloadPage();
                        }
                    });
                }
            });
        },
        addOutTraffic: function() {
        	this.resetOutTraffic();
        	this.select_supplier_name = null;
        	var _self = this;
        	layer.open({
        		type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "出库", area: '800px',
        		content: jQuery("#addOutTrafficDiv"),
        		btn: ['提交', '取消'],
        		btn1: function(index) {
        			$.ajax({
        				url: basePath + "/admin/traffic/addOutTraffic",
        				data: _self.outTraffic,
        				success: function(result) {
        					if (result.code == "00") {
        						layer.msg('出库成功');
        						layer.close(index);
        					} else {
        						layer.alert(result.msg);
        					}
        					_self.reloadPage();
        				}
        			});
        		}
        	});
        },
        _editSupplier: function(type) {
        	if(type == 'in') {
        		this.select_supplier_name = this.inTraffic.supplierName;
        	} else if(type == 'out') {
        		this.select_supplier_name = this.outTraffic.supplierName;
        	} else {
        		layer.alert('系统错误');
        		return;
        	}
        	var _self = this;
        	layer.open({
        		type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "选择供货商", area: '350px',
        		content: jQuery("#supplierDiv"),
        		btn: ['确定', '取消'],
        		btn1: function(index) {
        			if(type == 'in') {
                		_self.inTraffic.supplierName = _self.select_supplier_name;
                	} else if(type == 'out') {
                		_self.outTraffic.supplierName = _self.select_supplier_name;
                	} else {
                		layer.alert('系统错误');
                	}
        			layer.close(index);
        		}
        	});
        },
        exportGoodsTraffic: function() {},
        resetInTraffic: function() {
            this.inTraffic = cloneJsonObj(entity_inTrafficRequest);
        },
        resetOutTraffic: function() {
        	this.outTraffic = cloneJsonObj(entity_outTrafficRequest);
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            var _self = this;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'trafficType': _self.q.trafficType,
                    'trafficNo': _self.q.trafficNo,
                    'createTimeUp': _self.q.createTimeUp,
                    'createTimeDown': _self.q.createTimeDown
                },
                page: page
            }).trigger("reloadGrid");
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
				_self.q.createTimeDown = value;
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
				_self.q.createTimeUp = value;
			});
		},
		rangeToday: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getDayStart(_now));
			this.q.createTimeUp = dateFormater(getDayEnd(_now));
		},
		rangeYesterday: function() {
			var _now = new Date();
			var _yesterday = getLastDay(_now);
			this.q.createTimeDown = dateFormater(getDayStart(_yesterday));
			this.q.createTimeUp = dateFormater(getDayEnd(_yesterday));
		},
		rangeDayBeforeYesterday: function() {
			var _now = new Date();
			var _dayBeforeYesterday = getLastDay(getLastDay(_now));
			this.q.createTimeDown = dateFormater(getDayStart(_dayBeforeYesterday));
			this.q.createTimeUp = dateFormater(getDayEnd(_dayBeforeYesterday));
		},
		rangeWeek: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getWeekStart(_now));
			this.q.createTimeUp = dateFormater(getWeekEnd(_now));
		},
		rangeLastWeek: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getLastWeekStart(_now));
			this.q.createTimeUp = dateFormater(getLastWeekEnd(_now));
		},
		range7Days: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(get7DaysBefore(_now));
			this.q.createTimeUp = dateFormater(getDayEnd(_now));
		},
		rangeMonth: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getMonthStart(_now));
			this.q.createTimeUp = dateFormater(getMonthEnd(_now));
		},
		rangeLastMonth: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getMonthStart(getLastMonthEnd(_now)));
			this.q.createTimeUp = dateFormater(getLastMonthEnd(_now));
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
    },
    mounted: function() {
	    this.datetimepickerLoad();
	    this.loadGoodsSuppliers();
	}
});