package cn.cash.register.dao;

import cn.cash.register.dao.domain.SystemParameter;

public interface SystemParameterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemParameter record);

    Long insertSelective(SystemParameter record);

    SystemParameter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemParameter record);

    int updateByPrimaryKey(SystemParameter record);

    SystemParameter selectByCode(String paramCode);

    void truncateAllTables();
}