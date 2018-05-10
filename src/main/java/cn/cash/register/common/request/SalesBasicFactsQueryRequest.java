/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.util.AssertUtil;

/**
 * 营业概况查询请求类
 * @author HuHui
 * @version $Id: SalesBasicFactsQueryRequest.java, v 0.1 2018年5月10日 下午9:24:50 HuHui Exp $
 */
public class SalesBasicFactsQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -7611473360908605449L;

    private String            timeUp;

    private String            timeDown;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(timeUp, "查询时间不能为空");
        AssertUtil.assertNotBlank(timeDown, "查询时间不能为空");
    }

    public String getTimeUp() {
        return timeUp;
    }

    public void setTimeUp(String timeUp) {
        this.timeUp = timeUp;
    }

    public String getTimeDown() {
        return timeDown;
    }

    public void setTimeDown(String timeDown) {
        this.timeDown = timeDown;
    }

}
