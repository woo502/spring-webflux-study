package com.woo502.fun.filter;

import java.util.List;

import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements WebFilter {

	static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		logger.info(getRequestMessage(exchange));
		Mono<Void> filter = chain.filter(exchange);
		exchange.getResponse().beforeCommit(()->{
			logger.info(getResponseMessage(exchange));
			return Mono.empty();
		});
		
		return filter;
	}

	private String getRequestMessage(ServerWebExchange exchange) {
		ServerHttpRequest request = exchange.getRequest();
        HttpMethod method = request.getMethod();
        String path = request.getURI().getPath();
        List<MediaType> acceptableMediaTypes = request.getHeaders().getAccept();
        MediaType contentType = request.getHeaders().getContentType();
        
        StringBuilder sb = new StringBuilder();
        sb.append(method);
        sb.append(" ");
        sb.append(path);
        sb.append(" ");
        sb.append(HttpHeaders.ACCEPT);
        sb.append(": ");
        sb.append(acceptableMediaTypes);
        sb.append(" ");
        sb.append(HttpHeaders.CONTENT_TYPE);
        sb.append(": ");
        sb.append(contentType);
        
        return sb.toString();
	}
	


	private String getResponseMessage(ServerWebExchange exchange) {
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();
        HttpMethod method = request.getMethod();
        String path = request.getURI().getPath();
        MediaType contentType = response.getHeaders().getContentType();
        HttpStatus status = getStatus(response);
        
        StringBuilder sb = new StringBuilder();
        sb.append(method);
        sb.append(" ");
        sb.append(path);
        sb.append(" HTTP");
        sb.append(status.value());
        sb.append(" ");
        sb.append(status.getReasonPhrase());
        sb.append(" ");
        sb.append(HttpHeaders.CONTENT_TYPE);
        sb.append(": ");
        sb.append(contentType);
        
        return sb.toString();
	}
	
	private HttpStatus getStatus(ServerHttpResponse response) {
		
		try {
			HttpStatus statusCode = response.getStatusCode();
			if (statusCode == null) {
				return HttpStatus.CONTINUE;
			}
			return statusCode;
		} catch (Exception e) {
			return HttpStatus.CONTINUE;
		}
		
	}
}
