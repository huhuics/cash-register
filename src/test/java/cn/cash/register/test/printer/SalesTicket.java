/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.printer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Date;

import cn.cash.register.util.DateUtil;

/**
 * 打印布局类
 * @author HuHui
 * @version $Id: SalesTicket.java, v 0.1 2018年5月25日 上午11:09:45 HuHui Exp $
 */
public class SalesTicket implements Printable {

    private ArrayList<Commodity> list;

    /** 收银员编号  */
    private String               cashier;

    /** 字体 */
    private Font                 font;

    /** 商品总数  */
    private String               saleNum;

    /** 总金额  */
    private String               saleSum;

    /**  实收 */
    private String               practical;

    /** 找零 */
    private String               changes;

    /** 订单号 */
    private String               orders;

    public SalesTicket(ArrayList<Commodity> list, String cashier, String saleNum, String saleSum, String practical, String changes, String orders) {
        this.list = list;
        this.cashier = cashier;
        this.saleNum = saleNum;
        this.saleSum = saleSum;
        this.practical = practical;
        this.changes = changes;
        this.orders = orders;
    }

    /**
     * @param Graphic指明打印的图形环境 
     * @param PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×842点） 
     * @param pageIndex指明页号 
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Component c = null;
        // 转换成Graphics2D 拿到画笔  
        Graphics2D g2 = (Graphics2D) graphics;
        // 设置打印颜色为黑色  
        g2.setColor(Color.black);

        // 打印起点坐标  
        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();

        // 虚线  
        float[] dash1 = { 4.0f };
        // width - 此 BasicStroke 的宽度。此宽度必须大于或等于 0.0f。如果将宽度设置为  
        // 0.0f，则将笔划呈现为可用于目标设备和抗锯齿提示设置的最细线条。  
        // cap - BasicStroke 端点的装饰  
        // join - 应用在路径线段交汇处的装饰  
        // miterlimit - 斜接处的剪裁限制。miterlimit 必须大于或等于 1.0f。  
        // dash - 表示虚线模式的数组  
        // dash_phase - 开始虚线模式的偏移量  

        // 设置画虚线  
        g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 4.0f, dash1, 0.0f));

        // 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）  
        font = new Font("宋体", Font.PLAIN, 11);
        g2.setFont(font);// 设置字体  
        float heigth = font.getSize2D();// 字体高度  
        // 标题  
        g2.drawString("维尼的小店", (float) x, (float) y + heigth);
        float line = 2 * heigth;

        font = new Font("宋体", Font.PLAIN, 8);
        g2.setFont(font);// 设置字体  
        heigth = font.getSize2D();// 字体高度  

        // 显示收银员  
        g2.drawString("收银员:" + cashier, (float) x, (float) y + line);
        // 显示订单号  
        g2.drawString("订单号:" + orders, (float) x + 60, (float) y + line);

        line += heigth;
        // 显示标题  
        g2.drawString("名称", (float) x + 20, (float) y + line);
        g2.drawString("单价", (float) x + 60, (float) y + line);
        g2.drawString("数量", (float) x + 85, (float) y + line);
        g2.drawString("总额", (float) x + 115, (float) y + line);
        line += heigth;
        g2.drawLine((int) x, (int) (y + line), (int) x + 158, (int) (y + line));

        // 第4行  
        line += heigth;

        // 显示内容  
        for (int i = 0; i < list.size(); i++) {

            Commodity commodity = list.get(i);

            g2.drawString(commodity.getName(), (float) x, (float) y + line);
            line += heigth;

            g2.drawString(commodity.getBarcode(), (float) x, (float) y + line);
            g2.drawString(commodity.getUnitPrice(), (float) x + 60, (float) y + line);
            g2.drawString(commodity.getNum(), (float) x + 90, (float) y + line);
            g2.drawString(commodity.getSum(), (float) x + 120, (float) y + line);
            line += heigth;

        }
        line += heigth;

        g2.drawLine((int) x, (int) (y + line), (int) x + 158, (int) (y + line));
        line += heigth;

        g2.drawString("售出商品数:" + saleNum + "件", (float) x, (float) y + line);
        g2.drawString("合计:" + saleSum + "元", (float) x + 70, (float) y + line);
        line += heigth;
        g2.drawString("实收:" + practical + "元", (float) x, (float) y + line);
        g2.drawString("找零:" + changes + "元", (float) x + 70, (float) y + line);
        line += heigth;
        g2.drawString("时间:" + DateUtil.getNewFormatDateString(new Date()), (float) x, (float) y + line);

        line += heigth;
        g2.drawString("欢迎您再次光临", (float) x + 20, (float) y + line);

        line += heigth;
        g2.drawString("钱票请当面点清，离开柜台恕不负责", (float) x, (float) y + line);
        switch (pageIndex) {
            case 0:

                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;

        }
    }

}
