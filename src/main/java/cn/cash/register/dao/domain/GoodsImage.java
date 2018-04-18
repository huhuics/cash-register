package cn.cash.register.dao.domain;

import java.util.Date;

public class GoodsImage extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -3209550658530087155L;

    private Long              id;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    private byte[]            goodsImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(byte[] goodsImage) {
        this.goodsImage = goodsImage;
    }
}