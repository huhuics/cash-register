var vm = new Vue({
    el: '#app',
    data: {
        sellerNo: null,
        password: null
    },
    methods: {
        login: function() {
            if (isBlank(this.sellerNo)) {
                layer.msg('收银员编号不能为空');
                return;
            }
            if (isBlank(this.password)) {
                layer.msg('密码不能为空');
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + '/cashierLogin',
                data: {
                    sellerNo: _self.sellerNo,
                    password: _self.password
                },
                success: function(result) {
                    if (result.code == "00") {
                        window.location.href = basePath + '/cashier/index';
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        }
    }
});