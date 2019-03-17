package com.lalagg.fun.dao;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.lalagg.fun.model.Answer;

import reactor.core.publisher.Flux;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, Long>{

//	@Query(sort = "{ qId : 1 }")
	public Flux<Answer> findByQIdIn(Collection<Long> qIds, Sort sort);
	
	@Query(sort = "{ qId : 1 }")
	public Flux<Answer> findAllByQId(Long qId);
	
}
