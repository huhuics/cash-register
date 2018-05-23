/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.Constants;
import cn.cash.register.dao.domain.SystemParameter;
import cn.cash.register.service.SystemParameterService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.DateUtil;
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

    @RequestMapping
    public String configPage() {
        return "backstage/_system-config";
    }

    @ResponseBody
    @PostMapping(value = "/initSystem")
    public ResultSet initSystem(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter shopNameParam = systemParameterService.getByCode(Constants.SHOP_NAME);

        //修改shop_name
        systemParameterService.updateById(shopNameParam.getId(), newValue);

        //修改register_time
        SystemParameter registerTimeParam = systemParameterService.getByCode(Constants.REGISTER_TIME);
        systemParameterService.updateById(registerTimeParam.getId(), DateUtil.getNewFormatDateString(new Date()));

        //设置试用到期时间
        SystemParameter invalidTimeParam = systemParameterService.getByCode(Constants.INVALID_TIME);
        Date invadeTime = DateUtils.addDays(new Date(), 15);
        systemParameterService.updateById(invalidTimeParam.getId(), DateUtil.getNewFormatDateString(invadeTime));

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

        systemParameterService.updateById(param.getId(), newValue);

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

        systemParameterService.updateById(param.getId(), newValue);

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

        systemParameterService.updateById(param.getId(), newValue);

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

        systemParameterService.updateById(param.getId(), newValue);

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

        systemParameterService.updateById(param.getId(), newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @GetMapping(value = "/queryRemainDays")
    public ResultSet queryRemainDays() {
        SystemParameter isAuthParam = systemParameterService.getByCode(Constants.IS_AUTHORIZED);
        if (isAuthParam != null && StringUtils.equals(isAuthParam.getParamValue(), Constants.TRUE)) {//已激活
            return ResultSet.success();
        }
        SystemParameter invalidTimeParam = systemParameterService.getByCode(Constants.INVALID_TIME);

        Date invalidTime = DateUtil.parseDateNewFormat(invalidTimeParam.getParamValue());

        long diff = DateUtil.getDiffDays(invalidTime, new Date());

        if (diff >= 0) {
            return ResultSet.success().put("diff", diff);
        }

        return ResultSet.error("请购买正版");
    }

    @ResponseBody
    @GetMapping(value = "/truncateAllTables")
    public ResultSet truncateAllTables() {
        systemParameterService.truncateAllTables();
        return ResultSet.success();
    }

}
