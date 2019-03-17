package com.woo502.fun.dao;

import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomizedRepository<T> {

	Flux<T> findRadom(int size, Class<T> clazz);

	Mono<T> findOneRandom(Class<T> clazz);

	Mono<T> findOneRandom(String excpetKey, Object exceptValue, Class<T> clazz);
	
	Flux<Long> findDistinct(Class<T> clazz, String distinct, Map<String, Object> params);
	
}
