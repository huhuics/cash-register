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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.GoodsInfoInportRequest;
import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.common.request.GoodsInfoRequest;
import cn.cash.register.dao.GoodsImageMapper;
import cn.cash.register.dao.GoodsInfoMapper;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.enums.StockFlowTypeEnum;
import cn.cash.register.enums.UpdateFieldEnum;
import cn.cash.register.excel.domain.GoodsInfoExcel;
import cn.cash.register.service.ExcelService;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.GoodsStockService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.ConvertUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.Money;
import cn.cash.register.util.TitleUtil;

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

    @Resource
    private GoodsStockService   stockService;

    @Resource
    private ExcelService        excelService;

    private List<String>        titles = TitleUtil.getGoodsInfoTitle();

    @Override
    public Long add(GoodsInfoRequest request) {
        LogUtil.info(logger, "收到增加商品请求");

        GoodsInfo goodsInfo = ConvertUtil.convert(request);

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

        return txTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                //判断库存是否发送变化
                GoodsInfo nativeGoodsInfo = goodsInfoMapper.selectByPrimaryKey(request.getId());
                if (request.getGoodsStock() != nativeGoodsInfo.getGoodsStock()) {
                    int flowCount = request.getGoodsStock() - nativeGoodsInfo.getGoodsStock();
                    stockService.record(request.getGoodsName(), request.getBarCode(), StockFlowTypeEnum.GOODS_STOCK_EDIT, flowCount, request.getBarCode());
                }

                GoodsInfo goodsInfo = ConvertUtil.convert(request);
                return goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
            }
        });
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
    public void batchUpdate(List<Long> goodsIds, String newValue, UpdateFieldEnum filedEnum) {
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

        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                for (GoodsInfo item : goodsInfos) {
                    if (filedEnum == UpdateFieldEnum.royaltyType) {
                        item.setRoyaltyType(newValue);
                    } else if (filedEnum == UpdateFieldEnum.vipPrice) {
                        Money newVipPrice = item.getSalesPrice().multiply(Double.parseDouble(newValue));
                        item.setVipPrice(newVipPrice);
                    } else if (filedEnum == UpdateFieldEnum.categoryName) {
                        item.setCategoryName(newValue);
                    } else if (filedEnum == UpdateFieldEnum.goodsTag) {
                        item.setGoodsTag(newValue);
                    } else if (filedEnum == UpdateFieldEnum.goodsBrand) {
                        item.setGoodsBrand(newValue);
                    } else if (filedEnum == UpdateFieldEnum.supplierName) {
                        item.setSupplierName(newValue);
                    } else if (filedEnum == UpdateFieldEnum.isIntegral) {
                        item.setIsIntegral(Boolean.valueOf(newValue));
                    } else if (filedEnum == UpdateFieldEnum.isVipDiscount) {
                        item.setIsVipDiscount(Boolean.valueOf(newValue));
                    } else if (filedEnum == UpdateFieldEnum.goodsStatus) {
                        item.setGoodsStatus(Boolean.valueOf(newValue));
                    } else if (filedEnum == UpdateFieldEnum.isGift) {
                        item.setIsGift(Boolean.valueOf(newValue));
                    } else if (filedEnum == UpdateFieldEnum.isHidden) {
                        item.setIsHidden(Boolean.valueOf(newValue));
                    } else if (filedEnum == UpdateFieldEnum.isBooked) {
                        item.setIsBooked(Boolean.valueOf(newValue));
                    } else if (filedEnum == UpdateFieldEnum.isFixedPrice) {
                        item.setIsFixedPrice(Boolean.valueOf(newValue));
                    } else if (filedEnum == UpdateFieldEnum.isTimeingPrice) {
                        item.setIsTimeingPrice(Boolean.valueOf(newValue));
                    }

                    goodsInfoMapper.updateByPrimaryKey(item);
                }
            }
        });

    }

    @Override
    public String export(GoodsInfoQueryRequest request) {
        LogUtil.info(logger, "收到商品信息数据导出请求");
        List<GoodsInfo> list = goodsInfoMapper.list(request);
        List<GoodsInfoExcel> excelDOs = ConvertUtil.convertToExcelDO(list);
        return excelService.write(Constants.GOODS_INFO_EXPORT_FILE_NAME, titles, excelDOs);
    }

    @Override
    public void inport(GoodsInfoInportRequest request) {
        LogUtil.info(logger, "收到商品信息数据导入请求");
        List<GoodsInfoExcel> goodsInfoExcels = excelService.read(request.getFileFullPath(), GoodsInfoExcel.class);
        List<GoodsInfo> goodsInfos = ConvertUtil.convertToGoodsInfo(goodsInfoExcels);

        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                for (GoodsInfo info : goodsInfos) {
                    goodsInfoMapper.insertSelective(info);
                }
            }
        });
    }

}
