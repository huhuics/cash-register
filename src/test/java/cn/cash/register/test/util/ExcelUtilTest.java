/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cash.register.util.ExcelUtil;

/**
 * ExcelUtilTest
 * 
 * @author 51
 * @version $Id: ExcelUtilTest.java, v 0.1 2018年6月7日 上午11:43:38 51 Exp $
 */
public class ExcelUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtilTest.class);

    @Test
    public void testCreateFile() throws IOException {
        String filePath = "E:\\testPath";
        String fileName = "test2.xlsx";
        String sheetName = "工作簿1";
        List<List<String>> data = new ArrayList<List<String>>();
        String[] s1 = { "第一行第一格", "第一行第二格" };
        String[] s2 = { "第二行第一格", "第二行第二格", "第二行第三格" };
        List<String> row1 = Arrays.asList(s1);
        List<String> row2 = Arrays.asList(s2);
        data.add(row1);
        data.add(row2);

        ExcelUtil.createExcel(filePath, fileName, sheetName, data);
    }

}
