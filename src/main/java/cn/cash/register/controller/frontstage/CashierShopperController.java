/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.request.ShopperInfoQueryRequest;
import cn.cash.register.dao.domain.ShopperInfo;
import cn.cash.register.service.ShopperInfoService;
import cn.cash.register.util.ResultSet;

/**
 * 导购员相关Controller
 * @author HuHui
 * @version $Id: ShopperController.java, v 0.1 2018年5月3日 下午2:58:09 HuHui Exp $
 */
@Controller
@RequestMapping("/cashier/shopper")
public class CashierShopperController {

    private static final Logger logger = LoggerFactory.getLogger(CashierShopperController.class);

    @Resource
    private ShopperInfoService  shopperInfoService;

    /**
     * 根据条件查询所有导购员资料
     */
    @ResponseBody
    @RequestMapping(value = "queryAll", method = RequestMethod.POST)
    public ResultSet queryAll(ShopperInfoQueryRequest request) {
        List<ShopperInfo> infos = shopperInfoService.queryAll(request);
        return ResultSet.success().put("infos", infos);
    }

}
