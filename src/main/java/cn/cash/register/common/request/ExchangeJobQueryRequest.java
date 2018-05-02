/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.util.AssertUtil;

/**
 * 交接班查询请求
 * @author HuHui
 * @version $Id: ExchangeJobQueryRequest.java, v 0.1 2018年5月2日 下午7:44:56 HuHui Exp $
 */
public class ExchangeJobQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -6519873446269197314L;

    /**
     * 查询结束时间
     */
    private String            gmtCreateUp;

    /**
     * 查询起始时间
     */
    private String            gmtCreateDown;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(gmtCreateUp, "查询时间不能为空");
        AssertUtil.assertNotBlank(gmtCreateDown, "查询时间不能为空");
    }

    public String getGmtCreateUp() {
        return gmtCreateUp;
    }

    public void setGmtCreateUp(String gmtCreateUp) {
        this.gmtCreateUp = gmtCreateUp;
    }

    public String getGmtCreateDown() {
        return gmtCreateDown;
    }

    public void setGmtCreateDown(String gmtCreateDown) {
        this.gmtCreateDown = gmtCreateDown;
    }

}
