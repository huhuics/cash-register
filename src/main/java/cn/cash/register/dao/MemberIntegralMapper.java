package cn.cash.register.dao;

import cn.cash.register.dao.domain.MemberIntegral;

public interface MemberIntegralMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberIntegral record);

    int insertSelective(MemberIntegral record);

    MemberIntegral selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberIntegral record);

    int updateByPrimaryKey(MemberIntegral record);
}