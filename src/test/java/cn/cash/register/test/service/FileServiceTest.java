/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.service;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import cn.cash.register.service.FileService;
import cn.cash.register.service.impl.FileServiceImpl;

/**
 * 
 * @author HuHui
 * @version $Id: FileServiceTest.java, v 0.1 2018年5月20日 下午5:08:52 HuHui Exp $
 */
public class FileServiceTest {

    private FileService fileService = new FileServiceImpl();

    @Test
    public void testWrite() throws IOException {
        fileService.write(new File("F:\\code"), "322a147c0d9cdc7aed2bd06ebf8f0a1d959667eb23c366cdc9da733e2b34987cc91e56ac78f4963547314f2571c9712b80990fbf250d971b7f7c31ee25991812");
    }

    @Test
    public void testRead() throws IOException {
        String content = fileService.read(new File("F:\\code"));
        System.out.println(content);
    }

}
