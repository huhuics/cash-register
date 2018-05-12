var vm = new Vue({
    el: '#salesBasicFactsDiv',
    data: {
        q: {
        	timeUp: null,
        	timeDown: null,
        },
        salesBasicFacts: [],
    },
    methods: {
        search: function() {
            var _self = this;
            $.ajax({
                url: basePath + '/admin/sales/queryBasicFacts',
                data: _self.q,
                success: function(result) {
                    if (result.code == "00") {
                        _self.salesBasicFacts = result.basicFacts;
                        layer.msg('查询成功');
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        exportSalesBasicFacts: function() {},
        datetimepickerLoad: function() {
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
				vm.q.timeDown = value;
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
				vm.q.timeUp = value;
			});
		},
		rangeToday: function() {
			var _now = new Date();
			this.q.timeDown = dateFormater(getDayStart(_now));
			this.q.timeUp = dateFormater(getDayEnd(_now));
		},
		rangeYesterday: function() {
			var _now = new Date();
			var _yesterday = getLastDay(_now);
			this.q.timeDown = dateFormater(getDayStart(_yesterday));
			this.q.timeUp = dateFormater(getDayEnd(_yesterday));
		},
		rangeDayBeforeYesterday: function() {
			var _now = new Date();
			var _dayBeforeYesterday = getLastDay(getLastDay(_now));
			this.q.timeDown = dateFormater(getDayStart(_dayBeforeYesterday));
			this.q.timeUp = dateFormater(getDayEnd(_dayBeforeYesterday));
		},
		rangeWeek: function() {
			var _now = new Date();
			this.q.timeDown = dateFormater(getWeekStart(_now));
			this.q.timeUp = dateFormater(getWeekEnd(_now));
		},
		rangeLastWeek: function() {
			var _now = new Date();
			this.q.timeDown = dateFormater(getLastWeekStart(_now));
			this.q.timeUp = dateFormater(getLastWeekEnd(_now));
		},
		range7Days: function() {
			var _now = new Date();
			this.q.timeDown = dateFormater(get7DaysBefore(_now));
			this.q.timeUp = dateFormater(getDayEnd(_now));
		},
		rangeMonth: function() {
			var _now = new Date();
			this.q.timeDown = dateFormater(getMonthStart(_now));
			this.q.timeUp = dateFormater(getMonthEnd(_now));
		},
		rangeLastMonth: function() {
			var _now = new Date();
			this.q.timeDown = dateFormater(getMonthStart(getLastMonthEnd(_now)));
			this.q.timeUp = dateFormater(getLastMonthEnd(_now));
		},
    },
    mounted: function() {
	    this.datetimepickerLoad();
	}
});