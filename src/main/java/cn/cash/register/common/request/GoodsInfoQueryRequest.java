package cn.cash.register.common.request;

/**
 * 商品列表查询请求类
 * @author 51
 * @version $Id: GoodsInfoQueryRequest.java, v 0.1 2018年4月18日 下午4:36:32 51 Exp $
 */
public class GoodsInfoQueryRequest extends BasePageRequest {

    /** uid */
    private static final long serialVersionUID = -1971057422449400246L;

    private boolean           goodsStatus;

    private String            goodsBrand;

    private String            categoryName;

    private String            supplierName;

    private String            goodsTag;

    private String            barCode;

    private String            goodsName;

    private String            pinyinCode;

    @Override
    public void validate() {
        super.validate();
    }

    public boolean getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(boolean goodsStatus) {
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

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

}
