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
        dbBackup: function() { // 系统备份
        	var _self = this;
            $.ajax({
            	type: 'GET',
                url: basePath + "/admin/systemConfig/dbBackup",
                success: function(result) {
                    if (result.code == "00") {
                        layer.alert('系统已成功备份');
                    } else {
                        layer.alert('系统备份失败');
                    }
                }
            });
        },
        dbRestore: function() { // 系统还原
        	document.getElementById("fileInput").value = '';
        	var _self = this;
        	layer.open({
                type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "系统还原", area: '600px',
                content: jQuery("#fileUploadDiv"),
                btn: ['确定还原', '取消'],
                btn1: function(index) {
                	var formData = new FormData();
                    formData.append("file", document.getElementById("fileInput").files[0]);
                    $.ajax({
                        url: basePath + '/admin/systemConfig/dbRestore',
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function(result) {
                            if (result.code == "00") {
                                layer.alert('还原成功');
                                layer.close(index);
                            } else {
                                layer.alert(result.msg);
                            }
                            _self.reloadPage();
                        }
                    });
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
                    		_self.authInfo = "系统剩余有效期：" + parseInt( 1 * result.diff ) + "天";
                    	}
                    } else if (result.code == "99") {
                    	_self.authInfo = "系统未授权，请购买正版";
                    	return;
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        load_shopName: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/systemConfig/queryByCode",
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
        load_registerTime: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/systemConfig/queryByCode",
        		data: { paramCode: 'REGISTER_TIME' },
        		success: function(result) {
        			if (result.code == "00") {
        				_self.registerTime = result.byCode.paramValue;
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
        load_invalidTime: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/systemConfig/queryByCode",
        		data: { paramCode: 'INVALID_TIME' },
        		success: function(result) {
        			if (result.code == "00") {
        				_self.invalidTime = result.byCode.paramValue;
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
        load_relatedEmail: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/systemConfig/queryByCode",
        		data: { paramCode: 'RELATED_EMAIL' },
        		success: function(result) {
        			if (result.code == "00") {
        				_self.relatedEmail = result.byCode.paramValue;
        			} else {
        				layer.alert(result.msg);
        			}
        		}
        	});
        },
        load_phone: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/systemConfig/queryByCode",
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
        		url: basePath + "/admin/systemConfig/queryByCode",
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
        		url: basePath + "/admin/systemConfig/queryByCode",
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
    	this.loadAuthInfo();
    	this.load_shopName();
    	this.load_registerTime();
    	this.load_invalidTime();
    	this.load_relatedEmail();
    	this.load_phone();
    	this.load_address();
    	this.load_pettyAmount();
    }
});