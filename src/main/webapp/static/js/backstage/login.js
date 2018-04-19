var vm = new Vue({
    el:'#app',
    data:{
    	loginName: null,
    	loginPassword: null
    },
    methods: {
    	login: function(){
    		$.ajax({
    			type : 'post',
    			url : ctx + '/adminLogin',
    			data : {
    				loginName: vm.loginName,
    				loginPassword: vm.loginPassword
    			},
    			success: function(result) {
                    if (result.code == "00") {
                    	window.location.href = ctx + '/admin/index'
                    } else {
                        layer.alert(result.msg);
                    }
                }
    		});
    	}
    }
});

