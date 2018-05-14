/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import cn.cash.register.common.request.MemberRechargeRequest;

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

}
