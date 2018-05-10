$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/member/rank/list',
        datatype: "json",
        colModel: [
        	{ label: '会员等级ID', name: 'id', hidden: true, key: true },
            { label: '等级名称', name: 'rankTitle', index: 'rank_Title', width: 80 },
            { label: '优惠折扣', name: 'discount', index: 'discount', width: 80 },
            { label: '是否积分', name: 'isIntegral', index: 'is_Integral', width: 80,
            	formatter: function(value, options, row) {
                    if (value) {
                        return '<span class="label label-success">积分</span>';
                    } else {
                    	return '<span class="label label-info">不积分</span>';
                    }
                }
            },
            { label: '自动升级规则', name: 'isAutoUpgrade', index: 'is_Auto_Upgrade', width: 80,
            	formatter: function(value, options, row) {
                    if (value) {
                        return '积分需达到' + row['integralToUpgrade'] + '分，等级有效期: ' + (row['rankPeriod']=='0'?'永久':'1年');
                    } else {
                    	return '无';
                    }
                }
            },
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

var entity_member_rank_info = {
	id: null,
	rankTitle: null,
	discount: null,
	isIntegral: null,
	isAutoUpgrade: null,
	integralToUpgrade: null,
	rankPeriod: null,
	prepaidCardNo: null
};

var vm = new Vue({
    el: '#memberRankListDiv',
    data: {
        memberRank: cloneJsonObj(entity_member_rank_info),
    },
    methods: {
        add: function() {
            this.resetMemberRank();
            this.memberRank.isIntegral = true; // 默认积分
            this.memberRank.isAutoUpgrade = false; // 默认关闭自动升级
            var _self = this;
            layer.open({
                type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "新增会员等级", area: '400px',
                content: jQuery("#memberRankDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        url: basePath + "/admin/member/rank/addOrUpdate",
                        data: _self.memberRank,
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
            var memberRankId = getSelectedRow();
            if (isBlank(memberRankId)) {
                return;
            }
            this.resetMemberRank();
            var _self = this;
            $.ajax({
                url: basePath + "/admin/member/rank/getById",
                data: { 'id': memberRankId },
                success: function(result) {
                    if (result.code == "00") {
                    	_self.memberRank = result.memberRank;
                        layer.open({
                            type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "编辑会员等级", area: '400px',
                            content: jQuery("#memberRankDiv"),
                            btn: ['提交', '取消'],
                            btn1: function(index) {
                            	delete _self.memberRank.gmtCreate;
                            	delete _self.memberRank.gmtUpdate;
                                $.ajax({
                                    url: basePath + "/admin/member/rank/addOrUpdate",
                                    data: _self.memberRank,
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
                        layer.alert("获取会员等级信息失败:" + result.msg);
                    }
                }
            });
        },
        del: function() {
        	// 注释掉批量删除，只能一次删除一条记录
//        	var memberRankIds = getSelectedRows();
//        	if (isBlank(memberRankIds) || memberRankIds.length < 1) {
//                return;
//            }
//        	var _self = this;
//        	confirm("确定删除这" + memberRankIds.length + "个会员等级吗?", function() {
//                $.ajax({
//                    url: basePath + "/admin/member/rank/delete",
//                    data: { 'idStr': memberRankIds + '' },
//                    success: function(result) {
//                        if (result.code == "00") {
//                            layer.msg('删除成功');
//                            _self.reloadPage();
//                        } else {
//                            layer.alert(result.msg);
//                        }
//                    }
//                });
//            });
        	var memberRankId = getSelectedRow();
        	if (isBlank(memberRankId)) {
                return;
            }
        	var rankTitle = getSelectedRowValue('rankTitle');
        	var _self = this;
        	confirm("确定删除" + rankTitle + "这个会员等级吗?", function() {
                $.ajax({
                    url: basePath + "/admin/member/rank/delete",
                    data: { 'id': memberRankId },
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
        resetMemberRank: function() {
            this.memberRank = cloneJsonObj(entity_member_rank_info);
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});