/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.SupplierQueryRequest;
import cn.cash.register.dao.SupplierInfoMapper;
import cn.cash.register.dao.domain.SupplierInfo;
import cn.cash.register.service.SupplierService;

/**
 * 供货商服务接口实现类
 * @author HuHui
 * @version $Id: SupplierServiceImpl.java, v 0.1 2018年4月24日 下午8:42:43 HuHui Exp $
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Resource
    private SupplierInfoMapper infoMapper;

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

}
