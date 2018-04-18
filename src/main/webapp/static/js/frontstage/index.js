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
    	cashRegisterPage:"frontstage/cashRegister",
    },
    methods: {
        
    }
});
