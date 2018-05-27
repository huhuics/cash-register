/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.printer;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.TradeRequest;
import cn.cash.register.dao.domain.SystemParameter;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.service.SystemParameterService;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.LogUtil;

/**
 * 小票打印服务类
 * @author HuHui
 * @version $Id: ReceiptPrintService.java, v 0.1 2018年5月26日 下午2:41:44 HuHui Exp $
 */
@Service
public class ReceiptPrintService {

    private static final Logger    logger = LoggerFactory.getLogger(ReceiptPrintService.class);

    @Resource
    private SystemParameterService paramService;

    /**
     * 收银成功后打印小票
     */
    public void print(TradeRequest request, TradeDetail tradeDetail) {
        try {
            Book book = new Book();
            //设置竖打
            PageFormat pf = new PageFormat();
            pf.setOrientation(PageFormat.PORTRAIT);
            SystemParameter receiptWidthParam = paramService.getByCode(Constants.RECEIPT_WIDTH);
            int receiptWidth = (receiptWidthParam == null ? Constants.RECEIPT_WIDTH_580 : Integer.parseInt(receiptWidthParam.getParamValue()));

            //设置页面的空白边距和可打印区域
            Paper paper = new Paper();
            paper.setSize(receiptWidth, 30000);//纸张大小
            paper.setImageableArea(0, 0, receiptWidth, 30000);

            pf.setPaper(paper);

            ShoppingReceipt receipt = conert(request, tradeDetail);
            book.append(new ReceiptPrintable(receipt, receiptWidth == Constants.RECEIPT_WIDTH_580 ? false : true), pf);

            //获取打印服务对象
            PrinterJob job = PrinterJob.getPrinterJob();
            //设置打印类
            job.setPageable(book);
            job.print();

        } catch (Exception e) {
            LogUtil.error(e, logger, "收银小票打印失败");
        }
    }

    private ShoppingReceipt conert(TradeRequest request, TradeDetail tradeDetail) {
        ShoppingReceipt receipt = new ShoppingReceipt();
        receipt.setShopName(paramService.getByCode(Constants.SHOP_NAME).getParamValue());
        receipt.setTradeNo(tradeDetail.getTradeNo());
        receipt.setTradeTime(DateUtil.getNewFormatDateString(tradeDetail.getTradeTime()));
        receipt.setMemberNo(tradeDetail.getMemberNo());
        receipt.setGoodsCount(tradeDetail.getGoodsCount().toString());
        receipt.setTotalAmount(tradeDetail.getTotalAmount().toString());
        receipt.setGoodsDiscount(tradeDetail.getGoodsDiscount().toString());
        receipt.setTotalActualAmount(tradeDetail.getTotalActualAmount().toString());
        receipt.setSellerNo(tradeDetail.getSellerNo());
        receipt.setChange(request.getChange());
        receipt.setGoodsItems(request.getGoodsItems());
        receipt.setPayChenals(request.getPayChenals());
        return receipt;
    }

}
