/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 交易类型枚举
 * @author HuHui
 * @version $Id: TradeTypeEnum.java, v 0.1 2018年4月23日 下午4:13:37 HuHui Exp $
 */
public enum TradeTypeEnum {

                           SALES("sales", "销售"),

                           seller("refund", "退款")

    ;

    /** 枚举代码 */
    private String code;

    /** 枚举值 */
    private String desc;

    private TradeTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
    
     * 根据代码获取枚举，如果code对应的枚举不存在，则返回null
    
     * @param code 枚举代码
    
     * @return     对应的枚举对象
    
     */
    public static TradeTypeEnum getByCode(String code) {
        for (TradeTypeEnum eachValue : TradeTypeEnum.values()) {
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
