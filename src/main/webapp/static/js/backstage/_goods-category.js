var setting = {
    view: {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false
    },
    edit: {
        enable: true,
        editNameSelectAll: true,
        showRemoveBtn: showRemoveBtn,
        showRenameBtn: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeDrag: beforeDrag,
        beforeEditName: beforeEditName,
        beforeRemove: beforeRemove,
        beforeRename: beforeRename,
        onRemove: onRemove,
        onRename: onRename
    }
};

/**
 * 拖拽前
 */
function beforeDrag(treeId, treeNodes) {
    return false; // 不允许拖拽
}

/**
 * 编辑前
 */
function beforeEditName(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("categoryTree");
    zTree.selectNode(treeNode);
    zTree.editName(treeNode);
    return false;
}

/**
 * 删除前
 */
function beforeRemove(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("categoryTree");
    zTree.selectNode(treeNode);
    return true;
}

/**
 * 删除
 */
function onRemove(e, treeId, treeNode) {
    $.ajax({
        url: basePath + "/admin/goods/deleteGoodsCategory",
        data: { 'id': treeNode.id },
        success: function(result) {
            if (result.code == "00") {
                layer.msg('删除成功');
                refreshTree();
            } else {
                layer.alert(result.msg);
            }
        }
    });
}

/**
 * 编辑前
 */
function beforeRename(treeId, treeNode, newName, isCancel) {
    if (newName.length == 0) {
        var zTree = $.fn.zTree.getZTreeObj("categoryTree");
        zTree.cancelEditName();
        layer.alert("节点名称不能为空.");
        return false;
    }
    return true;
}

/**
 * 编辑
 */
function onRename(e, treeId, treeNode, isCancel) {
    $.ajax({
        url: basePath + "/admin/goods/updateGoodsCategory",
        data: { 'id': treeNode.id, 'categoryName': treeNode.name },
        success: function(result) {
            if (result.code == "00") {
                layer.msg('编辑成功');
                refreshTree();
            } else {
                layer.alert(result.msg);
            }
        }
    });
}

function showRemoveBtn(treeId, treeNode) {
    return treeNode.parentId != 0; // 根目录不显示删除按钮
}

function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +
        "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function() {
        var zTree = $.fn.zTree.getZTreeObj("categoryTree");
        var parentId = treeNode.id;
        layer.open({
            type: 1,
            skin: 'layui-layer-lan',
            title: "添加分类",
            area: '350px',
            shadeClose: false,
            content: jQuery("#goodsCategoryAddDiv"),
            btn: ['提交', '取消'],
            btn1: function(index) {
                var categoryName = $("#goodsCategoryAddInput").val();
                if (categoryName.length == 0) {
                    layer.alert('输入分类为空');
                    return;
                }
                $.ajax({
                    url: basePath + "/admin/goods/addGoodsCategory",
                    data: { 'categoryName': categoryName, 'parentId': parentId },
                    success: function(result) {
                        if (result.code == "00") {
                            layer.msg('添加成功');
                            $("#goodsCategoryAddInput").val('');
                            layer.close(index);
                            refreshTree();
                        } else {
                            layer.alert(result.msg);
                        }
                    }
                });
            }
        });
        return false;
    });
};

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
};

function refreshTree() {
    $.ajax({
        url: basePath + "/admin/goods/getGoodsCategoryTree",
        data: { 'parentCategoryId': 0 },
        success: function(result) {
            if (result.code == "00") {
                goodsCategoryNodes = result.tree;
                $.fn.zTree.init($("#categoryTree"), setting, goodsCategoryNodes);
                var zTree = $.fn.zTree.getZTreeObj("categoryTree");
                zTree.expandAll(true);
            } else {
                layer.alert(result.msg);
            }
        }
    });
}

$(document).ready(function() {
    refreshTree();
});