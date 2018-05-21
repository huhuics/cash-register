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
            $.ajax({
                url: basePath + '/cashier/logout',
                success: function(result) {
                    if (result.code == "00") {
                        window.location.href = basePath + '/cashierLogin';
                    } else {
                        layer.alert('系统错误：' + result.msg);
                    }
                }
            });
        }
    }
});
