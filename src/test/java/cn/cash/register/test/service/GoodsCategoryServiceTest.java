package cn.cash.register.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

import cn.cash.register.service.GoodsCategoryService;
import cn.cash.register.test.BaseTest;
import cn.cash.register.util.LogUtil;

public class GoodsCategoryServiceTest extends BaseTest {

    @Resource
    private GoodsCategoryService categoryService;

    @Test
    public void test() {
        JSONArray tree = categoryService.getTree(1L);
        LogUtil.info(logger, "{0}", tree);
    }

}
