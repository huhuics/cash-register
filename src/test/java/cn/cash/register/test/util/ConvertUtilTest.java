/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.cash.register.dao.domain.DiscountGoodsDetail;
import cn.cash.register.dao.domain.GoodsItem;
import cn.cash.register.dao.domain.RoyaltyType;

/**
 * 
 * @author HuHui
 * @version $Id: ConvertUtilTest.java, v 0.1 2018年4月27日 下午7:09:20 HuHui Exp $
 */
public class ConvertUtilTest {

    @Test
    public void testConvert() {
        String idStr = "";
        String[] idArray = idStr.split(",");
        Long[] ids = (Long[]) ConvertUtils.convert(idArray, Long.class);

        for (Long id : ids) {
            System.out.println(id);
        }
    }

    @Test
    public void test() {
        RoyaltyType type = new RoyaltyType();
        type.setType("0");
        type.setValue("0");
        System.out.println(JSON.toJSON(type));
    }

    @Test
    public void testJson() {
        GoodsItem item1 = new GoodsItem();
        item1.setGoodsName("优衣库男t恤");
        item1.setTotalActualAmount("100.00");
        item1.setGoodsCount(2);
        item1.setGoodsId(2L);

        GoodsItem item2 = new GoodsItem();
        item2.setGoodsName("JackJonse风衣");
        item2.setTotalActualAmount("500.00");
        item2.setGoodsCount(1);

        ArrayList<GoodsItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        String itemsJsonStr = JSON.toJSONString(items);

        System.out.println(itemsJsonStr);

        List<GoodsItem> parseRet = JSON.parseArray(itemsJsonStr, GoodsItem.class);
        for (GoodsItem item : parseRet) {
            System.out.println(item);
        }
    }

    @Test
    public void testJsonMap() {
        Map<String, DiscountGoodsDetail> discountGoodsMap = new HashMap<>();
        DiscountGoodsDetail detail = new DiscountGoodsDetail(12.0, "12.13");
        discountGoodsMap.put("112233", detail);
        String jsonStr = JSON.toJSONString(discountGoodsMap);

        Map<String, DiscountGoodsDetail> parseObject = JSON.parseObject(jsonStr, Map.class);

        System.out.println(parseObject);
    }

}
