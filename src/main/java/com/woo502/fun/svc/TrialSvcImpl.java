package com.woo502.fun.svc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.woo502.fun.dao.AnswerRepository;
import com.woo502.fun.dao.IdGenRepository;
import com.woo502.fun.dao.QuestionRepository;
import com.woo502.fun.dao.TrialRepository;
import com.woo502.fun.dao.UserAnswerRepository;
import com.woo502.fun.model.Answer;
import com.woo502.fun.model.Question;
import com.woo502.fun.model.Trial;
import com.woo502.fun.model.UserAnswer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TrialSvcImpl implements TrialSvc {

	@Autowired
	private IdGenRepository idGen;
	@Autowired
	private TrialRepository trialRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private UserAnswerRepository userAnswerRepository;
	
	@Override
	public Mono<Trial> create(Long userId) throws Exception{

		return questionRepository.findRadom(10, Question.class)
		.collectList()
		.flatMap(list -> {

			Trial trial = new Trial(userId);
			trial.setQlist(list);
			
			List<Long> qIds = new ArrayList<>();
			for (int i=0; i<list.size(); i++) {
				qIds.add(list.get(i).getqId());
			}
			
			return answerRepository.findByQIdIn(qIds, null)
			.collectList()
			.flatMap(alist -> {
				
				trial.setAlist(alist);
				return idGen.genId(Trial.class)
						.flatMap(id -> {
							trial.settId((long)id);
							for (Answer a: trial.getAlist()) {
								a.settId((long)id);
								a.setuId(userId);
							}
							return trialRepository.save(trial);
						});
			});
		});
	}
	
	@Override
	public Mono<Question> changeQuestion(long Id, long tId) throws Exception {
		
		return trialRepository.findById(tId)
		.flatMap(trial -> {
			
			List<Long> qIds = new ArrayList<>();
			for (int i=0; i<trial.getQlist().size(); i++) {
				qIds.add(trial.getQlist().get(i).getqId());
			}
			
			return questionRepository.findOneRandom("_id", qIds, Question.class)
				.flatMap(q -> {
					return answerRepository.findAllByQId(q.getqId())
							.collectList()
							.flatMap(alist -> {
								for (int i=0; i<trial.getQlist().size(); i++) {
									if (trial.getQlist().get(i).getqId().longValue() == Id) {
										trial.getQlist().set(i, q);
										break;
									}
								}
								
								q.setAnswers(alist.toArray(new Answer[2]));
								
								for (int i=0; i<trial.getAlist().size(); i++) {
									if (trial.getAlist().get(i).getqId() == Id) {
										trial.getAlist().set(i, alist.get(0));
										alist.remove(0);
										if (alist.size() == 0) break;
									}
								}
								
								return trialRepository.save(trial).thenReturn(q);
							});
				});
				
		});
		
//		return questionRepository.findOneRandom("_id", Id, Question.class)
//				.flatMap(q -> answerRepository.findAllByQId(q.getqId())
//				.collectList()
//				.flatMap(alist -> trialRepository.findById(tId)
//						.flatMap(t -> {
//							
//							for (int i=0; i<t.getQlist().size(); i++) {
//								if (t.getQlist().get(i).getqId().longValue() == Id) {
//									t.getQlist().set(i, q);
//									break;
//								}
//							}
//							
//							q.setAnswers(alist.toArray(new Answer[2]));
//							
//							for (int i=0; i<t.getAlist().size(); i++) {
//								if (t.getAlist().get(i).getqId() == Id) {
//									t.getAlist().set(i, alist.get(0));
//									alist.remove(0);
//									if (alist.size() == 0) break;
//								}
//							}
//							
//							return trialRepository.save(t).map(t1 -> {return q;});
//						})
//				)
//			);
	}
	
	@Override
	public Mono<Answer> modifyAnswer(long tId, long aId, String text, byte isRight) throws Exception {
		
		return trialRepository.findById(tId)
			.flatMap(trial -> {
				Answer answer = null;
				for (int i=0;i<trial.getAlist().size();i++) {
					answer = trial.getAlist().get(i);
					if (answer.getAnsId().longValue() == aId) {
						answer.setText(text);
						answer.setIsRight(isRight);
						break;
					}
				}
				
				return trialRepository.updateInnerAnswer(trial, answer)
						.thenReturn(answer);
			});
		
	}
	
	@Override
	public Mono<Boolean> addAnswers(Flux<UserAnswer> answers) throws Exception {
		return answers.flatMap(ans -> {
				return idGen.genId(UserAnswer.class)
				.flatMap(id -> {
					ans.setId((long)id);
					return userAnswerRepository.save(ans);
				});
		})
		.log()
		.all(b -> b.getId() != null && b.getId() != 0)
		;
		
	}
	
	@Override
	public Mono<Trial> completeTrial(Long tId, String poster, String imgId, String voiceId, Integer limitUser, Long money,
			Integer correct) throws Exception {
		return trialRepository.findById(tId)
			.flatMap(trial -> {
				trial.setPicId(imgId);
				trial.setVoiceId(voiceId);
				trial.setLimitUser(limitUser);
				trial.setMoney(money);
				trial.setCorrect(correct);
				trial.setPoster(poster);
				trial.setStatus(2);
				
				return trialRepository.save(trial);
			});
	}
	
	@Override
	public Flux<Trial> userTrialList(Long userId) throws Exception{
		return trialRepository.findByUserId(userId, Sort.by(Direction.DESC, "createTime"));
	}
 	
	@Override
	public Flux<Trial> userAnswers(Long userId) throws Exception{
		return userAnswerRepository.findDistinct(UserAnswer.class, "tid", new HashMap<String, Object>(){
			{
				this.put("userId", userId);
				this.put("sort", "createTime");
				this.put("direction", Direction.DESC);
			}
		}).flatMap(tid -> {
			return trialRepository.findById(tid);
		})
		.sort(new Comparator<Trial>() {
			@Override
			public int compare(Trial o1, Trial o2) {
				return o1.getCreateTime().getTime() > o2.getCreateTime().getTime() ? 1 : -1;
			}
		});
	}
	
	@Override
	public Mono<Trial> detail(Long tId, Long userId) throws Exception{
		return trialRepository.findById(tId).flatMap(trial -> {
			return userAnswerRepository.findByUserIdAndTid(userId, tId, Sort.by(Direction.ASC, "_id"))
					.collectList()
					.map(alist -> {
						List<Answer> official = trial.getAlist();
						for (Answer answer : official) {
							for (UserAnswer userAnswer: alist) {
								if (answer.getAnsId().longValue() == userAnswer.getaId().longValue()) {
									answer.setUserChoose((byte)1);
									break;
								}
							}
						}
						return trial;
					});
		});
	}
}
