/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.dao.domain.MemberInfo;
import cn.cash.register.service.MemberService;
import cn.cash.register.util.ResultSet;

/**
 * 会员功能相关Controller
 * @author HuHui
 * @version $Id: MemberController.java, v 0.1 2018年4月24日 下午10:15:58 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    /**
     * 根据关键字搜索会员
     * @param keyword 会员号/姓名/手机号
     * @return  会员信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchMemberInfo")
    public ResultSet searchMemberInfo(String keyword) {
        List<MemberInfo> memberInfos = memberService.search(keyword);
        return ResultSet.success().put("memberInfos", memberInfos);
    }

}
