package com.lalagg.fun.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.lalagg.fun.model.UserAnswer;

import reactor.core.publisher.Flux;

public interface UserAnswerRepository extends ReactiveMongoRepository<UserAnswer, Long>, CustomizedRepository<UserAnswer> {

	Flux<UserAnswer> findTidDistinctByUserId(Long userId, Sort sort);
	
	Flux<UserAnswer> findByUserIdAndTid(Long userId, Long tId, Sort sort);
	
}
