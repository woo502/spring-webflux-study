package com.woo502.fun.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Repository
public class CustomizedRepositoryImpl<T> implements CustomizedRepository<T> {

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	@Override
	public Flux<T> findRadom (int size, Class<T> clazz) {
		SampleOperation matchStage = Aggregation.sample(size);
		Aggregation aggregation = Aggregation.newAggregation(matchStage);
		
		return mongoTemplate.aggregate(aggregation, clazz, clazz);
	}
	
	@Override
	public Mono<T> findOneRandom(Class<T> clazz) {
		SampleOperation simpleStage = Aggregation.sample(1);
		Aggregation aggregation = Aggregation.newAggregation(simpleStage);
		
		
		return mongoTemplate.aggregate(aggregation, clazz, clazz).elementAt(0);
	}
	
	@Override
	public Mono<T> findOneRandom(String excpetKey, Object exceptValue, Class<T> clazz) {
		SampleOperation simpleStage = Aggregation.sample(1);
		MatchOperation matchStage = null;
		if (exceptValue instanceof List) {
			matchStage = Aggregation.match(Criteria.where(excpetKey).nin((List)exceptValue));
		} else {
			matchStage = Aggregation.match(Criteria.where(excpetKey).ne(exceptValue));
		}
		Aggregation aggregation = Aggregation.newAggregation(simpleStage, matchStage);
		
		return mongoTemplate.aggregate(aggregation, clazz, clazz).elementAt(0);
	}

	@Override
	public Flux<Long> findDistinct(Class<T> clazz, String distinctField, Map<String, Object> params) {
		
		Query query = new Query();
		for (String key: params.keySet()) {
			if (!"sort".equals(key) && !"direction".equals(key)) {
				query.addCriteria(Criteria.where(key).is(params.get(key)));
			} else if ("sort".equals(key)) {
				query.with(Sort.by((Direction)params.get("direction"), params.get("sort").toString()));
			}
		}
		return mongoTemplate.query(clazz)
		.distinct(distinctField)
		.matching(query)
		.as(Long.class)
		.all();
		
	}
	
}
