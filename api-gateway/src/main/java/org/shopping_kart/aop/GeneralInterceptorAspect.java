package com.example.restwebservice.aop;

import com.example.restwebservice.beans.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class GeneralInterceptorAspect {
	
	private Logger log = LoggerFactory.getLogger(GeneralInterceptorAspect.class);
	@Autowired
	HttpServletRequest request;
	
	@Pointcut("execution(* com.example.restwebservice.controller.*.*(..))")
	public void loggingPointCut() {}
	
	@Before("loggingPointCut()")
	public void before(JoinPoint joinPoint) {
		log.info("Before Method Invoked :: {}",joinPoint.getSignature().getName());
		log.info("request :"+request.getQueryString());
		Object arg[] = joinPoint.getArgs();
		if(arg[0] instanceof LoginRequest){
			log.info("username:"+((LoginRequest) arg[0]).getUsername());
			log.info("secrets :"+((LoginRequest) arg[0]).getSecrets());
		}

		log.info("Request Data {}",joinPoint.getTarget());
	}
	
	@After("loggingPointCut()")
	public void after(JoinPoint joinPoint) {
		log.info("After Method Invoked :: {}",joinPoint.getSignature());
		log.info("Request Data {}",joinPoint.getTarget());
	}
	
	@AfterThrowing(value ="execution(* com.example.restwebservice.controller.*.*(..))",throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {		
		log.info("After method throwing exception :: {}",joinPoint.getSignature());		
		log.info("method throwing exception :: {}",exception.getLocalizedMessage());
		
	}

}
