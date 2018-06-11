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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.ExchangeJobQueryRequest;
import cn.cash.register.common.request.SalesAmountQueryRequest;
import cn.cash.register.common.request.SalesBasicFactsQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.dao.domain.ExchangeJobDetail;
import cn.cash.register.dao.domain.GoodsSaleStatistics;
import cn.cash.register.dao.domain.SalesBasicFacts;
import cn.cash.register.service.ExchangeJobService;
import cn.cash.register.service.SalesService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.ExcelUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 销售相关接口的Controller
 * @author HuHui
 * @version $Id: SalesController.java, v 0.1 2018年5月3日 下午3:27:14 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/admin/sales")
public class SalesController {

    private static final Logger logger = LoggerFactory.getLogger(SalesController.class);

    @Resource
    private ExchangeJobService  exchangeJobService;

    @Resource
    private SalesService        salesService;

    /**
     * 查询交接班记录
     */
    @ResponseBody
    @PostMapping(value = "/queryExchangeJobs")
    public ResultSet queryExchangeJobs(ExchangeJobQueryRequest request) {
        List<ExchangeJobDetail> exchangeJobs = exchangeJobService.query(request);
        return ResultSet.success().put("exchangeJobs", exchangeJobs);
    }

    /**
     * 查询营业概况
     */
    @ResponseBody
    @PostMapping(value = "/queryBasicFacts")
    public ResultSet queryBasicFacts(SalesBasicFactsQueryRequest request) {
        AssertUtil.assertNotNull(request, "查询参数不能为空");
        Map<String, SalesBasicFacts> basicFacts = salesService.queryBasicFacts(request);
        return ResultSet.success().put("basicFacts", basicFacts);
    }

    /**
     * 导出营业概况
     * @throws IOException 
     */
    @SuppressWarnings("resource")
    @RequestMapping(value = "/exportBasicFacts")
    public void exportBasicFacts(SalesBasicFactsQueryRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        AssertUtil.assertNotNull(request, "查询参数不能为空");
        Map<String, SalesBasicFacts> basicFacts = salesService.queryBasicFacts(request);

        // 根据查询结果在服务端生成excel文件
        String filePath = session.getServletContext().getRealPath(Constants.EXPORT_FILE_RELATIVE_PATH) + File.separator;
        String fileName = "营业概况导出_" + DateUtil.format(new Date(), DateUtil.msecFormat) + ".xlsx";
        String sheetName = "营业概况";

        List<List<String>> data = new ArrayList<List<String>>();
        String[] filterRow = { "统计起始时间", request.getTimeDown(), "", "统计结束时间", request.getTimeUp() };
        data.add(Arrays.asList(filterRow));
        String[] theadRow = { "", "概况", "现金支付", "银联支付", "储值卡支付", "支付宝支付", "微信支付" };
        data.add(Arrays.asList(theadRow));

        Iterator<String> iterator = basicFacts.keySet().iterator();
        while (iterator.hasNext()) {
            List<String> _row = new ArrayList<String>();
            String _key = iterator.next();
            _row.add(_key);
            SalesBasicFacts _obj = basicFacts.get(_key);
            _row.add(_obj.getBasicFacts());
            _row.add(ExcelUtil.obj2String(_obj.getCash()));
            _row.add(ExcelUtil.obj2String(_obj.getUnionpay()));
            _row.add(ExcelUtil.obj2String(_obj.getBalance()));
            _row.add(ExcelUtil.obj2String(_obj.getAlipay()));
            _row.add(ExcelUtil.obj2String(_obj.getWcpay()));
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
     * 查询商品销售统计
     */
    @ResponseBody
    @RequestMapping(value = "/queryGoodsSaleStatistics")
    public ResultSet queryGoodsSaleStatistics(TradeGoodsDetailQueryRequest request) {
        PageInfo<GoodsSaleStatistics> ret = salesService.queryGoodsSaleStatistics(request);
        return ResultSet.success().put("page", ret);
    }

    /**
     * 查询营业报表
     */
    @ResponseBody
    @RequestMapping(value = "/querySalesAmountByTime")
    public ResultSet querySalesAmountByTime(SalesAmountQueryRequest request) {
        LogUtil.info(logger, "收到查询营业报表请求,request={0}", request);
        JSONArray ret = salesService.querySalesAmountByTime(request);
        LogUtil.info(logger, "查询营业报表请求结果,ret={0}", ret);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 查询交接班记录页面
     */
    @GetMapping(value = "/queryExchangeJobs")
    public String queryExchangeJobsPage() {
        return "backstage/_sales-exchangeJobs";
    }

    /**
     * 查询营业概况页面
     */
    @GetMapping(value = "/queryBasicFacts")
    public String queryBasicFactsPage() {
        return "backstage/_sales-basicFacts";
    }

    /**
     * 查询销售单据页面
     */
    @GetMapping(value = "/tradeDetail")
    public String salesTradeDetailList() {
        return "backstage/_sales-tradeDetail-list";
    }

    /**
     * 查询销售统计页面
     */
    @GetMapping(value = "/goodsSaleStatistics")
    public String goodsSaleStatistics() {
        return "backstage/_sales-statistics-list";
    }

    /**
     * 查询营业报表页面
     */
    @GetMapping(value = "/goodsSaleAmountByTime")
    public String goodsSaleAmountByTime() {
        return "backstage/_sales-amountByTime";
    }

}
