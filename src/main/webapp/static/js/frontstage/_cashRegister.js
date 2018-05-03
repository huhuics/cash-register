var goods_item = { // 商品元素
    goodsId: null,
    barCode: null,
    goodsName: null,
    totalAmount: null, // 商品原价，对应字段salesPrice，根据辉哥的命名而改名
    isVipDiscount: null,
    vipPrice: null,
    //--- 以上为商品表中对应数据
    goodsDiscount: null, // 实际折扣
    totalActualAmount: null, // 实际单价
    goodsCount: null, // 数量
    priceTotal: null, // 总价.数量*实际单价
};

var vip_info = {
    id: null,
    name: null,
    score: null,
    discount: null
};

var vm = new Vue({
    el: '#cashRegisterDiv',
    data: {
        goods_list: [], // 购买商品清单.goods_item的数组
        goods_item: cloneJsonObj(goods_item),
        goods_keyword: null,
        price_without_barcode: null,
        vip_keyword: null,
        vip_info: cloneJsonObj(vip_info),
        summary_count: 0,
        summary_price: 0,
    },
    methods: {
        searchGoods: function() { // 根据关键字查找商品加入清单
            if (isBlank(this.goods_keyword)) {
                layer.alert("请输入：条码/拼音码/品名");
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + "/cashier/trade/searchGoodsInfo",
                data: { 'keyword': _self.goods_keyword },
                success: function(result) {
                    if (result.code == "00") {
                        if (result.size == 0) {
                            layer.alert("没有找到相关商品");
                            return;
                        } else if (result.size == 1) { // 查到唯一商品，直接加入
                            _self.reset_goods_item();
                            _self.transferGoodsToItem(result.goods);
                            _self.addItemToGoodsList(1);
                            return;
                        } else if (result.size > 1) { // 查到多个商品，选择加入
                            // TODO
                        }
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        transferGoodsToItem: function(goods) { // 将goods转换为item,对除count与priceTotal以外的值赋值
            this.reset_goods_item(); // 重置

            this.goods_item.goodsId = goods.id;
            this.goods_item.barCode = goods.barCode;
            this.goods_item.goodsName = goods.goodsName;
            this.goods_item.totalAmount = goods.salesPrice.amount;
            this.goods_item.isVipDiscount = goods.isVipDiscount;
            this.goods_item.vipPrice = goods.vipPrice;

            if (isBlank(this.vip_info.discount)) { // 没有录入会员信息，原价
                this.goods_item.totalActualAmount = this.goods_item.totalAmount;
                this.goods_item.goodsDiscount = 100;
            } else if (!goods.isVipDiscount) { // 已经录入会员信息，但商品不参与折扣，会员价
                this.goods_item.totalActualAmount = this.goods_item.vipPrice.toFixed(2);
                this.goods_item.goodsDiscount = (this.goods_item.totalActualAmount / this.goods_item.totalAmount * 100).toFixed(2);
            } else {
                this.goods_item.totalActualAmount = (this.goods_item.totalAmount * this.vip_info.discount / 100).toFixed(2);
                this.goods_item.goodsDiscount = this.vip_info.discount;
            }
        },
        addItemToGoodsList: function(count) { // 将指定数量的item加入list
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == this.goods_item.goodsId) {
                    this.goods_list[i].goodsCount = this.goods_list[i].goodsCount * 1 + count * 1; // 商品已经存在于列表中时
                    this.summary();
                    return;
                }
            }
            this.goods_item.goodsCount = 1;
            this.goods_list.push(cloneJsonObj(this.goods_item)); // 商品不存在于列表中时，添加进列表
            this.summary();
        },
        getItemById: function(id) {
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == this.goods_item.goodsId) {
                    this.goods_item = this.goods_list[i];
                    return;
                }
            }
            layer.alert("系统错误");
        },
        editItemDiscountById: function(id, goodsDiscount) { // 修改折扣
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == this.goods_item.goodsId) {
                    this.goods_list[i].goodsDiscount = goodsDiscount;
                    this.goods_list[i].totalActualAmount = (this.goods_list[i].salesPrice * this.goods_list[i].goodsDiscount / 100).toFixed(2);
                    this.summary();
                    return;
                }
            }
            layer.alert("系统错误");
        },
        editItemCountById: function(id, count) { // 修改数量
            this.getItemById();
            this.addItemToGoodsList(count * 1 - this.goods_item.goodsCount * 1);
        },
        editItemPriceById: function(id, totalActualAmount) { // 修改实际价格
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == this.goods_item.goodsId) {
                    this.goods_list[i].totalActualAmount = totalActualAmount;
                    this.goods_list[i].goodsDiscount = (this.goods_list[i].totalActualAmount / this.goods_list[i].salesPrice * 100).toFixed(2);
                    this.summary();
                    return;
                }
            }
            layer.alert("系统错误");
        },
        deleteItemById: function(id) { // 删除item
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == this.goods_item.goodsId) {
                    this.goods_list.splice(i, 1);
                    this.summary();
                    return;
                }
            }
            layer.alert("系统错误");
        },
        searchVipInfo: function() {
            if (isBlank(this.vip_keyword)) {
                this.reset_vip_info();
                this._afterVipInfoChange();
                layer.alert("会员信息已清除");
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + "/cashier/trade/searchMemberInfo",
                data: { 'keyword': _self.vip_keyword },
                success: function(result) {
                    if (result.code == "00") {
                        if (result.size == 0) {
                            layer.alert("没有找到相关会员");
                            return;
                        } else if (result.size == 1) { // 查到唯一会员
                            _self.reset_vip_info();
                            _self.transferMemberInfoToVipInfo(result.member);
                            _self._afterVipInfoChange();
                            return;
                        } else if (result.size > 1) { // 查到多个会员，选择加入
                            // TODO
                        }
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        _afterVipInfoChange: function() { // 会员信息变化后的处理
            // TODO
        },
        transferMemberInfoToVipInfo: function(member) {
            this.vip_info.id = member.id;
            this.vip_info.name = member.memberName;
            this.vip_info.score = member.memberIntegral;
            this.vip_info.discount = member.memberDiscount;
        },
        checkout: function() { // 收款
            var _self = this;
            $.ajax({
                url: basePath + "/cashier/trade/checkout",
                data:  {
                	  goodsItemsJSONStr: JSON.stringify(_self.goods_list),
                	  payChenalsJSONStr: "[{chenal: '',amount: ''}]",
                },
                success: function(result) {
                    if (result.code == "00") {
                        layer.alert("收款成功！");
                        _self.reload();
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        reset_goods_item: function() {
            this.goods_item = cloneJsonObj(goods_item);
        },
        reset_vip_info: function() {
            this.vip_info = cloneJsonObj(vip_info);
        },
        summary: function() { // 计算小计与统计数据
            var _totalCount = 0;
            var _totalPrice = 0;
            for (var i = 0; i < this.goods_list.length; i++) {
                this.goods_list[i].priceTotal = this.goods_list[i].totalActualAmount * this.goods_list[i].goodsCount;
                _totalCount += this.goods_list[i].goodsCount;
                _totalPrice += this.goods_list[i].priceTotal;
            }
            this.summary_count = _totalCount;
            this.summary_price = _totalPrice;
        },
        reload: function() {
            this.goods_list = [];
            this.goods_item = cloneJsonObj(goods_item);
            this.goods_keyword = null;
            this.price_without_barcode = null;
            this.vip_keyword = null;
            this.vip_info = cloneJsonObj(vip_info);
            this.summary_count = 0;
            this.summary_price = 0;
        }
    }
});