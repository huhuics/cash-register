/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.util;

import org.junit.Test;

import cn.cash.register.util.BarcodeUtil;

/**
 * 
 * @author HuHui
 * @version $Id: BarcodeUtilTest.java, v 0.1 2018年5月23日 下午5:16:16 HuHui Exp $
 */
public class BarcodeUtilTest {

    @Test
    public void testFile() {
        BarcodeUtil.generate("20180510144017883-001 ", "F:\\20180510144017883-001.jpeg");
    }

}
