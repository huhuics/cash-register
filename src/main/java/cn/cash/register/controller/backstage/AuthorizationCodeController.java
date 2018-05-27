/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/authCode")
public class AuthorizationCodeController {

    @Resource
    private AuthorizationCodeService authCodeService;

    /**
     * 生成密钥页面
     * 
     * @return
     */
    @RequestMapping
    public String authCode(Model model) {
        return "backstage/secretKey-gen";
    }

    /**
     * 将密钥写入U盘
     * 该方法用于生成加密设备
     * 成功返回true,失败返回false
     */
    @ResponseBody
    @PostMapping(value = "writeIntoUDisk")
    public ResultSet writeIntoUDisk(String authUser, String authPassword) {
        if (!StringUtils.equals(authUser, "root") || !StringUtils.equals(authPassword, "root")) {
            return ResultSet.error("用户名或密码错误");
        }
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
