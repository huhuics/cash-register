<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商品分类</title>
    <style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</head>

<body>
    <div id="goodsCategoryDiv">
        <ul id="categoryTree" class="ztree"></ul>
    </div>
    
    <script src="${ctx}/static/js/backstage/_goods-category.js"></script>
    
</body>

</html>