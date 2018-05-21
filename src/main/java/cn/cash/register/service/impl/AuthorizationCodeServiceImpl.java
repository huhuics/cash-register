/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cash.register.dao.AuthorizationCodeMapper;
import cn.cash.register.dao.domain.AuthorizationCode;
import cn.cash.register.service.AuthorizationCodeService;

/**
 * 密钥服务接口实现类
 * @author HuHui
 * @version $Id: AuthorizationCodeServiceImpl.java, v 0.1 2018年5月18日 下午8:40:10 HuHui Exp $
 */
@Service
public class AuthorizationCodeServiceImpl implements AuthorizationCodeService {

    @Resource
    private AuthorizationCodeMapper codeMapper;

    @Override
    public boolean writeIntoUDisk() {
        AuthorizationCode one = codeMapper.selectOne();

        return false;
    }

    @Override
    public int delete(String code) {
        return codeMapper.deleteByPrimaryKey(code);
    }

    @Override
    public AuthorizationCode queryByCode(String code) {
        return codeMapper.selectByCode(code);
    }

}
