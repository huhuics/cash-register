$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/shopper/achievement/queryPage',
        datatype: "json",
        colModel: [
        	{ label: '导购明细ID', name: 'id', hidden: true, key: true },
        	{ label: '日期', name: 'tradeTime', index: 'trade_Time', width: 80 },
            { label: '导购员', name: 'shopperNo', index: 'shopper_No', width: 80 },
            { label: '商品名称', name: 'goodsName', index: 'goods_Name', width: 80 },
            { label: '颜色', name: 'goodsColor', index: 'goods_Color', width: 80 },
            { label: '尺码', name: 'goodsSize', index: 'goods_Size', width: 80 },
            { label: '单价', name: 'totalAmount.amount', index: 'total_Amount', width: 80 },
            { label: '数量', name: 'goodsCount', index: 'goods_Count', width: 80 },
            { label: '实收', name: 'totalActualAmount.amount', index: 'total_Actual_Amount', width: 80 },
            { label: '利润', name: 'profitAmount.amount', index: 'profit_Amount', width: 80 },
            { label: '提成', name: 'profitAmount.amount', index: 'profit_Amount', width: 80 },
            { label: '类型', name: 'tradeType', index: 'trade_Type', width: 80 }
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
        postData: {
            'tradeTimeUp': dateFormater(getDayStart()),
            'tradeTimeDown': dateFormater(getDayEnd())
        }
    });
});



var vm = new Vue({
    el: '#shopperAchievementListDiv',
    data: {
        q: {
        	bizNo: '',
        	categoryName: '',
        	tradeTimeUp: null,
        	tradeTimeDown: null,
        },
        goods_categorys: [], // 全部分类
        shoppers: [], // 全部导购员
    },
    methods: {
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.bizNo = '';
            this.q.categoryName = '';
            this.q.tradeTimeUp = null;
            this.q.tradeTimeDown = null;
            this.reloadPage();
        },
        exportShopperAchievement: function() {},
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'bizNo': this.q.bizNo,
                    'categoryName': this.q.categoryName,
                    'tradeTimeUp': this.q.tradeTimeUp,
                    'tradeTimeDown': this.q.tradeTimeDown
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
				_self.q.tradeTimeUp = value;
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
				_self.q.tradeTimeDown = value;
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
        loadShoppers: function() { // 加载所有导购员列表
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/shopper/queryAll",
        		data: {status: true},
        		success: function(result) {
        			if (result.code == "00") {
        				_self.shoppers = result.infos;
        			} else {
        				layer.alert("加载导购员列表出错" + result.msg);
        			}
        		}
        	});
        },
    },
    mounted: function() {
    	this.datetimepickerLoad();
    	this.loadGoodsCategorys();
    	this.loadShoppers();
    	this.rangeToday();
    }
});