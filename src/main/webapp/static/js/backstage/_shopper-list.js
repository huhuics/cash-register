$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/shopper/queryPage',
        datatype: "json",
        colModel: [
        	{ label: '导购员ID', name: 'id', hidden: true, key: true },
            { label: '编号', name: 'shopperNo', index: 'shopper_No', width: 80 },
            { label: '姓名', name: 'name', index: 'name', width: 80 },
            { label: '电话', name: 'phone', index: 'phone', width: 80 },
            { label: '销售提成', name: 'salesPercentage', index: 'sales_Percentage', width: 80 },
            { label: '充值提成', name: 'rechargePercentage', index: 'recharge_Percentage', width: 80 },
            { label: '购物卡提成', name: 'shoppingCardPercentage', index: 'shopping_Card_Percentage', width: 80 },
            {
                label: '状态',
                name: 'status',
                index: 'status',
                sortable: false,
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

var entity_shopper_info = {
	id: null,
	shopperNo: null,
	name: null,
	phone: null,
	salesPercentage: null,
	rechargePercentage: null,
	shoppingCardPercentage: null,
	status: null,
	royaltyType: null
};

var vm = new Vue({
    el: '#shopperListDiv',
    data: {
        q: {
            status: '',
            shopperNo: null,
            name: null,
        },
        shopper: cloneJsonObj(entity_shopper_info),
    },
    methods: {
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.shopperNo = null;
            this.q.name = null;
            this.q.status = '';
            this.reloadPage();
        },
        add: function() {
            this.resetShopper();
            this.shopper.status = true; // 默认状态为启用
            var _self = this;
            layer.open({
                type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "新增导购员", area: '600px',
                content: jQuery("#shopperDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        url: basePath + "/admin/shopper/addOrUpdate",
                        data: _self.shopper,
                        success: function(result) {
                            if (result.code == "00") {
                                layer.msg('添加成功');
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
            var shopperId = getSelectedRow();
            if (isBlank(shopperId)) {
                return;
            }
            this.resetShopper();
            var _self = this;
            $.ajax({
                url: basePath + "/admin/shopper/queryById",
                data: { 'id': shopperId },
                success: function(result) {
                    if (result.code == "00") {
                    	_self.shopper = result.info;
                        layer.open({
                            type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "编辑导购员", area: '500px',
                            content: jQuery("#shopperDiv"),
                            btn: ['提交', '取消'],
                            btn1: function(index) {
                            	delete _self.shopper.gmtCreate;
                            	delete _self.shopper.gmtUpdate;
                                $.ajax({
                                    url: basePath + "/admin/shopper/addOrUpdate",
                                    data: _self.shopper,
                                    success: function(result) {
                                        if (result.code == "00") {
                                            layer.msg('编辑成功');
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
                        layer.alert("获取导购员资料失败:" + result.msg);
                    }
                }
            });
        },
        del: function() {
        	var shopperId = getSelectedRow();
        	if (isBlank(shopperId)) {
                return;
            }
        	var shopperName = getSelectedRowValue('name');
        	var _self = this;
        	confirm("确定删除" + shopperName + "这个导购员吗?", function() {
                $.ajax({
                    url: basePath + "/admin/shopper/deleteById",
                    data: { 'id': shopperId },
                    success: function(result) {
                        if (result.code == "00") {
                            layer.msg('删除成功');
                            _self.reloadPage();
                        } else {
                            layer.alert(result.msg);
                        }
                    }
                });
            });

        },
        resetShopper: function() {
            this.shopper = cloneJsonObj(entity_shopper_info);
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            var _self = this;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'shopperNo': _self.q.shopperNo,
                    'name': _self.q.name,
                    'status': _self.q.status
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});