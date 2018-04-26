var goods_entity = { // 商品实体 cloneJsonObj(goods_entity)
	id : null,
	goodsImageId : null,
	goodsName : null,
	barCode : null,
	productNumber : null,
	pinyinCode : null,
	categoryName : null,
	goodsStatus : true, // 默认状态：启用
	goodsBrand : null,
	goodsColor : null,
	goodsSize : null,
	goodsTag : null,
	goodsStock : null,
	quantityUnit : null,
	stockUpperLimit : null,
	stockLowerLimit : null,
	lastImportPrice : null,
	averageImportPrice : null,
	salesPrice : null,
	tradePrice : null,
	vipPrice : null,
	isVipDiscount : true, // 默认折扣
	supplierName : null,
	productionDate : null,
	qualityGuaranteePeriod : null,
	isIntegral : null,
	royaltyType : null,
	isBooked : null,
	isGift : null,
	isWeigh : null,
	isFixedPrice : null,
	isTimeingPrice : null,
	isHidden : null,
	remark : null,
	gmtUpdate : null,
	gmtCreate : null
}

var goodsColor_entity = { // 颜色实体 cloneJsonObj(goodsColor_entity)
	id : null,
	color : null,
	gmtUpdate : null,
	gmtCreate : null
}

var goodsSize_entity = { // 尺码实体 cloneJsonObj(goodsSize_entity)
	id : null,
	sizeName : null,
	gmtUpdate : null,
	gmtCreate : null
}

var goodsUnit_entity = { // 单位实体 cloneJsonObj(goodsUnit_entity)
		id : null,
		unitName : null,
		gmtUpdate : null,
		gmtCreate : null
}

var goodsBrand_entity = { // 品牌实体 cloneJsonObj(goodsBrand_entity)
		id : null,
		brandName : null,
		gmtUpdate : null,
		gmtCreate : null
}

var goodsTag_entity = { // 标签实体 cloneJsonObj(goodsTag_entity)
		id : null,
		tagName : null,
		gmtUpdate : null,
		gmtCreate : null
}
