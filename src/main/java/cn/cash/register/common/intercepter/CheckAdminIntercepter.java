package cn.cash.register.common.intercepter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.cash.register.common.Constants;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.enums.SellerRoleEnum;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.util.LogUtil;

/**
 * 管理员登录拦截器
 * 
 * @author 51
 * @version $Id: CheckAdminIntercepter.java, v 0.1 2018年4月17日 下午2:32:35 51 Exp $
 */
@Component
public class CheckAdminIntercepter extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CheckAdminIntercepter.class);

    @Resource
    private SellerInfoService   sellerInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute(Constants.LOGIN_FLAG_ADMIN); // 从session中获取用户信息

        LogUtil.info(logger, "管理员角色拦截,当前登录管理员:{0}", obj);

        if (obj == null || !(obj instanceof SellerInfo)) {// session中没有值，未登录
            request.getSession().removeAttribute(Constants.LOGIN_FLAG_ADMIN);
            request.getRequestDispatcher("/toAdminLogin").forward(request, response);
            return false;
        } else {// session中有值
            SellerInfo sessionSeller = (SellerInfo) obj;
            SellerInfo sellerInfo = sellerInfoService.queryById(sessionSeller.getId());
            if (null == sellerInfo || !StringUtils.equals(sellerInfo.getRole(), SellerRoleEnum.admin.getCode()) || !sellerInfo.getStatus()) {
                request.getSession().removeAttribute(Constants.LOGIN_FLAG_ADMIN);
                request.getRequestDispatcher("/toAdminLogin").forward(request, response);
                return false;
            }
            request.getSession().removeAttribute(Constants.LOGIN_FLAG_ADMIN);
            request.getSession().setAttribute(Constants.LOGIN_FLAG_ADMIN, sellerInfo);
            return true;
        }

    }

}
