package com.woo502.fun.svc;

import com.woo502.fun.model.Answer;
import com.woo502.fun.model.Question;
import com.woo502.fun.model.Trial;
import com.woo502.fun.model.UserAnswer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrialSvc {

	Mono<Trial> create(Long userId) throws Exception;

	Mono<Question> changeQuestion(long Id, long tId) throws Exception;

	Mono<Answer> modifyAnswer(long tId, long aId, String text, byte isRight) throws Exception;

	Mono<Boolean> addAnswers(Flux<UserAnswer> answers) throws Exception;

	Mono<Trial> completeTrial(Long tId, String poster, String imgId, String voiceId, Integer limitUser, Long money,
			Integer correct) throws Exception;

	Flux<Trial> userTrialList(Long userId) throws Exception;

	Flux<Trial> userAnswers(Long userId) throws Exception;

	Mono<Trial> detail(Long tId, Long userId) throws Exception;
	
	

}
