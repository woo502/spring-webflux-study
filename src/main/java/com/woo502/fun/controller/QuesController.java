package com.woo502.fun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woo502.fun.model.Answer;
import com.woo502.fun.model.ReturnModel;
import com.woo502.fun.model.Trial;
import com.woo502.fun.model.UserAnswer;
import com.woo502.fun.svc.TrialSvc;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trial")
public class QuesController extends BaseController {
	
	@Autowired
	private TrialSvc trialSvc;

	@ApiOperation(value="创建一个问题集", notes="创建问答之后才可以进行问题,答案及奖品的编辑")
	@RequestMapping("/create")
	public Mono<ReturnModel> create() throws Exception {
		Long userId = 1l;
		return genResultModel(trialSvc.create(userId));
	}

	@ApiOperation(value="更换问题")
	@ApiImplicitParams({ 
	@ApiImplicitParam(name="tId", value="问题集id", paramType="path", dataType="int"),
	@ApiImplicitParam(name="qId", value="要更换的问题id", paramType="path", dataType="int")
	})
	@RequestMapping("/{tId}/{qId}/exchange")
	public Mono<ReturnModel> exchange(@PathVariable Long tId, @PathVariable Long qId) throws Exception {
		return genResultModel(trialSvc.changeQuestion(qId, tId));
	}

	@ApiOperation(value="更改答案")
	@ApiImplicitParams({ 
	@ApiImplicitParam(name="tId", value="问题集id", paramType="path", dataType="int"),
	@ApiImplicitParam(name="qId", value="要更改的问题id", paramType="path", dataType="int"),
	@ApiImplicitParam(name="aId", value="要更改的答案id", paramType="path", dataType="int"),
	@ApiImplicitParam(name="text", value="新的答案文本", paramType="path", dataType="string"),
	@ApiImplicitParam(name="isRight", value="是否是正确答案1是 2否", paramType="path", dataType="int")
	})
	@PostMapping("/{tId}/{qId}/{aId}/modify")
	public Mono<ReturnModel> edit(@PathVariable Long tId, @PathVariable Long qId, @PathVariable Long aId, 
			@RequestBody Answer answer) throws Exception {
		
		return genResultModel(trialSvc.modifyAnswer(tId, aId, answer.getText(), answer.getIsRight()));
	}

	@ApiOperation(value="问题集编辑完成")
	@ApiImplicitParams({ 
	@ApiImplicitParam(name="tId", value="问题集id", paramType="path", dataType="int"),
	@ApiImplicitParam(name="poster", value="海报id", paramType="query", dataType="string"),
	@ApiImplicitParam(name="picId", value="奖品图片", paramType="query", dataType="string"),
	@ApiImplicitParam(name="voiceId", value="奖品音频", paramType="query", dataType="string"),
	@ApiImplicitParam(name="limitUser", value="人数限制", paramType="query", dataType="int"),
	@ApiImplicitParam(name="money", value="红包金额(冗余)", paramType="query", dataType="int"),
	@ApiImplicitParam(name="correct", value="及格线", paramType="query", dataType="int")
	})
	@RequestMapping("/{tId}/finish")
	public Mono<ReturnModel> finish(@PathVariable Long tId, @RequestBody Trial trial) throws Exception {
		return genResultModel(trialSvc.completeTrial(tId, trial.getPicId(), trial.getPicId(), trial.getVoiceId(),
				trial.getLimitUser(), trial.getMoney(), trial.getCorrect()));
	}

	@ApiOperation(value="我的问题集")
	@GetMapping("/mylist")
	public Mono<ReturnModel> mylist() throws Exception{
		Long userId = 1l;
		return genResultModel(trialSvc.userTrialList(userId ));
	}

	@ApiOperation(value="我的回答")
	@GetMapping("/myAnswers")
	public Mono<ReturnModel> myAnswers() throws Exception {
		Long userId = 1l;
		return genResultModel(trialSvc.userAnswers(userId));
	}

	@ApiOperation(value="添加用户答案")
	@RequestMapping("/{tId}/answer")
	public Mono<ReturnModel> answer(@PathVariable Long tId, @RequestBody Flux<UserAnswer> answers) throws Exception {
		return genBoolResultModel(trialSvc.addAnswers(answers));
	}

	@ApiOperation(value="问题集详情")
	@ApiImplicitParam(name="tId", value="问题集id", paramType="path", dataType="int")
	@GetMapping("/{tId}")
	public Mono<ReturnModel> trialDetail(@PathVariable Long tId) throws Exception{
		Long userId = 1l;
		return genResultModel(trialSvc.detail(tId, userId));
	}

}
