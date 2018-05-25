/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.Constants;
import cn.cash.register.dao.domain.SystemParameter;
import cn.cash.register.service.SystemParameterService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.ResultSet;

/**
 * 系统设置Controller
 * @author HuHui
 * @version $Id: SystemConfigController.java, v 0.1 2018年5月18日 下午3:02:11 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/cashier/systemConfig")
public class CashierConfigController {

    @Resource
    private SystemParameterService systemParameterService;

    /**
     * 根据param_code查询参数值
     * @param paramCode {@link Constants}
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/queryByCode")
    public ResultSet queryByCode(String paramCode) {
        AssertUtil.assertNotBlank(paramCode, "参数不能为空");
        SystemParameter byCode = systemParameterService.getByCode(paramCode);
        return ResultSet.success().put("byCode", byCode);
    }

}
