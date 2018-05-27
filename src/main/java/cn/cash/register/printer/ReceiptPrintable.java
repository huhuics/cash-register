/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.printer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.cash.register.dao.domain.GoodsItem;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.Money;

/**
 * 条码打印布局类
 * @author HuHui
 * @version $Id: ReceiptPrintable.java, v 0.1 2018年5月26日 下午12:20:20 HuHui Exp $
 */
public class ReceiptPrintable implements Printable {

    private ShoppingReceipt     receipt;

    /**
     * true表示800mm规格, false表示580规格
     */
    private boolean             isWider;

    private Font                font;

    private static final String fontName = "宋体";

    public ReceiptPrintable(ShoppingReceipt receipt, boolean isWider) {
        this.receipt = receipt;
        this.isWider = isWider;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Component c = null;
        double pageWidth = pageFormat.getPaper().getWidth();
        //拿到画笔
        Graphics2D g2d = (Graphics2D) graphics;
        //设置打印颜色
        g2d.setColor(Color.black);

        //打印起始坐标
        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();

        // 设置虚线 
        float[] dash1 = { 4.0f };
        g2d.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 4.0f, dash1, 0.0f));

        font = new Font(fontName, Font.PLAIN, 12);
        g2d.setFont(font);
        float height = getHeight(font.getSize2D());
        float line = 2 * height;

        //居中画标题
        FontMetrics fontMetrics = g2d.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(receipt.getShopName());
        //        g2d.drawString(receipt.getShopName(), (float) ((pageWidth - textWidth) / 8.6), (float) y + height);
        g2d.drawString(receipt.getShopName(), (float) x, (float) y + height);

        font = new Font(fontName, Font.PLAIN, 8);
        g2d.setFont(font);
        height = getHeight(font.getSize2D());

        //画收银员
        g2d.drawString("收银员:" + receipt.getSellerNo(), (float) x, (float) y + line);
        line += height;

        //画订单号
        g2d.drawString("订单号:" + receipt.getTradeNo(), (float) x, (float) y + line);
        line += height;

        //画标题
        g2d.drawString("名称", (float) x + 15, (float) y + line);
        g2d.drawString("单价", (float) x + getOffset(50), (float) y + line);
        g2d.drawString("数量", (float) x + getOffset(85), (float) y + line);
        g2d.drawString("总额", (float) x + getOffset(135), (float) y + line);//115
        line += height;

        //画虚线
        g2d.drawLine((int) x, (int) (y + line), (int) x + getOffset(158), (int) (y + line));
        line += height;

        //画商品内容
        for (GoodsItem item : receipt.getGoodsItems()) {
            g2d.drawString(item.getGoodsName(), (float) x, (float) y + line);
            line += height;

            //            g2d.drawString(item.getBarCode(), (float) x, (float) y + line);
            g2d.drawString(new Money(item.getTotalActualAmount()).divide(item.getGoodsCount()).toString(), (float) x + getOffset(50), (float) y + line);
            g2d.drawString(item.getGoodsCount().toString(), (float) x + getOffset(85), (float) y + line);
            g2d.drawString(item.getTotalActualAmount(), (float) x + getOffset(115), (float) y + line);
            line += height;
        }
        line += height;

        //画虚线
        g2d.drawLine((int) x, (int) (y + line), (int) x + getOffset(158), (int) (y + line));
        line += height;

        g2d.drawString("售出商品数:" + receipt.getGoodsCount() + "件", (float) x, (float) y + line);
        g2d.drawString("合计:" + receipt.getTotalAmount() + "元", (float) x + getOffset(70), (float) y + line);
        line += height;
        g2d.drawString("实收:" + receipt.getTotalActualAmount() + "元", (float) x, (float) y + line);
        g2d.drawString("找零:" + (StringUtils.isBlank(receipt.getChange()) ? "0" : receipt.getChange()) + "元", (float) x + getOffset(70), (float) y + line);
        line += height;
        g2d.drawString("时间:" + DateUtil.getNewFormatDateString(new Date()), (float) x, (float) y + line);

        line += height;
        g2d.drawString("欢迎您再次光临", (float) x + getOffset(40), (float) y + line);

        line += height;
        g2d.drawString("钱票请当面点清，离开柜台恕不负责", (float) x + getOffset(0), (float) y + line);
        switch (pageIndex) {
            case 0:
                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;

        }
    }

    private float getHeight(float fontFeight) {
        return fontFeight * 1.3f;
    }

    private int getOffset(int defaultOffset) {
        if (isWider) {
            return defaultOffset + 20;
        } else {
            return defaultOffset;
        }
    }

}
