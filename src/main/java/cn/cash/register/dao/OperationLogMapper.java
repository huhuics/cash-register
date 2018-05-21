package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.LogQueryRequest;
import cn.cash.register.dao.domain.OperationLog;

public interface OperationLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OperationLog record);

    int insertSelective(OperationLog record);

    OperationLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperationLog record);

    int updateByPrimaryKey(OperationLog record);

    List<OperationLog> list(LogQueryRequest request);
}