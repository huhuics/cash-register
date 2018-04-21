/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 会员查询请求类
 * @author HuHui
 * @version $Id: MemberInfoQueryRequest.java, v 0.1 2018年4月20日 上午11:40:30 HuHui Exp $
 */
public class MemberInfoQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = 3878907222906977717L;

    private String            memberRank;

    private String            shopperName;

    private boolean           status;

    private String            memberNo;

    private String            memberName;

    private String            phone;

    public String getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(String memberRank) {
        this.memberRank = memberRank;
    }

    public String getShopperName() {
        return shopperName;
    }

    public void setShopperName(String shopperName) {
        this.shopperName = shopperName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
