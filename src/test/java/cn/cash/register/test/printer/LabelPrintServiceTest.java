/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.printer;

import org.junit.Test;

import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.printer.LabelPrintService;
import cn.cash.register.util.Money;

/**
 * 
 * @author HuHui
 * @version $Id: LabelPrintServiceTest.java, v 0.1 2018年5月27日 下午3:25:36 HuHui Exp $
 */
public class LabelPrintServiceTest {

    @Test
    public void test() {
        LabelPrintService labelPrint = new LabelPrintService();
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsName("优衣库男t恤");
        goodsInfo.setBarCode("1122333444566");
        goodsInfo.setGoodsColor("红色");
        goodsInfo.setGoodsSize("L");
        goodsInfo.setSalesPrice(new Money(34));

        labelPrint.print(goodsInfo);
    }

}
