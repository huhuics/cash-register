/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * 收银员登录登出Controller
 * @author HuHui
 * @version $Id: LoginAndOutController.java, v 0.1 2018年5月2日 下午9:09:14 HuHui Exp $
 */
@Controller
@RequestMapping("/")
public class LoginAndOutController {

    @Resource
    private SellerInfoService  sellerInfoService;

    @Resource
    private ExchangeJobService exchangeJobService;

    @Resource
    private LogService         logService;

    /**
     * 收银员登录页
     */
    @GetMapping(value = "/toCashierLogin")
    public String login() {
        return "frontstage/login";
    }

    /**
     * 收银员登录
     */
    @ResponseBody
    @PostMapping(value = "/cashierLogin")
    public ResultSet login(String sellerNo, String password, HttpSession session) {

        AssertUtil.assertNotBlank(sellerNo, "收银员编号不能为空");
        AssertUtil.assertNotBlank(password, "密码不能为空");

        // 删除可能存在session中的记录
        session.removeAttribute(Constants.LOGIN_FLAG_SELLER);
        session.removeAttribute(Constants.CURRENT_JOB_ID);

        // 校验登录用户名密码
        SellerInfo seller = sellerInfoService.login(sellerNo, password);

        // 创建未完成的交接班记录
        Long exchangeJobId = exchangeJobService.create(sellerNo);

        // 放入session
        session.setAttribute(Constants.LOGIN_FLAG_SELLER, seller);
        session.setAttribute(Constants.CURRENT_JOB_ID, exchangeJobId);

        logService.record(LogSourceEnum.front, SubSystemTypeEnum.employee, sellerNo, "登录收银台");
        return ResultSet.success().put("seller", seller).put("exchangeJobId", exchangeJobId);
    }

    /**
     * 收银员登出(交接班接口)
     */
    @ResponseBody
    @RequestMapping(value = "/cashier/logout")
    public ResultSet exchangeJob(HttpSession session) {
        Long exchangeJobId = (Long) session.getAttribute(Constants.CURRENT_JOB_ID);
        boolean ret = exchangeJobService.exchangeJob(exchangeJobId);

        // 删除存在session中的记录
        session.removeAttribute(Constants.LOGIN_FLAG_SELLER);
        session.removeAttribute(Constants.CURRENT_JOB_ID);

        return ResultSet.success().put("ret", ret);
    }

}
