/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 货流类型枚举
 * @author HuHui
 * @version $Id: TrafficTypeEnum.java, v 0.1 2018年4月25日 上午11:46:06 HuHui Exp $
 */
public enum TrafficTypeEnum {
                             IN("in", "进货"),

                             ORDINARY_OUT("ordinaryOut", "普通出库"),

                             SUPPLIER_OUT("supplierOut", "退货给供货商")

    ;

    /** 枚举代码 */
    private String code;

    /** 枚举值 */
    private String desc;

    private TrafficTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
    
    * 根据代码获取枚举，如果code对应的枚举不存在，则返回null
    
    * @param code 枚举代码
    
    * @return     对应的枚举对象
    
    */
    public static TrafficTypeEnum getByCode(String code) {
        for (TrafficTypeEnum eachValue : TrafficTypeEnum.values()) {
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
