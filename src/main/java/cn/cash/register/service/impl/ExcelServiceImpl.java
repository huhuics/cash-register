/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.stereotype.Service;

import cn.cash.register.common.Constants;
import cn.cash.register.service.ExcelService;
import cn.cash.register.util.AssertUtil;

/**
 * Excel文件服务接口实现类
 * @author HuHui
 * @version $Id: ExcelServiceImpl.java, v 0.1 2018年4月27日 下午7:52:49 HuHui Exp $
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    private static final String PATH = "F:\\";

    @Override
    public String write(String fileName, List<String> titles, List<?> contents) {

        AssertUtil.assertNotBlank(fileName, "文件名不能为空");
        AssertUtil.assertNotBlank(titles, "excel标题不能为空");
        AssertUtil.assertNotBlank(contents, "Excel内容不能为空");

        Class<?> clazz = contents.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();
        AssertUtil.assertEquals(fields.length, titles.size(), "表标题和内容不一致");

        //1.创建Workbook
        HSSFWorkbook book = new HSSFWorkbook();

        //2.创建sheet
        HSSFSheet sheet = book.createSheet();

        //3.创建标题行,第0行
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = book.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        for (int i = 0; i < titles.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titles.get(i));
            cell.setCellStyle(style);
        }

        //4.插入数据
        for (int k = 0; k < contents.size(); k++) {
            row = sheet.createRow(k + 1);//从第1行开始写数据
            for (int m = 0; m < fields.length; m++) {
                Field filed = fields[m];
                try {
                    String cellValue = (String) FieldUtils.readDeclaredField(contents.get(k), filed.getName(), true);
                    if (StringUtils.isNotBlank(cellValue) && !StringUtils.equals(cellValue, Constants.NULL_STR)) {
                        row.createCell(m).setCellValue(cellValue);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        FileOutputStream out = null;
        File file = null;
        try {
            file = new File(PATH + fileName);
            out = new FileOutputStream(file);
            book.write(out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            try {
                out.close();
                book.close();
            } catch (IOException e) {
            }
        }
        return file.getAbsolutePath();
    }

    @Override
    public <T> List<T> read(String filePath, Class<?> T) {
        // TODO Auto-generated method stub
        return null;
    }

}
