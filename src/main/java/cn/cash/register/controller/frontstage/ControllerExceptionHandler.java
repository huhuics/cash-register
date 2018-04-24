/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * Controller异常处理器
 * @author HuHui
 * @version $Id: ExceptionHandler.java, v 0.1 2018年4月18日 下午5:07:20 HuHui Exp $
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResultSet handleRuntimeException(RuntimeException e) {
        LogUtil.error(e, logger, "收到运行时异常{0}", e.getLocalizedMessage());
        return ResultSet.error(e.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultSet handleException(Exception e) {
        LogUtil.error(e, logger, "收到未知异常{0}", e.getLocalizedMessage());
        return ResultSet.error(e.getLocalizedMessage());
    }

}
