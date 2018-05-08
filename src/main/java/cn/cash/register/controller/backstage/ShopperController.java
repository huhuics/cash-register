/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.AchievementQueryRequest;
import cn.cash.register.common.request.SellerInfoQueryRequest;
import cn.cash.register.common.request.ShopperInfoQueryRequest;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.dao.domain.ShopperInfo;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.service.ShopperInfoService;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 导购员相关Controller
 * @author HuHui
 * @version $Id: ShopperController.java, v 0.1 2018年5月3日 下午2:58:09 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/shopper")
public class ShopperController {

    private static final Logger logger = LoggerFactory.getLogger(ShopperController.class);

    @Resource
    private ShopperInfoService  shopperInfoService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "backstage/_shopper-list";
    }

    /**
     * 导购员列表
     * 
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultSet queryList(SellerInfoQueryRequest request) {
        LogUtil.info(logger, "[Controller]收到#导购员列表查询#请求,request={0}", request);

        PageInfo<SellerInfo> pageInfo = null; // TODO

        LogUtil.info(logger, "[Controller]#导购员列表查询#请求处理,pageInfo={0}", pageInfo);
        return ResultSet.success().put("page", pageInfo);
    }

    /**
     * 增加或更新导购员
     */
    @ResponseBody
    @RequestMapping(value = "addOrUpdate", method = RequestMethod.POST)
    public ResultSet add(ShopperInfo info) {
        if (info.getId() == null) {
            shopperInfoService.add(info);
        } else {
            shopperInfoService.update(info);
        }
        return ResultSet.success();
    }

    /**
     * 删除导购员
     */
    @ResponseBody
    @RequestMapping(value = "deleteById", method = RequestMethod.POST)
    public ResultSet deleteById(Long id) {
        int ret = shopperInfoService.delete(id);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 根据Id查询导购员
     */
    @ResponseBody
    @RequestMapping(value = "queryById", method = RequestMethod.POST)
    public ResultSet queryById(Long id) {
        ShopperInfo info = shopperInfoService.queryById(id);
        return ResultSet.success().put("info", info);
    }

    /**
     * 根据条件查询导购员资料
     */
    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResultSet query(ShopperInfoQueryRequest request) {
        List<ShopperInfo> infos = shopperInfoService.query(request);
        return ResultSet.success().put("infos", infos);
    }

    /**
     * 查询导购员业绩
     */
    @ResponseBody
    @RequestMapping(value = "queryAchievement", method = RequestMethod.POST)
    public ResultSet queryAchievement(AchievementQueryRequest request) {
        PageInfo<TradeGoodsDetail> ret = shopperInfoService.queryAchievement(request);
        return ResultSet.success().put("ret", ret);
    }

}
