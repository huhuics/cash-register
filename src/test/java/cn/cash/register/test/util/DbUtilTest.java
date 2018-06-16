package cn.cash.register.test.util;

import javax.annotation.Resource;

import org.junit.Test;

import cn.cash.register.dao.SystemParameterMapper;
import cn.cash.register.util.DbUtil;

public class DbUtilTest {

    @Resource
    private SystemParameterMapper mapper;

    @Test
    public void testBackup() {
        DbUtil.backup();
    }

    @Test
    public void testRestore() {
        String sqlFilePath = "c:\\cash-register-20180616085828.sql";
        DbUtil.restore(sqlFilePath);
    }

}
