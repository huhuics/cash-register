/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import java.util.Date;

/**
 * 货流分页查询请求类
 * @author HuHui
 * @version $Id: GoodsTrafficQueryRequest.java, v 0.1 2018年4月25日 上午10:43:24 HuHui Exp $
 */
public class GoodsTrafficQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = -4085917828920972609L;

    private String            trafficType;

    private Date              createTimeUp;

    private Date              createTimeDown;

    private String            trafficNo;

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public String getTrafficNo() {
        return trafficNo;
    }

    public void setTrafficNo(String trafficNo) {
        this.trafficNo = trafficNo;
    }

    public Date getCreateTimeUp() {
        return createTimeUp;
    }

    public void setCreateTimeUp(Date createTimeUp) {
        this.createTimeUp = createTimeUp;
    }

    public Date getCreateTimeDown() {
        return createTimeDown;
    }

    public void setCreateTimeDown(Date createTimeDown) {
        this.createTimeDown = createTimeDown;
    }

}
