<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>商品报损</title>
</head>

<body style="overflow: hidden;">
    <div id="goodsLoseAddDiv" v-cloak>
        <div>
            <table class="table table-bordered table-hover" style="font-family: 'Microsoft YaHei';">
                <thead>
                    <tr>
                        <th>商品条码</th>
                        <th>商品名称</th>
                        <th>颜色</th>
                        <th>尺码</th>
                        <th>进货价</th>
                        <th>售价</th>
                        <th>报损量</th>
                        <th>报损价</th>
                        <th>报损原因</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in goods_list" style="height: 30px; line-height: 30px;">
                        <td>{{item.barCode}}</td>
                        <td>{{item.goodsName}}</td>
                        <td>{{item.goodsColor}}</td>
                        <td>{{item.goodsSize}}</td>
                        <td>{{item.averageImportPrice}}</td>
                        <td>{{item.salesPrice}}</td>
                        <td>
                            <input class="form-control" type="text" v-model="item.loseCount" @blur="editItemCountById(item.goodsId,item.loseCount)" @keyup.enter="editItemCountById(item.goodsId,item.loseCount)">
                        </td>
                        <td>
                            <!-- <input class="form-control" type="text" v-model="item.loseAmount" @blur="editItemLoseAmountById(item.goodsId,item.loseAmount)" @keyup.enter="editItemLoseAmountById(item.goodsId,item.loseAmount)"> -->
                            {{item.loseAmount}}
                        </td>
                        <td>
                            <input class="form-control" type="text" v-model="item.loseReason" @blur="editItemLoseReasonById(item.goodsId,item.loseReason)" @keyup.enter="editItemLoseReasonById(item.goodsId,item.loseReason)">
                        </td>
                        <td>
                            <button class="btn btn-danger" type="button" @click="deleteItemById(item.goodsId)">删除</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="operationDiv">
            <table class="table table-bordered" style="background-color: #EEE;">
                <tbody>
                    <tr>
                        <td class="col-xs-3">
                            <div>
                                <div class="col-xs-12">
                                    <div class="input-group">
                                        <input type="text" class="form-control toFocus" v-model="goods_keyword" @keyup.enter="searchGoods" placeholder="条码/拼音码/品名">
                                        <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" @click="searchGoods">确定</button>
                                </span>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td class="col-xs-3">
                            <div>
                                <div class="col-xs-12">
                                    <button class="btn btn-info" type="button">
                                        总报损 <span class="badge">{{summary_count}}&nbsp;</span>
                                    </button>
                                    <button class="btn btn-info" type="button">
                                        总进价 <span class="badge">{{summary_averageImportPrice}}&nbsp;元</span>
                                    </button>
                                    <button class="btn btn-info" type="button">
                                        总售价 <span class="badge">{{summary_salesPrice}}&nbsp;元</span>
                                    </button>
                                </div>
                            </div>
                        </td>
                        <td class="col-xs-4" style="vertical-align: middle;">
                            <div>
                                <button type="button" class="btn btn-danger btn-lg btn-block" @click="addGoodsLose">提交</button>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <%@ include file="./_goodsLose-add/_goods-select.jsp"%>
    </div>
    <script src="${ctx}/static/js/frontstage/_goodsLose-add.js"></script>
</body>

</html>