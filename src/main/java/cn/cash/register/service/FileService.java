/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作接口
 * @author HuHui
 * @version $Id: FileService.java, v 0.1 2018年5月20日 下午4:26:59 HuHui Exp $
 */
public interface FileService {

    /**
     * 写文件
     */
    void write(File file, String content) throws IOException;

    /**
     * 读文件
     */
    String read(File file) throws IOException;

}
