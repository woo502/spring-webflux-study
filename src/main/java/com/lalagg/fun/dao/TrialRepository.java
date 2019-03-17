package com.lalagg.fun.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.lalagg.fun.model.Trial;

import reactor.core.publisher.Flux;

@Repository
public interface TrialRepository extends ReactiveMongoRepository<Trial, Long>, MyTrialRepository {

	public Flux<Trial> findByUserId(Long userId, Sort sort);
		
}
