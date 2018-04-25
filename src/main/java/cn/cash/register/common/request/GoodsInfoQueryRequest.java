package cn.cash.register.common.request;

/**
 * 商品列表查询请求类
 * @author 51
 * @version $Id: GoodsInfoQueryRequest.java, v 0.1 2018年4月18日 下午4:36:32 51 Exp $
 */
public class GoodsInfoQueryRequest extends BasePageRequest {

    /** uid */
    private static final long serialVersionUID = -1971057422449400246L;

    private Boolean           goodsStatus;

    private String            goodsBrand;

    private String            categoryName;

    private String            supplierName;

    private String            goodsTag;

    /**
     * 条码/拼音码/商品名
     */
    private String            keyword;

    @Override
    public void validate() {
        super.validate();
    }

    public Boolean getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Boolean goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
