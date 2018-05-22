/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.cash.register.service.SystemParameterService;
import cn.cash.register.test.BaseTest;

/**
 * 
 * @author HuHui
 * @version $Id: TruncateAllTablesTest.java, v 0.1 2018年5月22日 下午2:14:08 HuHui Exp $
 */
public class TruncateAllTablesTest extends BaseTest {

    @Resource
    private SystemParameterService service;

    @Test
    public void testTruncate() {
        service.truncateAllTables();
    }

}
