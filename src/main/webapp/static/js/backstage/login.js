var vm = new Vue({
    el: '#app',
    data: {
        loginName: null,
        loginPassword: null,
        shopName: null
    },
    methods: {
        login: function() {
            var _self = this;
            $.ajax({
                url: basePath + '/adminLogin',
                data: {
                    loginName: _self.loginName,
                    loginPassword: _self.loginPassword
                },
                success: function(result) {
                    if (result.code == "00") {
                    	if(result.init == "true") {
                    		window.location.href = basePath + '/admin/index';
                    		return;
                    	} else if(result.init == "false") {
                    		layer.open({
                                type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "系统初始化", area: '400px',
                                content: jQuery("#systemInitDiv"),
                                btn: ['提交'],
                                btn1: function(index) {
                                	if(isBlank(_self.shopName)) {
                                		layer.msg("店名不能为空");
                                		return;
                                	}
                                    $.ajax({
                                        url: basePath + "/admin/systemConfig/setShopName",
                                        data: { newValue: _self.shopName },
                                        success: function(result) {
                                            if (result.code == "00") {
                                                layer.msg('初始化成功');
                                                layer.close(index);
                                                window.location.href = basePath + '/admin/index';
                                            } else {
                                                layer.alert(result.msg);
                                            }
                                        }
                                    });
                                }
                            });
                    	} else {
                    		layer.alert("系统错误");
                    	}
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        }
    }
});