package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.MemberRechargeCheckQueryRequest;
import cn.cash.register.common.request.MemberRechargeQueryRequest;
import cn.cash.register.common.request.SalesBasicFactsQueryRequest;
import cn.cash.register.dao.domain.MemberRechargeDetail;
import cn.cash.register.dao.domain.RechargeCheckDetail;

public interface MemberRechargeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberRechargeDetail record);

    int insertSelective(MemberRechargeDetail record);

    MemberRechargeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberRechargeDetail record);

    int updateByPrimaryKey(MemberRechargeDetail record);

    List<MemberRechargeDetail> queryByTime(SalesBasicFactsQueryRequest request);

    List<MemberRechargeDetail> list(MemberRechargeQueryRequest request);

    List<RechargeCheckDetail> listCheck(MemberRechargeCheckQueryRequest request);
}