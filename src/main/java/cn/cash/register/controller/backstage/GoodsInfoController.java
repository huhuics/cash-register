/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsBatchEditRequest;
import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.common.request.GoodsInfoRequest;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.RoyaltyType;
import cn.cash.register.enums.UpdateFieldEnum;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;
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

    private static final Logger logger = LoggerFactory.getLogger(GoodsInfoController.class);

    @Resource
    private GoodsInfoService    goodsInfoService;

    private static final String SEP    = ",";

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
        validateAddRequest(request);
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
        AssertUtil.assertNotNull(request.getId(), "商品id不能为空");
        int result = goodsInfoService.update(request);
        return ResultSet.success().put("result", result);
    }

    /**
     * 删除商品信息
     * 可删除单个商品,也支持批量删除
     * @param idStr  商品主键id,使用半角逗号隔开
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsInfo")
    public ResultSet deleteGoodsInfo(String idStr) {
        AssertUtil.assertNotBlank(idStr, "商品id不能为空");
        String[] idArray = idStr.split(SEP);
        Long[] ids = (Long[]) ConvertUtils.convert(idArray, Long.class);
        goodsInfoService.delete(Arrays.asList(ids));
        return ResultSet.success();
    }

    /**
     * 根据商品id查询商品详情
     * @param goodsInfoId  商品id
     * @return             商品信息对象
     */
    @ResponseBody
    @RequestMapping(value = "/queryGoodsInfoById")
    public ResultSet queryGoodsInfoById(Long goodsInfoId) {
        AssertUtil.assertNotNull(goodsInfoId, "商品id不能为空");
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
        AssertUtil.assertNotBlank(keyword, "查询关键字不能为空");
        List<GoodsInfo> goodsInfos = goodsInfoService.search(keyword);
        return ResultSet.success().put("goodsInfos", goodsInfos);
    }

    /**
     * 批量修改操作
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/batchUpdate")
    public ResultSet batchUpdate(GoodsBatchEditRequest request) {
        LogUtil.info(logger, "[Controller]接收到批量编辑请求,request={0}", request);
        request.validate();

        String[] idArray = request.getTargetIds().split(SEP);
        Long[] ids = (Long[]) ConvertUtils.convert(idArray, Long.class);
        List<Long> goodsIds = Arrays.asList(ids);

        // 提成方式
        if (StringUtils.isNotBlank(request.getRoyaltyType())) {
            RoyaltyType royaltyType = new RoyaltyType();
            royaltyType.setType(request.getRoyaltyType());
            royaltyType.setValue(request.getRoyaltyValue());
            goodsInfoService.batchUpdate(goodsIds, JSON.toJSONString(royaltyType), UpdateFieldEnum.royaltyType);
        }
        // 分类
        if (StringUtils.isNotBlank(request.getCategoryName())) {
            goodsInfoService.batchUpdate(goodsIds, request.getCategoryName(), UpdateFieldEnum.categoryName);
        }
        // 添加标签
        if (StringUtils.isNotBlank(request.getGoodsTagAdd())) {
            goodsInfoService.batchUpdate(goodsIds, request.getGoodsTagAdd(), UpdateFieldEnum.goodsTag);
        }
        // 移除标签
        if (StringUtils.isNotBlank(request.getGoodsTagRemove())) {
            goodsInfoService.batchUpdate(goodsIds, request.getGoodsTagRemove(), UpdateFieldEnum.goodsTag);
        }
        // 品牌
        if (StringUtils.isNotBlank(request.getGoodsBrand())) {
            goodsInfoService.batchUpdate(goodsIds, request.getGoodsBrand(), UpdateFieldEnum.goodsBrand);
        }
        // 供货商 
        if (StringUtils.isNotBlank(request.getSupplierName())) {
            goodsInfoService.batchUpdate(goodsIds, request.getSupplierName(), UpdateFieldEnum.supplierName);
        }
        // 是否积分
        if (StringUtils.isNotBlank(request.getIsIntegral())) {
            Boolean bool = transferStringToBoolean(request.getIsIntegral());
            if (null != bool) {
                goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.isIntegral);
            }
        }
        // 优惠情况
        if (StringUtils.isNotBlank(request.getIsVipPrice())) {
            Boolean bool = transferStringToBoolean(request.getIsVipPrice());
            if (null != bool) {
                //TODO goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.);
            }
        }
        // 状态
        if (StringUtils.isNotBlank(request.getGoodsStatus())) {
            Boolean bool = transferStringToBoolean(request.getGoodsStatus());
            if (null != bool) {
                goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.goodsStatus);
            }
        }
        // 是否赠品
        if (StringUtils.isNotBlank(request.getIsGift())) {
            Boolean bool = transferStringToBoolean(request.getIsGift());
            if (null != bool) {
                goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.isGift);
            }
        }
        // 是否隐藏
        if (StringUtils.isNotBlank(request.getIsHidden())) {
            Boolean bool = transferStringToBoolean(request.getIsHidden());
            if (null != bool) {
                goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.isHidden);
            }
        }
        // 能否预约 
        if (StringUtils.isNotBlank(request.getIsBooked())) {
            Boolean bool = transferStringToBoolean(request.getIsBooked());
            if (null != bool) {
                goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.isBooked);
            }
        }
        // 是否称重
        if (StringUtils.isNotBlank(request.getIsWeigh())) {
            Boolean bool = transferStringToBoolean(request.getIsWeigh());
            if (null != bool) {
                //TODO goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.isWeigh);
            }
        }
        // 是否时价
        if (StringUtils.isNotBlank(request.getIsTimeingPrice())) {
            Boolean bool = transferStringToBoolean(request.getIsTimeingPrice());
            if (null != bool) {
                goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.isTimeingPrice);
            }
        }
        // 是否固价
        if (StringUtils.isNotBlank(request.getIsFixedPrice())) {
            Boolean bool = transferStringToBoolean(request.getIsFixedPrice());
            if (null != bool) {
                goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.isFixedPrice);
            }
        }

        return ResultSet.success();
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
        AssertUtil.assertNotNull(goodsImageId, "商品图片id不能为空");
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
        AssertUtil.assertNotNull(goodsInfoId, "商品id不能为空");
        goodsInfoService.updateGoodsImage(goodsInfoId, goodsImage);
    }

    @ResponseBody
    @RequestMapping(value = "/genePinyinShort")
    public ResultSet genePinyinShort(String goodsName) {
        AssertUtil.assertNotBlank(goodsName, "商品名称不能为空");
        String pinyin = PinyinUtil.getPinyinHeadLowerChar(goodsName);
        return ResultSet.success().put("pinyin", pinyin);
    }

    private void validateAddRequest(GoodsInfoRequest request) {
        AssertUtil.assertNotBlank(request.getGoodsName(), "商品名称不能为空");
        AssertUtil.assertNotBlank(request.getBarCode(), "商品条码不能为空");
        AssertUtil.assertNotBlank(request.getProductNumber(), "商品货号不能为空");
        AssertUtil.assertNotBlank(request.getCategoryName(), "商品分类不能为空");
        AssertUtil.assertNotNull(request.getGoodsStock(), "商品库存不能为空");
        AssertUtil.assertNotBlank(request.getLastImportPrice(), "进货价不能为空");
        AssertUtil.assertNotBlank(request.getAverageImportPrice(), "评价进货价不能为空");
        AssertUtil.assertNotBlank(request.getSalesPrice(), "销售价不能为空");
    }

    private Boolean transferStringToBoolean(String yesOrNo) {
        if (StringUtils.equals(yesOrNo, "yes")) {
            return true;
        }
        if (StringUtils.equals(yesOrNo, "no")) {
            return false;
        }
        return null;
    }

}
