/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.SupplierQueryRequest;
import cn.cash.register.dao.SupplierInfoMapper;
import cn.cash.register.dao.domain.SupplierInfo;
import cn.cash.register.service.SupplierService;
import cn.cash.register.util.LogUtil;

/**
 * 供货商服务接口实现类
 * @author HuHui
 * @version $Id: SupplierServiceImpl.java, v 0.1 2018年4月24日 下午8:42:43 HuHui Exp $
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    @Resource
    private SupplierInfoMapper  infoMapper;

    @Override
    public Long add(SupplierInfo supplier) {
        supplier.setGmtCreate(new Date());
        return infoMapper.insertSelective(supplier);
    }

    @Override
    public int delete(Long id) {
        return infoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(SupplierInfo supplier) {
        return infoMapper.updateByPrimaryKeySelective(supplier);
    }

    @Override
    public SupplierInfo queryById(Long id) {
        return infoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<String> querySupplierNames() {
        return infoMapper.selectAllNames();
    }

    @Override
    public PageInfo<SupplierInfo> queryList(SupplierQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<SupplierInfo> list = infoMapper.list(request);

        return new PageInfo<SupplierInfo>(list);
    }

    @Override
    public List<SupplierInfo> transfer2SupplierInfo(List<List<String>> excelData) {
        List<SupplierInfo> result = new ArrayList<SupplierInfo>();
        try {
            for (List<String> _rowData : excelData) {
                SupplierInfo supplierInfo = new SupplierInfo();
                supplierInfo.setSupplierCode(StringUtils.isBlank(_rowData.get(0)) ? null : _rowData.get(0));
                supplierInfo.setSupplierName(StringUtils.isBlank(_rowData.get(1)) ? null : _rowData.get(1));
                supplierInfo.setPinyinCode(StringUtils.isBlank(_rowData.get(2)) ? null : _rowData.get(2));
                supplierInfo.setContactName(StringUtils.isBlank(_rowData.get(3)) ? null : _rowData.get(3));
                supplierInfo.setContactPhone(StringUtils.isBlank(_rowData.get(4)) ? null : _rowData.get(4));
                supplierInfo.setContactEmail(StringUtils.isBlank(_rowData.get(5)) ? null : _rowData.get(5));
                supplierInfo.setDeliveryRebate(StringUtils.isBlank(_rowData.get(6)) ? null : Double.valueOf(_rowData.get(6)));
                supplierInfo.setRegularRebate(StringUtils.isBlank(_rowData.get(7)) ? null : Double.valueOf(_rowData.get(7)));
                supplierInfo.setSupplierAddress(StringUtils.isBlank(_rowData.get(8)) ? null : _rowData.get(8));
                supplierInfo.setRemark(StringUtils.isBlank(_rowData.get(9)) ? null : _rowData.get(9));
                supplierInfo.setStatus(StringUtils.isBlank(_rowData.get(10)) ? null : Boolean.valueOf(_rowData.get(10)));
                result.add(supplierInfo);
            }
        } catch (Exception e) {
            LogUtil.error(e, logger, "导入供货商信息格式有误");
            throw new RuntimeException("导入供货商信息格式有误", e);
        }
        return result;
    }

}
