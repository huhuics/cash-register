/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库备份与还原
 * @author HuHui
 * @version $Id: DbUtil.java, v 0.1 2018年6月16日 上午7:14:53 HuHui Exp $
 */
public class DbUtil {

    private static final Logger logger         = LoggerFactory.getLogger(DbUtil.class);

    private static final String DB_NAME        = "cash-register";

    private static final String MYSQL_INS_PATH = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\";

    public static boolean backup() {
        InputStream in = null;
        InputStreamReader inReader = null;
        BufferedReader br = null;
        OutputStreamWriter writer = null;
        FileOutputStream fout = null;

        try {
            logger.info(DB_NAME + "开始备份!");
            // mysqldump的安装路径，支持带空格
            String cmd = "\"\" \"" + MYSQL_INS_PATH + "bin\\mysqldump\" -hlocalhost -uroot -phuhui " + DB_NAME;
            // cmd命令在后台执行，没有命令窗口出现或者一闪而过的情况
            Process process = Runtime.getRuntime().exec("cmd /c start /b " + cmd);
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。
            // 注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行   
            in = process.getInputStream();// 控制台的输出信息作为输入流                             
            inReader = new InputStreamReader(in, "utf8");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码   

            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串   
            br = new BufferedReader(inReader);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            // 要用来做导入用的sql目标文件：   
            fout = new FileOutputStream("C:\\" + DB_NAME + "-" + DateUtil.getLongDateString(new Date()) + ".sql");
            writer = new OutputStreamWriter(fout, "utf8");
            writer.write(outStr);
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免   
            writer.flush();

        } catch (Exception e) {
            logger.error(DB_NAME + "备份失败!", e);
            return false;
        } finally {
            try {
                in.close();
                inReader.close();
                br.close();
                writer.close();
                fout.close();
            } catch (Exception e) {
                logger.error(DB_NAME + "备份失败!", e);
                return false;
            }
        }
        logger.info(DB_NAME + "备份成功!");
        return true;
    }

    public static boolean restore(String sqlFilePath) {
        OutputStream out = null;
        BufferedReader br = null;
        OutputStreamWriter writer = null;

        try {
            logger.info("开始还原!");
            // mysql的安装路径，支持带空格
            String cmd = "\"\" \"" + MYSQL_INS_PATH + "bin\\mysql\" -hlocalhost -uroot -phuhui " + DB_NAME;
            // cmd命令在后台执行，没有命令窗口出现或者一闪而过的情况
            Process process = Runtime.getRuntime().exec("cmd /c start /b " + cmd);
            out = process.getOutputStream();//控制台的输入信息作为输出流   
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(sqlFilePath), "utf8"));
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免   
            writer.flush();
        } catch (Exception e) {
            logger.error("还原失败!", e);
            return false;
        } finally {
            // 别忘记关闭输入输出流                
            try {
                out.close();
                br.close();
                writer.close();
            } catch (IOException e) {
                logger.error("还原失败!", e);
                return false;
            }
        }
        logger.info("还原成功!");
        return true;
    }

}
