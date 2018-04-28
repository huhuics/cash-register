/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.cash.register.common.request.GoodsInfoRequest;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.RoyaltyType;
import cn.cash.register.enums.RoyaltyTypeEnum;
import cn.cash.register.excel.domain.GoodsInfoExcel;

/**
 * 对象转换工具类
 * @author HuHui
 * @version $Id: ConvertUtil.java, v 0.1 2018年4月28日 下午4:57:34 HuHui Exp $
 */
public class ConvertUtil {

    public static List<GoodsInfo> convertToGoodsInfo(List<GoodsInfoExcel> excels) {
        List<GoodsInfo> infos = new ArrayList<>();
        for (GoodsInfoExcel excel : excels) {
            GoodsInfo info = new GoodsInfo();

            info.setGoodsName(excel.getGoodsName());
            info.setBarCode(excel.getBarCode());
            info.setProductNumber(excel.getProductNumber());
            info.setPinyinCode(excel.getPinyinCode());
            info.setCategoryName(excel.getCategoryName());
            if (StringUtils.isNotBlank(excel.getGoodsStatus())) {
                info.setGoodsStatus(StringUtils.equals(excel.getGoodsStatus(), "启用") ? true : false);
            }
            info.setGoodsBrand(excel.getGoodsBrand());
            info.setGoodsColor(excel.getGoodsColor());
            info.setGoodsSize(excel.getGoodsSize());
            info.setGoodsTag(excel.getGoodsTag());
            if (StringUtils.isNotBlank(excel.getGoodsStock())) {
                info.setGoodsStock(Integer.parseInt(excel.getGoodsStock()));
            }
            info.setQuantityUnit(excel.getQuantityUnit());
            if (StringUtils.isNotBlank(excel.getStockUpperLimit())) {
                info.setStockUpperLimit(Integer.parseInt(excel.getStockUpperLimit()));
            }
            if (StringUtils.isNotBlank(excel.getStockLowerLimit())) {
                info.setStockLowerLimit(Integer.parseInt(excel.getStockLowerLimit()));
            }

            if (StringUtils.isNotBlank(excel.getAverageImportPrice())) {
                info.setLastImportPrice(new Money(excel.getAverageImportPrice()));
                info.setAverageImportPrice(new Money(excel.getAverageImportPrice()));
            }
            if (StringUtils.isNotBlank(excel.getSalesPrice())) {
                info.setSalesPrice(new Money(excel.getSalesPrice()));
            }
            if (StringUtils.isNotBlank(excel.getTradePrice())) {
                info.setTradePrice(new Money(excel.getTradePrice()));
            }
            if (StringUtils.isNotBlank(excel.getVipPrice())) {
                info.setVipPrice(new Money(excel.getVipPrice()));
            }

            if (StringUtils.isNotBlank(excel.getIsVipDiscount())) {
                info.setIsVipDiscount(StringUtils.equals(excel.getIsVipDiscount(), "是") ? true : false);
            }

            info.setSupplierName(excel.getSupplierName());
            info.setProductionDate(excel.getProductionDate());

            if (StringUtils.isNotBlank(excel.getIsIntegral())) {
                info.setIsIntegral(StringUtils.equals(excel.getIsIntegral(), "是") ? true : false);
            }

            if (StringUtils.isNotBlank(excel.getRoyaltyType())) {
                RoyaltyTypeEnum royaltyType = RoyaltyTypeEnum.getByDesc(excel.getRoyaltyType());
                info.setRoyaltyType(JSON.toJSONString(royaltyType));
            }

            info.setIsBooked(StringUtils.equals(excel.getIsBooked(), "是") ? true : false);
            info.setIsGift(StringUtils.equals(excel.getIsGift(), "是") ? true : false);
            info.setIsFixedPrice(StringUtils.equals(excel.getIsFixedPrice(), "是") ? true : false);
            info.setIsTimeingPrice(StringUtils.equals(excel.getIsTimeingPrice(), "是") ? true : false);
            info.setRemark(excel.getRemark());

            infos.add(info);
        }

        return infos;
    }

    public static List<GoodsInfoExcel> convertToExcelDO(List<GoodsInfo> infos) {
        List<GoodsInfoExcel> excels = new ArrayList<GoodsInfoExcel>();
        for (GoodsInfo info : infos) {
            GoodsInfoExcel excel = new GoodsInfoExcel();

            excel.setGoodsName(info.getGoodsName());
            excel.setBarCode(info.getBarCode());
            excel.setProductNumber(info.getProductNumber());
            excel.setPinyinCode(info.getPinyinCode());
            excel.setCategoryName(info.getCategoryName());
            excel.setGoodsStatus(info.getGoodsStatus() ? "启用" : "停用");
            excel.setGoodsBrand(info.getGoodsBrand());
            excel.setGoodsColor(info.getGoodsColor());
            excel.setGoodsSize(info.getGoodsSize());
            excel.setGoodsTag(info.getGoodsTag());
            excel.setGoodsStock(info.getGoodsStock() + "");
            excel.setQuantityUnit(info.getQuantityUnit());
            excel.setStockUpperLimit(info.getStockUpperLimit() + "");
            excel.setStockLowerLimit(info.getStockLowerLimit() + "");

            if (info.getAverageImportPrice() != null) {
                excel.setAverageImportPrice(info.getAverageImportPrice().getAmount().doubleValue() + "");
            }
            if (info.getSalesPrice() != null) {
                excel.setSalesPrice(info.getSalesPrice().getAmount().doubleValue() + "");
            }
            if (info.getTradePrice() != null) {
                excel.setTradePrice(info.getTradePrice().getAmount().doubleValue() + "");
            }
            if (info.getVipPrice() != null) {
                excel.setVipPrice(info.getVipPrice().getAmount().doubleValue() + "");
            }

            excel.setIsVipDiscount(info.getIsVipDiscount() ? "是" : "否");
            excel.setSupplierName(info.getSupplierName());
            excel.setProductionDate(info.getProductionDate());
            excel.setIsIntegral(info.getIsIntegral() ? "是" : "否");

            if (StringUtils.isNotBlank(info.getRoyaltyType())) {
                RoyaltyType royaltyType = JSON.parseObject(info.getRoyaltyType(), RoyaltyType.class);
                RoyaltyTypeEnum byCode = RoyaltyTypeEnum.getByCode(royaltyType.getType());
                excel.setRoyaltyType(byCode.getDesc());
            }

            excel.setIsBooked(info.getIsBooked() ? "是" : "否");
            excel.setIsGift(info.getIsGift() ? "是" : "否");
            excel.setIsFixedPrice(info.getIsFixedPrice() ? "是" : "否");
            excel.setIsTimeingPrice(info.getIsTimeingPrice() ? "是" : "否");
            excel.setRemark(info.getRemark());

            excels.add(excel);
        }
        return excels;
    }

    public static GoodsInfo convert(GoodsInfoRequest request) {
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
        info.setRoyaltyType(JSON.toJSONString(request.getRoyaltyType()));
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
