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

import cn.cash.register.dao.domain.GoodsTag;
import cn.cash.register.service.GoodsTagService;
import cn.cash.register.util.ResultSet;

/**
 * 商品标签Controller
 * @author HuHui
 * @version $Id: GoodsTagController.java, v 0.1 2018年4月25日 下午9:20:42 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsTagController {

    @Resource
    private GoodsTagService tagService;

    /**
     * 增加商品标签
     * @param tagName 商品标签名称
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsTag")
    public ResultSet addGoodsTag(String tagName) {
        Long id = tagService.add(tagName);
        return ResultSet.success().put("id", id);
    }

    /**
     * 删除商品标签
     * @param id 商品标签主键id
     * @return   1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsTag")
    public ResultSet deleteGoodsTag(Long id) {
        int result = tagService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 修改商品标签
     * @param tag  只需填充对象的id和tagName两个值
     * @return     1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsTag")
    public ResultSet updateGoodsTag(GoodsTag tag) {
        int result = tagService.update(tag);
        return ResultSet.success().put("result", result);
    }

    /**
     * 根据主键查询商品标签
     * @param id  商品标签主键id
     * @return    商品标签对象
     */
    @ResponseBody
    @RequestMapping(value = "/queryGoodsTagById")
    public ResultSet queryGoodsTagById(Long id) {
        GoodsTag tag = tagService.queryById(id);
        return ResultSet.success().put("tag", tag);
    }

    /**
     * 查询所有商品标签
     */
    @ResponseBody
    @GetMapping(value = "/queryAllGoodsTag")
    public ResultSet queryAllGoodsTag() {
        List<GoodsTag> tags = tagService.queryAll();
        return ResultSet.success().put("tags", tags);
    }

}
