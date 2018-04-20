/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.MemberInfoQueryRequest;
import cn.cash.register.common.request.MemberRankQueryRequest;
import cn.cash.register.dao.MemberInfoMapper;
import cn.cash.register.dao.MemberIntegralMapper;
import cn.cash.register.dao.MemberRankMapper;
import cn.cash.register.dao.domain.MemberInfo;
import cn.cash.register.dao.domain.MemberIntegral;
import cn.cash.register.dao.domain.MemberRank;
import cn.cash.register.service.MemberService;

/**
 * 会员服务接口实现类
 * @author HuHui
 * @version $Id: MemberServiceImpl.java, v 0.1 2018年4月20日 上午11:42:21 HuHui Exp $
 */
@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger  logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Resource
    private MemberInfoMapper     infoMapper;

    @Resource
    private MemberRankMapper     rankMapper;

    @Resource
    private MemberIntegralMapper integralMapper;

    /****************************会员信息相关接口****************************/

    @Override
    public int addMember(MemberInfo memberInfo) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteMember(Long memberId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateMember(MemberInfo memberInfo) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public MemberInfo queryMember(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PageInfo<MemberInfo> queryList(MemberInfoQueryRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    /****************************会员等级相关接口****************************/

    @Override
    public int addMemRank(MemberRank rank) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteMemRank(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateMemRank(MemberRank rank) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public MemberRank queryMemRank(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PageInfo<MemberRank> queryList(MemberRankQueryRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    /****************************会员积分相关接口****************************/

    @Override
    public MemberIntegral queryMemIntegral() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateMemIntegral(MemberIntegral integral) {
        // TODO Auto-generated method stub
        return 0;
    }

}
