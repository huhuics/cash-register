/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 提成方式枚举
 * @author HuHui
 * @version $Id: RoyaltyTypeEnum.java, v 0.1 2018年4月28日 上午1:18:24 HuHui Exp $
 */
public enum RoyaltyTypeEnum {
                             one("0", "不提成"),

                             two("1", "销售价*导购员提成百分比"),

                             three("2", "利润*导购员提成百分比"),

                             four("3", "固定金额"),

                             five("4", "销售价*百分比"),

                             six("5", "利润*百分比"),

    ;

    /** 枚举代码 */
    private String code;

    /** 枚举值 */
    private String desc;

    private RoyaltyTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
    
     * 根据代码获取枚举，如果code对应的枚举不存在，则返回null
    
     * @param code 枚举代码
    
     * @return     对应的枚举对象
    
     */
    public static RoyaltyTypeEnum getByCode(String code) {
        for (RoyaltyTypeEnum eachValue : RoyaltyTypeEnum.values()) {
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
