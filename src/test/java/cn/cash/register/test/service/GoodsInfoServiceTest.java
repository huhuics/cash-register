/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.cash.register.common.request.GoodsInfoInportRequest;
import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.test.BaseTest;

/**
 * 
 * @author HuHui
 * @version $Id: GoodsInfoServiceTest.java, v 0.1 2018年4月28日 上午1:00:15 HuHui Exp $
 */
public class GoodsInfoServiceTest extends BaseTest {

    @Resource
    private GoodsInfoService goodsInfoService;

    @Test
    public void testExport() {
        GoodsInfoQueryRequest request = new GoodsInfoQueryRequest();
        String filePath = goodsInfoService.export(request);
        System.out.println(filePath);
    }

    @Test
    public void testInport() {
        String filePath = "D:\\git-repositories\\cash-register\\商品资料.xls";

        GoodsInfoInportRequest request = new GoodsInfoInportRequest();
        request.setFileFullPath(filePath);
        request.setIsAutoCreateBrand(true);
        request.setIsAutoCreateCategory(true);
        request.setIsAutoCreateUnit(true);
        request.setIsExistUpdate(true);

        goodsInfoService.inport(request);
    }

}
