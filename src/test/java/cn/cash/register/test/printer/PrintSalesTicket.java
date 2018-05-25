/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.printer;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

/**
 * 
 * @author HuHui
 * @version $Id: PrintSalesTicket.java, v 0.1 2018年5月25日 上午11:18:46 HuHui Exp $
 */
public class PrintSalesTicket {

    public static void main(String[] args) {
        PrintSalesTicket pst = new PrintSalesTicket();
        pst.printSale();
    }

    public void printSale() {
        try {
            // 通俗理解就是书、文档  
            Book book = new Book();
            // 设置成竖打  
            PageFormat pf = new PageFormat();
            pf.setOrientation(PageFormat.PORTRAIT);

            ArrayList<Commodity> cmdList = createCommodity();

            // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。  
            Paper paper = new Paper();
            paper.setSize(580, 30000);// 纸张大小  
            paper.setImageableArea(0, 0, 580, 30000);// A4(595 X  
                                                     // 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72  
            pf.setPaper(paper);

            book.append(new SalesTicket(cmdList, "维尼", cmdList.size() + "", "598.00", "598.00", "0.00", "20180525"), pf);

            // 获取打印服务对象  
            PrinterJob job = PrinterJob.getPrinterJob();
            // 设置打印类  
            job.setPageable(book);

            job.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Commodity> createCommodity() {
        ArrayList<Commodity> list = new ArrayList<Commodity>();
        list.add(new Commodity("优衣库男T恤", "199.00", "1", "199.00", "20180525001"));
        list.add(new Commodity("Adidas女运动上衣", "399.00", "1", "399.00", "20180525002"));

        return list;
    }

}
