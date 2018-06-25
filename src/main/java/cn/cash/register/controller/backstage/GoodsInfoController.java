/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.GoodsBatchEditRequest;
import cn.cash.register.common.request.GoodsInfoInportRequest;
import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.common.request.GoodsInfoRequest;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.RoyaltyType;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.enums.LogSourceEnum;
import cn.cash.register.enums.SubSystemTypeEnum;
import cn.cash.register.enums.UpdateFieldEnum;
import cn.cash.register.excel.domain.GoodsInfoExcel;
import cn.cash.register.printer.LabelPrintService;
import cn.cash.register.service.ExcelService;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.LogService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.ConvertUtil;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.NumUtil;
import cn.cash.register.util.PinyinUtil;
import cn.cash.register.util.ResultSet;
import cn.cash.register.util.TitleUtil;

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

    @Resource
    private LogService          logService;

    @Resource
    private LabelPrintService   labelPrintService;

    @Resource
    private ExcelService        excelService;

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
     * 导出商品资料
     */
    @SuppressWarnings("resource")
    @RequestMapping(value = "/exportGoodsInfo")
    public void exportGoodsInfo(GoodsInfoQueryRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        // 查询商品信息
        List<GoodsInfo> list = goodsInfoService.queryListForExport(request);
        List<GoodsInfoExcel> excelDOs = ConvertUtil.convertToExcelDO(list);

        // 根据查询结果在服务端生成excel文件
        String filePath = session.getServletContext().getRealPath(Constants.EXPORT_FILE_RELATIVE_PATH) + File.separator;
        String fileName = "商品资料导出_" + DateUtil.format(new Date(), DateUtil.msecFormat) + ".xls";
        List<String> titles = TitleUtil.getGoodsInfoTitle();
        String fileAbsolutePath = excelService.write(filePath + fileName, titles, excelDOs);

        // 返回生成文件
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileAbsolutePath))); // 获取输入流
        fileName = URLEncoder.encode(fileName, "UTF-8"); // 转码，免得文件名中文乱码
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName); // 设置文件下载头
        response.setContentType("multipart/form-data"); // 设置文件ContentType类型
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while ((len = bis.read()) != -1) {
            out.write(len);
            out.flush();
        }
        out.close();
    }

    /**
     * 商品资料导入
     *
     * @param file
     * @return
     * @throws IOException 
     */
    @ResponseBody
    @PostMapping(value = "/importGoodsInfo")
    public ResultSet importGoodsInfo(MultipartFile file, boolean isAutoCreateBrand, boolean isAutoCreateCategory, boolean isAutoCreateUnit, boolean isExistUpdate, HttpSession session) {
        LogUtil.info(logger, "收到商品资料导入请求");
        AssertUtil.assertNotNull(file, "系统异常:上传文件对象为空");

        // 1.接收文件
        String path = session.getServletContext().getRealPath(Constants.IMPORT_FILE_RELATIVE_PATH);
        String fileName = file.getOriginalFilename();
        LogUtil.info(logger, "文件上传请求:fileName={0}", fileName);

        File destinationFile = new File(path, fileName);
        if (!destinationFile.exists()) {
            destinationFile.mkdirs();
        }

        try {
            //MultipartFile自带的解析方法
            file.transferTo(destinationFile);
            LogUtil.info(logger, "文件上传成功,保存路径:path={0}", path);
        } catch (IllegalStateException | IOException e) {
            LogUtil.error(e, logger, "文件上传异常");
            return ResultSet.error("文件上传异常");
        }

        // 2.导入数据
        GoodsInfoInportRequest importRequest = new GoodsInfoInportRequest();
        importRequest.setFileFullPath(destinationFile.getAbsolutePath()); // 上传的文件
        importRequest.setIsAutoCreateBrand(isAutoCreateBrand);
        importRequest.setIsAutoCreateCategory(isAutoCreateCategory);
        importRequest.setIsAutoCreateUnit(isAutoCreateUnit);
        importRequest.setIsExistUpdate(isExistUpdate);

        LogUtil.info(logger, "商品资料导入请求importRequest={0}", importRequest);
        goodsInfoService.inport(importRequest);

        return ResultSet.success("数据导入成功");
    }

    /**
     * 增加商品信息
     */
    @ResponseBody
    @RequestMapping(value = "/addGoodsInfo")
    public ResultSet addGoodsInfo(GoodsInfoRequest request, HttpSession session) {
        validateAddRequest(request);
        Long id = goodsInfoService.add(request);
        SellerInfo seller = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_ADMIN);
        logService.record(LogSourceEnum.backstage, SubSystemTypeEnum.employee, seller.getSellerNo(), "增加商品" + request.getBarCode());
        return ResultSet.success().put("id", id);
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
            SellerInfo seller = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_ADMIN);
            logService.record(LogSourceEnum.backstage, SubSystemTypeEnum.employee, seller.getSellerNo(), "修改商品" + request.getBarCode());
        }
        return ResultSet.success().put("result", result);
    }

    /**
     * 删除商品信息
     * 可删除单个商品,也支持批量删除
     * @param idStr  商品主键id,使用半角逗号隔开
     */
    @ResponseBody
    @RequestMapping(value = "/deleteGoodsInfo")
    public ResultSet deleteGoodsInfo(String idStr, HttpSession session) {
        LogUtil.info(logger, "收到删除请求,idStr", idStr);
        AssertUtil.assertNotBlank(idStr, "商品id不能为空");
        String[] idArray = idStr.split(SEP);
        Long[] ids = (Long[]) ConvertUtils.convert(idArray, Long.class);
        goodsInfoService.delete(Arrays.asList(ids));
        SellerInfo seller = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_ADMIN);
        logService.record(LogSourceEnum.backstage, SubSystemTypeEnum.employee, seller.getSellerNo(), "删除商品" + idStr);
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
     * 批量修改操作
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/batchUpdate")
    public ResultSet batchUpdate(GoodsBatchEditRequest request, HttpSession session) {
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
                if (bool) {//会员价
                    if (StringUtils.isNotBlank(request.getVipPricePercent())) {
                        goodsInfoService.batchUpdate(goodsIds, request.getVipPricePercent(), UpdateFieldEnum.vipPrice);
                    }
                } else {//会员折扣
                    goodsInfoService.batchUpdate(goodsIds, bool.toString(), UpdateFieldEnum.isVipDiscount);
                }
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

        SellerInfo seller = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_ADMIN);
        logService.record(LogSourceEnum.backstage, SubSystemTypeEnum.employee, seller.getSellerNo(), "批量修改商品");

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
     * 打印商品标签
     */
    @ResponseBody
    @RequestMapping(value = "/printLabel")
    public ResultSet printLabel(Long goodsInfoId) {
        GoodsInfo goodsInfo = goodsInfoService.queryById(goodsInfoId);
        labelPrintService.print(goodsInfo);
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
