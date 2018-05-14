var vm = new Vue({
    el: '#app',
    data: {
        loginName: null,
        loginPassword: null
    },
    methods: {
        login: function() {
            var _self = this;
            $.ajax({
                type: 'post',
                url: basePath + '/adminLogin',
                data: {
                    loginName: _self.loginName,
                    loginPassword: _self.loginPassword
                },
                success: function(result) {
                    if (result.code == "00") {
                        window.location.href = basePath + '/admin/index'
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        }
    }
});