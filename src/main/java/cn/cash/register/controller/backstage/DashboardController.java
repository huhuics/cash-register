package cn.cash.register.controller.backstage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理首页面板Controller
 * 
 * @author 51
 * @version $Id: DashboardController.java, v 0.1 2018年4月19日 下午7:52:57 51 Exp $
 */
@Controller
@RequestMapping("/admin")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    /**
     * 后端面板
     * 
     * @return
     */
    @RequestMapping(value = "/dashboard")
    public String bdashboard() {
        return "backstage/_dashboard";
    }

}
