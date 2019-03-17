package com.lalagg.fun.svc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lalagg.fun.model.User;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSvcTest {

	@Autowired
	private UserSvc userSvc;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void createOrUpdateTest() {

		Mono<User> mono;
		try {
			mono = userSvc.createOrUpdate("");
			User u = mono.map(user -> {
				System.out.println(user.toString());
				return user;
			}).block();
			System.out.println(u.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
