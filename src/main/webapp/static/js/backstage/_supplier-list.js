$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/supplier/queryPage',
        datatype: "json",
        colModel: [
        	{ label: '供货商ID', name: 'id', hidden: true, key: true },
            { label: '编号', name: 'supplierCode', index: 'supplier_Code', width: 80 },
            { label: '名称', name: 'supplierName', index: 'supplier_Name', width: 80 },
            { label: '拼音码', name: 'pinyinCode', index: 'pinyin_Code', width: 80 },
            { label: '联系人', name: 'contactName', index: 'contact_Name', width: 80 },
            { label: '联系电话', name: 'contactPhone', index: 'contact_Phone', width: 80 },
            { label: '联系邮箱', name: 'contactEmail', index: 'contact_Email', width: 80 },
            { label: '配送费返点', name: 'deliveryRebate', index: 'delivery_Rebate', sortable: false, width: 80 },
            { label: '固定返利点', name: 'regularRebate', index: 'regular_Rebate', width: 80 },
            { label: '地址', name: 'supplierAddress', index: 'supplier_Address', width: 80 },
            { label: '备注', name: 'remark', index: 'remark', width: 80 },
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

var entity_supplier_info = {
	id: null,
	supplierCode: null,
	supplierName: null,
	pinyinCode: null,
	contactName: null,
	contactPhone: null,
	contactEmail: null,
	status: true,
	deliveryRebate: null,
	regularRebate: null,
	supplierAddress: null,
	remark: null,
};

var vm = new Vue({
    el: '#supplierListDiv',
    data: {
        q: {
        	supplierName: '',
            status: '',
        },
        supplier: cloneJsonObj(entity_supplier_info),
    },
    methods: {
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.supplierName = '';
            this.q.status = '';
            this.reloadPage();
        },
        add: function() {
            this.resetSupplier();
            this.supplier.status = true; // 默认状态为启用
            var _self = this;
            layer.open({
                type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "新增供货商", area: '600px',
                content: jQuery("#supplierDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        url: basePath + "/admin/supplier/addOrUpdate",
                        data: _self.supplier,
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
            var supplierId = getSelectedRow();
            if (isBlank(supplierId)) {
                return;
            }
            this.resetSupplier();
            var _self = this;
            $.ajax({
                type: "GET",
                url: basePath + "/admin/supplier/getById",
                data: { 'id': supplierId },
                success: function(result) {
                    if (result.code == "00") {
                    	_self.supplier = result.supplier;
                        layer.open({
                            type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "编辑供货商", area: '600px',
                            content: jQuery("#supplierDiv"),
                            btn: ['提交', '取消'],
                            btn1: function(index) {
                            	delete _self.supplier.gmtCreate;
                            	delete _self.supplier.gmtUpdate;
                                $.ajax({
                                    url: basePath + "/admin/supplier/addOrUpdate",
                                    data: _self.supplier,
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
                        layer.alert("获取供货商失败:" + result.msg);
                    }
                }
            });
        },
        del: function() {
        	var supplierId = getSelectedRow();
        	if (isBlank(supplierId)) {
                return;
            }
        	var supplierName = getSelectedRowValue('supplierName');
        	var _self = this;
        	confirm("确定删除" + supplierName + "这个供货商吗?", function() {
                $.ajax({
                    url: basePath + "/admin/supplier/delById",
                    data: { 'id': supplierId },
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
        getPinyinCode: function() {
        	var _self = this;
        	$.ajax({
        		url: basePath + "/admin/goods/genePinyinShort",
        		data: {'goodsName':_self.supplier.supplierName},
        		success: function(result) {
        			if (result.code == "00") {
        				_self.supplier.pinyinCode = result.pinyin;
        			} else {
        				layer.alert("拼音码生成失败：" + result.msg);
        			}
        		}
        	});
        },
        importSupplier: function() {},
        exportSupplier: function() {},
        resetSupplier: function() {
            this.supplier = cloneJsonObj(entity_supplier_info);
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'supplierName': this.q.supplierName,
                    'status': this.q.status
                },
                page: page
            }).trigger("reloadGrid");
        },
    }
});