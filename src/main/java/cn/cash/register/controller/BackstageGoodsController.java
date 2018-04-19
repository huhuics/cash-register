package cn.cash.register.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.request.PageInfoQueryRequest;
import cn.cash.register.util.ResultSet;

/**
 * 后台管理商品相关Controller
 * 
 * @author 51
 * @version $Id: BackstageGoodsController.java, v 0.1 2018年4月18日 下午4:24:57 51 Exp $
 */
@Controller
@RequestMapping("/backstage/goods")
public class BackstageGoodsController {

    private static final Logger logger = LoggerFactory.getLogger(BackstageGoodsController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "backstage/_goods-list";
    }

    /**
     * 商品资料列表
     * 
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultSet queryList(PageInfoQueryRequest request) {

        Object pageInfo = new Object(); // TODO 需要根据查询条件查询并返回PageInfo对象

        return ResultSet.success().put("page", pageInfo);
    }

}
