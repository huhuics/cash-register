package cn.cash.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面测试Controller
 * <p>用于跳转页面
 * 
 * @author 51
 * @version $Id: PageTestController.java, v 0.1 2018年4月17日 上午10:33:02 51 Exp $
 */
@Controller
@RequestMapping("/test/page")
public class PageTestController {

    /**
     * 后端登陆页
     * @return
     */
    @RequestMapping(value = "/backstage/login")
    public String blogin() {
        return "backstage/login";
    }

    /**
     * 后端面板
     * @return
     */
    @RequestMapping(value = "/backstage/dashboard")
    public String bdashboard() {
        return "backstage/_dashboard";
    }

    /**
     * 后端首页
     * 
     * @return
     */
    @RequestMapping(value = "/backstage")
    public String bindex(Model model) {
        return "backstage/index";
    }

    /**
     * 前端登录页
     * 
     * @return
     */
    @RequestMapping(value = "/frontstage/login")
    public String flogin(Model model) {
        return "frontstage/login";
    }

    /**
     * 前端首页
     * 
     * @return
     */
    @RequestMapping(value = "/frontstage")
    public String findex(Model model) {
        return "frontstage/index";
    }

    /**
     * 前端收银
     * 
     * @return
     */
    @RequestMapping(value = "/frontstage/cashRegister")
    public String fcashRegister(Model model) {
        return "frontstage/_cashRegister";
    }

}
