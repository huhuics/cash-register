package cn.cash.register.controller.backstage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.util.ResultSet;

/**
 * 后台管理商品相关Controller
 * @author 51
 * @version $Id: GoodsController.java, v 0.1 2018年4月18日 下午4:24:57 51 Exp $
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsInfoService    goodsInfoService;

    /**
     * 跳转到商品资料页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "backstage/_goods-list";
    }

    /**
     * 查询商品资料列表
     */
    @ResponseBody
    @RequestMapping(value = "/goodsInfoList")
    public ResultSet queryGoodsInfoList(GoodsInfoQueryRequest request) {

        PageInfo<GoodsInfo> queryList = goodsInfoService.queryList(request);

        return ResultSet.success().put("page", queryList);
    }

    /**
     * 新增商品单位
     */

}
