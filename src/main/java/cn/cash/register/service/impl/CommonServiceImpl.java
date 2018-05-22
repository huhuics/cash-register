/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.support.TransactionTemplate;

import cn.cash.register.service.CommonService;

/**
 * 通用服务接口实现类
 * @author HuHui
 * @version $Id: CommonServiceImpl.java, v 0.1 2018年5月22日 上午10:59:08 HuHui Exp $
 */
public class CommonServiceImpl implements CommonService {

    @Resource
    private TransactionTemplate txTemplate;

    @Override
    public void clearAllSystemData() {

    }

}
