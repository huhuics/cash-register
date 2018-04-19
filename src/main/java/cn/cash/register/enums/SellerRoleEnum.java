/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 收银员角色枚举
 * @author HuHui
 * @version $Id: SellerRoleEnum.java, v 0.1 2018年4月18日 下午4:13:37 HuHui Exp $
 */
public enum SellerRoleEnum {

                            admin("admin", "管理员"),

                            seller("seller", "收银员")

    ;

    /** 枚举代码 */
    private String code;

    /** 枚举值 */
    private String desc;

    private SellerRoleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
    
     * 根据代码获取枚举，如果code对应的枚举不存在，则返回null
    
     * @param code 枚举代码
    
     * @return     对应的枚举对象
    
     */
    public static SellerRoleEnum getByCode(String code) {
        for (SellerRoleEnum eachValue : SellerRoleEnum.values()) {
            if (StringUtils.equals(code, eachValue.getCode())) {
                return eachValue;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
