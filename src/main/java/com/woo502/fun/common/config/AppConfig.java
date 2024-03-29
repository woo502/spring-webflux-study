package com.woo502.fun.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;

import com.woo502.fun.model.User;


@Configuration
public class AppConfig {

	@Bean
	@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
	public User authUser() {
		return new User();
	}
	
}
