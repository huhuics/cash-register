/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsTrafficQueryRequest;
import cn.cash.register.common.request.InTrafficRequest;
import cn.cash.register.common.request.OutTrafficRequest;
import cn.cash.register.dao.domain.GoodsTraffic;
import cn.cash.register.service.GoodsTrafficService;
import cn.cash.register.util.ResultSet;

/**
 * 货流Controller
 * @author HuHui
 * @version $Id: GoodsTrafficController.java, v 0.1 2018年4月25日 下午9:28:47 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/traffic")
public class GoodsTrafficController {

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

    /**
     * 商品出库
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addOutTraffic")
    public ResultSet addOutTraffic(OutTrafficRequest request) {
        request.validate();
        Long id = trafficService.addOutTraffic(request);
        return ResultSet.success().put("id", id);
    }

    /**
     * 根据货流主键id查询详情
     */
    @ResponseBody
    @RequestMapping(value = "/queryById")
    public ResultSet queryById(Long id) {
        GoodsTraffic traffic = trafficService.queryById(id);
        return ResultSet.success().put("traffic", traffic);
    }

    /**
     * 货流分页查询
     */
    @ResponseBody
    @RequestMapping(value = "/queryList")
    public ResultSet queryList(GoodsTrafficQueryRequest request) {
        PageInfo<GoodsTraffic> traffics = trafficService.queryList(request);
        return ResultSet.success().put("traffics", traffics);
    }
}
