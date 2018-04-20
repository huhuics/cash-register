package cn.cash.register.dao;

import cn.cash.register.dao.domain.MemberInfo;

public interface MemberInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberInfo record);

    int insertSelective(MemberInfo record);

    MemberInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberInfo record);

    int updateByPrimaryKey(MemberInfo record);
}