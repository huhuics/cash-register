/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.Map;

import cn.cash.register.common.request.SalesBasicFactsQueryRequest;
import cn.cash.register.dao.domain.SalesBasicFacts;
import cn.cash.register.enums.SalesBasicFactsEnum;

/**
 * 销售服务接口
 * 提供销售数据的数据统计功能
 * @author HuHui
 * @version $Id: SalesService.java, v 0.1 2018年5月10日 下午9:13:12 HuHui Exp $
 */
public interface SalesService {

    /**
     * 查询营业概况
     * @return 营业概况,key为{@link SalesBasicFactsEnum}
     */
    Map<String, SalesBasicFacts> queryBasicFacts(SalesBasicFactsQueryRequest request);

}
