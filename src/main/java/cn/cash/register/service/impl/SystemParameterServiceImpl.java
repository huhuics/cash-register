/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.cash.register.dao.SystemParameterMapper;
import cn.cash.register.dao.domain.SystemParameter;
import cn.cash.register.service.SystemParameterService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;

/**
 * 系统参数服务接口实现类
 * @author HuHui
 * @version $Id: SystemParameterServiceImpl.java, v 0.1 2018年4月18日 下午3:53:31 HuHui Exp $
 */
@Service
public class SystemParameterServiceImpl implements SystemParameterService {

    private static final Logger   logger = LoggerFactory.getLogger(SystemParameterServiceImpl.class);

    @Resource
    private SystemParameterMapper parameterMapper;

    @Override
    public Long add(SystemParameter param) {

        if (param == null) {
            return 0L;
        }

        LogUtil.info(logger, "收到增加系统参数请求,param={0}", param);
        param.setGmtCreate(new Date());

        return parameterMapper.insertSelective(param);

    }

    @Override
    public SystemParameter getById(Long id) {
        LogUtil.info(logger, "收到系统参数查询请求,id={0}", id);

        AssertUtil.assertNotNull(id, "参数id不能为空");

        return parameterMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Long id, String newValue) {
        AssertUtil.assertNotNull(id, "参数id不能为空");
        AssertUtil.assertNotBlank(newValue, "新值不能为空");

        SystemParameter sysParam = new SystemParameter();
        sysParam.setId(id);
        sysParam.setParamValue(newValue);

        return parameterMapper.updateByPrimaryKeySelective(sysParam);
    }

    @Override
    public int deleteById(Long id) {
        AssertUtil.assertNotNull(id, "参数id不能为空");

        return parameterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SystemParameter getByCode(String code) {
        return parameterMapper.selectByCode(code);
    }

    @Override
    public void truncateAllTables() {
        parameterMapper.truncateAllTables();
    }

}
