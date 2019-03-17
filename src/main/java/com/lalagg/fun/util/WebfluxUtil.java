package com.lalagg.fun.util;

import java.util.Map;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class WebfluxUtil {
	/**
	 * 
	 * @param forwardToPath: forward target path that begin with /.
	 * @param exchange: the current source server exchange
	 * @param forwardAttrs : the attributes that added to forward Exchange.
	 * @return Mono<Void> to signal forwarding request completed.
	 */
	public static ServerWebExchange forward(String forwardToPath,ServerWebExchange exchange,Map<String,Object> forwardAttrs){
	    ServerHttpRequest forwardReq = exchange.getRequest().mutate().path(forwardToPath).build();
	    ServerWebExchange forwardExchange = exchange.mutate().request(forwardReq).build();
        if(null != forwardAttrs && !forwardAttrs.isEmpty()) {
        		forwardExchange.getAttributes().putAll(forwardAttrs);
        }
        return forwardExchange;
	}
}
