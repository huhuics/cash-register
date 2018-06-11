//iframe自适应
$(window).on('resize', function() {
    var $content = $('.content');
    $content.height($(this).height() - 135);
    $content.find('iframe').each(function() {
        $(this).height($content.height());
    });
}).resize();

var vm = new Vue({
    el:'#app',
    data:{
    	iframeSrc : "cashRegister",
    	loginTime: null, // 登陆时间
    	exchangeJobTime: null, // 交接班时间
    },
    methods: {
    	menuClick : function(url) {
			var url = (event.currentTarget + '').split('#')[1];
			vm.iframeSrc = url;

			var $currentA = $("a[href='#" + url + "']");

			// 导航菜单展开
			$(".navbar-nav li").removeClass("active");
			$(".dropdown-menu li").removeClass("active");
			$currentA.parents("li").addClass("active");

			vm.navTitle = $currentA.text();
		},
        exchangeJob: function() {
        	this.exchangeJobTime = dateFormater(new Date());
        	var _self = this;
        	// 加载交接班信息
        	$.ajax({
                url: basePath + '/cashier/exchangeJobInfo',
                success: function(result) {
                	_self.loginTime = result.loginTime;
                	// TODO 其它交接班信息
                }
            });
        	layer.open({
                type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "交接班", area: '600px',
                content: jQuery("#exchangeJobDiv"),
                btn: ['交接班并登出', '返回'],
                btn1: function(index) {
                	$.ajax({
                        url: basePath + '/cashier/logout',
                        success: function(result) {
                            if (result.code == "00") {
                                window.location.href = basePath + '/toCashierLogin';
                            } else {
                                layer.alert('系统错误：' + result.msg);
                            }
                        }
                    });
                }
            });
        }
    }
});
