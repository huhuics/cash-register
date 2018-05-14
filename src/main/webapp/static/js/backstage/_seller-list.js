$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/seller/list',
        datatype: "json",
        colModel: [{ label: '收银员ID', name: 'id', hidden: true, key: true },
            { label: '所属门店', name: 'partOfShop', index: 'part_Of_Shop', width: 200 },
            { label: '编号', name: 'sellerNo', index: 'seller_No', width: 80 },
            { label: '姓名', name: 'name', index: 'name', width: 80 },
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
            { label: '密码', name: 'password', index: 'password', sortable: false, width: 100 },
            { label: '电话', name: 'phone', index: 'phone', width: 100 },
            {
                label: '状态',
                name: 'status',
                index: 'status',
                width: 80,
                formatter: function(value, options, row) {
                    if (value == '1') {
                        return '<span class="label label-success">启用</span>';
                    }
                    if (value == '0') {
                        return '<span class="label label-danger">禁用</span>';
                    }
                    return '未知状态:' + value;
                }
            }
        ],
        viewrecords: true, height: "auto", width: "100%",
        rowNum: 10, rowList: [10, 30, 50], 
        rownumbers: true, rownumWidth: 45,
        shrinkToFit: true, autowidth: true,
        multiselect: true,
        sortname: "gmt_Update", sortorder: "desc",
        pager: "#jqGridPager",
        jsonReader: { root: "page.list", page: "page.pageNum", total: "page.pages", records: "page.total" },
        prmNames: { page: "pageNum", rows: "pageSize", order: "order" }
    });
});

var vm = new Vue({
    el: '#app',
    data: {
        q: {
            sellerNo: null,
            name: null,
            phone: null,
            status: ''
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
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.sellerNo = null;
            this.q.name = null;
            this.q.phone = null;
            this.q.status = '';
            this.reloadPage();
        },
        add: function() {
            this.resetSeller();
            this.seller.status = true; // 默认状态为启用
            this.seller.role = 'seller'; // 默认角色为收银员
            this.seller.cashPermission = ["option1", "option2"]; // 默认收银端权限
            this.seller.backgroundPermission = ["option2", "option3"]; // 默认后台权限
            var _self = this;
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: "新增收银员",
                area: '500px',
                shadeClose: false,
                content: jQuery("#sellerDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        url: basePath + "/admin/seller/addOrUpdate",
                        data: {
                            'sellerNo': _self.seller.sellerNo,
                            'name': _self.seller.name,
                            'password': _self.seller.password,
                            'phone': _self.seller.phone,
                            'role': _self.seller.role,
                            'status': _self.seller.status,
                            'cashPermission': JSON.stringify(_self.seller.cashPermission),
                            'backgroundPermission': JSON.stringify(_self.seller.backgroundPermission)
                        },
                        dataType: "json",
                        success: function(result) {
                            if (result.code == "00") {
                                layer.alert('添加成功');
                                layer.close(index);
                            } else {
                                layer.alert(result.msg);
                            }
                            _self.reloadPage();
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
            this.resetSeller();
            var _self = this;
            $.ajax({
                type: "GET",
                url: basePath + "/admin/seller/getById",
                data: {
                    'id': sellerId
                },
                dataType: "json",
                success: function(result) {
                    if (result.code == "00") {
                        _self.seller.id = result.seller.id;
                        _self.seller.sellerNo = result.seller.sellerNo;
                        _self.seller.name = result.seller.name;
                        _self.seller.password = result.seller.password;
                        _self.seller.phone = result.seller.phone;
                        _self.seller.role = result.seller.role;
                        _self.seller.status = result.seller.status;
                        _self.seller.cashPermission = JSON.parse(result.seller.cashPermission);
                        _self.seller.backgroundPermission = JSON.parse(result.seller.backgroundPermission);
                        layer.open({
                            type: 1,
                            skin: 'layui-layer-lan',
                            title: "编辑收银员",
                            area: '500px',
                            shadeClose: false,
                            content: jQuery("#sellerDiv"),
                            btn: ['提交', '取消'],
                            btn1: function(index) {
                                $.ajax({
                                    type: "POST",
                                    url: basePath + "/admin/seller/addOrUpdate",
                                    data: {
                                        'id': _self.seller.id, // 编辑收银员时必传id
                                        'sellerNo': _self.seller.sellerNo,
                                        'name': _self.seller.name,
                                        'password': _self.seller.password,
                                        'phone': _self.seller.phone,
                                        'role': _self.seller.role,
                                        'status': _self.seller.status,
                                        'cashPermission': JSON.stringify(_self.seller.cashPermission),
                                        'backgroundPermission': JSON.stringify(_self.seller.backgroundPermission)
                                    },
                                    dataType: "json",
                                    success: function(result) {
                                        if (result.code == "00") {
                                            layer.alert('操作成功');
                                            layer.close(index);
                                        } else {
                                            layer.alert(result.msg);
                                        }
                                        _self.reloadPage();
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
            this.resetSeller();
            var sellerId = getSelectedRow();
            var sellerNo = getSelectedRowValue('sellerNo');
            var name = getSelectedRowValue('name');
            if (isBlank(sellerId)) {
                return;
            }
            var _self = this;
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
                            _self.reloadPage();
                        } else {
                            layer.alert(result.msg);
                        }
                    }
                });
            });

        },
        resetSeller: function() {
            this.seller.id = null;
            this.seller.partOfShop = null;
            this.seller.sellerNo = null;
            this.seller.name = null;
            this.seller.role = 'seller';
            this.seller.password = null;
            this.seller.phone = null;
            this.seller.status = null;
            this.seller.cashPermission = [];
            this.seller.backgroundPermission = [];
        },
        reloadPage: function() {
            this.showList = true;
            var _self = this;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'sellerNo': _self.q.sellerNo,
                    'name': _self.q.name,
                    'phone': _self.q.phone,
                    'status': _self.q.status
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});