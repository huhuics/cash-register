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

import cn.cash.register.dao.domain.GoodsColor;
import cn.cash.register.service.GoodsColorService;
import cn.cash.register.util.ResultSet;

/**
 * 商品颜色Controller
 * @author HuHui
 * @version $Id: GoodsColorController.java, v 0.1 2018年4月25日 下午9:19:08 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsColorController {

    @Resource
    private GoodsColorService colorService;

    /**
     * 增加商品颜色
     * @param colorName 颜色名称
     * @return 主键
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsColor")
    public ResultSet addGoodsColor(String colorName) {
        Long id = colorService.addColor(colorName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品颜色
     * @param id 商品颜色主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsColor")
    public ResultSet deleteGoodsColor(Long id) {
        int result = colorService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 查询所有商品颜色
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsColor")
    public ResultSet queryAllGoodsColor() {
        List<GoodsColor> colors = colorService.queryAll();
        return ResultSet.success().put("colors", colors);
    }

}
