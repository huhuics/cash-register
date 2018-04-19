package cn.cash.register.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.cash.register.service.GoodsColorService;
import cn.cash.register.test.BaseTest;

public class GoodsColorServiceTest extends BaseTest {

    @Resource
    private GoodsColorService colorService;

    @Test
    public void testDelete() {
        colorService.delete(1L);
    }

}
