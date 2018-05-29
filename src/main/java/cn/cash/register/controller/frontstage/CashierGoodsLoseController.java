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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.request.GoodsLoseInfoAddRequest;
import cn.cash.register.dao.domain.GoodsLoseReason;
import cn.cash.register.service.GoodsLoseService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 商品报损Controller
 * @author HuHui
 * @version $Id: GoodsLoseController.java, v 0.1 2018年4月26日 下午7:47:47 HuHui Exp $
 */
@Controller
@RequestMapping("/cashier/goodsLose")
public class CashierGoodsLoseController {

    private static final Logger logger = LoggerFactory.getLogger(CashierGoodsLoseController.class);

    @Resource
    private GoodsLoseService    loseService;

    /**
     * 增加报损原因
     * @return  主键id
     */
    @ResponseBody
    @RequestMapping("/addLoseReason")
    public ResultSet addLoseReason(String reason) {
        AssertUtil.assertNotBlank(reason, "报损原因不能为空");
        Long id = loseService.addLoseReason(reason);
        return ResultSet.success().put("id", id);
    }

    /**
     * 根据id删除报损原因
     */
    @ResponseBody
    @RequestMapping("/deleteLoseReason")
    public ResultSet deleteLoseReason(Long id) {
        int result = loseService.deleteLoseReason(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 修改报损原因
     */
    @ResponseBody
    @RequestMapping("/updateLoseReason")
    public ResultSet updateLoseReason(GoodsLoseReason reason) {
        AssertUtil.assertNotNull(reason.getId(), "id不能为空");
        AssertUtil.assertNotBlank(reason.getReason(), "报损原因不能为空");
        int result = loseService.updateLoseReason(reason);
        return ResultSet.success().put("result", result);
    }

    /**
     * 查询所有报损原因
     */
    @ResponseBody
    @GetMapping("/queryAllReason")
    public ResultSet queryAllReason() {
        List<GoodsLoseReason> loseReasons = loseService.queryAllReason();
        return ResultSet.success().put("loseReasons", loseReasons);
    }

    /**
     * 增加商品报损记录
     * @return  主键id
     */
    @ResponseBody
    @RequestMapping("/addLoseInfo")
    public ResultSet addLoseInfo(GoodsLoseInfoAddRequest request) {
        LogUtil.info(logger, "收到增加商品报损记录请求,request={0}", request);

        AssertUtil.assertNotBlank(request.getLoseItemsStr(), "报损商品不能为空");

        Long id = loseService.addLoseInfo(request);
        return ResultSet.success().put("id", id);
    }

}
