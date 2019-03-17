package com.woo502.fun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woo502.fun.common.BaseModel;
import com.woo502.fun.model.ReturnModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected Mono<ReturnModel> genResultModel(Mono<? extends BaseModel> obj) {
		return obj.map(b -> {
			ReturnModel ret = new ReturnModel(0, "成功", b);
			return ret;
		}
		);
	}
	
	protected Mono<ReturnModel> genResultModel(Flux<? extends BaseModel> obj) {
		return obj.collectList().map(b -> {
			ReturnModel ret = new ReturnModel(0, "成功", b);
			return ret;
		}
		);
	}
	
	protected Mono<ReturnModel> genBoolResultModel(Mono<Boolean> obj) {
		return obj.map(b -> {
			if (b) {
				ReturnModel ret = new ReturnModel(0, "成功", null);
				return ret;
			} else {
				ReturnModel ret = new ReturnModel(-3, "操作失败", null);
				return ret;
			}
		}
		);
	}
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
	public Mono<ReturnModel> exceptionHandler(ServerHttpRequest req, Exception e) {
		logger.error("内部异常", e);
		return Mono.just(new ReturnModel(-1, "系统繁忙"));
	}
	
}
