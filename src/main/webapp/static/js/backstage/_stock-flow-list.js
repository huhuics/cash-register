var vm = new Vue({
    el: '#stockFlowListDiv',
    data: {
        q: {
            flowType: '',
            goodsName: null,
            barCode: null,
            gmtCreateUp: null,
            gmtCreateDown: null,
        },
        stockFlowList: [],
    },
    methods: {
        search: function() {
            var _self = this;
            $.ajax({
                url: basePath + '/admin/stock/flow/queryList',
                data: _self.q,
                success: function(result) {
                    if (result.code == "00") {
                        _self.stockFlowList = result.list;
                        layer.msg('查询成功');
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        datetimepickerLoad : function() {
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
				vm.q.gmtCreateUp = value;
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
				vm.q.gmtCreateDown = value;
			});
		}
    },
    mounted: function() {
	    this.datetimepickerLoad();
	}
});