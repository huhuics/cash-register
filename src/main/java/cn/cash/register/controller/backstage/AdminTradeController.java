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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.MemberInfo;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.MemberService;
import cn.cash.register.service.TradeService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.ExcelUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 后端交易服务Controller
 * 
 * @author 51
 * @version $Id: AdminTradeController.java, v 0.1 2018年5月22日 上午10:24:55 51 Exp $
 */
@Controller
@RequestMapping("/admin/trade")
public class AdminTradeController {

    private static final Logger logger = LoggerFactory.getLogger(AdminTradeController.class);

    @Resource
    private TradeService        tradeService;

    @Resource
    private GoodsInfoService    goodsInfoService;

    @Resource
    private MemberService       memberService;

    /**
     * 销售单据分页查询
     */
    @ResponseBody
    @RequestMapping(value = "/queryTradeDetailList")
    public ResultSet queryTradeDetailList(TradeDetailQueryRequest request) {
        PageInfo<TradeDetail> tradeDetail = tradeService.queryTradeDetailList(request);
        return ResultSet.success().put("page", tradeDetail);
    }

    /**
     * 销售单据分页导出
     */
    @RequestMapping(value = "/exportTradeDetailList")
    public void exportTradeDetailList(TradeDetailQueryRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        PageInfo<TradeDetail> tradeDetail = tradeService.queryTradeDetailList(request);

        // 根据查询结果在服务端生成excel文件
        String filePath = session.getServletContext().getRealPath(Constants.EXPORT_FILE_RELATIVE_PATH) + File.separator;
        String fileName = "销售单据导出_" + DateUtil.format(new Date(), DateUtil.msecFormat) + ".xlsx";
        String sheetName = "销售单据";

        List<List<String>> data = new ArrayList<List<String>>();
        String[] filterRow = { "收银员", request.getSellerNo(), "", "支付方式", request.getPayChenal(), "", "交易类型", request.getTradeType(), "", "起始时间", request.getTradeTimeDown(), "", "截止时间",
                               request.getTradeTimeUp(), "", "订单号", request.getTradeNo() };
        data.add(Arrays.asList(filterRow));
        String[] pageRow = { "当前页", tradeDetail.getPageNum() + "/" + tradeDetail.getPages(), "每页数量", tradeDetail.getPageSize() + "", "总数", tradeDetail.getTotal() + "" };
        data.add(Arrays.asList(pageRow));
        String[] theadRow = { "流水号", "日期", "类型（refund:退款;sales:销售）", "收银员", "会员", "商品数量", "商品原价", "实收金额", "利润", "导购员" };
        data.add(Arrays.asList(theadRow));

        List<TradeDetail> list = tradeDetail.getList();
        for (TradeDetail _obj : list) {
            List<String> _row = new ArrayList<String>();
            _row.add(_obj.getTradeNo());
            _row.add(DateUtil.format(_obj.getTradeTime(), DateUtil.newFormat));
            _row.add(_obj.getTradeType());
            _row.add(_obj.getSellerNo());
            _row.add(_obj.getMemberName());
            _row.add(_obj.getGoodsCount().toString());
            _row.add(_obj.getTotalAmount().toString());
            _row.add(_obj.getTotalActualAmount().toString());
            _row.add(_obj.getProfitAmount().toString());
            _row.add(_obj.getShopperNo());
            data.add(_row);
        }
        try {
            ExcelUtil.createExcel(filePath, fileName, sheetName, data); // 文件成功生成在服务端
        } catch (IOException e) {
            LogUtil.error(e, logger, "文件生成失败");
        }

        // 返回生成文件
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath, fileName))); // 获取输入流
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
     * 商品销售明细分页查询
     */
    @ResponseBody
    @RequestMapping(value = "/queryTradeGoodsDetailList")
    public ResultSet queryTradeGoodsDetailList(TradeGoodsDetailQueryRequest request) {
        PageInfo<TradeGoodsDetail> tradeGoodsDetail = tradeService.queryTradeGoodsDetailList(request);
        return ResultSet.success().put("tradeGoodsDetail", tradeGoodsDetail);
    }

    /**
     * 根据关键字搜索并添加商品
     * @param keyword 条码/拼音码/商品名
     * @return  商品信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchGoodsInfo")
    public ResultSet searchGoodsInfo(String keyword) {
        AssertUtil.assertNotBlank(keyword, "查询关键字不能为空");

        List<GoodsInfo> goodsInfos = goodsInfoService.search(keyword);

        ResultSet resultSet = ResultSet.success().put("goodsInfos", goodsInfos);

        if (CollectionUtils.isEmpty(goodsInfos)) {
            return resultSet.put("size", 0);
        } else {
            return resultSet.put("size", goodsInfos.size());
        }
    }

    /**
     * 根据关键字搜索会员
     * @param keyword 会员号/姓名/手机号
     * @return  会员信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchMemberInfo")
    public ResultSet searchMemberInfo(String keyword) {
        AssertUtil.assertNotBlank(keyword, "关键字不能为空");

        List<MemberInfo> memberInfos = memberService.search(keyword);

        ResultSet resultSet = ResultSet.success().put("memberInfos", memberInfos);

        if (CollectionUtils.isEmpty(memberInfos)) {
            return resultSet.put("size", 0);
        } else {
            return resultSet.put("size", memberInfos.size());
        }
    }

}
