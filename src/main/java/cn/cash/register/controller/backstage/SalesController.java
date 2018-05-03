/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.request.ExchangeJobQueryRequest;
import cn.cash.register.dao.domain.ExchangeJobDetail;
import cn.cash.register.service.ExchangeJobService;
import cn.cash.register.util.ResultSet;

/**
 * 销售相关接口的Controller
 * @author HuHui
 * @version $Id: SalesController.java, v 0.1 2018年5月3日 下午3:27:14 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/admin/sales")
public class SalesController {

    @Resource
    private ExchangeJobService exchangeJobService;

    @ResponseBody
    @RequestMapping(value = "/queryExchangeJobs", method = RequestMethod.POST)
    public ResultSet queryExchangeJobs(ExchangeJobQueryRequest request) {
        List<ExchangeJobDetail> exchangeJobs = exchangeJobService.query(request);
        return ResultSet.success().put("exchangeJobs", exchangeJobs);
    }

}
