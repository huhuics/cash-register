/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.SupplierQueryRequest;
import cn.cash.register.dao.domain.SupplierInfo;

/**
 * 供货商服务接口
 * @author HuHui
 * @version $Id: SupplierService.java, v 0.1 2018年4月24日 下午8:40:01 HuHui Exp $
 */
public interface SupplierService {

    /**
     * 增加供货商
     * @param supplier 供货商
     * @return         主键
     */
    Long add(SupplierInfo supplier);

    /**
     * 根据主键id删除供货商
     */
    int delete(Long id);

    /**
     * 根据主键修改供货商
     */
    int update(SupplierInfo supplier);

    /**
     * 根据id查询供货商信息
     */
    SupplierInfo queryById(Long id);

    /**
     * 查询所有供货商名称
     */
    List<String> querySupplierNames();

    /**
     * 分页查询供货商
     */
    PageInfo<SupplierInfo> queryList(SupplierQueryRequest request);

    /**
     * 将从Excel读取的供货商转换为供货商信息
     * 
     * @param excelData
     * @return
     */
    List<SupplierInfo> transfer2SupplierInfo(List<List<String>> excelData);

}
