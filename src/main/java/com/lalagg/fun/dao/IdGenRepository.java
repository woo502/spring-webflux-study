package com.lalagg.fun.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.lalagg.fun.model.SequenceId;

import reactor.core.publisher.Mono;

@Repository
public class IdGenRepository {

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	public Mono<Object> genId(Class<? extends Object> T) {
		
		Query query = new Query(Criteria.where("_id").is(T.getSimpleName()));
		Update update = new Update().inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Mono<SequenceId> seq = mongoTemplate.findAndModify(query, update, options, SequenceId.class)
				.switchIfEmpty(mongoTemplate.save(new SequenceId(T.getSimpleName(), 1)));
		
		return seq.flatMap(seqId -> Mono.just(seqId.getSeq()));
		
	}
}
