/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.SellerInfoQueryRequest;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.test.BaseTest;

/**
 * 
 * @author HuHui
 * @version $Id: SellerInfoServiceTest.java, v 0.1 2018年4月19日 上午10:30:50 HuHui Exp $
 */
public class SellerInfoServiceTest extends BaseTest {

    @Resource
    SellerInfoService sellerInfoService;

    @Test
    public void testAddSeller() {
        SellerInfo sellerInfo = createSeller();
        int ret = sellerInfoService.addSeller(sellerInfo);
        Assert.assertTrue(ret == 1);
    }

    @Test
    public void testQueryList() {
        SellerInfoQueryRequest request = new SellerInfoQueryRequest();
        request.setPageNum(2);
        request.setSidx("id");

        PageInfo<SellerInfo> sellers = sellerInfoService.queryList(request);
        Assert.assertTrue(sellers.getList().size() == 10);
    }

    private SellerInfo createSeller() {
        SellerInfo seller = new SellerInfo();
        seller.setPartOfShop("小熊维尼的糖果店");
        seller.setSellerNo("1001");
        seller.setName("维尼");
        seller.setRole("seller");
        seller.setPassword("123");
        seller.setPhone("88886666");
        seller.setStatus(true);

        return seller;
    }

}
