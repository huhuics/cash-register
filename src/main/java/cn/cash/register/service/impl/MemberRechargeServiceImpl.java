/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;

import cn.cash.register.common.request.MemberRechargeRequest;
import cn.cash.register.dao.MemberInfoMapper;
import cn.cash.register.dao.MemberRechargeDetailMapper;
import cn.cash.register.dao.domain.MemberInfo;
import cn.cash.register.dao.domain.MemberRechargeDetail;
import cn.cash.register.service.MemberRechargeService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.Money;

/**
 * 会员充值服务接口实现类
 * @author HuHui
 * @version $Id: MemberRechargeServiceImpl.java, v 0.1 2018年5月14日 下午7:35:36 HuHui Exp $
 */
@Resource
public class MemberRechargeServiceImpl implements MemberRechargeService {

    private static final Logger        logger = LoggerFactory.getLogger(MemberRechargeServiceImpl.class);

    @Resource
    private MemberRechargeDetailMapper rechargeDetailMapper;

    @Resource
    private MemberInfoMapper           infoMapper;

    @Resource
    private TransactionTemplate        txTemplate;

    @Override
    public boolean recharge(MemberRechargeRequest request) {
        LogUtil.info(logger, "收到会员充值请求");
        AssertUtil.assertNotNull(request, "充值请求不能为空");
        request.validate();

        return txTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                MemberInfo memberInfo = infoMapper.selectByNo(request.getMemberNo());
                if (memberInfo == null) {
                    return false;
                }

                Money rechargeTotalAmount = new Money(request.getRechargeAmount());
                if (StringUtils.isNotBlank(request.getDonationAmount())) {
                    rechargeTotalAmount.addTo(new Money(request.getDonationAmount()));
                }

                Money newBalance = memberInfo.getAccountBalance().addTo(rechargeTotalAmount);

                memberInfo.setAccountBalance(newBalance);
                memberInfo.setShopperName(request.getShopperName());

                infoMapper.updateByPrimaryKeySelective(memberInfo);

                //创建充值记录
                MemberRechargeDetail rechargeDetail = new MemberRechargeDetail();
                rechargeDetail.setGmtCreate(new Date());
                rechargeDetail.setSellerNo(request.getSellerNo());
                rechargeDetail.setShopperNo(request.getShopperNo());
                rechargeDetail.setRechargeAmount(new Money(request.getRechargeAmount()));
                rechargeDetail.setDonationAmount(new Money(request.getDonationAmount()));
                rechargeDetail.setTotalAmount(rechargeTotalAmount);
                rechargeDetail.setPayChenal(JSON.toJSONString(request.getPayChenal()));

                rechargeDetailMapper.insertSelective(rechargeDetail);

                return true;
            }
        });
    }

}
