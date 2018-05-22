var option = {
    url: basePath + '/admin/promotion/list',
    datatype: "json",
    mtype: "post",
    colModel: [{ label: '促销ID', name: 'id', hidden: true, key: true },
        { label: '促销名称', name: 'promotionName', index: 'promotion_Name', width: 160 },
        { label: '促销类型', name: 'promotionType', index: 'promotion_Type', width: 150 },
        { label: '是否会员专享', name: 'isMemberOnly', index: 'is_Member_Only', width: 80 },
        { label: '会员是否折上折', name: 'isMemberDiscountTwice', index: 'is_Member_Discount_Twice', width: 80 },
        {
            label: '开始日期',
            name: 'startTime',
            index: 'start_Time',
            width: 150,
            formatter: "date",
            formatoptions: {
                srcformat: 'Y-m-d',
                newformat: 'Y-m-d'
            }
        },
        {
        	label: '结束日期',
        	name: 'endTime',
        	index: 'end_Time',
        	width: 150,
        	formatter: "date",
        	formatoptions: {
        		srcformat: 'Y-m-d',
        		newformat: 'Y-m-d'
        	}
        },
        { label: '状态', name: 'status', index: 'status', width: 80 },
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
}

var promotion_entity = {
	id: null,
	promotionName: null,
	promotionType: null,
	isMemberOnly: null,
	isMemberDiscountTwice: null,
	startTime: null,
	endTime: null,
	status: null,
	detail: null
};

$(function() {
    $("#jqGrid").jqGrid(option);
});

var vm = new Vue({
    el: '#promotionListDiv',
    data: {
        q: {
        	promotionType: '',
        	status: '',
        	promotionName: null
        },
        promotion: cloneJsonObj(promotion_entity),
        promotionGoodsIds: [],
        discountGoodsMap: null,
        
    },
    methods: {
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.promotionType = '';
            this.q.status = '';
            this.q.promotionName = null;
            this.reloadPage();
        },
        resetPromotion: function() {
        	this.promotion = cloneJsonObj(promotion_entity);
        	this.promotionGoodsIds = [];
            this.discountGoodsMap = null;
        },
        add: function() {
            this.resetPromotion();
            var _self = this;
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: "新增促销活动",
                area: '650px',
                shadeClose: false,
                content: jQuery("#promotionDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    if (isBlank(_self.promotion.promotionName)) {
                        layer.alert("必填项为空！");
                        return;
                    }
                    $.ajax({
                        url: basePath + "/admin/promotion/add",
                        data: {
                        	'item': _self.promotion,
                        	'goodsIds': _self.promotionGoodsIds,
                        	'discountGoodsMap': _self.discountGoodsMap
                        },
                        success: function(result) {
                            if (result.code == "00") {
                                layer.msg('添加成功');
                                layer.close(index);
                                _self.resetPromotion();
                                _self.reloadPage();
                            } else {
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            });
        },
        update: function() {
            var promotionId = getSelectedRow();
            if (isBlank(promotionId)) {
                return;
            }
            this.resetPromotion();
            var _self = this;
            $.ajax({
                url: basePath + "/admin/promotion/queryById",
                data: { 'id': promotionId },
                success: function(result) {
                    if (result.code == "00") {
                        _self.promotion = result.ret;
                        layer.open({
                            type: 1,
                            skin: 'layui-layer-lan',
                            title: "编辑促销信息",
                            area: '650px',
                            shadeClose: false,
                            content: jQuery("#promotionDiv"),
                            btn: ['提交', '取消'],
                            btn1: function(index) {
                            	if (isBlank(_self.promotion.promotionName)) {
                                    layer.alert("必填项为空！");
                                    return;
                                }
                                $.ajax({
                                    url: basePath + "/admin/promotion/update",
                                    data: {
                                    	'item': _self.promotion,
                                    },
                                    success: function(result) {
                                        if (result.code == "00") {
                                            layer.msg('编辑成功');
                                            layer.close(index);
                                            _self.resetPromotion();
                                            _self.reloadPage();
                                        } else {
                                            layer.alert(result.msg);
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        layer.alert("获取促销信息失败:" + result.msg);
                    }
                }
            });

        },
        del: function() {
            var promotionId = getSelectedRow();
            if (isBlank(promotionId)) {
                return;
            }
            var _self = this;
            confirm("确定删除这个促销吗?", function() {
                $.ajax({
                    url: basePath + "/admin/promotion/delete",
                    data: { 'id': promotionId },
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
        _editPromotionGoods: function() {
        	
        },
        searchGoods: function() {
        	
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            var _self = this;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'promotionType': _self.q.promotionType,
                    'status': _self.q.status,
                    'promotionName': _self.q.promotionName,
                },
                page: page
            }).trigger("reloadGrid");
        },
    }
});