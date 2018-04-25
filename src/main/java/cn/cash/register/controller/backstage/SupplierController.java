/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

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
@RequestMapping("/admin/supplier")
public class SupplierController {

    @Resource
    private SupplierService supplierService;

    /**
     * 跳转到供应商信息页
     */
    @GetMapping
    public String list() {
        //TODO
        return "";
    }

    /**
     * 增加供货商
     * @return 返回主键
     */
    @ResponseBody
    @RequestMapping(value = "/addSupplierInfo")
    public ResultSet addSupplierInfo(SupplierInfo supplier) {
        Long result = supplierService.add(supplier);
        return ResultSet.success().put("result", result);
    }

    /**
     * 根据主键id删除供货商
     * @return 1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSupplierInfo")
    public ResultSet deleteSupplierInfo(Long id) {
        int result = supplierService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 修改供货商
     * @return 1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateSupplierInfo")
    public ResultSet updateSupplierInfo(SupplierInfo supplier) {
        int result = supplierService.update(supplier);
        return ResultSet.success().put("result", result);
    }

    /**
     * 根据id查询供货商信息
     */
    @ResponseBody
    @RequestMapping(value = "/querySupplierById")
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

    /**
     * 分页查询供货商
     */
    @ResponseBody
    @RequestMapping(value = "/querySupplierByList")
    public ResultSet querySupplierByList(SupplierQueryRequest request) {
        PageInfo<SupplierInfo> supplierList = supplierService.queryList(request);
        return ResultSet.success().put("supplierList", supplierList);
    }

}
