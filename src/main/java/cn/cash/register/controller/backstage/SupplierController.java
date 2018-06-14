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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.SupplierQueryRequest;
import cn.cash.register.dao.domain.SupplierInfo;
import cn.cash.register.service.SupplierService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.ExcelUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 供应商相关功能Controller
 * @author HuHui
 * @version $Id: SupplierController.java, v 0.1 2018年4月24日 下午9:08:01 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/supplier")
public class SupplierController {

    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Resource
    private SupplierService     supplierService;

    /**
     * 跳转到供应商信息页
     */
    @GetMapping
    public String list() {
        return "backstage/_supplier-list";
    }

    /**
     * 分页查询供货商
     */
    @ResponseBody
    @RequestMapping(value = "/queryPage")
    public ResultSet querySupplierByList(SupplierQueryRequest request) {
        PageInfo<SupplierInfo> supplierList = supplierService.queryList(request);
        return ResultSet.success().put("page", supplierList);
    }

    /**
     * 分页导出供货商
     */
    @SuppressWarnings("resource")
    @RequestMapping(value = "/exportPage")
    public void exportSupplierByList(SupplierQueryRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        PageInfo<SupplierInfo> supplierList = supplierService.queryList(request);

        // 根据查询结果在服务端生成excel文件
        String filePath = session.getServletContext().getRealPath(Constants.EXPORT_FILE_RELATIVE_PATH) + File.separator;
        String fileName = "供货商信息导出_" + DateUtil.format(new Date(), DateUtil.msecFormat) + ".xlsx";
        String sheetName = "供货商";

        List<List<String>> data = new ArrayList<List<String>>();
        String[] filterRow = { "状态(true:启用;false:禁用)", ExcelUtil.obj2String(request.isStatus()), "", "姓名", request.getSupplierName() };
        data.add(Arrays.asList(filterRow));
        String[] pageRow = { "当前页", supplierList.getPageNum() + "/" + supplierList.getPages(), "每页数量", supplierList.getPageSize() + "", "总数", supplierList.getTotal() + "" };
        data.add(Arrays.asList(pageRow));
        String[] theadRow = { "编号", "名称", "拼音码", "联系人", "联系电话", "联系邮箱", "配送费返点", "固定返利点", "地址", "备注", "状态(true:启用;false:禁用)" };
        data.add(Arrays.asList(theadRow));

        List<SupplierInfo> list = supplierList.getList();
        for (SupplierInfo _obj : list) {
            List<String> _row = new ArrayList<String>();
            _row.add(_obj.getSupplierCode());
            _row.add(_obj.getSupplierName());
            _row.add(_obj.getPinyinCode());
            _row.add(_obj.getContactName());
            _row.add(_obj.getContactPhone());
            _row.add(_obj.getContactEmail());
            _row.add(ExcelUtil.obj2String(_obj.getDeliveryRebate()));
            _row.add(ExcelUtil.obj2String(_obj.getRegularRebate()));
            _row.add(_obj.getSupplierAddress());
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

    /**
     * 导入供货商
     *
     * @param file
     * @return
     * @throws IOException 
     */
    @ResponseBody
    @PostMapping(value = "/importList")
    public ResultSet importList(MultipartFile file, HttpSession session) {
        LogUtil.info(logger, "收到供货商资料导入请求");
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

        // 2.读取数据
        List<SupplierInfo> suppliers = null;
        try {
            List<List<String>> excelData = ExcelUtil.readExcel(destinationFile, 11); // 供货商信息共有11列
            AssertUtil.assertTrue(CollectionUtils.isNotEmpty(excelData), "未读取到任何信息");
            suppliers = supplierService.transfer2SupplierInfo(excelData);
        } catch (IOException e) {
            LogUtil.error(e, logger, "文件读取异常");
            return ResultSet.error("文件读取异常");
        }

        // 3.存储数据
        int successCount = 0;
        int failCount = 0;
        String failInfo = "";
        for (SupplierInfo _supplier : suppliers) {
            LogUtil.info(logger, "[Controller]#导入供货商#,supplierInfo={0}", _supplier);
            try {
                supplierService.add(_supplier);
                successCount++;
            } catch (Exception e) {
                failCount++;
                failInfo = failInfo + "【" + _supplier.toString() + "】";
            }
        }

        String resultInfo = "成功导入" + successCount + "条供货商信息";
        if (failCount > 0) {
            resultInfo += (",以下" + failCount + "条导入失败:" + failInfo);
        }

        return ResultSet.success(resultInfo);
    }

    /**
     * 增加或编辑供货商
     * @return 返回主键
     */
    @ResponseBody
    @RequestMapping(value = "/addOrUpdate")
    public ResultSet addSupplierInfo(SupplierInfo supplier) {
        supplier.validate();
        if (null == supplier.getId()) {
            supplierService.add(supplier);
        } else {
            supplierService.update(supplier);
        }
        return ResultSet.success();
    }

    /**
     * 根据主键id删除供货商
     * @return 1:删除成功,0:删除失败
     */
    @ResponseBody
    @RequestMapping(value = "/delById")
    public ResultSet deleteSupplierInfo(Long id) {
        int result = supplierService.delete(id);
        return ResultSet.success().put("result", result);
    }

    /**
     * 根据id查询供货商信息
     */
    @ResponseBody
    @RequestMapping(value = "/getById")
    public ResultSet querySupplierById(Long id) {
        SupplierInfo supplier = supplierService.queryById(id);
        return ResultSet.success().put("supplier", supplier);
    }

    /**
     * 查询所有供货商名称
     */
    @ResponseBody
    @GetMapping(value = "/queryAllSupplierNames")
    public ResultSet queryAllSupplierNames() {
        List<String> names = supplierService.querySupplierNames();
        return ResultSet.success().put("names", names);
    }

}
