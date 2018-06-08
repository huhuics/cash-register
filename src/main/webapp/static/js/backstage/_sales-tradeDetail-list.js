$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/trade/queryTradeDetailList',
        datatype: "json",
        colModel: [
        	{ label: '销售单据ID', name: 'id', hidden: true, key: true },
            { label: '流水号', name: 'tradeNo', index: 'trade_No', width: 80 },
            { label: '日期', name: 'tradeTime', index: 'trade_Time', width: 80 },
            { label: '类型', name: 'tradeType', index: 'trade_Type', width: 80,
            	formatter: function(value, options, row) {
                	if (value == 'refund') {
                        return '退款';
                    } else if (value == 'sales') {
                        return '销售';
                    } else {
                    	return '未知类型：'+value;
                    }
                }
            },
            { label: '收银员', name: 'sellerNo', index: 'seller_No', width: 80 },
            { label: '会员', name: 'memberName', index: 'member_Name', width: 80 },
            { label: '商品数量', name: 'goodsCount', index: 'goods_Count', width: 80 },
            { label: '商品原价', name: 'totalAmount.amount', index: 'total_Amount', width: 80 },
            { label: '实收金额', name: 'totalActualAmount.amount', index: 'total_Actual_Amount', width: 80 },
            { label: '利润', name: 'profitAmount.amount', index: 'profit_Amount', width: 80 },
            { label: '导购员', name: 'shopperNo', index: 'shopper_No', width: 80 },
        ],
        viewrecords: true, height: "auto", width: "100%",
        rowNum: 10, rowList: [10, 30, 50], 
        rownumbers: true, rownumWidth: 45,
        shrinkToFit: true, autowidth: true,
        multiselect: true,
        sortname: "id", sortorder: "asc",
        pager: "#jqGridPager",
        jsonReader: { root: "page.list", page: "page.pageNum", total: "page.pages", records: "page.total" },
        prmNames: { page: "pageNum", rows: "pageSize", order: "order" },
        postData: { tradeTimeUp: dateFormater(getDayEnd()), tradeTimeDown: dateFormater(getDayStart()) }
    });
});

var vm = new Vue({
    el: '#salesTradeDetailListDiv',
    data: {
        q: {
        	sellerNo: '',
        	payChenal: '',
        	tradeType: '',
        	tradeTimeUp: dateFormater(getDayEnd()),
        	tradeTimeDown: dateFormater(getDayStart()),
        	tradeNo: null,
        },
        sellers: [], // 全部收银员
    },
    methods: {
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.sellerNo = '';
            this.q.payChenal = '';
            this.q.tradeType = '';
            this.q.tradeTimeUp = dateFormater(getDayEnd());
            this.q.tradeTimeDown = dateFormater(getDayStart());
            this.q.tradeNo = null;
            this.reloadPage();
        },
        exportSalesTradeDetail: function() {
        	var url = basePath + '/admin/trade/exportTradeDetailList';
        	url += '?pageNum=' + $('#jqGrid').getGridParam('page');
        	url += '&pageSize=' + $('#jqGrid').getGridParam('rowNum');
        	url += '&order=' + $('#jqGrid').getGridParam('sortorder');
        	url += '&sidx=' + $('#jqGrid').getGridParam('sortname');
        	if(!isBlank(this.q.sellerNo)) url += '&sellerNo=' + this.q.sellerNo;
        	if(!isBlank(this.q.payChenal)) url += '&payChenal=' + this.q.payChenal;
        	if(!isBlank(this.q.tradeType)) url += '&tradeType=' + this.q.tradeType;
        	if(!isBlank(this.q.tradeTimeUp)) url += '&tradeTimeUp=' + this.q.tradeTimeUp;
        	if(!isBlank(this.q.tradeTimeDown)) url += '&tradeTimeDown=' + this.q.tradeTimeDown;
        	if(!isBlank(this.q.tradeNo)) url += '&tradeNo=' + this.q.tradeNo;
        	
        	window.location.href = url;
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            var _self = this;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'sellerNo': _self.q.sellerNo,
                    'payChenal': _self.q.payChenal,
                    'tradeType': _self.q.tradeType,
                    'tradeTimeUp': _self.q.tradeTimeUp,
                    'tradeTimeDown': _self.q.tradeTimeDown,
                    'tradeNo': _self.q.tradeNo,
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
		loadSellers: function() { // 加载所有收银员列表
        	var _self = this;
        	$.ajax({
        		type: 'GET',
        		url: basePath + "/admin/seller/queryAll",
        		data: {status: true},
        		success: function(result) {
        			if (result.code == "00") {
        				_self.sellers = result.sellerInfos;
        			} else {
        				layer.alert("加载收银员列表出错" + result.msg);
        			}
        		}
        	});
        },
    },
    mounted: function() {
    	this.datetimepickerLoad();
    	this.loadSellers();
    }
});