package com.lalagg.fun.svc;

import com.lalagg.fun.model.User;

import reactor.core.publisher.Mono;

public interface UserSvc {

	Mono<User> createOrUpdate(String jscode) throws Exception;

}
