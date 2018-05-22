/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import cn.cash.register.dao.domain.SystemParameter;

/**
 * 系统参数服务接口
 * @author HuHui
 * @version $Id: SystemParameterService.java, v 0.1 2018年4月18日 下午3:47:07 HuHui Exp $
 */
public interface SystemParameterService {

    /**
     * 增加系统参数
     * 返回主键
     */
    Long add(SystemParameter param);

    /**
     * 根据参数id查询参数
     */
    SystemParameter getById(Long id);

    /**
     * 根据参数编码查询
     */
    SystemParameter getByCode(String code);

    /**
     * 根据id修改参数值
     * @param id     
     * @param newValue  参数新值
     */
    int updateById(Long id, String newValue);

    /**
     * 根据id删除参数
     * @param id
     */
    int deleteById(Long id);

    /**
     * 清空所有数据
     */
    void truncateAllTables();

}
