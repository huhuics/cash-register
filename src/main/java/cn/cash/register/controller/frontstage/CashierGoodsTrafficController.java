/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.request.InTrafficRequest;
import cn.cash.register.service.GoodsTrafficService;
import cn.cash.register.util.ResultSet;

/**
 * 货流Controller
 * 
 * @author 51
 * @version $Id: CashierGoodsTrafficController.java, v 0.1 2018年5月24日 下午9:39:17 51 Exp $
 */
@Controller
@RequestMapping("/cashier/traffic")
public class CashierGoodsTrafficController {

    @Resource
    private GoodsTrafficService trafficService;

    /**
     * 商品进货
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addInTraffic")
    public ResultSet addInTraffic(InTrafficRequest request) {
        request.validate();
        Long id = trafficService.addInTraffic(request);
        return ResultSet.success().put("id", id);
    }

}
