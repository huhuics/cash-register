$(function() {
    $("#jqGrid")
        .jqGrid({
            url: '../backstage/goods/list',
            datatype: "json",
            colModel: [{
                    label: '订单ID',
                    name: 'id',
                    hidden: true,
                    key: true
                },
                {
                    label: '商品名称',
                    name: 'tradeNo',
                    index: 'trade_No',
                    width: 200
                },
                {
                    label: '客户号',
                    name: 'customerNo',
                    index: 'customer_No',
                    width: 150
                },
                {
                    label: '账户号',
                    name: 'accountNo',
                    index: 'account_No',
                    width: 150
                },
                {
                    label: '币种',
                    name: 'currencyType',
                    index: 'currency_Type',
                    width: 60
                },
                {
                    label: '金额',
                    name: 'amount.amount',
                    index: 'amount',
                    sortable: false,
                    width: 100,
                    formatter: "number",
                    formatoptions: {
                        thousandsSeparator: ','
                    }
                },
                {
                    label: '手续费',
                    name: 'feeAmount.amount',
                    index: 'fee_Amount',
                    sortable: false,
                    width: 100,
                    formatter: "number",
                    formatoptions: {
                        thousandsSeparator: ','
                    }
                },
                {
                    label: '提现银行及账户信息',
                    name: 'receiveBankAccountInfo',
                    index: 'receive_Bank_Account_Info',
                    formatter: function(value, options, row) {
                        var jsonvalue = JSON.parse(value);
                        var bankInfo = jsonvalue.bank_name +
                            '(' + jsonvalue.bank_no + ')';
                        var accountInfo = jsonvalue.account_name +
                            '(' +
                            jsonvalue.account_no +
                            ')';
                        return '<i class="fa fa-bank"></i>&nbsp;' +
                            bankInfo +
                            '<br>' +
                            '<i class="fa fa-credit-card"></i>&nbsp;' +
                            accountInfo;
                    },
                    width: 500
                },
                {
                    label: '状态',
                    name: 'status',
                    index: 'status',
                    width: 80,
                    formatter: function(value, options, row) {
                        if (value == 'PROCESSING') {
                            return vm.STATUS_VALUE_ENUM.PROCESSING;
                        }
                        if (value == 'SUCCESS') {
                            return vm.STATUS_VALUE_ENUM.SUCCESS;
                        }
                        if (value == 'FAIL') {
                            return vm.STATUS_VALUE_ENUM.FAIL;
                        }
                        return value;
                    }
                }, {
                    label: '状态变更时间',
                    name: 'gmtStatus',
                    index: 'gmt_Status',
                    width: 200,
                    formatter: "date",
                    formatoptions: {
                        srcformat: 'Y-m-d H:i:s',
                        newformat: 'Y-m-d H:i:s'
                    }
                }
            ],
            viewrecords: true,
            height: "100%",
            width: "100%",
            shrinkToFit: false,
            rowNum: 10,
            rowList: [10, 30, 50],
            rownumbers: true,
            rownumWidth: 45,
            autowidth: true,
            multiselect: true,
            sortname: "gmt_Status",
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
            accountNo: null,
            tradeNo: null,
            customerNo: null,
            status: null
        },
        showList: true,
        confirmAmountFirst: null,
        successWithdrawRequest: {
            tradeNo: null,
            currencyTypeConfirm: null,
            amountConfirm: null,
            authUsername: null,
            authPassword: null
        },
        denyWithdrawRequest: {
            tradeNo: null,
            currencyTypeConfirm: null,
            amountConfirm: null,
            authUsername: null,
            authPassword: null
        },
        STATUS_VALUE_ENUM: {
            PROCESSING: '<span class="label label-info">处理中</span>',
            SUCCESS: '<span class="label label-success">处理成功</span>',
            FAIL: '<span class="label label-danger">处理失败</span>'
        }
    },
    methods: {
        query: function() {
            vm.reload();
        },
        refresh: function() {
            vm.q.accountNo = null;
            vm.q.tradeNo = null;
            vm.q.customerNo = null;
            vm.q.status = '';
            $(".selectpicker").selectpicker('val', '');
            vm.reload();
        },
        successWithdrawConfirm: function() {
            var status = getSelectedRowValue('status');
            if (status == null) {
                return;
            }
            if (status != vm.STATUS_VALUE_ENUM.PROCESSING) {
                alert("只能选择状态为处理中的订单");
                return;
            }
            vm.confirmAmountFirst = null;
            vm.successWithdrawRequest.tradeNo = getSelectedRowValue('tradeNo');
            vm.successWithdrawRequest.currencyTypeConfirm = getSelectedRowValue('currencyType');
            vm.successWithdrawRequest.amountConfirm = null;
            vm.successWithdrawRequest.authUsername = null;
            vm.successWithdrawRequest.authPassword = null;
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "成功提现金额确认",
                area: ['600px', '225px'],
                shadeClose: false,
                content: jQuery("#confirmSuccessWithdraw1"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    if (vm.confirmAmountFirst == null || vm.confirmAmountFirst == '') {
                        layer.alert('请输入确认金额');
                    } else {
                        layer.close(index);
                        vm.successWithdraw();
                    }
                }
            });
        },
        successWithdraw: function() {
            layer
                .open({
                    type: 1,
                    skin: 'layui-layer-molv',
                    title: "成功提现授权",
                    area: ['780px', '350px'],
                    shadeClose: false,
                    content: jQuery("#confirmSuccessWithdraw2"),
                    btn: ['确认提交', '取消'],
                    btn1: function(index) {
                        if (vm.confirmAmountFirst != vm.successWithdrawRequest.amountConfirm) {
                            layer.alert('两次确认金额不一致');
                            layer.close(index);
                        } else {
                            $.ajax({
                                type: "POST",
                                url: "../order/withdraw2bank/success",
                                data: vm.successWithdrawRequest,
                                dataType: "json",
                                success: function(result) {
                                    if (result.code == "00") {
                                        layer.alert('操作成功');
                                        layer.close(index);
                                    } else {
                                        layer
                                            .alert(result.msg);
                                    }
                                    vm.reload();
                                }
                            });
                        }
                    }
                });

        },
        denyWithdrawConfirm: function() {
            var status = getSelectedRowValue('status');
            if (status == null) {
                return;
            }
            if (status != vm.STATUS_VALUE_ENUM.PROCESSING) {
                alert("只能选择状态为处理中的订单");
                return;
            }
            vm.confirmAmountFirst = null;
            vm.denyWithdrawRequest.tradeNo = getSelectedRowValue('tradeNo');
            vm.denyWithdrawRequest.currencyTypeConfirm = getSelectedRowValue('currencyType');
            vm.denyWithdrawRequest.amountConfirm = null;
            vm.denyWithdrawRequest.authUsername = null;
            vm.denyWithdrawRequest.authPassword = null;
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "不予提现金额确认",
                area: ['600px', '225px'],
                shadeClose: false,
                content: jQuery("#confirmDenyWithdraw1"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    if (vm.confirmAmountFirst == null || vm.confirmAmountFirst == '') {
                        layer.alert('请输入确认金额');
                    } else {
                        layer.close(index);
                        vm.denyWithdraw();
                    }
                }
            });
        },
        denyWithdraw: function() {
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: '不予提现授权',
                area: ['780px', '350px'],
                shadeClose: false,
                content: jQuery("#confirmDenyWithdraw2"),
                btn: ['确认提交', '取消'],
                btn1: function(index) {
                    if (vm.confirmAmountFirst != vm.denyWithdrawRequest.amountConfirm) {
                        layer.alert('两次确认金额不一致');
                        layer.close(index);
                    } else {
                        $.ajax({
                            type: "POST",
                            url: "../order/withdraw2bank/deny",
                            data: vm.denyWithdrawRequest,
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
                }
            });
        },
        reload: function() {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'accountNo': vm.q.accountNo,
                    'customerNo': vm.q.customerNo,
                    'tradeNo': vm.q.tradeNo,
                    'status': vm.q.status
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});