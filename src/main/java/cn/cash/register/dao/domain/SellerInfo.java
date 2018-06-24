package cn.cash.register.dao.domain;

import java.util.Date;

import cn.cash.register.util.AssertUtil;

public class SellerInfo extends BaseDomain {
    /**  */
    private static final long serialVersionUID = 2867150032263025348L;

    private Long              id;

    private String            partOfShop;

    private String            sellerNo;

    private String            name;

    private String            role;

    private String            password;

    private String            phone;

    private Boolean           status;

    private String            cashPermission;

    private String            backgroundPermission;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public void validate() {
        AssertUtil.assertNotBlank(sellerNo, "收银员编号不能为空");
        AssertUtil.assertNotBlank(name, "收银员姓名不能为空");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartOfShop() {
        return partOfShop;
    }

    public void setPartOfShop(String partOfShop) {
        this.partOfShop = partOfShop == null ? null : partOfShop.trim();
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo == null ? null : sellerNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCashPermission() {
        return cashPermission;
    }

    public void setCashPermission(String cashPermission) {
        this.cashPermission = cashPermission == null ? null : cashPermission.trim();
    }

    public String getBackgroundPermission() {
        return backgroundPermission;
    }

    public void setBackgroundPermission(String backgroundPermission) {
        this.backgroundPermission = backgroundPermission == null ? null : backgroundPermission.trim();
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