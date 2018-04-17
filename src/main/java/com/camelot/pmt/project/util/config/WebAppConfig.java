package com.camelot.pmt.project.util.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.camelot.pmt.project.util.interceptor.LogInterceptor;


@SuppressWarnings("deprecation")
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*List<String> list = new ArrayList<>();
		list.add("/css/**");
		list.add("/js/**");
		list.add("/images/**");
		registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**").excludePathPatterns(list);*/
		registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
