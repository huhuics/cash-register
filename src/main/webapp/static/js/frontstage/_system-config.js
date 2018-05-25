var vm = new Vue({
    el: '#systemConfigDiv',
    data: {
        shopName: null,
        phone: null,
        address: null,
        pettyAmount: null,
    },
    methods: {
        load_shopName: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/cashier/systemConfig/queryByCode",
        		data: { paramCode: 'SHOP_NAME' },
        		success: function(result) {
        			if (result.code == "00") {
        				_self.shopName = result.byCode.paramValue;
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
        load_address: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/cashier/systemConfig/queryByCode",
        		data: { paramCode: 'ADDRESS' },
        		success: function(result) {
        			if (result.code == "00") {
        				_self.address = result.byCode.paramValue;
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
        load_pettyAmount: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/cashier/systemConfig/queryByCode",
        		data: { paramCode: 'PETTY_AMOUNT' },
        		success: function(result) {
        			if (result.code == "00") {
        				_self.pettyAmount = result.byCode.paramValue;
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
    },
    mounted: function() {
    	this.load_shopName();
    	this.load_phone();
    	this.load_address();
    	this.load_pettyAmount();
    }
});