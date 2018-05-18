/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.util;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import cn.cash.register.dao.AuthorizationCodeMapper;
import cn.cash.register.dao.domain.AuthorizationCode;
import cn.cash.register.test.BaseTest;

/**
 * 
 * @author HuHui
 * @version $Id: DigestTest.java, v 0.1 2018年5月18日 下午7:25:29 HuHui Exp $
 */
public class DigestTest extends BaseTest {

    @Resource
    private AuthorizationCodeMapper codeMapper;

    @Test
    public void testBase64Encode() {
        String encodeBase64 = Base64.encodeBase64String("123".getBytes());
        System.out.println(encodeBase64);
    }

    @Test
    public void testSha512Hex() {
        String sha512Hex = DigestUtils.sha512Hex("12334423423432432");
        System.out.println(sha512Hex);
    }

    @Test
    public void testInsertSha512Hex() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            long currentTimeMillis = System.currentTimeMillis();
            String sha512Hex = DigestUtils.sha512Hex(currentTimeMillis + "");
            AuthorizationCode record = new AuthorizationCode(sha512Hex);
            codeMapper.insert(record);
            System.out.println(sha512Hex);
            Thread.sleep(5);
        }
    }

}
