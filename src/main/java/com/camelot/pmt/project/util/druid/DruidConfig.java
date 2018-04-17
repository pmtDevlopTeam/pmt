package com.camelot.pmt.project.util.druid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;

@Configuration
public class DruidConfig {

	@Bean(name = "myDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DruidDataSource MyDataSource() {
		return new DruidDataSource();
	}
	
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		//设置白名单，为空时都可以访问
		servletRegistrationBean.addInitParameter("allow", "");
		//设置黑名单，黑名单优先于白名单
		servletRegistrationBean.addInitParameter("deny", "192.168.0.1");
		servletRegistrationBean.addInitParameter("loginUsername", "guodx");
		servletRegistrationBean.addInitParameter("loginPassword", "123456");
		//禁用 reset all功能
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.addUrlPatterns("/*");
		bean.addInitParameter("exclusions", "*.js, *.gif, *.jpg, *.png, *.css, *.ico, /druid/*");
		return bean;
	}
}
