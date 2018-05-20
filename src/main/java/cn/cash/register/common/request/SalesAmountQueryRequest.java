/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.enums.TimePeriodEnum;
import cn.cash.register.util.AssertUtil;

/**
 * 根据营业时间段查询交易额请求类
 * @author HuHui
 * @version $Id: SalesAmountQueryRequest.java, v 0.1 2018年5月15日 下午8:27:52 HuHui Exp $
 */
public class SalesAmountQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -6502295750770002873L;

    private String            timeUp;

    private String            timeDown;

    /**
     * 查询时间维度{@link TimePeriodEnum}
     */
    private String            timePeriod;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(timeUp, "查询时间不能为空");
        AssertUtil.assertNotBlank(timeDown, "查询时间不能为空");
        AssertUtil.assertNotBlank(timePeriod, "查询时间维度不能为空");
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

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

}
