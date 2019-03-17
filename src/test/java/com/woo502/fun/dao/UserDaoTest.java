package com.woo502.fun.dao;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woo502.fun.dao.UserRepository;
import com.woo502.fun.model.User;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

		User user = new User();
		
		user.setUserId(1l);
		user.setOpenId("aaa");
		user.setCreateTime(new Date());
		Mono<User> mono = userRepository.save(user);
		
		System.out.println(mono.block().toString());
	}

}
