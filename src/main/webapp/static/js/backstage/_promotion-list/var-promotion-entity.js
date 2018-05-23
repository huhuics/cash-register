var promotion_entity = { // 促销实体
	id: null,
	promotionName: null,
	promotionType: null,
	isMemberOnly: null,
	isMemberDiscountTwice: null,
	startTime: null,
	endTime: null,
	status: null,
}

var promotion_goods_entity = { // 促销商品实体
	id: null,
	promotionId: null,
	goodsId: null,
	barCode: null,
	categoryName: null,
	discount: null,
}

var promotion_goods_item = { // 促销商品元素
	id: null,
	promotionId: null,
	goodsId: null,
	barCode: null,
	categoryName: null,
	discount: null,
	// 以上为促销商品实体部分，以下为商品部分
	goodsName: null,
	salesPrice: null,
	// 以下为计算的折后价部分
	priceWithDiscount: null
}

