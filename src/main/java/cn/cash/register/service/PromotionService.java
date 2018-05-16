/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.PromotionQueryRequest;
import cn.cash.register.dao.domain.PromotionDetail;

/**
 * 促销服务接口
 * @author HuHui
 * @version $Id: PromotionService.java, v 0.1 2018年5月16日 下午2:45:49 HuHui Exp $
 */
public interface PromotionService {

    /**
     * 增加促销
     */
    Long add(PromotionDetail item, List<Long> goodsIds);

    /**
     * 删除促销
     */
    int delete(Long id);

    /**
     * 修改促销
     */
    int update(PromotionDetail item);

    /**
     * 根据id查询促销
     */
    PromotionDetail queryById(Long id);

    /**
     * 分页查询促销
     */
    PageInfo<PromotionDetail> list(PromotionQueryRequest request);

}
