/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.io.File;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * U盘检测线程
 * @author HuHui
 * @version $Id: DiskSearchUtil.java, v 0.1 2018年5月18日 下午9:19:15 HuHui Exp $
 */
public class DiskSearchService implements Callable<String> {

    /** root 现有文件系统的盘符 */
    private File[]           roots      = File.listRoots();

    /** fileVector 为了遍历U盘内文件 */
    private Vector<File>     fileVector = new Vector<File>();

    private volatile boolean sign       = false;

    private volatile boolean loop       = true;

    public static void main(String[] args) throws Exception {
        DiskSearchService diskSearch = new DiskSearchService();
        FutureTask<String> future = new FutureTask<String>(diskSearch);
        future.run();
        String uDisk = future.get();
        System.out.println("插入U盘盘符为:" + uDisk);
    }

    @Override
    public String call() throws Exception {
        System.out.println("U盘检测中...");

        while (loop) {
            File[] tempFiles = File.listRoots();

            fileVector.removeAllElements();

            /** 检测到了有U盘插入 */
            if (tempFiles.length > roots.length) {
                for (int i = tempFiles.length - 1; i >= 0; i--) {
                    sign = false;
                    for (int j = roots.length - 1; j >= 0; j--) {
                        /** 如果前后比较的盘符相同 */
                        if (tempFiles[i].equals(roots[j])) {
                            sign = true;
                        }
                    }
                    /** 如果前后比较的盘符不相同，将不相同的盘符写入向量，并做进一步处理 */
                    if (!sign) {
                        fileVector.add(tempFiles[i]);
                        return tempFiles[i].toString();
                    }
                }
                roots = File.listRoots();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        return null;
    }

}
