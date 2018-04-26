/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.service;

import org.junit.Assert;
import org.junit.Test;

import cn.cash.register.enums.UpdateFieldEnum;

/**
 * 
 * @author HuHui
 * @version $Id: UpdateFieldEnumTest.java, v 0.1 2018年4月26日 上午9:40:06 HuHui Exp $
 */
public class UpdateFieldEnumTest {

    @Test
    public void test() {
        String filedEnumCode = "royaltyType";
        UpdateFieldEnum filedEnum = UpdateFieldEnum.valueOf(filedEnumCode);
        Assert.assertTrue(filedEnum == UpdateFieldEnum.royaltyType);
    }

}
