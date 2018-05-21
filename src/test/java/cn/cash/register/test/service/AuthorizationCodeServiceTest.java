/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import cn.cash.register.service.AuthorizationCodeService;
import cn.cash.register.test.BaseTest;

/**
 * 
 * @author HuHui
 * @version $Id: AuthorizationCodeServiceTest.java, v 0.1 2018年5月21日 上午9:45:06 HuHui Exp $
 */
public class AuthorizationCodeServiceTest extends BaseTest {

    @Resource
    private AuthorizationCodeService codeService;

    @Test
    public void testWriteUDisk() {
        boolean ret = codeService.writeIntoUDisk();
        Assert.assertTrue(ret);
    }

    @Test
    public void testCheckAuth() {
        boolean ret = codeService.checkAuth();
        Assert.assertTrue(ret);
        boolean loopRet = codeService.loopCheckAuth();
        Assert.assertTrue(loopRet);
    }

    @Test
    public void testLoopCheck() {
        boolean loopRet = codeService.loopCheckAuth();
        Assert.assertFalse(loopRet);
    }

}
