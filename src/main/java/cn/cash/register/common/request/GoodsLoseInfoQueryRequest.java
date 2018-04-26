/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 商品报损信息查询请求类
 * @author HuHui
 * @version $Id: GoodsLoseInfoQueryRequest.java, v 0.1 2018年4月26日 下午7:10:36 HuHui Exp $
 */
public class GoodsLoseInfoQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = 2670597664117545225L;

    private String            gmtCreateUp;

    private String            gmtCreateDown;

    @Override
    public void validate() {

    }

    public String getGmtCreateUp() {
        return gmtCreateUp;
    }

    public void setGmtCreateUp(String gmtCreateUp) {
        this.gmtCreateUp = gmtCreateUp;
    }

    public String getGmtCreateDown() {
        return gmtCreateDown;
    }

    public void setGmtCreateDown(String gmtCreateDown) {
        this.gmtCreateDown = gmtCreateDown;
    }

}
