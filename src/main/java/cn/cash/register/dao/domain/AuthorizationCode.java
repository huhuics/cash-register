package cn.cash.register.dao.domain;

public class AuthorizationCode extends BaseDomain {
    /**  */
    private static final long serialVersionUID = 9076318582665565854L;
    private String            code;

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
}