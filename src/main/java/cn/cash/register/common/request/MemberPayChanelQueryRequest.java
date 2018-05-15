/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 会员支付方式查询请求类
 * @author HuHui
 * @version $Id: MemberPayChanelQueryRequest.java, v 0.1 2018年5月14日 下午9:03:01 HuHui Exp $
 */
public class MemberPayChanelQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -6686835042408249596L;

    private String            memberNo;

    private String            timeUp;

    private String            timeDown;

    public MemberPayChanelQueryRequest() {
    }

    public MemberPayChanelQueryRequest(String memberNo, String timeUp, String timeDown) {
        this.memberNo = memberNo;
        this.timeUp = timeUp;
        this.timeDown = timeDown;
    }

    @Override
    public void validate() {

    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
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
