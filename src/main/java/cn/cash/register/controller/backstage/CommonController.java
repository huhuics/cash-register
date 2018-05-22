package cn.cash.register.controller.backstage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.Constants;
import cn.cash.register.dao.SystemParameterMapper;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.dao.domain.SystemParameter;
import cn.cash.register.enums.SellerRoleEnum;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 后台管理基本Controller
 * 
 * @author 51
 * @version $Id: CommonController.java, v 0.1 2018年4月19日 下午6:42:19 51 Exp $
 */
@Controller
@RequestMapping("/")
public class CommonController {

    private static final Logger   logger = LoggerFactory.getLogger(CommonController.class);

    @Resource
    private SellerInfoService     sellerInfoService;

    @Resource
    private SystemParameterMapper systemParameterMapper;

    /**
     * 后台管理首页
     * 
     * @return
     */
    @RequestMapping(value = "/admin/index")
    public String index(Model model) {
        return "backstage/index";
    }

    /**
     * 管理员登录页面
     * 
     * @return
     */
    @GetMapping(value = "/toAdminLogin")
    public String toLogin() {
        return "backstage/login";
    }

    /**
     * 管理员登录
     * 
     * @return
     */
    @PostMapping(value = "/adminLogin")
    @ResponseBody
    public ResultSet login(String loginName, String loginPassword, HttpSession session) {
        LogUtil.info(logger, "[Controller]收到#管理员登录#请求,loginName={0},loginPassword={1}", loginName, loginPassword);

        session.removeAttribute(Constants.LOGIN_FLAG_ADMIN);// 移除当前可能有的登录用户

        // 校验登录用户名密码
        SellerInfo seller = sellerInfoService.login(loginName, loginPassword);
        AssertUtil.assertEquals(seller.getRole(), SellerRoleEnum.admin.getCode(), "该用户不是管理员");

        session.setAttribute(Constants.LOGIN_FLAG_ADMIN, seller);

        SystemParameter isInit = systemParameterMapper.selectByCode(Constants.IS_INIT);
        if (isInit == null || StringUtils.equals(isInit.getParamValue(), Constants.FALSE)) {
            return ResultSet.success().put("init", "false");
        } else {
            return ResultSet.success().put("init", "true");
        }
    }

    /**
     * 管理员登出
     */
    @ResponseBody
    @RequestMapping(value = "/admin/logout")
    public ResultSet exchangeJob(HttpSession session) {
        // 删除存在session中的记录
        session.removeAttribute(Constants.LOGIN_FLAG_ADMIN);
        return ResultSet.success();
    }
}
