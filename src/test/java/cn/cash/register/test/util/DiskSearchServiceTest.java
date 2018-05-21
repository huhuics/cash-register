/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.cash.register.service.impl.DiskSearchService;
import cn.cash.register.test.BaseTest;

/**
 * 
 * @author HuHui
 * @version $Id: DiskSearchUtilTest.java, v 0.1 2018年5月20日 上午11:18:58 HuHui Exp $
 */
public class DiskSearchServiceTest extends BaseTest {

    @Resource
    private ThreadPoolTaskExecutor executor;

    @Test
    public void testUDisk() throws InterruptedException, ExecutionException, TimeoutException {
        DiskSearchService diskSearch = new DiskSearchService();
        Future<String> ret = executor.submit(diskSearch);
        //        String uDisk = ret.get(20, TimeUnit.SECONDS);
        String uDisk = ret.get();
        System.out.println(uDisk);
        //        Thread.sleep(15000);
    }

}
