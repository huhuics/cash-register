package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.AchievementQueryRequest;
import cn.cash.register.common.request.SellerInfoQueryRequest;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 后台管理收银员相关Controller
 * 
 * @author 51
 * @version $Id: SellerController.java, v 0.1 2018年4月19日 下午8:02:52 51 Exp $
 */
@Controller
@RequestMapping("/admin/seller")
public class SellerController {

    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    @Resource
    private SellerInfoService   sellerInfoService;

    /**
     * 收银员列表页面
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "backstage/_seller-list";
    }

    /**
     * 收银员业绩页面
     * 
     * @return
     */
    @RequestMapping(value = "/achievement", method = RequestMethod.GET)
    public String achievementList() {
        return "backstage/_seller-achievement-list";
    }

    /**
     * 翻页查询收银员列表
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

    /**
     * 查询所有收银员列表
     * 
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/queryAll")
    public ResultSet queryAll() {
        LogUtil.info(logger, "[Controller]收到#查询所有收银员#请求");

        List<SellerInfo> sellerInfos = sellerInfoService.queryAll();

        LogUtil.info(logger, "[Controller]#查询所有收银员#请求处理,sellerInfos={0}", sellerInfos);
        return ResultSet.success().put("sellerInfos", sellerInfos);
    }

    /**
     * 添加或更新收银员
     * 
     * @return
     */
    @RequestMapping(value = "/addOrUpdate")
    @ResponseBody
    public ResultSet addOrUpdate(SellerInfo sellerInfo) {
        LogUtil.info(logger, "[Controller]收到#添加或更新收银员#请求");
        // 根据ID是否为空判断是新增还是编辑
        if (sellerInfo.getId() == null) {
            LogUtil.info(logger, "[Controller]#添加收银员#,sellerInfo={0}", sellerInfo);
            sellerInfo.validate();
            sellerInfoService.addSeller(sellerInfo);
        } else {
            LogUtil.info(logger, "[Controller]#修改收银员#,sellerInfo={0}", sellerInfo);
            sellerInfoService.update(sellerInfo);
        }

        return ResultSet.success();
    }

    /**
     * 根据ID获取收银员信息
     * 
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public ResultSet getById(long id) {
        LogUtil.info(logger, "[Controller]收到#根据ID获取收银员信息#请求,id={0}", id);

        SellerInfo sellerInfo = sellerInfoService.queryById(id);

        return ResultSet.success().put("seller", sellerInfo);
    }

    /**
     * 根据ID删除收银员
     * 
     * @return
     */
    @RequestMapping(value = "/delById", method = RequestMethod.POST)
    @ResponseBody
    public ResultSet delById(Long id) {
        LogUtil.info(logger, "[Controller]收到#根据ID删除收银员信息#请求,id={0}", id);

        sellerInfoService.delete(id);

        return ResultSet.success();
    }

    /**
     * 查询收银员业绩
     */
    @ResponseBody
    @RequestMapping(value = "/achievement/queryPage")
    public ResultSet querySellerAchievement(AchievementQueryRequest request) {
        PageInfo<TradeGoodsDetail> ret = sellerInfoService.queryAchievement(request);
        return ResultSet.success().put("page", ret);
    }

}
