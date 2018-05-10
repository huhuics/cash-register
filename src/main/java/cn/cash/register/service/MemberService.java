/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.MemberInfoQueryRequest;
import cn.cash.register.common.request.MemberRankQueryRequest;
import cn.cash.register.common.request.MemberRechargeRequest;
import cn.cash.register.dao.domain.MemberInfo;
import cn.cash.register.dao.domain.MemberIntegral;
import cn.cash.register.dao.domain.MemberRank;
import cn.cash.register.util.Money;

/**
 * 会员服务接口
 * @author HuHui
 * @version $Id: MemberService.java, v 0.1 2018年4月20日 上午11:03:39 HuHui Exp $
 */
public interface MemberService {

    /****************************会员信息相关接口****************************/

    /**
     * 增加会员
     * 返回主键
     */
    Long addMember(MemberInfo memberInfo);

    /**
     * 根据主键删除会员
     */
    int deleteMember(Long memberId);

    /**
     * 修改会员
     */
    int updateMember(MemberInfo memberInfo);

    /**
     * 根据id查询会员信息
     */
    MemberInfo queryMember(Long id);

    /**
     * 根据关键字搜索会员
     * @param keyword 会员号/姓名/手机号
     * @return  结果不翻页
     */
    List<MemberInfo> search(String keyword);

    /**
     * 分页查询会员信息
     */
    PageInfo<MemberInfo> queryList(MemberInfoQueryRequest request);

    /**
     * 修改会员积分值
     */
    void updateIntegral(Long memberId, String moneyStr);

    /**
     * 修改会员积分值
     */
    void updateIntegral(Long memberId, Money money);

    /**
     * 会员余额充值
     */
    boolean recharge(MemberRechargeRequest request);

    /****************************会员等级相关接口****************************/

    /**
     * 增加会员等级信息
     * 返回主键
     */
    Long

            addMemRank(MemberRank rank);

    /**
     * 删除会员等级
     */
    int deleteMemRank(Long id);

    /**
     * 修改会员等级
     */
    int updateMemRank(MemberRank rank);

    /**
     * 根据id查询会员等级
     */
    MemberRank queryMemRank(Long id);

    /**
     * 翻页查询会员等级
     */
    PageInfo<MemberRank> listRank(MemberRankQueryRequest request);

    /**
     * 查询所有会员等级
     * @return
     */
    List<MemberRank> listAllRank();

    /****************************会员积分方式相关接口****************************/

    /**
     * 查询会员积分策略（策略只有一条记录）
     */
    MemberIntegral queryMemIntegral();

    /**
     * 修改会员积分策略
     */
    int updateMemIntegral(MemberIntegral integral);

}
