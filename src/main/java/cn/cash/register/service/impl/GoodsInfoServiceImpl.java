/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.PageInfoQueryRequest;
import cn.cash.register.dao.GoodsImageMapper;
import cn.cash.register.dao.GoodsInfoMapper;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;

/**
 * 商品信息服务接口实现类
 * @author HuHui
 * @version $Id: GoodsInfoServiceImpl.java, v 0.1 2018年4月19日 下午7:32:59 HuHui Exp $
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsInfoServiceImpl.class);

    @Resource
    private GoodsInfoMapper     goodsInfoMapper;

    @Resource
    private TransactionTemplate txTemplate;

    @Resource
    private GoodsImageMapper    goodsImageMapper;

    @Override
    public int add(GoodsInfo goodsInfo) {
        LogUtil.info(logger, "收到增加商品请求,goodsInfo={0}", goodsInfo);

        return goodsInfoMapper.insertSelective(goodsInfo);
    }

    @Override
    public int delete(Long id) {
        LogUtil.info(logger, "收到删除商品请求,id={0}", id);
        return goodsInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(GoodsInfo goodsInfo) {
        LogUtil.info(logger, "收到修改商品请求,id={0}", goodsInfo.getId());
        return goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
    }

    @Override
    public GoodsInfo queryById(Long id) {
        LogUtil.info(logger, "收到查询商品请求,id={0}", id);
        return goodsInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<GoodsInfo> queryList(PageInfoQueryRequest request) {
        LogUtil.info(logger, "收到商品分页查询请求");
        request.validate();

        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<GoodsInfo> list = goodsInfoMapper.list(request);

        return new PageInfo<GoodsInfo>(list);
    }

    @Override
    public int updateGoodsImage(Long goodsInfoId, byte[] goodsImage) {

        LogUtil.info(logger, "收到商品图片改变请求,goodsInfoId={0}", goodsInfoId);

        AssertUtil.assertNotNull(goodsInfoId, "商品id不能为空");

        txTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsInfoId);
                Long goodsImageId = goodsInfo.getGoodsImageId();

                if (ArrayUtils.isEmpty(goodsImage)) {//说明是删除
                    if (goodsImageId != null) {
                        goodsImageMapper.deleteByPrimaryKey(goodsImageId);
                        goodsInfo.setGoodsImageId(null);
                        goodsInfoMapper.updateByPrimaryKey(goodsInfo);
                    }
                } else {//增加或修改
                    //插入图片表
                    GoodsImage image = new GoodsImage(new Date(), goodsImage);
                    Long id = goodsImageMapper.insertWithKey(image);
                    goodsInfo.setGoodsImageId(id);
                    goodsInfoMapper.updateByPrimaryKey(goodsInfo);

                    //修改图片信息
                    if (goodsImageId != null) {
                        goodsImageMapper.deleteByPrimaryKey(goodsImageId);
                    }
                }
            }
        });

        return 0;
    }

    @Override
    public GoodsImage queryGoodsImage(Long goodsImageId) {
        LogUtil.info(logger, "收到查询商品图片请求imageId={0}", goodsImageId);
        return goodsImageMapper.selectByPrimaryKey(goodsImageId);
    }

}
