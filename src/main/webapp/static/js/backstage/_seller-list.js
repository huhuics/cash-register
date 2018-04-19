$(function() {
    $("#jqGrid")
        .jqGrid({
            url: basePath + '/admin/seller/list',
            datatype: "json",
            colModel: [{
                    label: '收银员ID',
                    name: 'id',
                    hidden: true,
                    key: true
                },
                {
                    label: '所属门店',
                    name: 'partOfShop',
                    index: 'part_Of_Shop',
                    width: 200
                },
                {
                    label: '编号',
                    name: 'sellerNo',
                    index: 'seller_No',
                    width: 150
                },
                {
                    label: '姓名',
                    name: 'name',
                    index: 'name',
                    width: 150
                },
                {
                    label: '角色',
                    name: 'role',
                    index: 'role',
                    width: 60
                },
                {
                    label: '密码',
                    name: 'password',
                    index: 'password',
                    sortable: false,
                    width: 100
                },
                {
                	label: '电话',
                	name: 'phone',
                	index: 'phone',
                	width: 100
                },
                {
                    label: '状态',
                    name: 'status',
                    index: 'status',
                    width: 80,
                    formatter: function(value, options, row) {
                        if (value == '1') {
                            return '启用';
                        }
                        if (value == '0') {
                            return '禁用';
                        }
                        return '未知状态:'+value;
                    }
                }
            ],
            viewrecords: true,
            height: 550,
            width: "100%",
            shrinkToFit: true,
            rowNum: 10,
            rowList: [10, 30, 50],
            rownumbers: true,
            rownumWidth: 45,
            autowidth: true,
            multiselect: true,
            sortname: "gmt_Create",
            sortorder: "desc",
            pager: "#jqGridPager",
            jsonReader: {
                root: "page.list",
                page: "page.pageNum",
                total: "page.pages",
                records: "page.total"
            },
            prmNames: {
                page: "pageNum",
                rows: "pageSize",
                order: "order"
            }
        });
});

var vm = new Vue({
    el: '#app',
    data: {
        q: {
        	sellerNo: null,
        	name: null,
        	phone: null,
            status: '1'
        }
    },
    methods: {
        query: function() {
            vm.reload();
        },
        refresh: function() {
            vm.q.sellerNo = null;
            vm.q.name = null;
            vm.q.phone = null;
            vm.q.status = '1';
            $(".selectpicker").selectpicker('val', '1');
            vm.reload();
        },
        reload: function() {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'sellerNo': vm.q.sellerNo,
                    'name': vm.q.name,
                    'phone': vm.q.phone,
                    'status': vm.q.status
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});