/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.SalesBasicFactsQueryRequest;
import cn.cash.register.dao.SystemParameterMapper;
import cn.cash.register.dao.domain.SalesBasicFacts;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.dao.domain.SystemParameter;
import cn.cash.register.enums.LogSourceEnum;
import cn.cash.register.enums.SalesBasicFactsEnum;
import cn.cash.register.enums.SubSystemTypeEnum;
import cn.cash.register.service.ExchangeJobService;
import cn.cash.register.service.LogService;
import cn.cash.register.service.SalesService;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.DateUtil;
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
    private SellerInfoService     sellerInfoService;

    @Resource
    private ExchangeJobService    exchangeJobService;

    @Resource
    private LogService            logService;

    @Resource
    private SalesService          salesService;

    @Resource
    private SystemParameterMapper systemParameterMapper;

    /**
     * 收银员登录页
     */
    @RequestMapping(value = "/toCashierLogin")
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

        SystemParameter isInit = systemParameterMapper.selectByCode(Constants.IS_INIT);
        if (isInit == null || StringUtils.equals(isInit.getParamValue(), Constants.FALSE)) {
            return ResultSet.error("系统未初始化");
        }

        // 删除可能存在session中的记录
        session.removeAttribute(Constants.LOGIN_FLAG_SELLER);
        session.removeAttribute(Constants.CURRENT_JOB_ID);
        session.removeAttribute(Constants.SELLER_LOGIN_TIME);

        // 校验登录用户名密码
        SellerInfo seller = sellerInfoService.login(sellerNo, password);

        // 创建未完成的交接班记录
        Long exchangeJobId = exchangeJobService.create(sellerNo);

        // 放入session
        session.setAttribute(Constants.LOGIN_FLAG_SELLER, seller);
        session.setAttribute(Constants.CURRENT_JOB_ID, exchangeJobId);
        session.setAttribute(Constants.SELLER_LOGIN_TIME, DateUtil.format(new Date(), DateUtil.newFormat));

        logService.record(LogSourceEnum.front, SubSystemTypeEnum.employee, sellerNo, "登录收银台");
        return ResultSet.success().put("seller", seller).put("exchangeJobId", exchangeJobId);
    }

    /**
     * 收银员登出前查询交接班信息
     */
    @ResponseBody
    @RequestMapping(value = "/cashier/exchangeJobInfo")
    public ResultSet exchangeJobInfo(String exchangeJobTime, HttpSession session) {
        // 查询交接班时的统计信息
        String loginTime = (String) session.getAttribute(Constants.SELLER_LOGIN_TIME); // 登录时间

        // 查询这段时间内的营业概况
        SalesBasicFactsQueryRequest request = new SalesBasicFactsQueryRequest();
        request.setTimeDown(loginTime);
        request.setTimeUp(exchangeJobTime);
        Map<String, SalesBasicFacts> basicFacts = salesService.queryBasicFacts(request);
        String goodsSalesFacts = basicFacts.get(SalesBasicFactsEnum.goods_sales.toString()).getBasicFacts();
        String balanceFacts = basicFacts.get(SalesBasicFactsEnum.balance.toString()).getBasicFacts();

        return ResultSet.success().put("loginTime", loginTime).put("goodsSalesFacts", goodsSalesFacts).put("balanceFacts", balanceFacts);
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
