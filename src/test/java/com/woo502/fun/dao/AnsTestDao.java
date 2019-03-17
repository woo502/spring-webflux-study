package com.woo502.fun.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.woo502.fun.dao.AnswerRepository;
import com.woo502.fun.model.Answer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnsTestDao {
	
	@Autowired
	private AnswerRepository dao;

	@Before
	public void setUp() throws Exception {
	}

//	@Test
	public void test() {
		
		List<Answer> alist = new ArrayList<>();
		for (int i=11; i<=20; i++) {
			alist.add(new Answer(i * 2l - 1, 0, i, 1, (byte)1, (byte)1, new Date(), "问题"+i+"答案"+1));
			System.out.println(i * 2l - 1);
			alist.add(new Answer(i * 2l, 0, i, 1, (byte)1, (byte)1, new Date(), "问题"+i+"答案"+2));
			System.out.println(i * 2l);
		}
		System.out.println(alist.size());
		dao.saveAll(alist)
		.map(ans -> {
			System.out.println(ans);
			return ans;
		})
		.subscribe();
		
	}
	
//	@Test
	public void FindTest() {
//		dao.findAllByQId(1l)
//		.map(ans -> {
//			System.out.println(ans);
//			return ans;
//		})
//		.log()
//		.subscribe();
		dao.findByQIdIn(Arrays.asList(1l
				,2l,3l,4l,5l,6l,7l,8l,9l,10l
				), Sort.by("qId"))
		.map(ans -> {
			System.out.println(ans);
			return ans;
		})
		.log()
		.subscribe();
	}

}
