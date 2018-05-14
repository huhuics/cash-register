var vm = new Vue({
    el: '#stockCheckListDiv',
    data: {
        q: {
        	checkDateUp: null,
        	checkDateDown: null,
        },
        goodsStockCheckList: [],
    },
    methods: {
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
        exportSalesBasicFacts: function() {},
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