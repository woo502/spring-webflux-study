package com.woo502.fun.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.woo502.fun.model.Question;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, Long>, CustomizedRepository<Question> {

}
