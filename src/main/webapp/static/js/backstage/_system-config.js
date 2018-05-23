var vm = new Vue({
    el: '#systemConfigDiv',
    data: {
        shopName: null,
        registerTime: null,
        invalidTime: null,
        relatedEmail: null,
        phone: null,
        address: null,
        pettyAmount: null,
        authInfo: null // 授权信息
    },
    methods: {
    	updateRelatedEmail: function() {
            var _self = this;
            $.ajax({
                url: basePath + "/admin/systemConfig/setRelatedEmail",
                data: { newValue: _self.relatedEmail },
                success: function(result) {
                    if (result.code == "00") {
                    	layer.msg('更新成功');
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        updatePhone: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/systemConfig/setPhone",
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
        updateAddress: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/systemConfig/setAddress",
        		data: { newValue: _self.address },
        		success: function(result) {
        			if (result.code == "00") {
        				layer.msg('更新成功');
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
        updatePettyAmount: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/systemConfig/setPettyAmount",
        		data: { newValue: _self.pettyAmount },
        		success: function(result) {
        			if (result.code == "00") {
        				layer.msg('更新成功');
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
        truncateAllTables: function() { // 清空所有数据
        	var _self = this;
        	confirm("这个操作将清空您所有的数据，您确定要继续吗", function() {
                $.ajax({
                	type: 'GET',
                    url: basePath + "/admin/systemConfig/truncateAllTables",
                    success: function(result) {
                        if (result.code == "00") {
                            layer.alert('删除成功');
                        } else {
                            layer.alert(result.msg);
                        }
                    }
                });
            });
        },
        loadAuthInfo: function() { // 加载授权信息
        	var _self = this;
        	$.ajax({
        		type: 'GET',
                url: basePath + "/admin/systemConfig/queryRemainDays",
                success: function(result) {
                    if (result.code == "00") {
                    	if(isBlank(result.diff)) {
                    		_self.authInfo = "系统已授权";
                    		return;
                    	} else {
                    		_self.authInfo = "系统剩余有效期：" + parseInt( 1 * result.diff / (1000 * 60 * 60 * 24)) + "天";
                    	}
                    } else if (result.code == "99") {
                    	_self.authInfo = "系统未授权，请购买正版";
                    	return;
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        }
    },
    mounted: function() {
    	this.loadAuthInfo();
    }
});