var vm = new Vue({
    el: '#app',
    data: {
    	auth_user: null,
    	auth_password: null
    },
    methods: {
        gen: function() {
            if (isBlank(this.auth_user)) {
                layer.msg('用户名不能为空');
                return;
            }
            if (isBlank(this.auth_password)) {
                layer.msg('密码不能为空');
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + '/authCode/writeIntoUDisk',
                data: {
                	authUser: _self.auth_user,
                	authPassword: _self.auth_password
                },
                success: function(result) {
                    if (result.code == "00") {
                    	layer.msg("密钥成功生成！");
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        }
    }
});