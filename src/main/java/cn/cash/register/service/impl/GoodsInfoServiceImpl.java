/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.common.request.GoodsInfoRequest;
import cn.cash.register.dao.GoodsImageMapper;
import cn.cash.register.dao.GoodsInfoMapper;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.enums.UpdateFieldEnum;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.Money;

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
    public Long add(GoodsInfoRequest request) {
        LogUtil.info(logger, "收到增加商品请求");

        GoodsInfo goodsInfo = convert(request);

        return goodsInfoMapper.insertSelective(goodsInfo);
    }

    @Override
    public void delete(List<Long> ids) {
        LogUtil.info(logger, "收到删除商品请求,ids={0}", ids);

        for (Long id : ids) {
            goodsInfoMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public int update(GoodsInfoRequest request) {
        LogUtil.info(logger, "收到修改商品请求,id={0}", request.getId());
        GoodsInfo goodsInfo = convert(request);
        return goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
    }

    @Override
    public int update(GoodsInfo goodsInfo) {
        LogUtil.info(logger, "收到修改商品请求,id={0}", goodsInfo.getId());
        return goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
    }

    @Override
    public int updateStock(Long id, int count) {
        LogUtil.info(logger, "收到修改商品库存请求,id={0},count={1}", id, count);
        return goodsInfoMapper.updateStock(id, count);
    }

    @Override
    public GoodsInfo queryById(Long id) {
        LogUtil.info(logger, "收到查询商品请求,id={0}", id);
        return goodsInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsInfo> search(String keyword) {
        LogUtil.info(logger, "收到商品搜索请求,keyword={0}", keyword);
        return goodsInfoMapper.search(keyword);
    }

    @Override
    public PageInfo<GoodsInfo> queryList(GoodsInfoQueryRequest request) {
        LogUtil.info(logger, "收到商品分页查询请求");
        request.validate();

        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<GoodsInfo> list = goodsInfoMapper.list(request);

        return new PageInfo<GoodsInfo>(list);
    }

    @Override
    public void updateGoodsImage(Long goodsInfoId, byte[] goodsImage) {

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
    }

    @Override
    public GoodsImage queryGoodsImage(Long goodsImageId) {
        LogUtil.info(logger, "收到查询商品图片请求imageId={0}", goodsImageId);
        return goodsImageMapper.selectByPrimaryKey(goodsImageId);
    }

    @Override
    public void batchUpdate(List<Long> goodsIds, Object newValue, UpdateFieldEnum filedEnum) {
        if (CollectionUtils.isEmpty(goodsIds)) {
            return;
        }

        LogUtil.info(logger, "收到批量操作请求,ids.size={0}", goodsIds.size());

        List<GoodsInfo> goodsInfos = new ArrayList<>(goodsIds.size());

        for (Long id : goodsIds) {
            GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(id);
            if (goodsInfo != null) {
                goodsInfos.add(goodsInfo);
            }
        }

        for (GoodsInfo item : goodsInfos) {
            if (filedEnum == UpdateFieldEnum.royaltyType) {
                JSONObject json = (JSONObject) newValue;
                item.setRoyaltyType(json.toJSONString());
            } else if (filedEnum == UpdateFieldEnum.vipPrice) {
                Money vipPrice = new Money((String) newValue);
                item.setVipPrice(vipPrice);
            } else if (filedEnum == UpdateFieldEnum.categoryName) {
                item.setCategoryName((String) newValue);
            } else if (filedEnum == UpdateFieldEnum.goodsTag) {
                item.setGoodsTag((String) newValue);
            } else if (filedEnum == UpdateFieldEnum.goodsBrand) {
                item.setGoodsBrand((String) newValue);
            } else if (filedEnum == UpdateFieldEnum.supplierName) {
                item.setSupplierName((String) newValue);
            } else if (filedEnum == UpdateFieldEnum.isIntegral) {
                item.setIsIntegral((Boolean) newValue);
            } else if (filedEnum == UpdateFieldEnum.isVipDiscount) {
                item.setIsVipDiscount((Boolean) newValue);
            } else if (filedEnum == UpdateFieldEnum.goodsStatus) {
                item.setGoodsStatus((Boolean) newValue);
            } else if (filedEnum == UpdateFieldEnum.isGift) {
                item.setIsGift((Boolean) newValue);
            } else if (filedEnum == UpdateFieldEnum.isHidden) {
                item.setIsHidden((Boolean) newValue);
            } else if (filedEnum == UpdateFieldEnum.isBooked) {
                item.setIsBooked((Boolean) newValue);
            } else if (filedEnum == UpdateFieldEnum.isFixedPrice) {
                item.setIsFixedPrice((Boolean) newValue);
            } else if (filedEnum == UpdateFieldEnum.isTimeingPrice) {
                item.setIsTimeingPrice((Boolean) newValue);
            }

            goodsInfoMapper.updateByPrimaryKey(item);
        }

    }

    private GoodsInfo convert(GoodsInfoRequest request) {
        GoodsInfo info = new GoodsInfo();
        info.setId(request.getId());
        info.setGoodsImageId(request.getGoodsImageId());
        info.setGoodsName(request.getGoodsName());
        info.setBarCode(request.getBarCode());
        info.setProductNumber(request.getProductNumber());
        info.setPinyinCode(request.getPinyinCode());
        info.setCategoryName(request.getCategoryName());
        info.setGoodsStatus(request.getGoodsStatus());
        info.setGoodsBrand(request.getGoodsBrand());
        info.setGoodsColor(request.getGoodsColor());
        info.setGoodsSize(request.getGoodsSize());
        info.setGoodsTag(request.getGoodsTag());
        info.setGoodsStock(request.getGoodsStock());
        info.setQuantityUnit(request.getQuantityUnit());
        info.setStockUpperLimit(request.getStockUpperLimit());
        info.setStockLowerLimit(request.getStockLowerLimit());
        if (StringUtils.isNotBlank(request.getLastImportPrice())) {
            info.setLastImportPrice(new Money(request.getLastImportPrice()));
        }
        if (StringUtils.isNotBlank(request.getAverageImportPrice())) {
            info.setAverageImportPrice(new Money(request.getAverageImportPrice()));
        }
        if (StringUtils.isNotBlank(request.getSalesPrice())) {
            info.setSalesPrice(new Money(request.getSalesPrice()));
        }
        if (StringUtils.isNotBlank(request.getTradePrice())) {
            info.setTradePrice(new Money(request.getTradePrice()));
        }
        if (StringUtils.isNotBlank(request.getVipPrice())) {
            info.setVipPrice(new Money(request.getVipPrice()));
        }
        info.setIsVipDiscount(request.getIsVipDiscount());
        info.setSupplierName(request.getSupplierName());
        info.setProductionDate(request.getProductionDate());
        info.setQualityGuaranteePeriod(request.getQualityGuaranteePeriod());
        info.setIsIntegral(request.getIsIntegral());
        info.setRoyaltyType(request.getRoyaltyType());
        info.setIsBooked(request.getIsBooked());
        info.setIsGift(request.getIsGift());
        info.setIsWeigh(request.getIsWeigh());
        info.setIsFixedPrice(request.getIsFixedPrice());
        info.setIsTimeingPrice(request.getIsTimeingPrice());
        info.setIsHidden(request.getIsHidden());
        info.setRemark(request.getRemark());
        info.setGmtCreate(new Date());

        return info;
    }

}
