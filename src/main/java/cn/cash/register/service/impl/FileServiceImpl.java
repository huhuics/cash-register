/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.cash.register.service.FileService;

/**
 * 文件操作接口实现类
 * @author HuHui
 * @version $Id: FileServiceImpl.java, v 0.1 2018年5月20日 下午4:27:11 HuHui Exp $
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public void write(File file, String content) throws IOException {
        if (StringUtils.isEmpty(content)) {
            return;
        }
        FileUtils.write(file, content, "UTF-8");
    }

    @Override
    public String read(File file) throws IOException {
        return FileUtils.readFileToString(file, Charset.forName("UTF-8"));
    }

}
