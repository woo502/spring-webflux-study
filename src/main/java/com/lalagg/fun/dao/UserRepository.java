package com.lalagg.fun.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.lalagg.fun.model.User;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Long> {

	Mono<User> findByOpenId(String openId);

	Mono<Boolean> existsByOpenId(String openId);

}
