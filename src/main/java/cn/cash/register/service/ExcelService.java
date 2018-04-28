/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

/**
 * Excel文件服务接口
 * @author HuHui
 * @version $Id: ExcelService.java, v 0.1 2018年4月27日 下午7:43:51 HuHui Exp $
 */
public interface ExcelService {

    /**
     * 写Excel文件
     * @param fileName 生成的excel文件名字
     * @param titles   excel文件标题
     * @param contents 内容
     * @return         生成的excel路径
     */
    String write(String fileName, List<String> titles, List<?> contents);

    /**
     * 读Excel文件内容
     * @param filePath excel文件路径
     * @param T        文件内容对应的实体对象
     * @return         内容集合
     */
    <T> List<T> read(String filePath, Class<?> T);

}
