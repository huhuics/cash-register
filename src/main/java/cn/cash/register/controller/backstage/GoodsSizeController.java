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

import cn.cash.register.dao.domain.GoodsSize;
import cn.cash.register.service.GoodsSizeService;
import cn.cash.register.util.ResultSet;

/**
 * 商品尺寸Controller
 * @author HuHui
 * @version $Id: GoodsSizeController.java, v 0.1 2018年4月25日 下午9:19:55 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsSizeController {

    @Resource
    private GoodsSizeService sizeService;

    /**
     * 增加商品尺寸
     * @param sizeName 尺寸名称,如L,M,S
     * @return 主键
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsSize")
    public ResultSet addGoodsSize(String sizeName) {
        Long id = sizeService.addSize(sizeName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品尺寸
     * @param id 商品尺寸主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsSize")
    public ResultSet deleteGoodsSize(Long id) {
        int result = sizeService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 查询所有商品尺寸
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsSize")
    public ResultSet queryAllGoodsSize() {
        List<GoodsSize> sizes = sizeService.queryAll();
        return ResultSet.success().put("sizes", sizes);
    }

}
