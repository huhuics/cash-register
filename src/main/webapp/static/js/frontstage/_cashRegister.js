var goods_item = { // 商品元素
    goodsId: null,
    barCode: null,
    goodsName: null,
    salesPrice: null, // 商品原价
    isVipDiscount: null,
    vipPrice: null,
    promotionId: null, // 商品促销信息id
    //--- 以上为商品表中对应数据
    promotionStatus: null,
    promotionIsMemberOnly: null,
    promotionIsMemberDiscountTwice: null,
    promotionDiscount: null,    
    //--- 以上为商品促销信息
    goodsDiscount: null, // 实际折扣
    actualAmount: null, // 实际单价
    goodsCount: null, // 数量
    totalAmount: null, // 原总价.数量*商品原单价
    totalActualAmount: null, // 总价.数量*实际单价
};

var vip_info = {
    id: null,
    name: null,
    score: null,
    discount: null,
    memberNo: null
};

var payChenals = {
    payChenal_cash: {
        chenal: 'cash',
        amount: 0
    },
    payChenal_unionpay: {
        chenal: 'unionpay',
        amount: 0
    },
    payChenal_alipay: {
        chenal: 'alipay',
        amount: 0
    },
    payChenal_wcpay: {
        chenal: 'wcpay',
        amount: 0
    },
    payChenal_balance: {
    	chenal: 'balance',
    	amount: 0
    },
}

var vm = new Vue({
    el: '#cashRegisterDiv',
    data: {
        goods_list: [], // 购买商品清单，goods_item的数组
        goods_item: cloneJsonObj(goods_item),
        goods_keyword: null,
        keyword_search_goods_list: [], // 搜索商品清单
        select_goods_id_list: [], // 选择要加入的商品列表
        price_without_barcode: null, // 无码商品价格
        noBarcodeIdNum: -1, // 无码商品id序列
        vip_keyword: null,
        keyword_search_vip_list: [], // 搜索会员清单
        select_vip_id: null, // 选择的会员id
        vip_info: cloneJsonObj(vip_info),
        summary_count: 0,
        summary_price: 0,
        payChenals: cloneJsonObj(payChenals),
    },
    computed: {
        change() {
            var amountSelected = 0;
            if (this.payChenals.payChenal_cash.amount > 0) {
                amountSelected += 1 * this.payChenals.payChenal_cash.amount;
            }
            if (this.payChenals.payChenal_unionpay.amount > 0) {
                amountSelected += 1 * this.payChenals.payChenal_unionpay.amount;
            }
            if (this.payChenals.payChenal_alipay.amount > 0) {
                amountSelected += 1 * this.payChenals.payChenal_alipay.amount;
            }
            if (this.payChenals.payChenal_wcpay.amount > 0) {
                amountSelected += 1 * this.payChenals.payChenal_wcpay.amount;
            }
            if (this.payChenals.payChenal_balance.amount > 0) {
            	amountSelected += 1 * this.payChenals.payChenal_balance.amount;
            }
            return amountSelected * 1 - this.summary_price * 1;
        },
    },
    methods: {
        searchGoods: function() { // 根据关键字查找商品加入清单
            if (isBlank(this.goods_keyword)) {
                layer.msg("请输入：条码/拼音码/品名");
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + "/cashier/trade/searchGoodsInfo",
                data: { 'keyword': _self.goods_keyword },
                success: function(result) {
                    if (result.code == "00") {
                        if (result.size == 0) {
                            layer.msg("没有找到相关商品");
                            return;
                        } else if (result.size == 1) { // 查到唯一商品，直接加入
                            _self.transferGoodsToItem(result.goodsInfos[0]);
                            _self.addItemToGoodsList(1);
                            return;
                        } else if (result.size > 1) { // 查到多个商品，选择加入
                            _self.keyword_search_goods_list = result.goodsInfos;
                            _self.select_goods_id_list = []; // 打开窗口前清空选择列表
                            layer.open({
                                type: 1,
                                skin: 'layui-layer-lan',
                                title: "搜索并选择商品",
                                area: '800px',
                                shadeClose: false,
                                content: jQuery("#goodsSelectDiv"),
                                btn: ['加入', '取消'],
                                btn1: function(index) {
                                    var _goodsList = _self.keyword_search_goods_list;
                                    for (var i = 0; i < _self.select_goods_id_list.length; i++) {
                                        _id = _self.select_goods_id_list[i];
                                        for (var j = 0; j < _goodsList.length; j++) {
                                            if (_goodsList[j].id == _id) {
                                                _self.transferGoodsToItem(_goodsList[j]);
                                                _self.addItemToGoodsList(1);
                                                break;
                                            }
                                        }
                                    }
                                    layer.close(index);
                                }
                            });
                        }
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        searchGoodsInBox: function() { // 根据关键字查找商品列表显示在待加入界面
            this.select_goods_id_list = [];
            if (isBlank(this.goods_keyword)) {
                this.keyword_search_goods_list = [];
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + "/cashier/trade/searchGoodsInfo",
                data: { 'keyword': _self.goods_keyword },
                success: function(result) {
                    if (result.code == "00") {
                        _self.keyword_search_goods_list = result.goodsInfos;
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        addNoBarcodeItem: function() {
            if (isBlank(this.price_without_barcode)) {
                layer.msg('请输入价格');
                return;
            }
            this.createNoBarcodeItem();
            this.addItemToGoodsList(1);

        },
        createNoBarcodeItem: function() { // 创建无码收银商品
            this.reset_goods_item(); // 重置

            this.goods_item.goodsId = this.noBarcodeIdNum--;
            this.goods_item.barCode = null;
            this.goods_item.goodsName = '无码商品';
            this.goods_item.salesPrice = this.price_without_barcode;
            this.goods_item.isVipDiscount = true;
            this.goods_item.vipPrice = null;

            if (isBlank(this.vip_info.discount)) { // 没有录入会员信息，原价
                this.goods_item.actualAmount = this.goods_item.salesPrice;
                this.goods_item.goodsDiscount = 100;
            } else {
                this.goods_item.actualAmount = (this.goods_item.salesPrice * this.vip_info.discount / 100).toFixed(2);
                this.goods_item.goodsDiscount = this.vip_info.discount;
            }
        },
        transferGoodsToItem: function(goods) { // 将goods转换为item,对除count、totalAmount、totalActualAmount以外的值赋值
            this.reset_goods_item(); // 重置

            this.goods_item.goodsId = goods.id;
            this.goods_item.barCode = goods.barCode;
            this.goods_item.goodsName = goods.goodsName;
            this.goods_item.salesPrice = goods.salesPrice.amount;
            this.goods_item.isVipDiscount = goods.isVipDiscount;
            this.goods_item.vipPrice = goods.vipPrice;
            this.goods_item.promotionId = goods.promotionId;
            
            if(!isBlank(this.goods_item.promotionId)){ // 该商品为促销商品
            	__log('商品促销id不为空，开始查找促销信息',this.goods_item.promotionId)
            	var _self = this;
	            $.ajax({
	                url: basePath + "/cashier/promotion/queryPromotionById",
	                async: false,
	                data: {
	                    id: _self.goods_item.promotionId
	                },
	                success: function(result1) {
	                    if (result1.code == "00") {
	                    	__log('查找到促销信息promotionDetail',result1.ret)
	                    	var _promotionDetail = result1.ret;
	                    	if(_promotionDetail.status) {
	                        	// 促销信息未过期
	                    		__log('促销信息未过期，开始查找促销商品信息')
	                    		$.ajax({
	            	                url: basePath + "/cashier/promotion/getPromotion",
	            	                async: false,
	            	                data: {
	            	                    goodsId: _self.goods_item.goodsId,
	            	                    promotionId: _promotionDetail.id,
	            	                },
	            	                success: function(result2) {
	            	                    if (result2.code == "00") {
	            	                    	__log('查找到促销商品信息', result2.ret)
	            	                    	layer.msg('查询到商品促销信息');
	            	                    	_self.goods_item.promotionStatus = true;
	            	                    	_self.goods_item.promotionIsMemberOnly = result1.ret.isMemberOnly;
	            	                    	_self.goods_item.promotionIsMemberDiscountTwice = result1.ret.isMemberDiscountTwice;
	            	                    	_self.goods_item.promotionDiscount = result2.ret.discount;
	            	                    } else {
	            	                        layer.alert("查询商品促销信息失败" + result2.msg);
	            	                    }
	            	                }
	            	            });
	                    	} else {
	                    		__log('促销信息过期')
	                    		_self.goods_item.promotionStatus = false;
	                    	}
	                    } else {
	                        layer.alert("查询促销信息失败:" + result1.msg);
	                    }
	                }
	            });
            }
            
            if(this.goods_item.promotionStatus) {
            	// 促销商品
            	if(this.goods_item.promotionIsMemberOnly == 'true') {
            		// 会员专享促销
                	if (isBlank(this.vip_info.discount)) { // 没有录入会员信息，原价
                        this.goods_item.actualAmount = this.goods_item.salesPrice;
                        this.goods_item.goodsDiscount = 100;
                    } else { // 已经录入会员信息
                    	if (!goods.isVipDiscount) { // 享受会员价
	                        this.goods_item.actualAmount = this.goods_item.vipPrice.toFixed(2);
	                        this.goods_item.goodsDiscount = (this.goods_item.actualAmount / this.goods_item.salesPrice * 100).toFixed(2);
	                    } else { // 享受会员折扣
	                        this.goods_item.actualAmount = (this.goods_item.salesPrice * this.vip_info.discount / 100).toFixed(2);
	                        this.goods_item.goodsDiscount = this.vip_info.discount;
	                    }
                    	if(this.goods_item.promotionIsMemberDiscountTwice) {
                    		// 会员折上折
                    		this.goods_item.actualAmount = 1 * this.goods_item.actualAmount * this.goods_item.promotionDiscount / 100;
                    		this.goods_item.goodsDiscount = (this.goods_item.actualAmount / this.goods_item.salesPrice * 100).toFixed(2);
                    	}
                    }
            	} else {
            		// 非会员专享促销
            		if (isBlank(this.vip_info.discount)) { // 没有录入会员信息，促销折扣
                        this.goods_item.actualAmount = 1 * this.goods_item.salesPrice * this.goods_item.promotionDiscount / 100;
                		this.goods_item.goodsDiscount = (this.goods_item.actualAmount / this.goods_item.salesPrice * 100).toFixed(2);
                    } else { // 已经录入会员信息
                    	if (!goods.isVipDiscount) { // 享受会员价
	                        this.goods_item.actualAmount = this.goods_item.vipPrice.toFixed(2);
	                        this.goods_item.goodsDiscount = (this.goods_item.actualAmount / this.goods_item.salesPrice * 100).toFixed(2);
	                    } else { // 享受会员折扣
	                        this.goods_item.actualAmount = (this.goods_item.salesPrice * this.vip_info.discount / 100).toFixed(2);
	                        this.goods_item.goodsDiscount = this.vip_info.discount;
	                    }
                    	if(this.goods_item.promotionIsMemberDiscountTwice) {
                    		// 会员折上折
                    		this.goods_item.actualAmount = 1 * this.goods_item.actualAmount * this.goods_item.promotionDiscount / 100;
                    		this.goods_item.goodsDiscount = (this.goods_item.actualAmount / this.goods_item.salesPrice * 100).toFixed(2);
                    	}
                    }
            	}
            } else {
            	// 非促销商品
            	if (isBlank(this.vip_info.discount)) { // 没有录入会员信息，原价
                    this.goods_item.actualAmount = this.goods_item.salesPrice;
                    this.goods_item.goodsDiscount = 100;
                } else if (!goods.isVipDiscount) { // 已经录入会员信息，享受会员价
                    this.goods_item.actualAmount = this.goods_item.vipPrice.toFixed(2);
                    this.goods_item.goodsDiscount = (this.goods_item.actualAmount / this.goods_item.salesPrice * 100).toFixed(2);
                } else { // 已经录入会员信息，享受会员折扣
                    this.goods_item.actualAmount = (this.goods_item.salesPrice * this.vip_info.discount / 100).toFixed(2);
                    this.goods_item.goodsDiscount = this.vip_info.discount;
                }
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
                if (this.goods_list[i].goodsId == id) {
                    this.goods_list[i].goodsDiscount = goodsDiscount;
                    this.goods_list[i].actualAmount = (this.goods_list[i].salesPrice * this.goods_list[i].goodsDiscount / 100).toFixed(2);
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
        editItemPriceById: function(id, actualAmount) { // 修改实际价格
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == id) {
                    this.goods_list[i].actualAmount = actualAmount;
                    this.goods_list[i].goodsDiscount = (this.goods_list[i].actualAmount / this.goods_list[i].salesPrice * 100).toFixed(2);
                    this.summary();
                    return;
                }
            }
            layer.alert("系统错误");
        },
        deleteItemById: function(id) { // 删除item
            for (var i = 0; i < this.goods_list.length; i++) {
                if (this.goods_list[i].goodsId == id) {
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
                layer.msg("会员信息已清除");
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + "/cashier/trade/searchMemberInfo",
                data: { 'keyword': _self.vip_keyword },
                success: function(result) {
                    if (result.code == "00") {
                        if (result.size == 0) {
                            layer.msg("没有找到相关会员");
                            return;
                        } else if (result.size == 1) { // 查到唯一会员
                            _self.transferMemberInfoToVipInfo(result.memberInfos[0]);
                            _self._afterVipInfoChange();
                            return;
                        } else if (result.size > 1) { // 查到多个会员，选择加入
                            _self.keyword_search_vip_list = result.memberInfos;
                            layer.open({
                                type: 1,
                                skin: 'layui-layer-lan',
                                title: "搜索并选择会员",
                                area: '800px',
                                shadeClose: false,
                                content: jQuery("#vipSelectDiv"),
                                btn: ['确认', '取消'],
                                btn1: function(index) {
                                    if (isBlank(_self.select_vip_id)) {
                                        layer.msg("未选择会员信息！");
                                        return;
                                    }
                                    var _memberList = _self.keyword_search_vip_list;
                                    for (var j = 0; j < _memberList.length; j++) {
                                        if (_memberList[j].id == _self.select_vip_id) {
                                            _self.transferMemberInfoToVipInfo(_memberList[j]);
                                            _self._afterVipInfoChange();
                                            layer.close(index);
                                            _self.select_vip_id = null;
                                            return;
                                        }
                                    }
                                    layer.alert("系统错误");
                                }
                            });
                        }
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        searchVipInfoInBox: function() {
            this.select_vip_id = null;
            if (isBlank(this.vip_keyword)) {
                this.keyword_search_vip_list = [];
                return;
            }
            var _self = this;
            $.ajax({
                url: basePath + "/cashier/trade/searchMemberInfo",
                data: { 'keyword': _self.vip_keyword },
                success: function(result) {
                    if (result.code == "00") {
                        this.keyword_search_vip_list = result.memberInfos;
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        },
        _afterVipInfoChange: function() { // 会员信息变化后的处理
            if (isBlank(this.vip_info.discount)) { // 会员信息被清空时
            	__log('清空会员信息')
                for (var i = 0; i < this.goods_list.length; i++) {
                    var _item = this.goods_list[i];
                    if (_item.promotionStatus && !_item.promotionIsMemberOnly) {
                        // 促销商品且非会员专享促销，商品恢复促销价
                    	__log('促销商品且非会员专享促销，商品恢复促销价')
                        _item.actualAmount = 1 * _item.salesPrice * _item.promotionDiscount / 100;
                        _item.goodsDiscount = (_item.actualAmount / _item.salesPrice * 100).toFixed(2);
                    } else {
                        // 商品恢复原价
                    	__log('商品恢复原价')
                        _item.actualAmount = _item.salesPrice;
                        _item.goodsDiscount = 100;
                    }
                }
            } else { // 会员信息被添加时
            	__log('添加会员信息')
                for (var i = 0; i < this.goods_list.length; i++) {
                    var _item = this.goods_list[i];
                    if (_item.promotionStatus) {
                        // 促销商品
                    	__log('该商品为促销商品')
                        if (_item.promotionIsMemberOnly) {
                            // 会员专享促销
                        	__log('该商品为会员专享促销商品')
                            if (!goods.isVipDiscount) { // 享受会员价
                            	__log('享受会员价')
                                _item.actualAmount = _item.vipPrice.toFixed(2);
                                _item.goodsDiscount = (_item.actualAmount / _item.salesPrice * 100).toFixed(2);
                            } else { // 享受会员折扣
                            	__log('享受会员折扣')
                                _item.actualAmount = (_item.salesPrice * this.vip_info.discount / 100).toFixed(2);
                                _item.goodsDiscount = this.vip_info.discount;
                            }
                            if (_item.promotionIsMemberDiscountTwice) {
                                // 会员折上折
                            	__log('会员折上折！')
                                _item.actualAmount = 1 * _item.actualAmount * _item.promotionDiscount / 100;
                                _item.goodsDiscount = (_item.actualAmount / _item.salesPrice * 100).toFixed(2);
                            }
                        } else {
                            // 非会员专享促销
                        	__log('该商品不是会员专享促销商品')
                            if (!_item.isVipDiscount) { // 享受会员价
                            	__log('享受会员价')
                                _item.actualAmount = _item.vipPrice.toFixed(2);
                                _item.goodsDiscount = (_item.actualAmount / _item.salesPrice * 100).toFixed(2);
                            } else { // 享受会员折扣
                            	__log('享受会员折扣')
                                _item.actualAmount = (_item.salesPrice * this.vip_info.discount / 100).toFixed(2);
                                _item.goodsDiscount = this.vip_info.discount;
                            }
                            if (_item.promotionIsMemberDiscountTwice) {
                                // 会员折上折
                            	__log('会员折上折！')
                                _item.actualAmount = 1 * _item.actualAmount * _item.promotionDiscount / 100;
                                _item.goodsDiscount = (_item.actualAmount / _item.salesPrice * 100).toFixed(2);
                            }
                        }
                    } else {
                        // 非促销商品
                    	__log('该商品不是促销商品')
                        if (!_item.isVipDiscount) { // 已经录入会员信息，享受会员价
                            _item.actualAmount = _item.vipPrice.toFixed(2);
                            _item.goodsDiscount = (_item.actualAmount / _item.salesPrice * 100).toFixed(2);
                        } else { // 已经录入会员信息，享受会员折扣
                            _item.actualAmount = (_item.salesPrice * this.vip_info.discount / 100).toFixed(2);
                            _item.goodsDiscount = this.vip_info.discount;
                        }
                    }
                }
            }
            this.summary();

        },
        transferMemberInfoToVipInfo: function(member) {
            this.reset_vip_info();
            this.vip_info.id = member.id;
            this.vip_info.name = member.memberName;
            this.vip_info.score = member.memberIntegral;
            this.vip_info.discount = member.memberDiscount;
            this.vip_info.memberNo = member.memberNo;
        },
        toCheckout: function() { // 去收款
        	this.reset_payChenals();
            var _self = this;
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: "收款",
                area: '800px',
                shadeClose: false,
                content: jQuery("#checkoutDiv"),
                btn: ['确定', '取消'],
                btn1: function(index) {
                	var _msg = _self.checkPayChenals();
                    if (!isBlank(_msg)) {
                        layer.msg(_msg);
                        return;
                    }
                    if(_self.change < 0) {
                    	layer.msg('收款金额不足！');
                        return;
                    }
                    _self.payChenals.payChenal_cash.amount -= _self.change; // 所有找零都用现金，该值可能会为负
                    $.ajax({
                        url: basePath + "/cashier/trade/checkout",
                        data: {
                            goodsItemsJSONStr: JSON.stringify(_self.goods_list),
                            payChenalsJSONStr: _self.payChenalsStr(),
                            memberId: _self.vip_info.id,
                            memberNo: _self.vip_info.memberNo,
                            change: _self.change
                        },
                        success: function(result) {
                            if (result.code == "00") {
                                layer.msg("收款成功！");
                                layer.close(index);
                                _self.reload();
                            } else {
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            });
        },
        checkout_all_cash: function() {
        	this.reset_payChenals();
        	this.payChenals.payChenal_cash.amount = this.summary_price;
        },
        checkout_all_alipay: function() {
        	this.reset_payChenals();
        	this.payChenals.payChenal_alipay.amount = this.summary_price;
        },
        checkout_all_unionpay: function() {
        	this.reset_payChenals();
        	this.payChenals.payChenal_unionpay.amount = this.summary_price;
        },
        checkout_all_wcpay: function() {
        	this.reset_payChenals();
        	this.payChenals.payChenal_wcpay.amount = this.summary_price;
        },
        checkout_all_balance: function() {
        	this.reset_payChenals();
        	this.payChenals.payChenal_balance.amount = this.summary_price;
        },
        checkPayChenals: function() {
            var countSelected = 0;
            if (this.payChenals.payChenal_cash.amount > 0) {
                countSelected++;
            }
            if (this.payChenals.payChenal_unionpay.amount > 0) {
                countSelected++;
            }
            if (this.payChenals.payChenal_alipay.amount > 0) {
                countSelected++;
            }
            if (this.payChenals.payChenal_wcpay.amount > 0) {
                countSelected++;
            }
            if (this.payChenals.payChenal_balance.amount > 0) {
            	countSelected++;
            }
            if (countSelected > 2) {
                return '付款通道不能多于2个';
            }
            return null;
        },
        payChenalsStr: function() {
            var str = '[';
            str += '{chenal: "cash",amount: "' + this.payChenals.payChenal_cash.amount + '"}';
            if (this.payChenals.payChenal_unionpay.amount > 0) {
                str += ',{chenal: "unionpay",amount: "' + this.payChenals.payChenal_cash.amount + '"}';
            }
            if (this.payChenals.payChenal_alipay.amount > 0) {
                str += ',{chenal: "alipay",amount: "' + this.payChenals.payChenal_cash.amount + '"}';
            }
            if (this.payChenals.payChenal_wcpay.amount > 0) {
                str += ',{chenal: "wcpay",amount: "' + this.payChenals.payChenal_cash.amount + '"}';
            }
            if (this.payChenals.payChenal_balance.amount > 0) {
            	str += ',{chenal: "balance",amount: "' + this.payChenals.payChenal_balance.amount + '"}';
            }
            str += ']'
            return str;
        },
        reset_goods_item: function() {
            this.goods_item = cloneJsonObj(goods_item);
        },
        reset_vip_info: function() {
            this.vip_info = cloneJsonObj(vip_info);
        },
        reset_payChenals: function() {
        	this.payChenals = cloneJsonObj(payChenals);
        },
        summary: function() { // 计算小计与统计数据
            var _totalCount = 0;
            var _totalPrice = 0;
            for (var i = 0; i < this.goods_list.length; i++) {
            	this.goods_list[i].totalAmount = this.goods_list[i].salesPrice * this.goods_list[i].goodsCount;
                this.goods_list[i].totalActualAmount = this.goods_list[i].actualAmount * this.goods_list[i].goodsCount;
                _totalCount += this.goods_list[i].goodsCount;
                _totalPrice += this.goods_list[i].totalActualAmount;
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
            this.focus();
        },
        focus: function() { // 设置页面焦点
        	$(".toFocus").focus();
        }
    },
    mounted: function() {
    	this.focus();
    }
});