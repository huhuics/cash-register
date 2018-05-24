$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/cashier/member/list',
        datatype: "json",
        colModel: [
        	{ label: '会员ID', name: 'id', hidden: true, key: true },
            { label: '编号', name: 'memberNo', index: 'member_No', width: 80 },
            { label: '姓名', name: 'memberName', index: 'member_Name', width: 80 },
            { label: '等级', name: 'memberRank', index: 'member_Rank', width: 80 },
            { label: '折扣', name: 'memberDiscount', index: 'member_Discount', width: 80 },
            { label: '积分', name: 'memberIntegral', index: 'member_Integral', width: 80 },
            { label: '电话', name: 'phone', index: 'phone', width: 80 },
            { label: '密码', name: 'password', index: 'password', sortable: false, width: 80 },
            { label: '生日', name: 'birthday', index: 'birthday', width: 80 },
            { label: '允许赊账', name: 'isOnCredit', index: 'is_On_Credit', width: 80,
            	formatter: function(value, options, row) {
                    if (value) {
                        return '<span class="label label-success">允许</span>';
                    } else {
                    	return '<span class="label label-info">不允许</span>';
                    }
                }
            },
            { label: 'QQ', name: 'qqNo', index: 'qq_No', width: 80 },
            { label: '邮箱', name: 'email', index: 'email', width: 80 },
            { label: '地址', name: 'address', index: 'address', width: 80 },
            { label: '导购员', name: 'shopperName', index: 'shopper_Name', width: 80 },
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

var entity_member_info = {
	id: null,
	status: null,
	memberNo: null,
	memberName: null,
	memberRank: '',
	memberDiscount: null,
	memberIntegral: 0, // 积分默认为0
	phone: null,
	password: null,
	birthday: null,
	isOnCredit: null,
	qqNo: null,
	email: null,
	address: null,
	shopperName: '',
	remark: null
};

var vm = new Vue({
    el: '#memberListDiv',
    data: {
        q: {
        	memberRank: '',
        	shopperName: '',
            status: '',
            keyword: null,
        },
        member: cloneJsonObj(entity_member_info),
        memberRanks: [], // 全部会员等级
        shoppers: [], // 全部导购员
    },
    methods: {
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.memberRank = '';
            this.q.shopperName = '';
            this.q.status = '';
            this.q.keyword = null;
            this.reloadPage();
        },
        add: function() {
            this.resetMember();
            this.member.status = true; // 默认状态为启用
            this.member.isOnCredit = false; // 默认不允许赊账
            var _self = this;
            layer.open({
                type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "新增会员", area: '600px',
                content: jQuery("#memberDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        url: basePath + "/cashier/member/addOrUpdate",
                        data: _self.member,
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
            var memberId = getSelectedRow();
            if (isBlank(memberId)) {
                return;
            }
            this.resetMember();
            var _self = this;
            $.ajax({
                type: "GET",
                url: basePath + "/cashier/member/getById",
                data: { 'id': memberId },
                success: function(result) {
                    if (result.code == "00") {
                    	_self.member = result.memberInfo;
                        layer.open({
                            type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "编辑会员", area: '600px',
                            content: jQuery("#memberDiv"),
                            btn: ['提交', '取消'],
                            btn1: function(index) {
                            	delete _self.member.gmtCreate;
                            	delete _self.member.gmtUpdate;
                                $.ajax({
                                    url: basePath + "/cashier/member/addOrUpdate",
                                    data: _self.member,
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
                        layer.alert("获取会员资料失败:" + result.msg);
                    }
                }
            });
        },
        del: function() {
        	var memberId = getSelectedRow();
        	if (isBlank(memberId)) {
                return;
            }
        	var memberName = getSelectedRowValue('memberName');
        	var _self = this;
        	confirm("确定删除" + memberName + "这个会员吗?", function() {
                $.ajax({
                    url: basePath + "/cashier/member/delById",
                    data: { 'id': memberId },
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
        importMember: function() {},
        exportMember: function() {},
        resetMember: function() {
            this.member = cloneJsonObj(entity_member_info);
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'memberRank': this.q.memberRank,
                    'shopperName': this.q.shopperName,
                    'keyword': this.q.keyword,
                    'status': this.q.status
                },
                page: page
            }).trigger("reloadGrid");
        },
        loadMemberRanks: function() { // 加载所有会员等级列表
        	var _self = this;
        	$.ajax({
        		type: "GET",
        		url: basePath + "/cashier/member/rank/listAll",
        		success: function(result) {
        			if (result.code == "00") {
        				_self.memberRanks = result.memberRanks;
        			} else {
        				layer.alert("加载会员等级列表出错" + result.msg);
        			}
        		}
        	});
        },
        loadShoppers: function() { // 加载所有导购员列表
        	var _self = this;
        	$.ajax({
        		url: basePath + "/cashier/shopper/queryAll",
        		data: {status: true},
        		success: function(result) {
        			if (result.code == "00") {
        				_self.shoppers = result.infos;
        			} else {
        				layer.alert("加载导购员列表出错" + result.msg);
        			}
        		}
        	});
        },
    },
    mounted: function() {
    	// 加载全部等级
    	this.loadMemberRanks();
    	// 加载全部导购员
    	this.loadShoppers();
    }
});