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
                    width: 80
                },
                {
                    label: '姓名',
                    name: 'name',
                    index: 'name',
                    width: 80
                },
                {
                    label: '角色',
                    name: 'role',
                    index: 'role',
                    formatter: function(value, options, row) {
                        if (value == 'seller') {
                            return '收银员';
                        }
                        if (value == 'admin') {
                            return '管理员';
                        }
                        return '未知角色:' + value;
                    },
                    width: 80
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
                        return '未知状态:' + value;
                    }
                }
            ],
            viewrecords: true,
            height: "500",
            width: "100%",
            shrinkToFit: true,
            rowNum: 10,
            rowList: [10, 30, 50],
            rownumbers: true,
            rownumWidth: 45,
            autowidth: true,
            multiselect: true,
            sortname: "gmt_Update",
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
        },
        seller: {
            id: null,
            partOfShop: null,
            sellerNo: null,
            name: null,
            role: null,
            password: null,
            phone: null,
            status: null,
            cashPermission: [],
            backgroundPermission: []
        }
    },
    methods: {
        search: function() {
            vm.reload();
        },
        resetSearch: function() {
            vm.q.sellerNo = null;
            vm.q.name = null;
            vm.q.phone = null;
            vm.q.status = '1';
            $(".selectpicker").selectpicker('val', '1');
            vm.reload();
        },
        add: function() {
            vm.resetSeller();
            vm.seller.status = '1'; // 默认状态为启用
            vm.seller.role = 'seller'; // 默认角色为收银员
            vm.seller.cashPermission = ["option1", "option2"]; // 默认收银端权限
            vm.seller.backgroundPermission = ["option2", "option3"]; // 默认后台权限
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: "新增收银员",
                area: '600px',
                shadeClose: false,
                content: jQuery("#sellerDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        type: "POST",
                        url: basePath + "/admin/seller/addOrUpdate",
                        data: {
                            'sellerNo': vm.seller.sellerNo,
                            'name': vm.seller.name,
                            'password': vm.seller.password,
                            'phone': vm.seller.phone,
                            'role': vm.seller.role,
                            'status': vm.seller.status,
                            'cashPermission': JSON.stringify(vm.seller.cashPermission),
                            'backgroundPermission': JSON.stringify(vm.seller.backgroundPermission)
                        },
                        dataType: "json",
                        success: function(result) {
                            if (result.code == "00") {
                                layer.alert('添加成功');
                                layer.close(index);
                            } else {
                                layer.alert(result.msg);
                            }
                            vm.reload();
                        }
                    });
                }
            });
        },
        update: function() {
            var sellerId = getSelectedRow();
            if (isBlank(sellerId)) {
                return;
            }
            vm.resetSeller();
            $.ajax({
                type: "GET",
                url: basePath + "/admin/seller/getById",
                data: {
                    'id': sellerId
                },
                dataType: "json",
                success: function(result) {
                    if (result.code == "00") {
                        vm.seller.id = result.seller.id;
                        vm.seller.sellerNo = result.seller.sellerNo;
                        vm.seller.name = result.seller.name;
                        vm.seller.password = result.seller.password;
                        vm.seller.phone = result.seller.phone;
                        vm.seller.role = result.seller.role;
                        vm.seller.status = result.seller.status;
                        vm.seller.cashPermission = JSON.parse(result.seller.cashPermission);
                        vm.seller.backgroundPermission = JSON.parse(result.seller.backgroundPermission);
                        layer.open({
                            type: 1,
                            skin: 'layui-layer-lan',
                            title: "编辑收银员",
                            area: '600px',
                            shadeClose: false,
                            content: jQuery("#sellerDiv"),
                            btn: ['提交', '取消'],
                            btn1: function(index) {
                                $.ajax({
                                    type: "POST",
                                    url: basePath + "/admin/seller/addOrUpdate",
                                    data: {
                                        'id': vm.seller.id, // 编辑收银员时必传id
                                        'sellerNo': vm.seller.sellerNo,
                                        'name': vm.seller.name,
                                        'password': vm.seller.password,
                                        'phone': vm.seller.phone,
                                        'role': vm.seller.role,
                                        'status': vm.seller.status,
                                        'cashPermission': JSON.stringify(vm.seller.cashPermission),
                                        'backgroundPermission': JSON.stringify(vm.seller.backgroundPermission)
                                    },
                                    dataType: "json",
                                    success: function(result) {
                                        if (result.code == "00") {
                                            layer.alert('操作成功');
                                            layer.close(index);
                                        } else {
                                            layer.alert(result.msg);
                                        }
                                        vm.reload();
                                    }
                                });
                            }
                        });
                    } else {
                        layer.alert("获取收银员资料失败:" + result.msg);
                    }
                }
            });
        },
        del: function() {
        	vm.resetSeller();
            var sellerId = getSelectedRow();
            var sellerNo = getSelectedRowValue('sellerNo');
            var name = getSelectedRowValue('name');
            if (isBlank(sellerId)) {
                return;
            }
            confirm("确定删除[" + name + "(" + sellerNo + ")]这个收银员吗?", function() {
                $.ajax({
                    type: "POST",
                    url: basePath + "/admin/seller/delById",
                    data: {
                        'id': sellerId
                    },
                    dataType: "json",
                    success: function(result) {
                        if (result.code == "00") {
                            layer.alert('删除成功');
                            vm.reload();
                        } else {
                            layer.alert(result.msg);
                        }
                    }
                });
            });

        },
        resetSeller: function() {
            vm.seller.id = null;
            vm.seller.partOfShop = null;
            vm.seller.sellerNo = null;
            vm.seller.name = null;
            vm.seller.role = 'seller';
            vm.seller.password = null;
            vm.seller.phone = null;
            vm.seller.status = null;
            vm.seller.cashPermission = [];
            vm.seller.backgroundPermission = [];
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