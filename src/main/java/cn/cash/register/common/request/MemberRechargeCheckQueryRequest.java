/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.util.AssertUtil;

/**
 * 充值对账查询请求类
 * @author HuHui
 * @version $Id: MemberRechargeCheckQueryRequest.java, v 0.1 2018年5月14日 下午8:12:08 HuHui Exp $
 */
public class MemberRechargeCheckQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -6588332483915797605L;

    /** 会员等级名称 */
    private String            rankTitle;

    private String            gmtCreateUp;

    private String            gmtCreateDown;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(gmtCreateUp, "查询时间不能为空");
        AssertUtil.assertNotBlank(gmtCreateDown, "查询时间不能为空");
    }

    public String getRankTitle() {
        return rankTitle;
    }

    public void setRankTitle(String rankTitle) {
        this.rankTitle = rankTitle;
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
