/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.AchievementQueryRequest;
import cn.cash.register.common.request.SellerInfoQueryRequest;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.dao.domain.TradeGoodsDetail;

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
     * 查询所有收银员信息
     */
    List<SellerInfo> queryAll();

    /**
     * 根据id查询收银员信息
     */
    SellerInfo queryById(Long id);

    /**
     * 根据收银员编号查询收银员信息
     */
    SellerInfo queryBySellerNo(String sellerNo);

    /**
     * 收银员或管理员登录
     */
    SellerInfo login(String sellerNo, String password);

    /**
     * 查询收银员业绩
     */
    PageInfo<TradeGoodsDetail> queryAchievement(AchievementQueryRequest request);

    /**
     * 修改seller_info表中admin的店名
     * @param adminId   admin用户的主键id
     * @param shopName  店名
     */
    void updateShopName(Long adminId, String shopName);

}
