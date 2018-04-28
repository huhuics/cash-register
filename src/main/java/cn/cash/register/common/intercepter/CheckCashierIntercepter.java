package cn.cash.register.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.cash.register.common.Constants;

/**
 * 收银员登录拦截器
 * 
 * @author 51
 * @version $Id: CheckCashierIntercepter.java, v 0.1 2018年4月17日 下午2:33:17 51 Exp $
 */
@Component
public class CheckCashierIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute(Constants.LOGIN_FLAG); // 从session中获取用户信息
        // TODO 51 配置拦截器逻辑
        return true;

    }

}
