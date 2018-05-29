var vm = new Vue({
    el: '#memberIntegralDiv',
    data: {
        memberIntegral: {
        	id: null,
        	status: true,
        	integralType: 1,
        	integralValue: null,
        	exchangeType: null,
        	isClear: false
        }
    },
    methods: {
        addOrUpdate: function() {
            if (isBlank(this.memberIntegral.integralValue)) {
            	layer.alert('请设置积分方式')
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + "/admin/member/integral/update",
                data: _self.memberIntegral,
                success: function(result) {
                    if (result.code == "00") {
                    	layer.msg('已保存');
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        loadMemberIntegral: function() {
        	var _self = this;
        	$.ajax({
        		type: 'GET',
                url: basePath + "/admin/member/integral/query",
                success: function(result) {
                    if (result.code == "00") {
                    	if(isBlank(result.memberIntegral)) {
                    		layer.alert('还没有设置会员积分规则，请设置');
                    	} else {
                    		_self.memberIntegral = result.memberIntegral;
                    		delete _self.memberIntegral.gmtCreate;
                    		delete _self.memberIntegral.gmtUpdate;
                    	}
                    } else {
                        layer.alert('加载会员积分策略失败: ' + result.msg);
                    }
                }
            });
        }
    },
    mounted: function() {
    	this.loadMemberIntegral();
    }
});