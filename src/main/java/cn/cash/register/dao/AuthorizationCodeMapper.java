package cn.cash.register.dao;

import cn.cash.register.dao.domain.AuthorizationCode;

public interface AuthorizationCodeMapper {
    int deleteByPrimaryKey(String code);

    int insert(AuthorizationCode record);

    int insertSelective(AuthorizationCode record);

    AuthorizationCode selectByCode(String code);
}