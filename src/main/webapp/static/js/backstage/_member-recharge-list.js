$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/member/recharge/list',
        datatype: "json",
        mtype: "post",
        colModel: [
            { label: '会员编号', name: 'memberNo', index: 'member_No', width: 80 },
            { label: '会员姓名', name: 'memberName', index: 'member_Name', width: 80 },
            { label: '会员等级', name: 'rankTitle', index: 'rank_Title', width: 80 },
            { label: '收银员编号', name: 'sellerNo', index: 'seller_No', width: 80 },
            { label: '导购员编号', name: 'shopperNo', index: 'shopper_No', width: 80 },
            { label: '充值金额', name: 'rechargeAmount', index: 'recharge_Amount', width: 80 },
            { label: '赠送金额', name: 'donationAmount', index: 'donation_Amount', width: 80 },
            { label: '充值总金额', name: 'totalAmount', index: 'total_Amount', width: 80 },
            { label: '支付方式', name: 'payChenal', index: 'pay_Chenal', width: 80 },
            { label: '充值时间', name: 'gmtCreate', index: 'gmt_Create', width: 80 },
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
        postData: { gmtCreateUp: dateFormater(getDayEnd()), gmtCreateDown: dateFormater(getDayStart()) }
    });
});

var vm = new Vue({
    el: '#saleStatisticsListDiv',
    data: {
        q: {
        	payChanel: '',
        	gmtCreateUp: dateFormater(getDayEnd()),
        	gmtCreateDown: dateFormater(getDayStart()),
        	sellerNo: null
        }
    },
    methods: {
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
        	this.payChanel = '';
        	this.q.gmtCreateUp = dateFormater(getDayEnd());
            this.q.gmtCreateDown = dateFormater(getDayStart());
        	this.sellerNo = null;
            this.reloadPage();
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            var _self = this;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                	'payChanel': _self.q.categoryName,
                	'sellerNo': _self.q.goodsBrand,
                	'gmtCreateUp': _self.q.gmtCreateUp,
                	'gmtCreateDown': _self.q.gmtCreateDown,
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
				_self.q.gmtCreateDown = value;
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
				_self.q.gmtCreateUp = value;
			});
		},
		rangeToday: function() {
			var _now = new Date();
			this.q.gmtCreateDown = dateFormater(getDayStart(_now));
			this.q.gmtCreateUp = dateFormater(getDayEnd(_now));
		},
		rangeYesterday: function() {
			var _now = new Date();
			var _yesterday = getLastDay(_now);
			this.q.gmtCreateDown = dateFormater(getDayStart(_yesterday));
			this.q.gmtCreateUp = dateFormater(getDayEnd(_yesterday));
		},
		rangeDayBeforeYesterday: function() {
			var _now = new Date();
			var _dayBeforeYesterday = getLastDay(getLastDay(_now));
			this.q.gmtCreateDown = dateFormater(getDayStart(_dayBeforeYesterday));
			this.q.gmtCreateUp = dateFormater(getDayEnd(_dayBeforeYesterday));
		},
		rangeWeek: function() {
			var _now = new Date();
			this.q.gmtCreateDown = dateFormater(getWeekStart(_now));
			this.q.gmtCreateUp = dateFormater(getWeekEnd(_now));
		},
		rangeLastWeek: function() {
			var _now = new Date();
			this.q.gmtCreateDown = dateFormater(getLastWeekStart(_now));
			this.q.gmtCreateUp = dateFormater(getLastWeekEnd(_now));
		},
		range7Days: function() {
			var _now = new Date();
			this.q.gmtCreateDown = dateFormater(get7DaysBefore(_now));
			this.q.gmtCreateUp = dateFormater(getDayEnd(_now));
		},
		rangeMonth: function() {
			var _now = new Date();
			this.q.gmtCreateDown = dateFormater(getMonthStart(_now));
			this.q.gmtCreateUp = dateFormater(getMonthEnd(_now));
		},
		rangeLastMonth: function() {
			var _now = new Date();
			this.q.gmtCreateDown = dateFormater(getMonthStart(getLastMonthEnd(_now)));
			this.q.gmtCreateUp = dateFormater(getLastMonthEnd(_now));
		},
    },
    mounted: function() {
    	this.datetimepickerLoad();
    }
});