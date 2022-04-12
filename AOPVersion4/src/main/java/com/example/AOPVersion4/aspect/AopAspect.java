package com.example.AOPVersion4.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AopAspect {
	
	@Pointcut("execution(* com.example.AOPVersion4.*.controller.*.*(..))")
	public void handle() {
		
	}
	
	@Around("handle()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Object rtn = null;
		
		log.info("before");
		rtn = pjp.proceed();
		log.info("after");
		return rtn;
	}
}
