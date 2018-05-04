//jqGrid的配置信息
//$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

function getRootPath_web() {
    var curWwwPath = window.document.location.href; // 当前完整网址
    var pathName = window.document.location.pathname; // 主机地址之后的目录
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos); // 主机地址，如： http://localhost:8080
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1); // 带"/"的项目名
    return (localhostPaht + projectName);
}

var basePath = getRootPath_web();

//全局配置
$.ajaxSetup({
    dataType: "json",
    type: "POST",
    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
    cache: false
});

//重写alert
window.alert = function(msg, callback) {
    parent.layer.alert(msg, function(index) {
        parent.layer.close(index);
        if (typeof(callback) === "function") {
            callback("ok");
        }
    });
}

//重写confirm式样框
window.confirm = function(msg, callback) {
    parent.layer.confirm(msg, { btn: ['确定', '取消'] },
        function(index) { //确定事件
            parent.layer.close(index);
            if (typeof(callback) === "function") {
                callback("ok");
            }
        });
}

function layerOpen_skinlan_0btn(title, area, id) {
	layer.open({
		type: 1, skin: 'layui-layer-lan', title: title, area: area, shadeClose: false,
		content: jQuery(id)
	});
}
function layerOpen_skinlan_1btn(title, area, id) {
	layer.open({
		type: 1, skin: 'layui-layer-lan', title: title, area: area, shadeClose: false, btn: ['确定'],
		content: jQuery(id)
	});
}

//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        alert("只能选择一条记录");
        return;
    }

    return selectedIDs[0];
}

//获取某一条记录的某列值
function getSelectedRowValue(colName) {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        alert("只能选择一条记录");
        return;
    }

    return grid.jqGrid('getRowData', selectedIDs[0])[colName];
}

//单选情况下某一条记录的某列值
function getSingleSelectedRowValue(colName) {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }
    return grid.jqGrid('getRowData', rowKey)[colName];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请至少选择一条记录");
        return;
    }

    return grid.getGridParam("selarrrow");
}

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}

//克隆JSON对象
function cloneJsonObj(obj) {
	return JSON.parse(JSON.stringify(obj));
}

//控制台打印日志
function __log(msg, obj) {
	var str = msg;
	if(!isBlank(obj)){
		str += ',obj: [' + JSON.stringify(obj) + ']';
	}
	console.log(str);
}

/** 
 * 数字前面补0
 * @param num 数字
 * @param length 总长度
 * @returns
 */
function PrefixInteger(num, length) {
    return (Array(length).join(0) + num).slice(-length);
}