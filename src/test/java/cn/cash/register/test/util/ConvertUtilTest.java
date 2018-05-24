/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.cash.register.dao.domain.GoodsItem;
import cn.cash.register.dao.domain.PromotionGoodsDetail;
import cn.cash.register.dao.domain.RoyaltyType;
import cn.cash.register.service.PromotionGoodsDetailService;
import cn.cash.register.test.BaseTest;

/**
 * 
 * @author HuHui
 * @version $Id: ConvertUtilTest.java, v 0.1 2018年4月27日 下午7:09:20 HuHui Exp $
 */
public class ConvertUtilTest extends BaseTest {

    @Resource
    private PromotionGoodsDetailService service;

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
    public void testPromotionDetail() {
        /*        PromotionGoodsDetail detail1 = new PromotionGoodsDetail();
        detail1.setBarCode("20180510144017883-001");
        detail1.setCategoryName("男士衬衫");
        detail1.setDiscount(100.00);
        detail1.setGoodsId(74L);
        detail1.setPromotionId(1L);
        ArrayList<PromotionGoodsDetail> details = new ArrayList<>();
        details.add(detail1);
        
        String detailsStr = JSON.toJSONString(details);
        System.out.println(detailsStr);
        
        ArrayList<PromotionGoodsDetail> convertDetails = JSON.parseObject(detailsStr, ArrayList.class);
        
        System.out.println(convertDetails);*/

        String jsonStr = "[{\"id\":null,\"promotionId\":\"1\",\"goodsId\":74,\"barCode\":\"20180510144017883-001\",\"categoryName\":\"男士衬衫\",\"discount\":\"100.00\"}]";

        ArrayList<PromotionGoodsDetail> convertDetails2 = JSON.parseObject(jsonStr, ArrayList.class);

        System.out.println(convertDetails2);

        service.addOrUpdate(jsonStr);
    }

}
