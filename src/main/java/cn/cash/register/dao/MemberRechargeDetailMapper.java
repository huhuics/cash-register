package cn.cash.register.dao;

import cn.cash.register.dao.domain.MemberRechargeDetail;

public interface MemberRechargeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberRechargeDetail record);

    int insertSelective(MemberRechargeDetail record);

    MemberRechargeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberRechargeDetail record);

    int updateByPrimaryKey(MemberRechargeDetail record);
}