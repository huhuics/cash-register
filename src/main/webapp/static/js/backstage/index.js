//iframe自适应
$(window).on('resize', function() {
    var $content = $('.content');
    $content.height($(this).height() - 185);
    $content.find('iframe').each(function() {
        $(this).height($content.height());
    });
}).resize();

var backstageIndexVM = new Vue({
    el: '#app',
    data: {
        iframeSrc: "sales/queryBasicFacts",
        navTitle: "营业概况",
        shopName: null,
    },
    methods: {
        menuClick: function(url) {
            var url = (event.currentTarget + '').split('#')[1];
            this.iframeSrc = url;

            var $currentA = $("a[href='#" + url + "']");

            // 导航菜单展开
            $(".sidebar-menu li").removeClass("active");
            $(".treeview-menu li").removeClass("active");
            $currentA.parents("li").addClass("active");

            this.navTitle = $currentA.text();
        },
        logout: function() {
        	$.ajax({
        		type: 'GET',
                url: basePath + '/admin/logout',
                success: function(result) {
                    if (result.code == "00") {
                        window.location.href = basePath + '/toAdminLogin';
                    } else {
                        layer.alert('系统错误：' + result.msg);
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
    },
    mounted: function() {
    	this.load_shopName();
    }
});