var vm = new Vue({
    el: '#goodsLoseListDiv',
    data: {
        q: {
            gmtCreateUp: null,
            gmtCreateDown: null,
        },
        goodsLoseList: [],
    },
    methods: {
        search: function() {
            var _self = this;
            $.ajax({
                url: basePath + '/admin/goodsLose/queryAllLoseInfo',
                data: _self.q,
                success: function(result) {
                    if (result.code == "00") {
                        _self.goodsLoseList = result.loseInfos;
                        layer.msg('查询成功');
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
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
				_self.q.gmtCreateUp = value;
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
				_self.q.gmtCreateDown = value;
			});
		}
    },
    mounted: function() {
	    this.datetimepickerLoad();
	}
});