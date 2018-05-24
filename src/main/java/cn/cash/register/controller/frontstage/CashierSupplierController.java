/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.SupplierQueryRequest;
import cn.cash.register.dao.domain.SupplierInfo;
import cn.cash.register.service.SupplierService;
import cn.cash.register.util.ResultSet;

/**
 * 供应商相关功能Controller
 * @author HuHui
 * @version $Id: SupplierController.java, v 0.1 2018年4月24日 下午9:08:01 HuHui Exp $
 */
@Controller
@RequestMapping("/cashier/supplier")
public class CashierSupplierController {

    @Resource
    private SupplierService supplierService;

    /**
     * 跳转到供应商信息页
     */
    @GetMapping
    public String list() {
        return "backstage/_supplier-list";
    }

    /**
     * 分页查询供货商
     */
    @ResponseBody
    @RequestMapping(value = "/queryPage")
    public ResultSet querySupplierByList(SupplierQueryRequest request) {
        PageInfo<SupplierInfo> supplierList = supplierService.queryList(request);
        return ResultSet.success().put("page", supplierList);
    }

    /**
     * 增加或编辑供货商
     * @return 返回主键
     */
    @ResponseBody
    @RequestMapping(value = "/addOrUpdate")
    public ResultSet addSupplierInfo(SupplierInfo supplier) {
        supplier.validate();
        if (null == supplier.getId()) {
            supplierService.add(supplier);
        } else {
            supplierService.update(supplier);
        }
        return ResultSet.success();
    }

    /**
     * 根据主键id删除供货商
     * @return 1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/delById")
    public ResultSet deleteSupplierInfo(Long id) {
        int result = supplierService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 根据id查询供货商信息
     */
    @ResponseBody
    @RequestMapping(value = "/getById")
    public ResultSet querySupplierById(Long id) {
        SupplierInfo supplier = supplierService.queryById(id);
        return ResultSet.success().put("supplier", supplier);
    }

    /**
     * 查询所有供货商名称
     */
    @ResponseBody
    @GetMapping(value = "/queryAllSupplierNames")
    public ResultSet queryAllSupplierNames() {
        List<String> names = supplierService.querySupplierNames();
        return ResultSet.success().put("names", names);
    }

}
