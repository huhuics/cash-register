/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.cash.register.dao.domain.GoodsCategory;
import cn.cash.register.service.GoodsCategoryService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 商品种类Controller
 * @author HuHui
 * @version $Id: GoodsCategoryController.java, v 0.1 2018年4月25日 下午9:17:01 HuHui Exp $
 */
@Controller
@RequestMapping("/admin")
public class GoodsCategoryController {

    private static final Logger  logger = LoggerFactory.getLogger(GoodsCategoryController.class);

    @Resource
    private GoodsCategoryService categoryService;

    /**
     * 跳转到商品分类页
     */
    @GetMapping(value = "/goods-category")
    public String list() {
        return "backstage/_goods-category";
    }

    /**
     * 增加商品种类
     * @param category 需填充categoryName和parentId两个字段
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/goods/addGoodsCategory")
    public ResultSet addGoodsCategory(GoodsCategory category) {
        AssertUtil.assertNotBlank(category.getCategoryName(), "商品种类名称不能为空");
        Long id = categoryService.add(category);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品种类
     * 该删除方法会将该结点及其子孙结点都删除
     * @param id 商品种类主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/goods/deleteGoodsCategory")
    public ResultSet deleteGoodsCategory(Long id) {
        categoryService.delete(id);
        return ResultSet.success().put("result", 1);
    }

    /**
     * 修改商品种类
     * @param category  需填充对象的id和categoryName两个值
     * @return          1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/goods/updateGoodsCategory")
    public ResultSet updateGoodsCategory(GoodsCategory category) {
        AssertUtil.assertNotNull(category.getId(), "商品id不能为空");
        AssertUtil.assertNotBlank(category.getCategoryName(), "商品种类名称不能为空");
        int result = categoryService.update(category);
        return ResultSet.success().put("result", result);
    }

    /**
     * 获取商品种类树
     * @param categoryId 当查询整棵树时填1,即根节点id
     * @return json数组
     */
    @ResponseBody
    @RequestMapping(value = "/goods/getGoodsCategoryTree")
    public ResultSet getGoodsCategoryTree(Long categoryId) {
        LogUtil.info(logger, "收到查询商品种类树请求,categoryId={0}", categoryId);
        JSONArray tree = categoryService.getTree(categoryId);
        return ResultSet.success().put("tree", tree);
    }

}
