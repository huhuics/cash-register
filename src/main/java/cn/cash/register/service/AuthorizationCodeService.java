/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import cn.cash.register.dao.domain.AuthorizationCode;

/**
 * 密钥服务接口
 * @author HuHui
 * @version $Id: AuthorizationCodeService.java, v 0.1 2018年5月18日 下午8:39:52 HuHui Exp $
 */
public interface AuthorizationCodeService {

    /**
     * 将密钥写入U盘
     */
    boolean writeIntoUDisk();

    /**
     * 删除密钥
     */
    int delete(String code);

    /**
     * 查询密钥
     */
    AuthorizationCode queryByCode(String code);

}
