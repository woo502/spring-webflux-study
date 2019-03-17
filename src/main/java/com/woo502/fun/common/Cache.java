package com.woo502.fun.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class Cache {

	private static ReactiveStringRedisTemplate strTemplate;

	@Autowired
	public static void setStrTemplate(ReactiveStringRedisTemplate strTemplate) {
		Cache.strTemplate = strTemplate;
	}
	
	public static void set(String key, String value) {
		ReactiveValueOperations<String, String> opsForValue = strTemplate.opsForValue();
		opsForValue.set(key, value)
		.subscribe();
	}
	
	public static Mono<String> get(String key) {
		return strTemplate.opsForValue().get(key);
	}
}
