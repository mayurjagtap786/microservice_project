package com.example.auth.aop;

import com.example.auth.beans.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class GeneralInterceptorAspect {
	
	private final Logger LOG = LoggerFactory.getLogger(GeneralInterceptorAspect.class);
	@Autowired
	HttpServletRequest request;
	
	@Pointcut("execution(* com.example.restwebservice.controller.*.*(..))")
	public void loggingPointCut() {}
	
	@Before("loggingPointCut()")
	public void before(JoinPoint joinPoint) {
		LOG.info("Before Method Invoked :: {}",joinPoint.getSignature().getName());
		LOG.info("request :"+request.getQueryString());
		Object arg[] = joinPoint.getArgs();
		if(arg[0] instanceof LoginRequest){
			LOG.info("username:"+((LoginRequest) arg[0]).getUsername());
			LOG.info("secrets :"+((LoginRequest) arg[0]).getSecrets());
		}

		LOG.info("Request Data {}",joinPoint.getTarget());
	}
	
	@After("loggingPointCut()")
	public void after(JoinPoint joinPoint) {
		LOG.info("After Method Invoked :: {}",joinPoint.getSignature());
		LOG.info("Request Data {}",joinPoint.getTarget());
	}
	
	@AfterThrowing(value ="execution(* com.example.restwebservice.controller.*.*(..))",throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		LOG.info("After method throwing exception :: {}",joinPoint.getSignature());
		LOG.info("method throwing exception :: {}",exception.getLocalizedMessage());
		
	}

}
