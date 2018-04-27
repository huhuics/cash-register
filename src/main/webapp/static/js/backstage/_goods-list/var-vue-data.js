var vue_data = {
	q: {
    	keyword: null,
        goodsStatus: '',
        categoryName: '',
        goodsBrand: '',
        supplierName: '',
        goodsTag: ''
    },
	goods_categorys: [], // 全部分类
    goods_brands: [], // 全部品牌
    goods_suppliers: [], // 全部供货商
    goods_tags: [], // 全部标签
    goods_units: [], // 全部单位
    goods_colors: [], // 全部颜色
    goods_sizes: [], // 全部尺码
	select_goods_tags: [], // 所选择的标签
	select_goods_colors: [], // 所选择的颜色
	select_goods_sizes: [], // 所选择的尺码
    switches: {
    	displayImageUpload: false, // 显示图片上传框开关(仅编辑时显示)
        colorSize: false, // 颜色尺码开关
        prodNumSame: false, // 货号和条码一致开关
    },
	goods: cloneJsonObj(goods_entity), // 商品实体
    goodsColor: cloneJsonObj(goodsColor_entity), // 颜色实体
    goodsSize: cloneJsonObj(goodsSize_entity), // 尺码实体
    goodsUnit: cloneJsonObj(goodsUnit_entity), // 单位实体
    goodsBrand: cloneJsonObj(goodsBrand_entity), // 品牌实体
    goodsTag: cloneJsonObj(goodsTag_entity), // 标签实体
    batchEdit: {
    	royaltyType: '', // 提成方式，0~5
    	royaltyValue: '', // 提成方式对应值
    	
    },
};