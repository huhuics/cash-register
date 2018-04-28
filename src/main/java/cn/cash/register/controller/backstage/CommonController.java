package cn.cash.register.controller.backstage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.Constants;
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

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * 后端首页
     * 
     * @return
     */
    @RequestMapping(value = "admin/index")
    public String index(Model model) {
        return "backstage/index";
    }

    /**
     * 登陆页面
     * 
     * @return
     */
    @RequestMapping(value = "adminLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "backstage/login";
    }

    /**
     * 登陆
     * 
     * @return
     */
    @RequestMapping(value = "adminLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultSet login(String loginName, String loginPassword, HttpSession session) {
        LogUtil.info(logger, "[Controller]收到#管理员登录#请求,loginName={0},loginPassword={1}", loginName, loginPassword);
        session.removeAttribute(Constants.LOGIN_FLAG);// 移除当前可能有的登录用户
        // TODO 调用service校验登录名和密码
        // need：校验是否通过标志boolean，管理员用户实体对象

        return ResultSet.success();
    }
}
