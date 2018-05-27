/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.printer;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.util.BarcodeUtil;
import cn.cash.register.util.LogUtil;

/**
 * 标签打印服务类
 * @author HuHui
 * @version $Id: LabelPrintService.java, v 0.1 2018年5月27日 下午3:20:39 HuHui Exp $
 */
@Service
public class LabelPrintService {

    private static final Logger logger = LoggerFactory.getLogger(LabelPrintService.class);

    public void print(GoodsInfo goodsInfo) {
        try {
            Book book = new Book();
            //设置竖打
            PageFormat pf = new PageFormat();
            pf.setOrientation(PageFormat.PORTRAIT);

            //设置页面的空白边距和可打印区域
            Paper paper = new Paper();
            paper.setSize(400, 300);//纸张大小
            paper.setImageableArea(5, 0, 400, 300);

            pf.setPaper(paper);

            GoodsLabel label = conert(goodsInfo);
            book.append(new LabelPrintable(label), pf);

            //获取打印服务对象
            PrinterJob job = PrinterJob.getPrinterJob();
            //设置打印类
            job.setPageable(book);
            job.print();

        } catch (Exception e) {
            LogUtil.error(e, logger, "收银小票打印失败");
        }
    }

    private GoodsLabel conert(GoodsInfo goodsInfo) {
        GoodsLabel label = new GoodsLabel();
        label.setGoodsName(goodsInfo.getGoodsName());
        label.setBarCode(goodsInfo.getBarCode());
        label.setGoodsColor(goodsInfo.getGoodsColor());
        label.setGoodsSize(goodsInfo.getGoodsSize());
        label.setSalesPrice(goodsInfo.getSalesPrice().toString());
        label.setBarCodeFile(BarcodeUtil.generate(goodsInfo.getBarCode(), "C:\\temp.jpg"));
        return label;
    }

}
