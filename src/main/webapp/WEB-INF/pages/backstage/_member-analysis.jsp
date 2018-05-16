<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>会员分析</title>
</head>

<body style="font-family: 'Microsoft YaHei';">
    <div id="memberAnalysisDiv" v-cloak>
        <div id="chart" style="height:500px;margin: 0 auto;"></div>
    </div>
    <script src="${ctx}/static/js/backstage/_member-analysis.js"></script>
</body>

</html>