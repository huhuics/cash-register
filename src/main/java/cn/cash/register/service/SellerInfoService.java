/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import cn.cash.register.dao.domain.SellerInfo;

/**
 * 收银员信息服务接口
 * @author HuHui
 * @version $Id: SellerInfoService.java, v 0.1 2018年4月18日 下午4:28:57 HuHui Exp $
 */
public interface SellerInfoService {

    /**
     * 增加收银员
     */
    int addSeller(SellerInfo sellerInfo);

    /**
     * 修改收银员资料
     */
    int update(SellerInfo sellerInfo);

    /**
     * 根据id删除收银员
     * @param id
     * @return
     */
    int delete(Long id);

}
