package com.lalagg.fun.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.ReactiveUpdateOperation.TerminatingUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.lalagg.fun.model.Answer;
import com.lalagg.fun.model.Trial;
import com.mongodb.client.result.UpdateResult;

import reactor.core.publisher.Mono;

public class MyTrialRepositoryImpl implements MyTrialRepository{
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	@Override
	public Mono<UpdateResult> updateInnerAnswer(Trial trial, Answer answer) {
		
		Query query = new Query(Criteria.where("_id").is(trial.gettId()).and("alist._id").is(answer.getAnsId()));
        Update update = Update
        		.update("alist.$.text", answer.getText())
        		.set("alist.$.isRight", answer.getIsRight());
        TerminatingUpdate<Trial> apply = mongoTemplate.update(Trial.class)
        .matching(query)
        .apply(update);
		
		return apply.first();
	}

}
