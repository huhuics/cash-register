/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.util;

import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

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

}
