//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

var vm = new Vue({
	el : '#app',
	data : {
		user : {},
		menuList : {},
		iframeSrc : "dashboard",
		navTitle : "Dashboard"
	},
	methods : {
		menuClick : function(url) {
			var url = (event.currentTarget + '').split('#')[1];
			vm.mainPage = url;

			var $currentA = $("a[href='#" + url + "']");

			// 导航菜单展开
			$(".sidebar-menu li").removeClass("active");
			$(".treeview-menu li").removeClass("active");
			$currentA.parents("li").addClass("active");

			vm.navTitle = $currentA.text();
		}
	}
});
