var vm = new Vue({
    el: '#receiptPrintSetDiv',
    data: {
    	receipt_width: 580,
    },
    methods: {
        update_receipt_width: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/cashier/systemConfig/setReceiptWidth",
        		data: { newValue: _self.receipt_width },
        		success: function(result) {
        			if (result.code == "00") {
        				layer.msg('更新成功');
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
        load_receipt_width: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/cashier/systemConfig/queryByCode",
        		data: { paramCode: 'RECEIPT_WIDTH' },
        		success: function(result) {
        			if (result.code == "00") {
        				_self.receipt_width = result.byCode.paramValue;
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
    },
    mounted: function() {
    	this.load_receipt_width();
    }
});