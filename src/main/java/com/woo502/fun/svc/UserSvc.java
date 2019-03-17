package com.woo502.fun.svc;

import com.woo502.fun.model.User;

import reactor.core.publisher.Mono;

public interface UserSvc {

	Mono<User> createOrUpdate(String jscode) throws Exception;

}
