/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.dao.domain.PromotionDetail;
import cn.cash.register.dao.domain.PromotionGoodsDetail;
import cn.cash.register.service.PromotionGoodsDetailService;
import cn.cash.register.service.PromotionService;
import cn.cash.register.util.ResultSet;

/**
 * 促销Controller
 * @author HuHui
 * @version $Id: PromotionController.java, v 0.1 2018年5月17日 下午5:22:12 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/cashier/promotion")
public class CashierPromotionController {
    private static final Logger         logger = LoggerFactory.getLogger(CashierPromotionController.class);

    @Resource
    private PromotionService            promotionService;

    @Resource
    private PromotionGoodsDetailService promotionGoodsDetailService;

    /**
     * 根据id查询促销
     */
    @ResponseBody
    @PostMapping(value = "/queryPromotionById")
    public ResultSet queryPromotionById(Long id) {
        PromotionDetail ret = promotionService.queryById(id);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 返回商品促销信息
     */
    @ResponseBody
    @PostMapping(value = "/getPromotion")
    public ResultSet getPromotion(Long goodsId, Long promotionId) {
        PromotionGoodsDetail ret = promotionService.getPromotion(goodsId, promotionId);
        return ResultSet.success().put("ret", ret);
    }

}
