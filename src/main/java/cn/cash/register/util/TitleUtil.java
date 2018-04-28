/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.util;

import java.util.ArrayList;
import java.util.List;

/**
 * excel标题工具类
 * @author HuHui
 * @version $Id: TitleUtil.java, v 0.1 2018年4月28日 上午12:28:53 HuHui Exp $
 */
public class TitleUtil {

    public static List<String> getGoodsInfoTitle() {
        List<String> title = new ArrayList<String>();
        title.add("商品名称（必填）");
        title.add("条码（必填）");
        title.add("货号（必填）");
        title.add("拼音码");
        title.add("商品分类");
        title.add("商品状态");
        title.add("品牌");
        title.add("颜色");
        title.add("尺码");
        title.add("标签");
        title.add("库存（必填）");
        title.add("单位");
        title.add("库存上限");
        title.add("库存下限");
        title.add("平均进货价（必填）");
        title.add("销售价（必填）");
        title.add("批发价");
        title.add("会员价");
        title.add("会员是否有折扣");
        title.add("供货商名称");
        title.add("生产日期");
        title.add("是否积分");
        title.add("提成方式");
        title.add("能否预约");
        title.add("是否允许赠送");
        title.add("是否固价");
        title.add("是否时价");
        title.add("商品备注");

        return title;
    }

}
