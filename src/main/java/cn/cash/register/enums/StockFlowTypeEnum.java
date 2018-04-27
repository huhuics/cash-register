/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 商品库存变动类型
 * @author HuHui
 * @version $Id: StockFlowTypeEnum.java, v 0.1 2018年4月26日 上午10:31:09 HuHui Exp $
 */
public enum StockFlowTypeEnum {

                               CHECKOUT("checkout", "商品销售"),

                               REFUND("refund", "退款"),

                               CANCEL("cancel", "反结账"),

                               TRAFFIC_IN("traffic_in", "货流进货"),

                               TRAFFIC_OUT("traffic_out", "货流出库"),

                               TRAFFIC_OUT_SUPPLIER("traffic_out_supplier", "货流退货"),

                               GOODS_LOSE("goods_lose", "商品报损"),

                               GOODS_STOCK_EDIT("goods_stock_edit", "编辑库存")

    ;

    /** 枚举代码 */
    private String code;

    /** 枚举值 */
    private String desc;

    private StockFlowTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
    
    * 根据代码获取枚举，如果code对应的枚举不存在，则返回null
    
    * @param code 枚举代码
    
    * @return     对应的枚举对象
    
    */
    public static StockFlowTypeEnum getByCode(String code) {
        for (StockFlowTypeEnum eachValue : StockFlowTypeEnum.values()) {
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
