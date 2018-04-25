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

    /**
     * 会员号/姓名/手机号
     */
    private String            keyword;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
