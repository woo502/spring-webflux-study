package com.woo502.fun.dao;

import com.mongodb.client.result.UpdateResult;
import com.woo502.fun.model.Answer;
import com.woo502.fun.model.Trial;

import reactor.core.publisher.Mono;

public interface MyTrialRepository {

	Mono<UpdateResult> updateInnerAnswer(Trial trial, Answer answer);
	
}
