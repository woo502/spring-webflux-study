package com.lalagg.fun.wx;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WxApi {
	
	static final String appid = "";
	static final String secret = "";
	
	static final String code2Session_Url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	
	public static Mono<String> jscode2session(String jscode) {
	
		final WebClient client = WebClient.create(code2Session_Url
				.replace("APPID", appid).replace("SECRET", secret).replace("JSCODE", jscode));
		
		Mono<String> httpResult = client.post().retrieve()
				.bodyToMono(String.class);
		
//		Mono<String> httpResult = Mono.just("{\"openid\": \"aaa1\"}");
		
		return httpResult;
	}
	
}
