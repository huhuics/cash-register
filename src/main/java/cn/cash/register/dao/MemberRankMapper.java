package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.dao.domain.MemberRank;

public interface MemberRankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberRank record);

    Long insertSelective(MemberRank record);

    MemberRank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberRank record);

    int updateByPrimaryKey(MemberRank record);

    List<MemberRank> listAll();
}