package cn.cash.register.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.cash.register.common.Constants;
import cn.cash.register.controller.frontstage.ControllerExceptionHandler;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.util.LogUtil;

/**
 * 收银员登录拦截器
 * 
 * @author 51
 * @version $Id: CheckCashierIntercepter.java, v 0.1 2018年4月17日 下午2:33:17 51 Exp $
 */
@Component
public class CheckCashierIntercepter extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute(Constants.LOGIN_FLAG_SELLER); // 从session中获取用户信息
        Object exchangeId = request.getSession().getAttribute(Constants.CURRENT_JOB_ID); // 从session中获取用户信息

        LogUtil.info(logger, "收银员角色拦截,当前登录收银员:{0},当前交接班ID:{1}", obj, exchangeId);

        if (obj == null || !(obj instanceof SellerInfo) || exchangeId == null) {// session中没有值，未登录
            request.getRequestDispatcher("/toCashierLogin").forward(request, response);
            return false;
        } else {// session中有值
            return true;
        }

    }

}
