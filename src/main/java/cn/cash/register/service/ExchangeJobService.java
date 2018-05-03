/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.common.request.ExchangeJobQueryRequest;
import cn.cash.register.dao.domain.ExchangeJobDetail;

/**
 * 交接班服务接口
 * @author HuHui
 * @version $Id: ExchangeJobService.java, v 0.1 2018年5月2日 下午7:41:12 HuHui Exp $
 */
public interface ExchangeJobService {

    /**
     * 增加交接班记录
     */
    Long create(String sellerNo);

    /**
     * 修改交接班记录
     */
    int update(ExchangeJobDetail detail);

    /**
     * 交接班
     * @param exchangeJobId 交接班记录主键id
     */
    boolean exchangeJob(Long exchangeJobId);

    /**
     * 查询交接班记录
     */
    List<ExchangeJobDetail> query(ExchangeJobQueryRequest request);

}
