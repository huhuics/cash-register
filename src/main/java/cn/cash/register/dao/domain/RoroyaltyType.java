/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

/**
 * 提成方式
 * @author HuHui
 * @version $Id: RoroyaltyType.java, v 0.1 2018年4月26日 下午9:40:36 HuHui Exp $
 */
public class RoroyaltyType extends BaseDomain {

    /**  */
    private static final long serialVersionUID = -7626436449421855634L;

    private String            type;

    private String            value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
