/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

/**
 * 会员等级及其数量
 * @author HuHui
 * @version $Id: MemberRankAndCounts.java, v 0.1 2018年5月14日 下午9:30:22 HuHui Exp $
 */
public class MemberRankAndCounts extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 1148757161378421388L;

    /** 会员名称 */
    private String            memberRank;

    /** 数量 */
    private Integer           counts;

    public String getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(String memberRank) {
        this.memberRank = memberRank;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

}
