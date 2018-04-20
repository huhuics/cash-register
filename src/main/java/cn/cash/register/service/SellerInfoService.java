/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.SellerInfoQueryRequest;
import cn.cash.register.dao.domain.SellerInfo;

/**
 * 收银员信息服务接口
 * @author HuHui
 * @version $Id: SellerInfoService.java, v 0.1 2018年4月18日 下午4:28:57 HuHui Exp $
 */
public interface SellerInfoService {

    /**
     * 增加收银员
     * 返回主键id
     */
    Long addSeller(SellerInfo sellerInfo);

    /**
     * 修改收银员资料
     */
    int update(SellerInfo sellerInfo);

    /**
     * 根据id删除收银员
     */
    int delete(Long id);

    /**
     * 分页查询收银员信息
     */
    PageInfo<SellerInfo> queryList(SellerInfoQueryRequest request);

    /**
     * 根据id查询收银员信息
     */
    SellerInfo queryById(Long id);

}
