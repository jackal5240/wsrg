package com.ogloba.ew.wsrg.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ogloba.ew.wsrg.interceptor.TerminalInterceptor;
import com.ogloba.ew.wsrg.interceptor.VersionValidIntercetor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private TerminalInterceptor terminalInterceptor;
	
	@Autowired
    private VersionValidIntercetor versionValidIntercetor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(terminalInterceptor)
//				.addPathPatterns("/**")
//				.excludePathPatterns("/actionEvents/**", "/app/login", "/user/**",
//        				"/tests/**", "/localtest/**", "/test/**", "/demo/**",
//        				"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc/**");
		
		registry.addInterceptor(versionValidIntercetor)
        		.addPathPatterns("/**")
        		.excludePathPatterns("/actionEvents/**", "/app/login", "/user/**",
        				"/tests/**", "/localtest/**", "/test/**", "/demo/**",
        				"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc/**");
	}

}
