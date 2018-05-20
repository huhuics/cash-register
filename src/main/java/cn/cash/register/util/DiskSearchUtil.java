/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.util;

import java.io.File;
import java.util.Vector;

/**
 * U盘检测工具类
 * @author HuHui
 * @version $Id: DiskSearchUtil.java, v 0.1 2018年5月18日 下午9:19:15 HuHui Exp $
 */
public class DiskSearchUtil implements Runnable {

    /** root 现有文件系统的盘符 */
    private File[]           roots      = File.listRoots();

    /** fileVector 为了遍历U盘内文件 */
    private Vector<File>     fileVector = new Vector<File>();

    private volatile boolean sign       = false;

    private SearchFileThread t          = null;

    @Override
    public void run() {
        System.out.println("Checking System...");

        while (true) {
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
                    }

                }
                roots = File.listRoots();
                t = new SearchFileThread(fileVector);
                t.start();

            } else {
                for (int i = roots.length - 1; i >= 0; i--) {
                    sign = false;
                    for (int j = tempFiles.length - 1; j >= 0; j--) {
                        if (tempFiles[j].equals(roots[i])) {
                            sign = true;
                        }
                    }
                    /** 如果前后比较的盘符不相同，表明U盘被拔出 */
                    if (!sign) {
                        System.out.println("QUIT:" + roots[i].toString());
                        fileVector.removeAllElements();
                        t.setIsExistToFalse();
                        // roots=File.listRoots();  
                    }
                }
                roots = File.listRoots();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class SearchFileThread extends Thread {

        private Vector<File> fileVector = null;

        private int          scanNum    = 1;

        /** 线程安全的变量，用于退出线程 */
        volatile boolean     isExist    = true;

        public SearchFileThread(Vector<File> fileVector) {
            this.fileVector = fileVector;
            System.out.println("fileVector size:" + fileVector.size());
        }

        @Override
        public void run() {

            File file = fileVector.elementAt(scanNum - 1);
            long totalMemory = file.getFreeSpace();

            while (isExist) {

                while (scanNum <= fileVector.size()) {

                    try {
                        System.out.println("search:" + fileVector.elementAt(scanNum - 1).toString() + " Total Space:" + fileVector.elementAt(scanNum - 1).getTotalSpace() / 1024 / 1024
                                           + "MB Free Space:" + fileVector.elementAt(scanNum - 1).getFreeSpace() / 1024 / 1024 + "MB");
                        /** 遍历文件内容 */
                        getFiles(fileVector.elementAt(scanNum - 1).getPath());
                        scanNum++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        scanNum++;
                    }
                }
                /** 如果盘符的大小发生变化，则有文件进出 */
                if (totalMemory != file.getFreeSpace()) {
                    System.out.println("文件发生变化----------------------");
                    getFiles(file.getPath());
                    totalMemory = file.getFreeSpace();
                }
            }

        }

        /** 
         * 递归遍历文件 
         * @param path 
         */
        public void getFiles(String path) {
            try {
                File file = new File(path);
                if (file.isDirectory()) {
                    File[] list = file.listFiles();
                    for (int i = 0; i < list.length; i++) {
                        if (list[i].isDirectory()) {
                            /** 递归调用 */
                            getFiles(list[i].getPath());
                        }
                        System.out.println("Find File:" + list[i].getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public synchronized void setIsExistToFalse() {
            if (isExist)
                isExist = false;
        }
    }
}
