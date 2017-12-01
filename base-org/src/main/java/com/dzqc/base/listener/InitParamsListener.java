package com.dzqc.base.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author : gongfangchao
 * @describe : 初始化参数监听器
 * @date : 2017/12/1
 **/
public class InitParamsListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String basePath = servletContextEvent.getServletContext().getContextPath();
        servletContextEvent.getServletContext().setAttribute("base", basePath);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
