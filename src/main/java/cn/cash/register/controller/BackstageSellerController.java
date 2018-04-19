package cn.cash.register.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.SellerInfoQueryRequest;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 后台管理收银员相关Controller
 * 
 * @author 51
 * @version $Id: BackstageSellerController.java, v 0.1 2018年4月19日 下午8:02:52 51 Exp $
 */
@Controller
@RequestMapping("/admin/seller")
public class BackstageSellerController {

    private static final Logger logger = LoggerFactory.getLogger(BackstageSellerController.class);

    @Resource
    private SellerInfoService   sellerInfoService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "backstage/_seller-list";
    }

    /**
     * 收银员列表
     * 
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public ResultSet queryList(SellerInfoQueryRequest request) {
        LogUtil.info(logger, "[Controller]收到#收银员列表查询#请求,request={0}", request);

        PageInfo<SellerInfo> pageInfo = sellerInfoService.queryList(request);

        LogUtil.info(logger, "[Controller]#收银员列表查询#请求处理,pageInfo={0}", pageInfo);
        return ResultSet.success().put("page", pageInfo);
    }

}
