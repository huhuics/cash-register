package cn.cash.register.dao.domain;

import java.util.Date;

public class AuthorizationCode extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -6063100559791953844L;

    private String            code;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public AuthorizationCode() {
    }

    public AuthorizationCode(String sha512Hex) {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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