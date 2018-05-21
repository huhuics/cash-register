package cn.cash.register.dao.domain;

import java.util.Date;

public class OperationLog extends BaseDomain {
    /**  */
    private static final long serialVersionUID = 3843449724188691559L;

    private Long              id;

    private String            logSource;

    private String            subSystemType;

    private String            operator;

    private String            logContent;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogSource() {
        return logSource;
    }

    public void setLogSource(String logSource) {
        this.logSource = logSource == null ? null : logSource.trim();
    }

    public String getSubSystemType() {
        return subSystemType;
    }

    public void setSubSystemType(String subSystemType) {
        this.subSystemType = subSystemType == null ? null : subSystemType.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent == null ? null : logContent.trim();
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