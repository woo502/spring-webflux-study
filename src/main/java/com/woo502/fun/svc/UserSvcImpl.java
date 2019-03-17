package com.woo502.fun.svc;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.woo502.fun.common.Cache;
import com.woo502.fun.common.jwt.JWTTokenService;
import com.woo502.fun.dao.IdGenRepository;
import com.woo502.fun.dao.UserRepository;
import com.woo502.fun.model.ExceptionModel;
import com.woo502.fun.model.User;
import com.woo502.fun.wx.WxApi;

import reactor.core.publisher.Mono;

@Service
public class UserSvcImpl implements UserSvc, ReactiveUserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IdGenRepository idGen;
	
	@Override
	public Mono<User> createOrUpdate(String jscode) throws Exception {
		final StringBuffer openIdbf = new StringBuffer();
		// 请求微信开放接口获取用户id
		return WxApi.jscode2session(jscode)
		// 转换成json
		.flatMap(rawString -> Mono.just(JSON.parseObject(rawString)))
		// 插入用户
		.flatMap(json -> {
			
			String openId = json.getString("openid");
			openIdbf.append(openId);
			
			// 确定用户是否存在
			return userRepository.existsByOpenId(openId)
			.flatMap(b -> {
				if (b) {
					return Mono.empty();
				} else {
					return idGen.genId(User.class)
							.flatMap(Obj -> userRepository.save(new User((long)Obj, openId, null, new Date()))
							);
				}
			})
			;
		})
		.doOnSuccessOrError((user, ex)->{
			if (ex != null) {
				
			}
		})
		.switchIfEmpty(Mono.empty()
				.flatMap(obj -> userRepository.findByOpenId(openIdbf.toString())
				.switchIfEmpty(Mono.error(new ExceptionModel(-2, null)))))
				.doOnSuccess(user -> {
					user.setToken(createToken(user.getUserId(), user.getOpenId()));
				});
	}
	
	private String createToken(Long userId, String openId) {
		String token = JWTTokenService.generateToken(openId, userId);
		Cache.set(String.valueOf(userId), token);
		return token;
	}

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return null;
	}
}
