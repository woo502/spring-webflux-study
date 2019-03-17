package com.woo502.fun.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.WebFilter;

import com.woo502.fun.filter.CustomTokenReactiveAuthenticationManager;
import com.woo502.fun.filter.ServerHttpCustomAuthenticationConverter;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
			.csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .logout().disable()
//	        .authorizeExchange()
//	        .pathMatchers("/")
//	        .authenticated()
//        .and()
    	.addFilterAt(customAuthenticationFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
			.authorizeExchange()
			.pathMatchers("/user/wxAuth/**", "/user/authErr")
			.permitAll()
		    .anyExchange()
		    .authenticated()
        .and()
        .build();

//		return http.build();
	}

	private WebFilter customAuthenticationFilter() {
        AuthenticationWebFilter bearerAuthenticationFilter;
        ServerAuthenticationConverter bearerConverter;
        ReactiveAuthenticationManager authManager;

        authManager  = new CustomTokenReactiveAuthenticationManager();
        bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
        bearerConverter = new ServerHttpCustomAuthenticationConverter();

        bearerAuthenticationFilter.setServerAuthenticationConverter(bearerConverter);
//        bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/api/**"));

        return bearerAuthenticationFilter;
	}
	
	
}
