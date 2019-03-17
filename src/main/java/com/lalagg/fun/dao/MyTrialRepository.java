package com.lalagg.fun.dao;

import com.lalagg.fun.model.Answer;
import com.lalagg.fun.model.Trial;
import com.mongodb.client.result.UpdateResult;

import reactor.core.publisher.Mono;

public interface MyTrialRepository {

	Mono<UpdateResult> updateInnerAnswer(Trial trial, Answer answer);
	
}
