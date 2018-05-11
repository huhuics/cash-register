<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>会员制度</title>
</head>

<body style="width: 600px; margin-left:100px; font-family: 'Microsoft YaHei';">
    <div id="memberIntegralDiv" v-cloak>
        <!-- 更新会员积分规则 -->
        <h3>会员积分规则</h3>
        <div>
            <form class="form-horizontal layerForm">
            	<div class="form-group">
            		<label>积分方式</label>
            		<div class="input-group">
	                    <span class="input-group-addon">每消费</span>
	                    <input type="text" class="form-control" v-model="memberIntegral.integralValue">
	                    <span class="input-group-addon">元，积1分</span>
		            </div>
                </div>
            	<div class="form-group">
            		<label>清空积分</label>
                   	<div class="checkbox">
	                   	<label>
					      <input type="checkbox" v-model="memberIntegral.isClear"> 是否在每年的1月1号凌晨，重置会员的积分为0
					    </label>
	                </div>
                </div>
                <button type="button" class="btn btn-primary" @click="addOrUpdate">提交</button>
            </form>
        </div>
        <!-- /更新会员积分规则 -->
    </div>
    <script src="${ctx}/static/js/backstage/_member-integral.js"></script>
</body>

</html>