/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.common.request.GoodsLoseInfoQueryRequest;
import cn.cash.register.common.request.GoodsLoseInfoAddRequest;
import cn.cash.register.dao.domain.GoodsLoseInfo;
import cn.cash.register.dao.domain.GoodsLoseReason;

/**
 * 商品报损服务接口
 * @author HuHui
 * @version $Id: GoodsLoseService.java, v 0.1 2018年4月26日 下午7:02:15 HuHui Exp $
 */
public interface GoodsLoseService {

    /**
     * 增加报损原因
     * @param reason 报损原因
     * @return  主键id
     */
    Long addLoseReason(String reason);

    /**
     * 根据id删除报损原因
     */
    int deleteLoseReason(Long id);

    /**
     * 修改报损原因
     */
    int updateLoseReason(GoodsLoseReason reason);

    /**
     * 查询所有报损原因
     */
    List<GoodsLoseReason> queryAllReason();

    /**
     * 增加商品报损记录
     * @return 主键id
     */
    Long addLoseInfo(GoodsLoseInfoAddRequest request);

    /**
     * 查询所有报损记录
     */
    List<GoodsLoseInfo> queryAllLoseInfo(GoodsLoseInfoQueryRequest request);

}
