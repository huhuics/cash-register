/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.cash.register.common.Constants;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.dao.domain.SystemParameter;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.service.SystemParameterService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.DbUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 系统设置Controller
 * @author HuHui
 * @version $Id: SystemConfigController.java, v 0.1 2018年5月18日 下午3:02:11 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/admin/systemConfig")
public class SystemConfigController {

    private static final Logger    logger = LoggerFactory.getLogger(SystemConfigController.class);

    @Resource
    private SystemParameterService systemParameterService;

    @Resource
    private SellerInfoService      sellerInfoService;

    @RequestMapping
    public String configPage() {
        return "backstage/_system-config";
    }

    /**
     * 初始化系统
     * 
     * @param newValue 即shopName
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/initSystem")
    public ResultSet initSystem(String newValue, HttpSession session) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");

        SellerInfo admin = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_ADMIN);

        SystemParameter shopNameParam = systemParameterService.getByCode(Constants.SHOP_NAME);

        //修改shop_name
        systemParameterService.updateById(shopNameParam.getId(), newValue);
        sellerInfoService.updateShopName(admin.getId(), newValue);

        //修改register_time
        SystemParameter registerTimeParam = systemParameterService.getByCode(Constants.REGISTER_TIME);
        systemParameterService.updateById(registerTimeParam.getId(), DateUtil.getNewFormatDateString(new Date()));

        //设置试用到期时间
        SystemParameter invalidTimeParam = systemParameterService.getByCode(Constants.INVALID_TIME);
        Date invadeTime = DateUtils.addDays(new Date(), 15);
        systemParameterService.updateById(invalidTimeParam.getId(), DateUtil.getNewFormatDateString(invadeTime));

        //设置系统为已初始化
        SystemParameter isInitParam = systemParameterService.getByCode(Constants.IS_INIT);
        systemParameterService.updateById(isInitParam.getId(), Constants.TRUE);

        //TODO
        SystemParameter isAuthParam = systemParameterService.getByCode(Constants.IS_AUTHORIZED);
        systemParameterService.updateById(isAuthParam.getId(), Constants.TRUE);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setPettyAmount")
    public ResultSet setPettyAmount(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.PETTY_AMOUNT);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        systemParameterService.updateById(param.getId(), newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setRelatedEmail")
    public ResultSet setRelatedEmail(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.RELATED_EMAIL);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        systemParameterService.updateById(param.getId(), newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setPhone")
    public ResultSet setPhone(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.PHONE);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        systemParameterService.updateById(param.getId(), newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setAddress")
    public ResultSet setAddress(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.ADDRESS);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        systemParameterService.updateById(param.getId(), newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @PostMapping(value = "/setIsAuthorized")
    public ResultSet setIsAuthorized(String newValue) {
        AssertUtil.assertNotBlank(newValue, "值不能为空");
        SystemParameter param = systemParameterService.getByCode(Constants.IS_AUTHORIZED);
        if (param == null) {
            return ResultSet.error("无此参数");
        }

        systemParameterService.updateById(param.getId(), newValue);

        return ResultSet.success("设置成功");
    }

    @ResponseBody
    @GetMapping(value = "/queryRemainDays")
    public ResultSet queryRemainDays() {
        SystemParameter isAuthParam = systemParameterService.getByCode(Constants.IS_AUTHORIZED);
        if (isAuthParam != null && StringUtils.equals(isAuthParam.getParamValue(), Constants.TRUE)) {//已激活
            return ResultSet.success();
        }
        SystemParameter invalidTimeParam = systemParameterService.getByCode(Constants.INVALID_TIME);

        Date invalidTime = DateUtil.parseDateNewFormat(invalidTimeParam.getParamValue());

        long diff = DateUtil.getDiffDays(invalidTime, new Date());

        LogUtil.info(logger, "invalidTime={0},diff={1}", invalidTime, diff);

        if (diff >= 0) {
            return ResultSet.success().put("diff", diff);
        }

        return ResultSet.error("请购买正版");
    }

    /**
     * 根据param_code查询参数值
     * @param paramCode {@link Constants}
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/queryByCode")
    public ResultSet queryByCode(String paramCode) {
        AssertUtil.assertNotBlank(paramCode, "参数不能为空");
        SystemParameter byCode = systemParameterService.getByCode(paramCode);
        return ResultSet.success().put("byCode", byCode);
    }

    /**
     * 系统备份
     * 
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/dbBackup")
    public ResultSet dbBackup() {
        if (DbUtil.backup()) {
            return ResultSet.success();
        }
        return ResultSet.error();
    }

    /**
     * 系统还原
     * 
     * @param file
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/dbRestore")
    public ResultSet dbRestore(MultipartFile file, HttpSession session) {
        LogUtil.info(logger, "收到系统还原请求");
        AssertUtil.assertNotNull(file, "系统异常:上传sql文件对象为空");

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

        // 2.系统还原
        if (DbUtil.restore(destinationFile.getAbsolutePath())) {
            return ResultSet.success();
        }
        return ResultSet.error();
    }

    @ResponseBody
    @GetMapping(value = "/truncateAllTables")
    public ResultSet truncateAllTables() {
        systemParameterService.truncateAllTables();
        return ResultSet.success();
    }

}
