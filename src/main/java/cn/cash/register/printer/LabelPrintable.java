/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.printer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 * 标签打印布局类
 * @author HuHui
 * @version $Id: LabelPrintable.java, v 0.1 2018年5月27日 下午3:15:00 HuHui Exp $
 */
public class LabelPrintable implements Printable {

    private GoodsLabel          label;

    private Font                font;

    private static final String fontName = "宋体";

    public LabelPrintable(GoodsLabel label) {
        this.label = label;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Component c = null;
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
        float line = height;

        //画商品名称
        g2d.drawString(label.getGoodsName(), (float) x, (float) y + height);

        font = new Font(fontName, Font.PLAIN, 8);
        g2d.setFont(font);
        height = getHeight(font.getSize2D());

        //画单价
        g2d.drawString("单价:" + label.getSalesPrice(), (float) x, (float) y + line);
        line += height;

        //画条形码
        Image image = Toolkit.getDefaultToolkit().getImage(label.getBarCodeFile().getAbsolutePath());
        g2d.drawImage(image, (int) x, (int) (y + line), 100, 38, null);
        line += 40;

        g2d.drawString("条号:" + label.getBarCode(), (float) x, (float) y + line);
        line += height;

        //画颜色和尺寸
        g2d.drawString("颜色:" + label.getGoodsColor(), (float) x, (float) y + line);
        g2d.drawString("尺寸:" + label.getGoodsSize(), (float) x + 50, (float) y + line);

        switch (pageIndex) {
            case 0:
                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;

        }
    }

    private float getHeight(float fontFeight) {
        return fontFeight;
    }

}
