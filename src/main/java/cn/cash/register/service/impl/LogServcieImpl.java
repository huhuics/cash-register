/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.LogQueryRequest;
import cn.cash.register.dao.OperationLogMapper;
import cn.cash.register.dao.domain.OperationLog;
import cn.cash.register.enums.LogSourceEnum;
import cn.cash.register.enums.SubSystemTypeEnum;
import cn.cash.register.service.LogService;

/**
 * 日志服务接口实现类
 * @author HuHui
 * @version $Id: LogServcieImpl.java, v 0.1 2018年5月21日 下午2:40:36 HuHui Exp $
 */
@Service
public class LogServcieImpl implements LogService {

    @Resource
    private OperationLogMapper logMapper;

    @Override
    public PageInfo<OperationLog> list(LogQueryRequest request) {
        request.validate();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<OperationLog> list = logMapper.list(request);

        return new PageInfo<>(list);
    }

    @Override
    public void record(LogSourceEnum source, SubSystemTypeEnum subSystem, String operatorId, String content) {
        OperationLog log = new OperationLog();
        log.setLogSource(source.name());
        log.setSubSystemType(subSystem.name());
        log.setOperator(operatorId);
        log.setLogContent(content);
        log.setGmtCreate(new Date());
        try {
            logMapper.insertSelective(log);
        } catch (Exception e) {
        }
    }

}
