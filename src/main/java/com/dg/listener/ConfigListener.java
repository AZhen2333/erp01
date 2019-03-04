package com.dg.listener;

import ch.qos.logback.core.net.server.ServerListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.HashMap;
import java.util.Map;

public class ConfigListener {
    private static Map<String, String> conf = new HashMap<>();

    public static Map<String, String> getConf() {
        return conf;
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        conf.clear();
    }

    public void contextInitialized(ServletContextEvent evt) {
        ServletContext sc = evt.getServletContext();
        // 项目发布,当前运行环境的绝对路径
        conf.put("realPath", sc.getRealPath("/").replaceFirst("/", ""));
        // servletContextPath,默认""
        conf.put("contextPath", sc.getContextPath());
    }
}
