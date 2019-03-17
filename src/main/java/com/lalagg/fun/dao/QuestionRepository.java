package com.lalagg.fun.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.lalagg.fun.model.Question;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, Long>, CustomizedRepository<Question> {

}
