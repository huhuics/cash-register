/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import cn.cash.register.service.SupplierService;
import cn.cash.register.test.BaseTest;

/**
 * 供应商服务接口测试
 * @author HuHui
 * @version $Id: SupplierServiceTest.java, v 0.1 2018年4月27日 上午11:07:46 HuHui Exp $
 */
public class SupplierServiceTest extends BaseTest {

    @Resource
    private SupplierService supplierService;

    @Test
    public void testQuerySupplierNames() {
        List<String> ret = supplierService.querySupplierNames();
        Assert.assertNotNull(ret);
    }

}
