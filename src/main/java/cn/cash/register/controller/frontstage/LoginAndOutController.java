/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.Constants;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.enums.LogSourceEnum;
import cn.cash.register.enums.SubSystemTypeEnum;
import cn.cash.register.service.ExchangeJobService;
import cn.cash.register.service.LogService;
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

    @Resource
    private LogService         logService;

    /**
     * 收银员登录
     * @return 收银员信息
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultSet login(String sellerNo, String password, HttpSession session) {

        AssertUtil.assertNotBlank(sellerNo, "收银员编号不能为空");
        AssertUtil.assertNotBlank(password, "密码不能为空");

        SellerInfo seller = sellerInfoService.login(sellerNo, password);
        //创建未完成的交接班记录
        Long exchangeJobId = exchangeJobService.create(sellerNo);
        session.setAttribute(Constants.LOGIN_FLAG, seller);
        session.setAttribute(Constants.CURRENT_JOB_ID, exchangeJobId);

        logService.record(LogSourceEnum.front, SubSystemTypeEnum.employee, sellerNo, "登录收银台");
        return ResultSet.success().put("seller", seller).put("exchangeJobId", exchangeJobId);
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
