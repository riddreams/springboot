package com.yan.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lwyan on 2018-05-19 14:15
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/").setViewName("/index");
		registry.addViewController("/login").setViewName("/login");
		registry.addViewController("/home").setViewName("/home");
		registry.addViewController("/register").setViewName("/register");
	}
}