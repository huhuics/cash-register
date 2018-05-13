package cn.cash.register.controller.frontstage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 收银端基本Controller
 * 
 * @author 51Sun
 * @version $Id: FrontstageCommonController.java, v 0.1 2018年5月13日 下午2:22:53 51Sun Exp $
 */
@Controller
@RequestMapping("/cashier")
public class FrontstageCommonController {

    /**
     * 前端首页
     * 
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model) {
        return "frontstage/index";
    }

    /**
     * 前端登录页
     * 
     * @return
     */
    @RequestMapping(value = "/login")
    public String flogin(Model model) {
        return "frontstage/login";
    }

    /**
     * 前端收银页
     * 
     * @return
     */
    @RequestMapping(value = "/cashRegister")
    public String fcashRegister(Model model) {
        return "frontstage/_cashRegister";
    }
}
