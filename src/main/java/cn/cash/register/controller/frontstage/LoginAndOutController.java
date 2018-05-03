/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.service.ExchangeJobService;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.ResultSet;

/**
 * 登录登出Controller
 * @author HuHui
 * @version $Id: LoginAndOutController.java, v 0.1 2018年5月2日 下午9:09:14 HuHui Exp $
 */
@Controller
@RequestMapping("/cashier/loginAndOut")
public class LoginAndOutController {

    @Resource
    private SellerInfoService  sellerInfoService;

    @Resource
    private ExchangeJobService exchangeJobService;

    /**
     * 收银员登录
     * @return 收银员信息
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultSet login(String sellerNo, String password) {

        AssertUtil.assertNotBlank(sellerNo, "收银员编号不能为空");
        AssertUtil.assertNotBlank(password, "密码不能为空");

        SellerInfo seller = sellerInfoService.login(sellerNo, password);

        return ResultSet.success().put("seller", seller);
    }

    /**
     * 创建未完成的交接班记录
     * 收银员成功登录以后调用此接口
     */
    @ResponseBody
    @RequestMapping(value = "/createUnFinishedExchangeJob", method = RequestMethod.POST)
    public ResultSet createUnFinishedExchangeJob(String sellerNo, String password) {
        //创建
        Long id = exchangeJobService.create(sellerNo);
        return ResultSet.success().put("id", id);
    }

    /**
     * 交接班接口
     */
    @ResponseBody
    @RequestMapping(value = "exchangeJob", method = RequestMethod.POST)
    public ResultSet exchangeJob(Long exchangeJobId) {
        boolean ret = exchangeJobService.exchangeJob(exchangeJobId);
        return ResultSet.success().put("ret", ret);
    }

}
