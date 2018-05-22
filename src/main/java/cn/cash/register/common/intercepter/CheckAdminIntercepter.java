package cn.cash.register.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.cash.register.common.Constants;
import cn.cash.register.dao.domain.SellerInfo;

/**
 * 管理员登录拦截器
 * 
 * @author 51
 * @version $Id: CheckAdminIntercepter.java, v 0.1 2018年4月17日 下午2:32:35 51 Exp $
 */
@Component
public class CheckAdminIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute(Constants.LOGIN_FLAG_ADMIN); // 从session中获取用户信息
        if (obj == null || !(obj instanceof SellerInfo)) {// session中没有值，未登录
            request.getRequestDispatcher("/adminLogin").forward(request, response);
            return false;
        } else {// session中有值
            return true;
        }

    }

}
