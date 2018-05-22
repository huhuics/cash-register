var vue_data = {
    q: {
        promotionType: '',
        status: '',
        promotionName: null
    },
    // -----↓↓↓添加/编辑促销数据↓↓↓-----
    promotion: cloneJsonObj(promotion_entity),

    // -----↓↓↓促销商品列表数据↓↓↓-----
    promotion_goods_item: cloneJsonObj(promotion_goods_item), // 详细的促销商品信息，包括商品信息
    promotion_goods_list_show: [], // 用于显示促销商品的列表，里面是一个个的promotion_goods_item
    
    promotion_goods: cloneJsonObj(promotion_goods_entity), // 促销商品信息
    promotion_goods_list_commit: [], // 经由列表转换而来的待提交的列表，里面是一个个的promotion_goods

    // -----↓↓↓商品选择列表数据↓↓↓-----
    goods_keyword: null,
    keyword_search_goods_list: [], // 搜索商品清单
    select_goods_id_list: [], // 选择要加入的商品列表
}