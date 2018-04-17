package cn.cash.register.dao.domain;

import java.util.Date;

public class ShopperInfo {
    private Long id;

    private String shopperNo;

    private String name;

    private String phone;

    private Double salesPercentage;

    private Double rechargePercentage;

    private Double shoppingCardPercentage;

    private Boolean status;

    private Date gmtUpdate;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopperNo() {
        return shopperNo;
    }

    public void setShopperNo(String shopperNo) {
        this.shopperNo = shopperNo == null ? null : shopperNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Double getSalesPercentage() {
        return salesPercentage;
    }

    public void setSalesPercentage(Double salesPercentage) {
        this.salesPercentage = salesPercentage;
    }

    public Double getRechargePercentage() {
        return rechargePercentage;
    }

    public void setRechargePercentage(Double rechargePercentage) {
        this.rechargePercentage = rechargePercentage;
    }

    public Double getShoppingCardPercentage() {
        return shoppingCardPercentage;
    }

    public void setShoppingCardPercentage(Double shoppingCardPercentage) {
        this.shoppingCardPercentage = shoppingCardPercentage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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