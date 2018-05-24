/*
 * 列表页jqgrid加载参数
 */
var option = {
    url: basePath + '/admin/promotion/list',
    datatype: "json",
    mtype: "post",
    colModel: [{ label: '促销ID', name: 'id', hidden: true, key: true },
        { label: '促销名称', name: 'promotionName', index: 'promotion_Name', width: 160 },
        { label: '促销类型', name: 'promotionType', index: 'promotion_Type', width: 150,
        	formatter: function(value, options, row) {
                if (value == 'discount') {
                    return '打折促销';
                } else {
                	return value;
                }
            }
        },
        { label: '是否会员专享', name: 'isMemberOnly', index: 'is_Member_Only', width: 80,
        	formatter: function(value, options, row) {
                if (value == 'true') {
                    return '是';
                } else {
                	return '否';
                }
            }
        },
        { label: '会员是否折上折', name: 'isMemberDiscountTwice', index: 'is_Member_Discount_Twice', width: 80,
        	formatter: function(value, options, row) {
                if (value == 'true') {
                    return '是';
                } else {
                	return '否';
                }
            }
        },
        { label: '开始日期', name: 'startTime', index: 'start_Time', width: 150,
            formatter: "date",
            formatoptions: {
                srcformat: 'Y-m-d',
                newformat: 'Y-m-d'
            }
        },
        { label: '结束日期', name: 'endTime', index: 'end_Time', width: 150,
        	formatter: "date",
        	formatoptions: {
        		srcformat: 'Y-m-d',
        		newformat: 'Y-m-d'
        	}
        },
        { label: '状态', name: 'status', index: 'status', width: 80,
        	formatter: function(value, options, row) {
                if (value == 'true' || value) {
                    return '<span class="label label-success">有效</span>';
                } else if (value == 'false' || !value){
                	return '<span class="label label-info">已失效</span>';
                } else {
                	return '未知状态：' + value;
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
}