var vm = new Vue({
    el: '#stockFlowListDiv',
    data: {
        q: {
            flowType: '',
            goodsName: '优衣库男士衬衫',
            barCode: '20180510144017883-001',
            gmtCreateUp: '2018-05-09 00:00:00',
            gmtCreateDown: '2018-05-11 23:59:59',
        },
        stockFlowList: [],
    },
    methods: {
        search: function() {
            var _self = this;
            $.ajax({
                url: basePath + '/admin/stock/flow/queryList',
                data: _self.q,
                success: function(result) {
                    if (result.code == "00") {
                        _self.stockFlowList = result.list;
                        layer.msg('查询成功');
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        }
    }
});