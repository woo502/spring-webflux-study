package com.woo502.fun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woo502.fun.model.ReturnModel;
import com.woo502.fun.svc.UserSvc;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserSvc userSvc;
	
	@ApiOperation(value="微信授权")
	@PostMapping("/wxAuth/{jscode}")
    public Mono<ReturnModel> createOrReplace(@PathVariable String jscode) throws Exception{
        return genResultModel(userSvc.createOrUpdate(jscode));
    }

	@ApiOperation(value="", hidden = true)
	@RequestMapping("/authErr")
	public Mono<ReturnModel> authErr() throws Exception {
		return Mono.just(new ReturnModel(-2, "用户未登录"));
	}
}
