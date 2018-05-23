var vm = new Vue({
    el: '#rechargeCheckListDiv',
    data: {
        q: {
        	rankTitle: '',
            gmtCreateUp: dateFormater(getDayEnd()),
            gmtCreateDown: dateFormater(getDayStart()),
        },
        memberRanks: [],
        rechargeCheckList: []
    },
    methods: {
        search: function() {
            var _self = this;
            $.ajax({
                url: basePath + '/admin/member/recharge/check',
                data: _self.q,
                success: function(result) {
                    if (result.code == "00") {
                        _self.rechargeCheckList = result.checkDetails;
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
		loadMemberRanks: function() { // 加载所有会员等级列表
        	var _self = this;
        	$.ajax({
        		type: "GET",
        		url: basePath + "/admin/member/rank/listAll",
        		success: function(result) {
        			if (result.code == "00") {
        				_self.memberRanks = result.memberRanks;
        			} else {
        				layer.alert("加载会员等级列表出错" + result.msg);
        			}
        		}
        	});
        },
    },
    mounted: function() {
	    this.datetimepickerLoad();
	    this.loadMemberRanks();
	}
});