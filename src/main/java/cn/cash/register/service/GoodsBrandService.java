/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.dao.domain.GoodsBrand;

/**
 * 商品品牌服务接口
 * @author HuHui
 * @version $Id: GoodsBrandService.java, v 0.1 2018年4月17日 下午4:11:20 HuHui Exp $
 */
public interface GoodsBrandService {

    /**
     * 增加商品品牌
     * @param brands 商品品牌集合
     */
    void addBrands(List<GoodsBrand> brands);

    /**
     * 查询所有商品品牌
     * @return 商品品牌集合
     */
    List<GoodsBrand> queryAll();

    /**
     * 修改商品品牌
     * @param brand 待修改对象
     */
    int update(GoodsBrand brand);

    /**
     * 删除商品品牌
     * @param brandId 待删除对象id
     */
    int delete(Long brandId);

}
