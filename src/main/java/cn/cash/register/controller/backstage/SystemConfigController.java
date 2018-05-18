/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

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
@RequestMapping(value = "/admin/systemConfig")
public class SystemConfigController {

    @Resource
    private SystemParameterService systemParameterService;

    @ResponseBody
    @PostMapping(value = "/setShopName")
    public ResultSet setShopName(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.SHOP_NAME);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        param.setParamValue(newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setPettyAmount")
    public ResultSet setPettyAmount(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.PETTY_AMOUNT);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        param.setParamValue(newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setRegisterTime")
    public ResultSet setRegisterTime(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.REGISTER_TIME);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        param.setParamValue(newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setInvalidTime")
    public ResultSet setInvalidTime(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.INVALID_TIME);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        param.setParamValue(newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setRelatedEmail")
    public ResultSet setRelatedEmail(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.RELATED_EMAIL);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        param.setParamValue(newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setPhone")
    public ResultSet setPhone(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.PHONE);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        param.setParamValue(newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setAddress")
    public ResultSet setAddress(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.ADDRESS);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        param.setParamValue(newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setIsAuthorized")
    public ResultSet setIsAuthorized(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.IS_AUTHORIZED);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        param.setParamValue(newValue);

        return ResultSet.success("设置成功");
    }

}
