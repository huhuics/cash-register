/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.MemberInfoQueryRequest;
import cn.cash.register.common.request.MemberRankQueryRequest;
import cn.cash.register.common.request.MemberRechargeCheckQueryRequest;
import cn.cash.register.common.request.MemberRechargeQueryRequest;
import cn.cash.register.dao.domain.MemberInfo;
import cn.cash.register.dao.domain.MemberIntegral;
import cn.cash.register.dao.domain.MemberRank;
import cn.cash.register.dao.domain.MemberRechargeDetail;
import cn.cash.register.dao.domain.RechargeCheckDetail;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.enums.LogSourceEnum;
import cn.cash.register.enums.SubSystemTypeEnum;
import cn.cash.register.service.LogService;
import cn.cash.register.service.MemberRechargeService;
import cn.cash.register.service.MemberService;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 会员功能相关Controller
 * @author HuHui
 * @version $Id: MemberController.java, v 0.1 2018年4月24日 下午10:15:58 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/member")
public class MemberController {

    private static final Logger   logger = LoggerFactory.getLogger(MemberController.class);

    @Resource
    private MemberService         memberService;

    @Resource
    private MemberRechargeService memberRechargeService;

    @Resource
    private LogService            logService;

    /****************************会员信息相关接口****************************/

    @GetMapping
    public String list() {
        return "backstage/_member-list";
    }

    /**
     * 会员列表
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public ResultSet queryList(MemberInfoQueryRequest request) {
        LogUtil.info(logger, "[Controller]收到#会员列表查询#请求,request={0}", request);

        PageInfo<MemberInfo> pageInfo = memberService.queryList(request);

        LogUtil.info(logger, "[Controller]#会员列表查询#请求处理,pageInfo={0}", pageInfo);
        return ResultSet.success().put("page", pageInfo);
    }

    /**
     * 添加或更新会员
     */
    @ResponseBody
    @RequestMapping(value = "/addOrUpdate")
    public ResultSet addOrUpdate(MemberInfo memberInfo, HttpSession session) {
        LogUtil.info(logger, "[Controller]收到#添加或更新会员#请求");
        SellerInfo seller = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_SELLER);
        // 根据ID是否为空判断是新增还是编辑
        if (memberInfo.getId() == null) {
            LogUtil.info(logger, "[Controller]#添加会员#,memberInfo={0}", memberInfo);
            memberService.addMember(memberInfo);
            logService.record(LogSourceEnum.backstage, SubSystemTypeEnum.employee, seller.getSellerNo(), "增加会员" + memberInfo.getMemberNo());
        } else {
            LogUtil.info(logger, "[Controller]#修改会员#,memberInfo={0}", memberInfo);
            memberService.updateMember(memberInfo);
            logService.record(LogSourceEnum.backstage, SubSystemTypeEnum.employee, seller.getSellerNo(), "修改会员" + memberInfo.getMemberNo());
        }

        return ResultSet.success();
    }

    /**
     * 根据ID获取会员信息
     */
    @ResponseBody
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResultSet getById(long id) {
        LogUtil.info(logger, "[Controller]收到#根据ID获取会员信息#请求,id={0}", id);

        MemberInfo memberInfo = memberService.queryMember(id);

        return ResultSet.success().put("memberInfo", memberInfo);
    }

    /**
     * 根据ID删除会员
     */
    @ResponseBody
    @RequestMapping(value = "/delById", method = RequestMethod.POST)
    public ResultSet delById(Long id, HttpSession session) {
        LogUtil.info(logger, "[Controller]收到#根据ID删除会员信息#请求,id={0}", id);
        SellerInfo seller = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_SELLER);
        memberService.deleteMember(id);
        logService.record(LogSourceEnum.backstage, SubSystemTypeEnum.employee, seller.getSellerNo(), "删除会员" + id);
        return ResultSet.success();
    }

    /**
     * 修改会员积分值
     * @param moneyStr 变动金额,单位:元
     */
    @ResponseBody
    @RequestMapping(value = "/updateIntegral", method = RequestMethod.POST)
    public ResultSet updateIntegral(Long memberId, String moneyStr, HttpSession session) {
        LogUtil.info(logger, "[Controller]收到#会员积分变动#请求,id={0},moneyStr={1}", memberId, moneyStr);
        SellerInfo seller = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_SELLER);
        memberService.updateIntegral(memberId, moneyStr);
        logService.record(LogSourceEnum.backstage, SubSystemTypeEnum.employee, seller.getSellerNo(), "修改会员积分" + memberId);
        return ResultSet.success();
    }

    /**
     * 会员分析页面
     */
    @GetMapping("/analysis")
    public String analysis() {
        return "backstage/_member-analysis";
    }

    /**
     * 会员分析
     */
    @ResponseBody
    @GetMapping(value = "/getRankAndCounts")
    public ResultSet getRankAndCounts() {
        JSONArray rankAndCounts = memberService.getRankAndCounts();
        return ResultSet.success().put("rankAndCounts", rankAndCounts);
    }

    /****************************会员等级相关接口****************************/

    @GetMapping("/rank")
    public String rankList() {
        return "backstage/_member-rank-list";
    }

    /**
     * 分页查询会员等级信息
     */
    @ResponseBody
    @RequestMapping("/rank/list")
    public ResultSet queryRankList(MemberRankQueryRequest request) {
        PageInfo<MemberRank> memberRanks = memberService.listRank(request);
        return ResultSet.success().put("page", memberRanks);
    }

    /**
     * 查询所有会员等级信息
     */
    @ResponseBody
    @GetMapping("/rank/listAll")
    public ResultSet queryAllRankList() {
        List<MemberRank> memberRanks = memberService.listAllRank();
        return ResultSet.success().put("memberRanks", memberRanks);
    }

    /**
     * 增加会员等级信息
     */
    @ResponseBody
    @RequestMapping(value = "/rank/addOrUpdate", method = RequestMethod.POST)
    public ResultSet addMemRank(MemberRank rank) {
        // 根据ID是否为空判断是新增还是编辑
        if (rank.getId() == null) {
            memberService.addMemRank(rank);
        } else {
            memberService.updateMemRank(rank);
        }
        return ResultSet.success();
    }

    /**
     * 删除会员等级信息
     */
    @ResponseBody
    @RequestMapping(value = "/rank/delete", method = RequestMethod.POST)
    public ResultSet deleteMemRank(Long id) {
        int ret = memberService.deleteMemRank(id);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 根据id查询会员等级
     */
    @ResponseBody
    @RequestMapping(value = "/rank/getById", method = RequestMethod.POST)
    public ResultSet queryMemRank(Long id) {
        MemberRank memberRank = memberService.queryMemRank(id);
        return ResultSet.success().put("memberRank", memberRank);
    }

    /****************************会员积分方式相关接口****************************/

    /**
     * 会员积分策略页面
     */
    @GetMapping(value = "/integral")
    public String integral() {
        return "backstage/_member-integral";
    }

    /**
     * 查询会员积分策略（策略只有一条记录）
     */
    @ResponseBody
    @GetMapping(value = "/integral/query")
    public ResultSet queryMemIntegral() {
        MemberIntegral memberIntegral = memberService.queryMemIntegral();
        return ResultSet.success().put("memberIntegral", memberIntegral);
    }

    /**
     * 修改会员积分策略
     */
    @ResponseBody
    @PostMapping(value = "/integral/update")
    public ResultSet updateMemIntegral(MemberIntegral integral) {
        int ret = memberService.updateMemIntegral(integral);
        return ResultSet.success().put("ret", ret);
    }

    /****************************会员充值相关接口****************************/

    /**
     * 充值明细
     */
    @ResponseBody
    @PostMapping(value = "/recharge/query")
    public ResultSet queryRechargeDetail(MemberRechargeQueryRequest request) {
        PageInfo<MemberRechargeDetail> rechargeDetails = memberRechargeService.query(request);
        return ResultSet.success().put("rechargeDetails", rechargeDetails);
    }

    /**
     * 储值卡对账
     */
    @ResponseBody
    @PostMapping(value = "/recharge/check")
    public ResultSet queryRechargeCheck(MemberRechargeCheckQueryRequest request) {
        List<RechargeCheckDetail> checkDetails = memberRechargeService.list(request);
        return ResultSet.success().put("checkDetails", checkDetails);
    }

}
