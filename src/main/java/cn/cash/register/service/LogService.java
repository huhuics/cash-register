/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.LogQueryRequest;
import cn.cash.register.dao.domain.OperationLog;
import cn.cash.register.enums.LogSourceEnum;
import cn.cash.register.enums.SubSystemTypeEnum;

/**
 * 日志服务接口
 * @author HuHui
 * @version $Id: LogService.java, v 0.1 2018年5月21日 下午2:40:21 HuHui Exp $
 */
public interface LogService {

    /**
     * 翻页查询
     */
    PageInfo<OperationLog> list(LogQueryRequest request);

    /**
     * 记录日志
     */
    void record(LogSourceEnum source, SubSystemTypeEnum subSystem, String operatorId, String content);
}
