/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import cn.cash.register.common.Constants;
import cn.cash.register.dao.AuthorizationCodeMapper;
import cn.cash.register.dao.domain.AuthorizationCode;
import cn.cash.register.service.AuthorizationCodeService;
import cn.cash.register.util.FileUtil;
import cn.cash.register.util.LogUtil;

/**
 * 密钥服务接口实现类
 * @author HuHui
 * @version $Id: AuthorizationCodeServiceImpl.java, v 0.1 2018年5月18日 下午8:40:10 HuHui Exp $
 */
@Service
public class AuthorizationCodeServiceImpl implements AuthorizationCodeService {

    private static final Logger     logger       = LoggerFactory.getLogger(AuthorizationCodeServiceImpl.class);

    @Resource
    private AuthorizationCodeMapper codeMapper;

    @Resource
    private ThreadPoolTaskExecutor  executor;

    private static final int        timeToWait   = 3;

    private String                  authFilePath = "";

    @Override
    public boolean writeIntoUDisk() {
        DiskSearchService diskSearchService = new DiskSearchService();
        Future<String> ret = executor.submit(diskSearchService);
        String uDiskName = null;
        try {
            uDiskName = ret.get(timeToWait, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {
            throw new RuntimeException("系统未检测到U盘,请重试", e);
        }

        if (StringUtils.isBlank(uDiskName)) {
            throw new RuntimeException("系统未检测到U盘,请重试");
        }

        AuthorizationCode one = codeMapper.selectOne();
        if (one == null) {
            throw new RuntimeException("密钥已用完,请联系开发者");
        }
        File file = new File(uDiskName + Constants.AUTH_CODE_FILE);
        try {
            file.delete();
            file.createNewFile();
            String sets = "attrib +H \"" + file.getAbsolutePath() + "\"";
            Runtime.getRuntime().exec(sets);
            FileUtil.write(file, one.getCode());
        } catch (IOException e) {
            throw new RuntimeException("创建U盘密钥文件失败", e);
        }

        one.setIsUsed(true);
        codeMapper.updateByPrimaryKeySelective(one);

        return true;
    }

    @Override
    public boolean checkAuth() {
        DiskSearchService diskSearchService = new DiskSearchService();
        Future<String> ret = executor.submit(diskSearchService);
        String uDiskName = null;
        try {
            uDiskName = ret.get(timeToWait, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {
            throw new RuntimeException("系统未检测到U盘,请重试", e);
        }

        if (StringUtils.isBlank(uDiskName)) {
            throw new RuntimeException("系统未检测到U盘,请重试");
        }
        File file = new File(uDiskName + Constants.AUTH_CODE_FILE);
        String code = null;
        try {
            code = FileUtil.read(file);
        } catch (IOException e) {
            throw new RuntimeException("读取密钥失败", e);
        }

        AuthorizationCode selectCode = codeMapper.selectByCode(code);

        if (selectCode == null) {
            return false;
        }
        authFilePath = file.getAbsolutePath();

        return true;
    }

    @Override
    public boolean loopCheckAuth() {
        if (StringUtils.isBlank(authFilePath)) {
            return false;
        }

        File authFile = new File(authFilePath);
        try {
            String code = FileUtil.read(authFile);
            AuthorizationCode selectCode = codeMapper.selectByCode(code);
            if (selectCode == null) {
                return false;
            }
        } catch (IOException e) {
            LogUtil.error(e, logger, "读取密钥文件失败");
            return false;
        }
        return true;
    }

}
