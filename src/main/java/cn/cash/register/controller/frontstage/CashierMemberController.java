/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.MemberInfoQueryRequest;
import cn.cash.register.dao.domain.MemberInfo;
import cn.cash.register.dao.domain.MemberRank;
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
@RequestMapping("/cashier/member")
public class CashierMemberController {

    private static final Logger   logger = LoggerFactory.getLogger(CashierMemberController.class);

    @Resource
    private MemberService         memberService;

    @Resource
    private MemberRechargeService memberRechargeService;

    @Resource
    private LogService            logService;

    /****************************会员信息相关接口****************************/

    @GetMapping
    public String list() {
        return "frontstage/_member-list";
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

    /****************************会员等级相关接口****************************/

    /**
     * 查询所有会员等级信息
     */
    @ResponseBody
    @GetMapping("/rank/listAll")
    public ResultSet queryAllRankList() {
        List<MemberRank> memberRanks = memberService.listAllRank();
        return ResultSet.success().put("memberRanks", memberRanks);
    }

}
