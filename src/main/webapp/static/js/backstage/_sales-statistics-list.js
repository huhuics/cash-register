$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/sales/queryGoodsSaleStatistics',
        datatype: "json",
        colModel: [
            { label: '商品名称', name: 'goodsName', index: 'goods_Name', width: 80 },
            { label: '条码', name: 'barCode', index: 'bar_Code', width: 80 },
            { label: '颜色', name: 'goodsColor', index: 'goods_Color', width: 80 },
            { label: '尺寸', name: 'goodsSize', index: 'goods_Size', width: 80 },
            { label: '分类', name: 'categoryName', index: 'category_Name', width: 80 },
            { label: '库存', name: 'goodsStock', index: 'goods_Stock', width: 80 },
            { label: '销量', name: 'salesCount', index: 'sales_Count', width: 80 },
            { label: '总金额', name: 'totalAmount.amount', index: 'total_Amount', width: 80 },
            { label: '实销总金额', name: 'totalActualAmount.amount', index: 'total_Actual_Amount', width: 80 },
            { label: '总利润', name: 'totalProfit.amount', index: 'total_Profit', width: 80 },
            { label: '利润率', name: 'profitRate', index: 'profit_Rate', width: 80,
            	formatter: function(value, options, row) {
            		return value + '%';
            	}
            },
        ],
        viewrecords: true, height: "auto", width: "100%",
        rowNum: 10, rowList: [10, 30, 50], 
        rownumbers: true, rownumWidth: 45,
        shrinkToFit: true, autowidth: true,
        multiselect: false,
        sortname: "bar_Code", sortorder: "asc",
        pager: "#jqGridPager",
        jsonReader: { root: "page.list", page: "page.pageNum", total: "page.pages", records: "page.total" },
        prmNames: { page: "pageNum", rows: "pageSize", order: "order" },
        postData: { tradeTimeUp: dateFormater(getDayEnd()), tradeTimeDown: dateFormater(getDayStart()) }
    });
});

var vm = new Vue({
    el: '#saleStatisticsListDiv',
    data: {
        q: {
        	categoryName: '',
        	goodsBrand: '',
        	supplierName: '',
        	goodsTag: '',
        	tradeTimeUp: dateFormater(getDayEnd()),
        	tradeTimeDown: dateFormater(getDayStart()),
        	barCode: null,
        	goodsName: null
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
        	this.categoryName = '';
        	this.goodsBrand = '';
        	this.supplierName = '';
        	this.goodsTag = '';
        	this.q.tradeTimeUp = dateFormater(getDayEnd());
            this.q.tradeTimeDown = dateFormater(getDayStart());
        	this.barCode = null;
        	this.goodsName = null;
            this.reloadPage();
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            var _self = this;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                	'categoryName': _self.q.categoryName,
                	'goodsBrand': _self.q.goodsBrand,
                	'supplierName': _self.q.supplierName,
                	'goodsTag': _self.q.goodsTag,
                	'tradeTimeUp': _self.q.tradeTimeUp,
                	'tradeTimeDown': _self.q.tradeTimeDown,
                	'barCode': _self.q.barCode,
                	'goodsName': _self.q.goodsName,
                },
                page: page
            }).trigger("reloadGrid");
        },
        datetimepickerLoad : function() {
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
				_self.q.tradeTimeDown = value;
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
				_self.q.tradeTimeUp = value;
			});
		},
		rangeToday: function() {
			var _now = new Date();
			this.q.tradeTimeDown = dateFormater(getDayStart(_now));
			this.q.tradeTimeUp = dateFormater(getDayEnd(_now));
		},
		rangeYesterday: function() {
			var _now = new Date();
			var _yesterday = getLastDay(_now);
			this.q.tradeTimeDown = dateFormater(getDayStart(_yesterday));
			this.q.tradeTimeUp = dateFormater(getDayEnd(_yesterday));
		},
		rangeDayBeforeYesterday: function() {
			var _now = new Date();
			var _dayBeforeYesterday = getLastDay(getLastDay(_now));
			this.q.tradeTimeDown = dateFormater(getDayStart(_dayBeforeYesterday));
			this.q.tradeTimeUp = dateFormater(getDayEnd(_dayBeforeYesterday));
		},
		rangeWeek: function() {
			var _now = new Date();
			this.q.tradeTimeDown = dateFormater(getWeekStart(_now));
			this.q.tradeTimeUp = dateFormater(getWeekEnd(_now));
		},
		rangeLastWeek: function() {
			var _now = new Date();
			this.q.tradeTimeDown = dateFormater(getLastWeekStart(_now));
			this.q.tradeTimeUp = dateFormater(getLastWeekEnd(_now));
		},
		range7Days: function() {
			var _now = new Date();
			this.q.tradeTimeDown = dateFormater(get7DaysBefore(_now));
			this.q.tradeTimeUp = dateFormater(getDayEnd(_now));
		},
		rangeMonth: function() {
			var _now = new Date();
			this.q.tradeTimeDown = dateFormater(getMonthStart(_now));
			this.q.tradeTimeUp = dateFormater(getMonthEnd(_now));
		},
		rangeLastMonth: function() {
			var _now = new Date();
			this.q.tradeTimeDown = dateFormater(getMonthStart(getLastMonthEnd(_now)));
			this.q.tradeTimeUp = dateFormater(getLastMonthEnd(_now));
		},
        loadGoodsCategorys: function() { // 加载所有商品分类列表
            var _self = this;
            $.ajax({
                url: basePath + "/admin/goods/getGoodsCategoryList",
                data: { 'parentCategoryId': 0 },
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
    	this.datetimepickerLoad();
        this.loadGoodsCategorys();
        this.loadGoodsBrands();
        this.loadGoodsSuppliers();
        this.loadGoodsTags();
    }
});