/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cash.register.service.MemberService;

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

}
