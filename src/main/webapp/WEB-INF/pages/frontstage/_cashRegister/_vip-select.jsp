<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 选择会员信息 -->
<div id="vipSelectDiv" style="display: none;">
	<form class="form-horizontal layerForm">
        <div class="form-group">
            <div class="col-xs-12">
            	<div class="input-group">
                    <input class="form-control" type="text" v-model="vip_keyword" placeholder="按会员号/手机号搜索">
                    <span class="input-group-btn">
                    	<button class="btn btn-default" type="button" @click="searchVipInfoInBox"><i class="fa fa-search"></i>&nbsp;搜索</button>
                    </span>
                </div>
            </div>
        </div>
        <div class="form-group">
        	<div class="col-xs-12">
        		<table class="table table-condensed" style="font-family: 'Microsoft YaHei';">
        			<thead>
        				<th></th>
        				<th>会员号</th>
        				<th>会员名</th>
        				<th>手机号</th>
        			</thead>
        			<tbody>
        				<tr v-for="member in keyword_search_vip_list">
        					<td><input type="radio" v-model="select_vip_id" :value="member.id"></td>
        					<td>{{member.memberNo}}</td>
        					<td>{{member.memberName}}</td>
        					<td>{{member.phone}}</td>
        				</tr>
        			</tbody>
        		</table>
        	</div>
        </div>
    </form>
</div>
<!-- /选择会员信息 -->