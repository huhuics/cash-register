/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.common.request.GoodsInfoRequest;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.enums.UpdateFieldEnum;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.util.NumUtil;
import cn.cash.register.util.PinyinUtil;
import cn.cash.register.util.ResultSet;

/**
 * 商品信息Controller
 * @author HuHui
 * @version $Id: GoodsInfoController.java, v 0.1 2018年4月25日 下午9:22:57 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsInfoController {

    @Resource
    private GoodsInfoService goodsInfoService;

    /**
     * 跳转到商品资料页
     */
    @GetMapping
    public String list() {
        return "backstage/_goods-list";
    }

    /**
     * 查询商品资料列表
     */
    @ResponseBody
    @RequestMapping(value = "/goodsInfoList")
    public ResultSet queryGoodsInfoList(GoodsInfoQueryRequest request) {
        PageInfo<GoodsInfo> queryList = goodsInfoService.queryList(request);
        return ResultSet.success().put("page", queryList);
    }

    /**
     * 增加商品信息
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsInfo")
    public ResultSet addGoodsInfo(GoodsInfoRequest request) {
        Long id = goodsInfoService.add(request);
        return ResultSet.success().put("id", id);
    }

    /**
     * 修改商品信息
     * 1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsInfo")
    public ResultSet updateGoodsInfo(GoodsInfoRequest request) {
        int result = goodsInfoService.update(request);
        return ResultSet.success().put("result", result);
    }

    /**
     * 删除商品信息
     * 可删除单个商品,也支持批量删除
     * @param ids  商品主键id
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsInfo")
    public void deleteGoodsInfo(ArrayList<Long> ids) {
        goodsInfoService.delete(ids);
    }

    /**
     * 根据商品id查询商品详情
     * @param goodsInfoId  商品id
     * @return             商品信息对象
     */
    @ResponseBody
    @RequestMapping(value = "/queryGoodsInfoById")
    public ResultSet queryGoodsInfoById(Long goodsInfoId) {
        GoodsInfo goodsInfo = goodsInfoService.queryById(goodsInfoId);
        return ResultSet.success().put("goodsInfo", goodsInfo);
    }

    /**
     * 根据关键字搜索商品
     * @param keyword 条码/拼音码/商品名
     * @return  商品信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchGoodsInfo")
    public ResultSet searchGoodsInfo(String keyword) {
        List<GoodsInfo> goodsInfos = goodsInfoService.search(keyword);
        return ResultSet.success().put("goodsInfos", goodsInfos);
    }

    /**
     * 批量修改操作
     * @param goodsIds   商品id集合
     * @param newValue   被修改字段的新的值
     * @param filedEnum  被修改的字段枚举{@link UpdateFieldEnum},只有枚举中的字段才支持批量修改
     */
    @ResponseBody
    @RequestMapping(value = "/batchUpdate")
    public void batchUpdate(ArrayList<Long> goodsIds, String newValue, String filedEnumCode) {
        goodsInfoService.batchUpdate(goodsIds, newValue, filedEnumCode);
    }

    /**
     * 获取商品条码
     */
    @ResponseBody
    @GetMapping(value = "/getBarCode")
    public ResultSet getBarCode() {
        return ResultSet.success().put("barCode", NumUtil.getBarCode());
    }

    /**
     * 获取商品图片
     * @param goodsImageId 商品图片id
     * @return 图片对象
     */
    @ResponseBody
    @RequestMapping(value = "/getGoodsImage")
    public ResultSet getGoodsImage(Long goodsImageId) {
        GoodsImage image = goodsInfoService.queryGoodsImage(goodsImageId);
        return ResultSet.success().put("image", image);
    }

    /**
     * 商品图片的增、删、改都可以调用此方法,删除的时候图片数组传null
     * @param goodsInfoId  商品id
     * @param goodsImage   图片二进制数据
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsImage")
    public void updateGoodsImage(Long goodsInfoId, byte[] goodsImage) {
        goodsInfoService.updateGoodsImage(goodsInfoId, goodsImage);
    }

    @ResponseBody
    @RequestMapping(value = "/genePinyinShort")
    public ResultSet genePinyinShort(String goodsName) {
        String pinyin = PinyinUtil.getPinyinHeadLowerChar(goodsName);
        return ResultSet.success().put("pinyin", pinyin);
    }

}
