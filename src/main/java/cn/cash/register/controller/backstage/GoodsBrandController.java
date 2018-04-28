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

import cn.cash.register.dao.domain.GoodsBrand;
import cn.cash.register.service.GoodsBrandService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.ResultSet;

/**
 * 商品品牌Controller
 * @author HuHui
 * @version $Id: GoodsBrandController.java, v 0.1 2018年4月25日 下午9:18:12 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsBrandController {

    @Resource
    private GoodsBrandService brandService;

    /**
     * 增加商品品牌
     * @param brandName 商品品牌名称
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsBrand")
    public ResultSet addGoodsBrand(String brandName) {
        AssertUtil.assertNotBlank(brandName, "品牌名称不能为空");
        Long id = brandService.addBrand(brandName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品品牌
     * @param id 商品品牌主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsBrand")
    public ResultSet deleteGoodsBrand(Long id) {
        int result = brandService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 修改商品品牌
     * @param brand  只需填充对象的id和brandName两个值
     * @return     1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsBrand")
    public ResultSet updateGoodsBrand(GoodsBrand brand) {
        AssertUtil.assertNotBlank(brand.getBrandName(), "品牌名称不能为空");
        int result = brandService.update(brand);
        return ResultSet.success().put("result", result);
    }

    /**
     * 查询所有商品品牌
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsBrand")
    public ResultSet queryAllGoodsBrand() {
        List<GoodsBrand> brands = brandService.queryAll();
        return ResultSet.success().put("brands", brands);
    }

}
