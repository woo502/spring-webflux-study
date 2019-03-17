package com.lalagg.fun.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lalagg.fun.model.Question;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuesDaoTest {
	
	@Autowired
	private QuestionRepository dao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void dataInit() {
		
		List<Question> qlist = new ArrayList<>();
		for (int i = 11; i<=20; i++) {
			Question q = new Question((long)i, "问题"+i, new Date());
			qlist.add(q);
		}
		
		dao.saveAll(qlist)
		.subscribe();
	}
	
//	@Test
	public void randomTest() {
		try {
			Flux<Question> findRadom = dao.findRadom(10, Question.class);
			findRadom.doOnNext(trial -> {
				System.out.println(trial);
			})
			.subscribe();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
