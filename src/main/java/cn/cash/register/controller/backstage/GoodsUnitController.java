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

import cn.cash.register.dao.domain.GoodsQuantityUnit;
import cn.cash.register.service.GoodsQuantityUnitService;
import cn.cash.register.util.ResultSet;

/**
 * 商品单位Controller
 * @author HuHui
 * @version $Id: GoodsUnitController.java, v 0.1 2018年4月25日 下午9:21:38 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsUnitController {

    @Resource
    private GoodsQuantityUnitService unitService;

    /**
     * 增加商品单位
     * @param unitName 商品单位名称,如件、个
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsUnit")
    public ResultSet addGoodsUnit(String unitName) {
        Long id = unitService.add(unitName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品单位
     * @param id 商品单位主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsUnit")
    public ResultSet deleteGoodsUnit(Long id) {
        int result = unitService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 修改商品单位
     * @param unit 只需填充对象的id和unitName两个值
     * @return     1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsUnit")
    public ResultSet updateGoodsUnit(GoodsQuantityUnit unit) {
        int result = unitService.update(unit);
        return ResultSet.success().put("result", result);
    }

    /**
     * 根据主键查询商品单位
     * @param id  商品单位主键id
     * @return    商品单位对象
     */
    @ResponseBody
    @RequestMapping(value = "/queryGoodsUnitById")
    public ResultSet queryGoodsUnitById(Long id) {
        GoodsQuantityUnit unit = unitService.queryById(id);
        return ResultSet.success().put("unit", unit);
    }

    /**
     * 查询所有商品单位
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsUnit")
    public ResultSet queryAllGoodsUnit() {
        List<GoodsQuantityUnit> units = unitService.queryAll();
        return ResultSet.success().put("units", units);
    }

}
