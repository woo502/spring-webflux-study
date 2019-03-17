package com.woo502.fun.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.woo502.fun.model.User;
import com.woo502.fun.util.WebfluxUtil;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		
//		ServerHttpRequest request = exchange.getRequest();
		
		try {
//			if (!request.getPath().value().contains("user/wxAuth")) {
//				String authKey = request.getHeaders().getFirst("authKey");
//				String[] split = authKey.split(";");
//				String userId = split[0];
//				String utoken = split[1];
//				
//				if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(utoken) || !auth(userId, utoken)) {
//					return chain.filter(WebfluxUtil.forward("/user/authErr", exchange, null));
//				}
				
//				user.setUserId(Long.parseLong(userId));
			
//			}
			
			
		} catch (Exception e) {
			return chain.filter(WebfluxUtil.forward("/user/authErr", exchange, null));
		}
		
		return chain.filter(exchange);
	}
	
	private boolean auth(String userId, String utoken) {
		return Cache.get(userId).block().equals(utoken);
	}

}
