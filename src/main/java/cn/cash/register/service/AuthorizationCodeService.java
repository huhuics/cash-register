/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

/**
 * 密钥服务接口
 * @author HuHui
 * @version $Id: AuthorizationCodeService.java, v 0.1 2018年5月18日 下午8:39:52 HuHui Exp $
 */
public interface AuthorizationCodeService {

    /**
     * 将密钥写入U盘
     * 该方法用于生成加密设备
     * 成功返回true,失败返回false
     */
    boolean writeIntoUDisk();

    /**
     * 比对密钥
     * 成功返回true,失败返回false
     */
    boolean checkAuth();

    /**
     * 循环检测密钥是否存在
     * 成功返回true,失败返回false
     */
    boolean loopCheckAuth();

}
