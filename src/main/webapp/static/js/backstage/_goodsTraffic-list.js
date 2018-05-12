$(function() {
    $("#jqGrid").jqGrid({
        url: basePath + '/admin/traffic/queryPage',
        datatype: "json",
        colModel: [
        	{ label: '货流ID', name: 'id', hidden: true, key: true },
            { label: '货流单号', name: 'trafficNo', index: 'traffic_No', width: 80 },
            { label: '货流类型', name: 'trafficType', index: 'traffic_Type', width: 80 },
            { label: '商品名称', name: 'goodsName', index: 'goods_Name', width: 80 },
            { label: '商品条码', name: 'barCode', index: 'bar_Code', width: 80 },
            { label: '商品颜色', name: 'goodsColor', index: 'goods_Color', width: 80 },
            { label: '商品尺寸', name: 'goodsSize', index: 'goods_Size', width: 80 },
            { label: '供货商名称', name: 'supplierName', index: 'supplier_Name', width: 80 },
            { label: '商品库存', name: 'goodsStock', index: 'goods_Stock', width: 80 },
            { label: '进货量', name: 'inCount', index: 'in_Count', width: 80 },
            { label: '进货价', name: 'inAmount.amount', index: 'in_Amount', width: 80 },
            { label: '进货赠送量', name: 'freeCount', index: 'free_Count', width: 80 },
            { label: '预付款', name: 'advancePaymentAmount.amount', index: 'advance_Payment_Amount', width: 80 },
            { label: '单位', name: 'quantityUnit', index: 'quantity_Unit', width: 80 },
            { label: '出库价格类型', name: 'outPriceType', index: 'out_Price_Type', width: 80 },
            { label: '出库价', name: 'outAmount.amount', index: 'out_Amount', width: 80 },
            { label: '出库量', name: 'outCount', index: 'out_Count', width: 80 },
            { label: '小计', name: 'totalAmount.amount', index: 'total_Amount', width: 80 },
            { label: '操作员编号', name: 'operatorNo', index: 'operator_No', width: 80 },
            { label: '备注', name: 'remark', index: 'remark', width: 80 },
            {
                label: '状态',
                name: 'status',
                index: 'status',
                sortable: false,
                width: 80,
                formatter: function(value, options, row) {
                    if (value == '1') {
                        return '<span class="label label-success">已完成</span>';
                    }
                    if (value == '0') {
                        return '<span class="label label-info">处理中</span>';
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

var entity_inTrafficRequest = {
	goodsId: null,
	status: null,
	goodsName: null,
	barCode: null,
	goodsColor: null,
	goodsSize: null,
	supplierName: null,
	goodsStock: null,
	inCount: null,
	inAmount: null,
	freeCount: null,
	quantityUnit: null,
	totalAmount: null,
	advancePaymentAmount: null,
	operatorNo: null,
	remark: null
};
var entity_outTrafficRequest = {
	goodsId: null,
	trafficType: null,
	goodsName: null,
	barCode: null,
	goodsColor: null,
	goodsSize: null,
	supplierName: null,
	goodsStock: null,
	quantityUnit: null,
	outPriceType: null,
	outAmount: null,
	outCount: null,
	totalAmount: null,
	operatorNo: null,
	remark: null
};

var vm = new Vue({
    el: '#goodsTrafficListDiv',
    data: {
        q: {
        	trafficType: '',
        	trafficNo: null,
        	createTimeUp: null,
        	createTimeDown: null,
        },
        inTraffic: cloneJsonObj(entity_inTrafficRequest),
        outTraffic: cloneJsonObj(entity_outTrafficRequest),
    },
    methods: {
        search: function() {
            this.reloadPage();
        },
        resetSearch: function() {
            this.q.trafficType = '';
            this.q.trafficNo = null;
            this.q.createTimeUp = null;
            this.q.createTimeDown = null;
            this.reloadPage();
        },
        addInTraffic: function() {
            this.resetInTraffic();
            var _self = this;
            layer.open({
                type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "进货", area: '600px',
                content: jQuery("#addInTrafficDiv"),
                btn: ['提交', '取消'],
                btn1: function(index) {
                    $.ajax({
                        url: basePath + "/admin/traffic/addInTraffic",
                        data: _self.inTraffic,
                        success: function(result) {
                            if (result.code == "00") {
                                layer.msg('进货成功');
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
        addOutTraffic: function() {
        	this.resetOutTraffic();
        	var _self = this;
        	layer.open({
        		type: 1, skin: 'layui-layer-lan', shadeClose: false, title: "出库", area: '600px',
        		content: jQuery("#addOutTrafficDiv"),
        		btn: ['提交', '取消'],
        		btn1: function(index) {
        			$.ajax({
        				url: basePath + "/admin/traffic/addOutTraffic",
        				data: _self.outTraffic,
        				success: function(result) {
        					if (result.code == "00") {
        						layer.msg('出库成功');
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
        resetInTraffic: function() {
            this.inTraffic = cloneJsonObj(entity_inTrafficRequest);
        },
        resetOutTraffic: function() {
        	this.outTraffic = cloneJsonObj(entity_outTrafficRequest);
        },
        reloadPage: function() {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            var _self = this;
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'trafficType': _self.q.trafficType,
                    'trafficNo': _self.q.trafficNo,
                    'createTimeUp': _self.q.createTimeUp,
                    'createTimeDown': _self.q.createTimeDown
                },
                page: page
            }).trigger("reloadGrid");
        },
        datetimepickerLoad: function() {
			$('#datetimepickerAfter').datetimepicker({
				language : 'zh-CN',
				todayHighlight : 1,
				format: 'yyyy-mm-dd hh:ii:ss',
			    autoclose: true,
			    minView: 0,
			    minuteStep: 1
			});
			$('#datetimepickerAfter').datetimepicker().on('hide', function(ev) {
				var value = $("#datetimepickerAfter").val();
				vm.q.createTimeDown = value;
			});
			$('#datetimepickerBefore').datetimepicker({
				language : 'zh-CN',
				todayHighlight : 1,
				format: 'yyyy-mm-dd hh:ii:ss',
			    autoclose: true,
			    minView: 0,
			    minuteStep: 1
			});
			$('#datetimepickerBefore').datetimepicker().on('hide', function(ev) {
				var value = $("#datetimepickerBefore").val();
				vm.q.createTimeUp = value;
			});
		},
		rangeToday: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getDayStart(_now));
			this.q.createTimeUp = dateFormater(getDayEnd(_now));
		},
		rangeYesterday: function() {
			var _now = new Date();
			var _yesterday = getLastDay(_now);
			this.q.createTimeDown = dateFormater(getDayStart(_yesterday));
			this.q.createTimeUp = dateFormater(getDayEnd(_yesterday));
		},
		rangeDayBeforeYesterday: function() {
			var _now = new Date();
			var _dayBeforeYesterday = getLastDay(getLastDay(_now));
			this.q.createTimeDown = dateFormater(getDayStart(_dayBeforeYesterday));
			this.q.createTimeUp = dateFormater(getDayEnd(_dayBeforeYesterday));
		},
		rangeWeek: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getWeekStart(_now));
			this.q.createTimeUp = dateFormater(getWeekEnd(_now));
		},
		rangeLastWeek: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getLastWeekStart(_now));
			this.q.createTimeUp = dateFormater(getLastWeekEnd(_now));
		},
		range7Days: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(get7DaysBefore(_now));
			this.q.createTimeUp = dateFormater(getDayEnd(_now));
		},
		rangeMonth: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getMonthStart(_now));
			this.q.createTimeUp = dateFormater(getMonthEnd(_now));
		},
		rangeLastMonth: function() {
			var _now = new Date();
			this.q.createTimeDown = dateFormater(getMonthStart(getLastMonthEnd(_now)));
			this.q.createTimeUp = dateFormater(getLastMonthEnd(_now));
		},
    },
    mounted: function() {
	    this.datetimepickerLoad();
	}
});