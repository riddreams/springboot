package com.yan.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lwyan on 2018-05-19 14:15
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/setting/settingbase").setViewName("setting/settingbase");
		registry.addViewController("/setting/home").setViewName("setting/home");
		registry.addViewController("/enjoy/enjoyIndex").setViewName("enjoy/enjoyIndex");
		registry.addViewController("/enjoy/game").setViewName("enjoy/game");
		registry.addViewController("/study/studyIndex").setViewName("study/studyIndex");
		registry.addViewController("/study/spring_aop").setViewName("study/spring_aop");
		registry.addViewController("/study/Echarts").setViewName("study/Echarts");
	}

	// 登录验证拦截器bean
	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}
	// 配置登录拦截规则
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

		// 排除配置
		/*
		addInterceptor.excludePathPatterns("/");
		addInterceptor.excludePathPatterns("/login");
		addInterceptor.excludePathPatterns("/index");
		addInterceptor.excludePathPatterns("/register");
		*/

		// 拦截配置
		addInterceptor.addPathPatterns("/setting/**")
				.addPathPatterns("/enjoy/game")
				.addPathPatterns("/note/sendNote");
	}
	// 登录验证拦截器实现类
	private class SecurityInterceptor extends HandlerInterceptorAdapter {

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			HttpSession session = request.getSession();
			if (session.getAttribute("userDO") != null)
				return true;
			// 跳转登录
			String url = "/login";
			response.sendRedirect(url);
			return false;
		}
	}

	// 文件上传解析器bean
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
}