package com.woo502.fun.svc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woo502.fun.model.Answer;
import com.woo502.fun.model.Question;
import com.woo502.fun.model.Trial;
import com.woo502.fun.model.UserAnswer;
import com.woo502.fun.svc.TrialSvc;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrialSvcTest {

	@Autowired
	private TrialSvc svc;
	
	@Before
	public void setUp() throws Exception {
		
	}

//	@Test
	public void createTrialTest() {
		try {
			Mono<Trial> create = svc.create(1l);
			
			Trial block = create.doOnError(new Consumer<Throwable>() {

				@Override
				public void accept(Throwable t) {
					t.printStackTrace();
				}
			})
			.log()
			.doOnNext(new Consumer<Trial>() {

				@Override
				public void accept(Trial t) {
					System.out.println(t);
				}
			})
			.block();
			
			System.out.println(block);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	@Test
	public void changeQuestionTest() {
		try {
			Mono<Question> changeQuestion = svc.changeQuestion(3l, 3l);
			Question block = changeQuestion.block();
			System.out.println(block);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void modifyAnserTest() {
		try {
			Mono<Answer> changeQuestion1 = svc.modifyAnswer(3, 32, "问题16答案2修改1", (byte)1);

			Mono<Answer> changeQuestion2 = svc.modifyAnswer(3, 31, "问题16答案1修改2", (byte)2);
			
			Mono<Answer> changeQuestion3 = svc.modifyAnswer(3, 13, "问题7答案1修改3", (byte)1);
			
			Mono<Answer> changeQuestion4 = svc.modifyAnswer(3, 14, "问题7答案2修改4", (byte)2);
			
			Mono<Answer> changeQuestion5 = svc.modifyAnswer(3, 16, "问题8答案2修改5", (byte)1);
			
			changeQuestion1.subscribe();
			changeQuestion2.subscribe();
			changeQuestion3.subscribe();
			changeQuestion4.subscribe();
			changeQuestion5.subscribe();
			
			Thread.sleep(2000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void completeTrial() {
		try {
			Mono<Trial> completeTrial = svc.completeTrial(3l, "123", "123", "", 1, 0l, 10);
			Trial block = completeTrial.block();
			System.out.println(block);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void addAnswersTest() {
		Flux<UserAnswer> flux;
		try {
			List<UserAnswer> answers = new ArrayList<>();
			answers.add(new UserAnswer(1l, 3l, 16l, "问题16答案2修改"));
			answers.add(new UserAnswer(1l, 3l, 7l, "问题7答案1"));
			
			flux = Flux.fromArray(answers.toArray(new UserAnswer[2]));
			
			svc.addAnswers(flux)
			.log()
			.block();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void userTrialListTest() {
		try {
			Flux<Trial> ret = svc.userTrialList(1l);
			
			ret.log()
			.doOnEach(trial -> {System.out.println(trial);})
			.collectList()
			.block();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
//	@Test
	public void userAnswersTest() {
		try {
			Flux<Trial> ret = svc.userAnswers(1l);
			
			ret.log()
			.doOnEach(ans -> {System.out.println(ans);})
			.collectList()
			.block();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
