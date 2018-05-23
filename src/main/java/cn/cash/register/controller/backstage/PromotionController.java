/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.PromotionAddRequest;
import cn.cash.register.common.request.PromotionQueryRequest;
import cn.cash.register.dao.domain.PromotionDetail;
import cn.cash.register.dao.domain.PromotionGoodsDetail;
import cn.cash.register.service.PromotionGoodsDetailService;
import cn.cash.register.service.PromotionService;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 促销Controller
 * @author HuHui
 * @version $Id: PromotionController.java, v 0.1 2018年5月17日 下午5:22:12 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/admin/promotion")
public class PromotionController {
    private static final Logger         logger = LoggerFactory.getLogger(PromotionController.class);

    @Resource
    private PromotionService            promotionService;

    @Resource
    private PromotionGoodsDetailService promotionGoodsDetailService;

    /******************************促销信息相关**********************************/

    /**
     * 促销信息页面
     */
    @GetMapping
    public String list() {
        return "backstage/_promotion-list";
    }

    /**
     * 增加促销
     */
    @ResponseBody
    @PostMapping(value = "/addPromotionDetail")
    public ResultSet addPromotionDetail(PromotionAddRequest request) {
        LogUtil.info(logger, "收到增加促销请求,request={0}", request);
        Long ret = promotionService.add(request);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 删除促销
     */
    @ResponseBody
    @PostMapping(value = "/deletePromotion")
    public ResultSet deletePromotion(Long promotionId) {
        int ret = promotionService.delete(promotionId);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 修改促销
     */
    @ResponseBody
    @PostMapping(value = "/updatePromotion")
    public ResultSet updatePromotion(PromotionDetail item) {
        int ret = promotionService.update(item);
        return ResultSet.success().put("ret", ret);
    }

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

    /**
     * 分页查询促销
     */
    @ResponseBody
    @PostMapping(value = "/list")
    public ResultSet list(PromotionQueryRequest request) {
        PageInfo<PromotionDetail> ret = promotionService.list(request);
        return ResultSet.success().put("page", ret);
    }

    /**********************************促销商品相关***************************************/

    /**
     * 增加促销商品
     */
    @ResponseBody
    @PostMapping(value = "/addPromotionGoodsDetail")
    public ResultSet addPromotionGoodsDetail(Long promotionId, ArrayList<PromotionGoodsDetail> promotionGoodsList) {
        promotionGoodsDetailService.add(promotionId, promotionGoodsList);
        return ResultSet.success();
    }

    /**
     * 删除促销商品
     */
    @ResponseBody
    @PostMapping(value = "/deletePromotionGoodsDetail")
    public ResultSet deletePromotionGoodsDetail(Long promotionGoodsId) {
        int ret = promotionGoodsDetailService.delete(promotionGoodsId);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 增加促销商品
     */
    @ResponseBody
    @PostMapping(value = "/updatePromotionGoodsDetail")
    public ResultSet updatePromotionGoodsDetail(ArrayList<PromotionGoodsDetail> promotionGoodsList) {
        promotionGoodsDetailService.update(promotionGoodsList);
        return ResultSet.success();
    }

    /**
     * 根据促销id查询所有促销商品
     */
    @ResponseBody
    @PostMapping(value = "/queryByPromotionId")
    public ResultSet queryByPromotionId(Long promotionId) {
        List<PromotionGoodsDetail> promotionGoods = promotionGoodsDetailService.queryByPromotionId(promotionId);
        return ResultSet.success().put("promotionGoods", promotionGoods);
    }

}
