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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.GoodsTrafficQueryRequest;
import cn.cash.register.common.request.InTrafficRequest;
import cn.cash.register.common.request.OutTrafficRequest;
import cn.cash.register.dao.domain.GoodsTraffic;
import cn.cash.register.service.GoodsTrafficService;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.ExcelUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 货流Controller
 * @author HuHui
 * @version $Id: GoodsTrafficController.java, v 0.1 2018年4月25日 下午9:28:47 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/traffic")
public class GoodsTrafficController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsTrafficController.class);

    @Resource
    private GoodsTrafficService trafficService;

    /**
     * 货流页面
     */
    @GetMapping
    public String list() {
        return "backstage/_goodsTraffic-list";
    }

    /**
     * 商品进货
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addInTraffic")
    public ResultSet addInTraffic(InTrafficRequest request) {
        request.validate();
        Long id = trafficService.addInTraffic(request);
        return ResultSet.success().put("id", id);
    }

    /**
     * 商品出库
     * @return 主键id
     */
    @ResponseBody
    @RequestMapping(value = "/addOutTraffic")
    public ResultSet addOutTraffic(OutTrafficRequest request) {
        request.validate();
        Long id = trafficService.addOutTraffic(request);
        return ResultSet.success().put("id", id);
    }

    /**
     * 根据货流主键id查询详情
     */
    @ResponseBody
    @RequestMapping(value = "/queryById")
    public ResultSet queryById(Long id) {
        GoodsTraffic traffic = trafficService.queryById(id);
        return ResultSet.success().put("traffic", traffic);
    }

    /**
     * 货流分页查询
     */
    @ResponseBody
    @RequestMapping(value = "/queryPage")
    public ResultSet queryList(GoodsTrafficQueryRequest request) {
        PageInfo<GoodsTraffic> traffics = trafficService.queryList(request);
        return ResultSet.success().put("page", traffics);
    }

    /**
     * 货流导出
     */
    @SuppressWarnings("resource")
    @RequestMapping(value = "/exportPage")
    public void exportPage(GoodsTrafficQueryRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        PageInfo<GoodsTraffic> traffics = trafficService.queryList(request);

        // 根据查询结果在服务端生成excel文件
        String filePath = session.getServletContext().getRealPath(Constants.EXPORT_FILE_RELATIVE_PATH) + File.separator;
        String fileName = "货流信息导出_" + DateUtil.format(new Date(), DateUtil.msecFormat) + ".xlsx";
        String sheetName = "货流信息";

        List<List<String>> data = new ArrayList<List<String>>();
        String[] filterRow = { "货流类型", request.getTrafficType(), "", "货流编号", request.getTrafficNo(), "", "起始时间", request.getCreateTimeDown(), "", "截止时间", request.getCreateTimeUp() };
        data.add(Arrays.asList(filterRow));
        String[] pageRow = { "当前页", traffics.getPageNum() + "/" + traffics.getPages(), "每页数量", traffics.getPageSize() + "", "总数", traffics.getTotal() + "" };
        data.add(Arrays.asList(pageRow));
        String[] theadRow = { "货流单号", "货流类型(in:进货;ordinaryOut:普通出库;supplierOut:退货给供货商)", "商品名称", "商品条码", "商品颜色", "商品尺寸", "供货商名称", "商品库存", "进货量", "进货价", "进货赠送量", "预付款", "单位",
                              "出库价格类型(last_import_price:以最近进货价出库;average_import_price:以平均进货价出库;sales_price:以商品销售价出库;trade_price:以商品批发价出库)", "出库价", "出库量", "小计", "操作员编号", "备注",
                              "状态(true:已完成;false:处理中)" };
        data.add(Arrays.asList(theadRow));

        List<GoodsTraffic> list = traffics.getList();
        for (GoodsTraffic _obj : list) {
            List<String> _row = new ArrayList<String>();
            _row.add(_obj.getTrafficNo());
            _row.add(_obj.getTrafficType());
            _row.add(_obj.getGoodsName());
            _row.add(_obj.getBarCode());
            _row.add(_obj.getGoodsColor());
            _row.add(_obj.getGoodsSize());
            _row.add(_obj.getSupplierName());
            _row.add(ExcelUtil.obj2String(_obj.getGoodsStock()));
            _row.add(ExcelUtil.obj2String(_obj.getInCount()));
            _row.add(ExcelUtil.obj2String(_obj.getInAmount()));
            _row.add(ExcelUtil.obj2String(_obj.getFreeCount()));
            _row.add(ExcelUtil.obj2String(_obj.getAdvancePaymentAmount()));
            _row.add(_obj.getQuantityUnit());
            _row.add(_obj.getOutPriceType());
            _row.add(ExcelUtil.obj2String(_obj.getOutAmount()));
            _row.add(ExcelUtil.obj2String(_obj.getOutCount()));
            _row.add(ExcelUtil.obj2String(_obj.getTotalAmount()));
            _row.add(_obj.getOperatorNo());
            _row.add(_obj.getRemark());
            _row.add(ExcelUtil.obj2String(_obj.getStatus()));
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
}
