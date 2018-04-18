package cn.cash.register.common.request.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 基础请求类
 * 
 * @author 51
 * @version $Id: BaseRequest.java, v 0.1 2018年4月18日 下午4:32:00 51 Exp $
 */
public abstract class BaseRequest implements Serializable {

    /**  */
    private static final long serialVersionUID = 1584615303866040072L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * 参数校验
     */
    public abstract void validate();

}
