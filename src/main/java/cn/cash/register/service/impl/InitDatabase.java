/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 在Spring容器启动前创建数据库
 * @author HuHui
 * @version $Id: InitDatabase.java, v 0.1 2018年6月16日 上午9:27:38 HuHui Exp $
 */
@Component
public class InitDatabase implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mysql";
        String username = "root";
        String password = "huhui";
        Connection conn = null;
        try {

            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, username, password);

            String sql = "CREATE DATABASE /*!32312 IF NOT EXISTS*/`cash-register` /*!40100 DEFAULT CHARACTER SET utf8 */";
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }

}
