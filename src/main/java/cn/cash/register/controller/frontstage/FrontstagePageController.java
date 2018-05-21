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
public class FrontstagePageController {

    /**
     * 收银端首页
     * 
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model) {
        return "frontstage/index";
    }

    /**
     * 收银端收银页
     * 
     * @return
     */
    @RequestMapping(value = "/cashRegister")
    public String cashRegister() {
        return "frontstage/_cashRegister";
    }

    /**
     * 收银端会员管理页
     * 
     * @return
     */
    @RequestMapping(value = "/memberPage")
    public String memberPage() {
        return "frontstage/_member-list";
    }

    /**
     * 收银端商品编辑页
     * 
     * @return
     */
    @RequestMapping(value = "/goodsEditPage")
    public String goodsEditPage() {
        return "frontstage/_goods-list";
    }

    /**
     * 收银端商品报损页
     * 
     * @return
     */
    @RequestMapping(value = "/goodsLosePage")
    public String goodsLosePage() {
        return "frontstage/_goodsLose-add";
    }

    /**
     * 收银端进货页
     * 
     * @return
     */
    @RequestMapping(value = "/goodsStockAddPage")
    public String goodsStockAddPage() {
        return "frontstage/_goods-stock-add";
    }

    /**
     * 收银端退货页
     * 
     * @return
     */
    @RequestMapping(value = "/refundPage")
    public String refundPage() {
        return "frontstage/_refund";
    }

    /**
     * 收银端设置页
     * 
     * @return
     */
    @RequestMapping(value = "/settings")
    public String settings() {
        return "frontstage/_settings";
    }
}
