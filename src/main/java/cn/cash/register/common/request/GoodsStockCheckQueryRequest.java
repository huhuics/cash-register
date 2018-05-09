/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.util.AssertUtil;

/**
 * 盘点记录查询请求
 * @author HuHui
 * @version $Id: GoodsCheckQueryRequest.java, v 0.1 2018年5月9日 下午9:07:45 HuHui Exp $
 */
public class GoodsStockCheckQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -4441476621396903463L;

    private String            checkDateUp;

    private String            checkDateDown;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(checkDateUp, "查询时间不能为空");
        AssertUtil.assertNotBlank(checkDateDown, "查询时间不能为空");
    }

    public String getCheckDateUp() {
        return checkDateUp;
    }

    public void setCheckDateUp(String checkDateUp) {
        this.checkDateUp = checkDateUp;
    }

    public String getCheckDateDown() {
        return checkDateDown;
    }

    public void setCheckDateDown(String checkDateDown) {
        this.checkDateDown = checkDateDown;
    }

}
