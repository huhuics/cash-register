/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.PromotionQueryRequest;
import cn.cash.register.dao.domain.DiscountGoodsDetail;
import cn.cash.register.dao.domain.PromotionDetail;
import cn.cash.register.service.PromotionService;
import cn.cash.register.util.ResultSet;

/**
 * 促销Controller
 * @author HuHui
 * @version $Id: PromotionController.java, v 0.1 2018年5月17日 下午5:22:12 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/admin/promotion")
public class PromotionController {

    @Resource
    private PromotionService promotionService;

    /**
     * 增加促销
     * Map的key为商品id
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public ResultSet add(PromotionDetail item, List<Long> goodsIds, List<DiscountGoodsDetail> discountGoodsList) {
        Long ret = promotionService.add(item, goodsIds, discountGoodsList);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 删除促销
     */
    @ResponseBody
    @PostMapping(value = "/delete")
    public ResultSet delete(Long id) {
        int ret = promotionService.delete(id);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 修改促销
     */
    @ResponseBody
    @PostMapping(value = "/update")
    public ResultSet update(PromotionDetail item) {
        int ret = promotionService.update(item);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 根据id查询促销
     */
    @ResponseBody
    @PostMapping(value = "/queryById")
    public ResultSet queryById(Long id) {
        PromotionDetail ret = promotionService.queryById(id);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 返回商品促销信息
     */
    @ResponseBody
    @PostMapping(value = "/getPromotion")
    public ResultSet getPromotion(Long goodsId, Long promotionId) {
        DiscountGoodsDetail ret = promotionService.getPromotion(goodsId, promotionId);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 分页查询促销
     */
    @ResponseBody
    @PostMapping(value = "/list")
    public ResultSet list(PromotionQueryRequest request) {
        PageInfo<PromotionDetail> ret = promotionService.list(request);
        return ResultSet.success().put("ret", ret);
    }

}
