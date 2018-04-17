/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.util;

import org.junit.Assert;
import org.junit.Test;

import cn.cash.register.util.PinyinUtil;

/**
 * 
 * @author HuHui
 * @version $Id: PinyinUtilTest.java, v 0.1 2018年4月16日 下午7:26:47 HuHui Exp $
 */
public class PinyinUtilTest {

    @Test
    public void testPinyin() {
        String str1 = "优衣库短袖";
        String lowerChar1 = PinyinUtil.getPinyinHeadLowerChar(str1);
        Assert.assertEquals(lowerChar1, "yykdx");

        String str2 = "JackJonse男士西裤";
        String lowerChar2 = PinyinUtil.getPinyinHeadLowerChar(str2);
        Assert.assertEquals(lowerChar2, "jackjonsensxk");
    }

}
