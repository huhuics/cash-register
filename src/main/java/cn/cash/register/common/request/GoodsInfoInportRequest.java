/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.util.AssertUtil;

/**
 * 商品导入请求类
 * @author HuHui
 * @version $Id: GoodsInfoInportRequest.java, v 0.1 2018年4月28日 下午3:01:32 HuHui Exp $
 */
public class GoodsInfoInportRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -3590956369083485739L;

    private Boolean           isAutoCreateCategory;

    private Boolean           isAutoCreateUnit;

    private Boolean           isAutoCreateBrand;

    private Boolean           isExistUpdate;

    private String            fileFullPath;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(fileFullPath, "文件路径不能为空");
    }

    public Boolean getIsAutoCreateCategory() {
        return isAutoCreateCategory;
    }

    public void setIsAutoCreateCategory(Boolean isAutoCreateCategory) {
        this.isAutoCreateCategory = isAutoCreateCategory;
    }

    public Boolean getIsAutoCreateUnit() {
        return isAutoCreateUnit;
    }

    public void setIsAutoCreateUnit(Boolean isAutoCreateUnit) {
        this.isAutoCreateUnit = isAutoCreateUnit;
    }

    public Boolean getIsAutoCreateBrand() {
        return isAutoCreateBrand;
    }

    public void setIsAutoCreateBrand(Boolean isAutoCreateBrand) {
        this.isAutoCreateBrand = isAutoCreateBrand;
    }

    public Boolean getIsExistUpdate() {
        return isExistUpdate;
    }

    public void setIsExistUpdate(Boolean isExistUpdate) {
        this.isExistUpdate = isExistUpdate;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }

    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }

}
