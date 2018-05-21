/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.enums.LogSourceEnum;
import cn.cash.register.enums.SubSystemTypeEnum;
import cn.cash.register.util.AssertUtil;

/**
 * 日志查询请求类
 * @author HuHui
 * @version $Id: LogQueryRequest.java, v 0.1 2018年5月21日 下午2:30:54 HuHui Exp $
 */
public class LogQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = -6921580828841394348L;

    /**
     * 日志来源,{@link LogSourceEnum}
     */
    private String            logSource;

    /**
     * 子系统类型,{@link SubSystemTypeEnum}
     */
    private String            subSystemType;

    private String            gmtCreateUp;

    private String            gmtCreateDown;

    @Override
    public void validate() {
        super.validate();
        AssertUtil.assertNotBlank(gmtCreateUp, "查询时间不能为空");
        AssertUtil.assertNotBlank(gmtCreateDown, "查询时间不能为空");
    }

    public String getLogSource() {
        return logSource;
    }

    public void setLogSource(String logSource) {
        this.logSource = logSource;
    }

    public String getSubSystemType() {
        return subSystemType;
    }

    public void setSubSystemType(String subSystemType) {
        this.subSystemType = subSystemType;
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
