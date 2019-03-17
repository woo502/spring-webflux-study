package com.woo502.fun.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.woo502.fun.controller.UserController;

@RunWith(SpringRunner.class)
public class UserControllerTest {

	private WebTestClient client;

    @Before
    public void setUp() {
        client = WebTestClient.bindToController(new UserController()).build(); 
    }
    
	@Test
	public void test() {
		System.out.println("test return : " + new String(client.post().uri("/user")
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.returnResult().getResponseBody())
		);
	}

}
