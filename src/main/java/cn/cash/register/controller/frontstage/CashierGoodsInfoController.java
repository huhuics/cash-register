/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.GoodsInfoInportRequest;
import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.common.request.GoodsInfoRequest;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.enums.LogSourceEnum;
import cn.cash.register.enums.SubSystemTypeEnum;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.LogService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.NumUtil;
import cn.cash.register.util.PinyinUtil;
import cn.cash.register.util.ResultSet;

/**
 * 商品信息Controller
 * @author HuHui
 * @version $Id: GoodsInfoController.java, v 0.1 2018年4月25日 下午9:22:57 HuHui Exp $
 */
@Controller
@RequestMapping("/cashier/goods")
public class CashierGoodsInfoController {

    private static final Logger logger = LoggerFactory.getLogger(CashierGoodsInfoController.class);

    @Resource
    private GoodsInfoService    goodsInfoService;

    @Resource
    private LogService          logService;

    private static final String SEP    = ",";

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
     * 修改商品信息
     * 1:修改成功,0:修改失败
     */
    @ResponseBody
    @RequestMapping(value = "/updateGoodsInfo")
    public ResultSet updateGoodsInfo(GoodsInfoRequest request, HttpSession session) {
        AssertUtil.assertNotNull(request.getId(), "商品id不能为空");
        int result = goodsInfoService.update(request);
        if (result > 0) {
            SellerInfo seller = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_SELLER);
            logService.record(LogSourceEnum.backstage, SubSystemTypeEnum.employee, seller.getSellerNo(), "修改商品" + request.getBarCode());
        }
        return ResultSet.success().put("result", result);
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

    /**
     * 生成字符串的拼音首字母
     * @param goodsName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/genePinyinShort")
    public ResultSet genePinyinShort(String goodsName) {
        AssertUtil.assertNotBlank(goodsName, "名称不能为空");
        String pinyin = PinyinUtil.getPinyinHeadLowerChar(goodsName);
        return ResultSet.success().put("pinyin", pinyin);
    }

    /**
     * 导出商品数据为Excel文件
     */
    @ResponseBody
    @RequestMapping(value = "/export")
    public ResultSet export(GoodsInfoQueryRequest request) {
        String excelFilePath = goodsInfoService.export(request);
        return ResultSet.success().put("excelFilePath", excelFilePath);
    }

    /**
     * 将Excel中的商品数据导入到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/inport")
    public ResultSet inport(GoodsInfoInportRequest request) {
        goodsInfoService.inport(request);
        return ResultSet.success();
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
