/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.service.AuthorizationCodeService;
import cn.cash.register.util.ResultSet;

/**
 * 软件注册Controller
 * @author HuHui
 * @version $Id: AuthorizationCodeController.java, v 0.1 2018年5月21日 上午11:26:45 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/authCode")
public class AuthorizationCodeController {

    @Resource
    private AuthorizationCodeService authCodeService;

    /**
     * 将密钥写入U盘
     * 该方法用于生成加密设备
     * 成功返回true,失败返回false
     */
    @ResponseBody
    @GetMapping(value = "writeIntoUDisk")
    public ResultSet writeIntoUDisk() {
        boolean ret = authCodeService.writeIntoUDisk();
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 比对密钥
     * 成功返回true,失败返回false
     */
    @ResponseBody
    @GetMapping(value = "checkAuth")
    public ResultSet checkAuth() {
        boolean ret = authCodeService.checkAuth();
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 循环检测密钥是否存在
     * 成功返回true,失败返回false
     */
    @ResponseBody
    @GetMapping(value = "loopCheckAuth")
    public ResultSet loopCheckAuth() {
        boolean ret = authCodeService.loopCheckAuth();
        return ResultSet.success().put("ret", ret);
    }

}
