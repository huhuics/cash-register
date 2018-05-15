/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.MemberRechargeCheckQueryRequest;
import cn.cash.register.common.request.MemberRechargeQueryRequest;
import cn.cash.register.common.request.MemberRechargeRequest;
import cn.cash.register.dao.domain.MemberRechargeDetail;
import cn.cash.register.dao.domain.RechargeCheckDetail;

/**
 * 会员充值服务接口
 * @author HuHui
 * @version $Id: MemberRechargeService.java, v 0.1 2018年5月14日 下午7:34:01 HuHui Exp $
 */
public interface MemberRechargeService {

    /**
     * 会员余额充值
     */
    boolean recharge(MemberRechargeRequest request);

    /**
     * 查询会员充值明细
     */
    PageInfo<MemberRechargeDetail> query(MemberRechargeQueryRequest request);

    /**
     * 会员充值对账查询
     * @param request
     * @return
     */
    List<RechargeCheckDetail> list(MemberRechargeCheckQueryRequest request);

}
