package cn.cash.register.dao.domain;

import java.util.Date;

public class AuthorizationCode extends BaseDomain {
    /**  */
    private static final long serialVersionUID = 3763341636444318896L;

    private String            code;

    private Boolean           isUsed;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public AuthorizationCode() {
    }

    public AuthorizationCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
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