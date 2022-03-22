package com.ggoreb.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ggoreb.practice.interceptor.LoginCheckInterceptor;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	LoginCheckInterceptor loginCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/question/**").addPathPatterns("/mypage");
							// 			강사님 답변 하지만 아래 방식으로 사용할 경우 resource 접근도 안됨.
		//registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/signin" , "/signup","/css/**","/js/**");
		
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
