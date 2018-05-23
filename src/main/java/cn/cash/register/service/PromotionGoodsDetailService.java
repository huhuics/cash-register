/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.dao.domain.PromotionGoodsDetail;

/**
 * 促销商品详情接口
 * @author HuHui
 * @version $Id: PromotionGoodsDetailService.java, v 0.1 2018年5月21日 下午9:30:19 HuHui Exp $
 */
public interface PromotionGoodsDetailService {

    /**
     * 增加或修改
     * @param details
     */
    void addOrUpdate(List<PromotionGoodsDetail> details);

    /**
     * 删除促销商品
     */
    int delete(Long id);

    /**
     * 根据促销id查询所有促销商品
     */
    List<PromotionGoodsDetail> queryByPromotionId(Long promotionId);

}
