package cn.cash.register.dao.domain;

import java.util.Date;

import cn.cash.register.util.Money;

public class MemberInfo extends BaseDomain {
    /**  */
    private static final long serialVersionUID = 7437361677449856715L;

    private Long              id;

    private Boolean           status;

    private String            memberNo;

    private String            memberName;

    private String            memberRank;

    private Integer           memberDiscount;

    private Double            memberIntegral;

    private String            phone;

    private String            password;

    private String            birthday;

    private Boolean           isOnCredit;

    private String            qqNo;

    private String            email;

    private String            address;

    private Money             accountBalance;

    private String            shopperName;

    private String            remark;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(String memberRank) {
        this.memberRank = memberRank == null ? null : memberRank.trim();
    }

    public Integer getMemberDiscount() {
        return memberDiscount;
    }

    public void setMemberDiscount(Integer memberDiscount) {
        this.memberDiscount = memberDiscount;
    }

    public Double getMemberIntegral() {
        return memberIntegral;
    }

    public void setMemberIntegral(Double memberIntegral) {
        this.memberIntegral = memberIntegral;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public Boolean getIsOnCredit() {
        return isOnCredit;
    }

    public void setIsOnCredit(Boolean isOnCredit) {
        this.isOnCredit = isOnCredit;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo == null ? null : qqNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Money getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Money accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getShopperName() {
        return shopperName;
    }

    public void setShopperName(String shopperName) {
        this.shopperName = shopperName == null ? null : shopperName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}