var vm = new Vue({
    el: '#receiptPrintSetDiv',
    data: {
        phone: null,
    },
    methods: {
        updatePhone: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/cashier/systemConfig/setPhone",
        		data: { newValue: _self.phone },
        		success: function(result) {
        			if (result.code == "00") {
        				layer.msg('更新成功');
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
        load_phone: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/cashier/systemConfig/queryByCode",
        		data: { paramCode: 'PHONE' },
        		success: function(result) {
        			if (result.code == "00") {
        				_self.phone = result.byCode.paramValue;
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
    },
    mounted: function() {
    	this.load_phone();
    }
});