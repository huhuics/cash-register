package cn.cash.register.common.request;

import cn.cash.register.util.AssertUtil;

/**
 * 基础分页类
 * 
 * @author 51
 * @version $Id: BasePageRequest.java, v 0.1 2018年4月18日 下午4:32:29 51 Exp $
 */
public class BasePageRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = 3515108529325911948L;

    /** 查询页码,默认查询第一页 */
    protected int             pageNum          = 1;

    /** 每页查询结果数 */
    protected int             pageSize         = 10;

    /** 排序字段名,默认主键排序:id*/
    protected String          sidx             = "id";

    /** 排序方式,默认:升序*/
    protected String          order            = "asc";

    /**
     * Getter method for property <tt>pageNum</tt>.
     * 
     * @return property value of pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Setter method for property <tt>pageNum</tt>.
     * 
     * @param pageNum value to be assigned to property pageNum
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Getter method for property <tt>pageSize</tt>.
     * 
     * @return property value of pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Setter method for property <tt>pageSize</tt>.
     * 
     * @param pageSize value to be assigned to property pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public void validate() {
        AssertUtil.assertNotNull(pageNum, "pageNum不能为空");
        AssertUtil.assertNotNull(pageSize, "pageSize不能为空");
    }
}
