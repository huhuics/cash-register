/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.util;

import java.util.UUID;

/**
 * 数字相关工具类
 * @author HuHui
 * @version $Id: UUIDUtil.java, v 0.1 2018年4月23日 下午12:53:34 HuHui Exp $
 */
public class NumUtil {

    /**
     * 获得一个UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
