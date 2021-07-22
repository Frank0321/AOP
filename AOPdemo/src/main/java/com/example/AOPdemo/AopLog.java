package com.example.AOPdemo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Component
@Slf4j
@Aspect
public class AopLog {

    //定義切點
    @Pointcut("execution(public * com.example..*(..))")
    public void pointcut(){
    }
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
       log.info("============= before advice starts =============");
       log.info(String.format("準耀要執行的方法名稱為 : %s", getMethodName(joinPoint)));
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        log.info("============= before advice ends ===============");;
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        log.info("============= after advice starts ==============");
        log.info(String.format("執行完的方法名稱為 : %s", getMethodName(joinPoint)));
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        log.info("============= after advice ends ================");;
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable){
        log.info("============= afterthrowing advice starts ==============");
        log.info(String.format("發生 exception 的方法為 : %s", getMethodName(joinPoint)));
        log.info(String.format("發生 exception 的錯誤訊息 : %s", throwable.getMessage()));
        log.info("============= afterthrowing advice ends ==============");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("============= around advice starts ==============");
        Object result = joinPoint.proceed();
        log.info("============= around advice ends ==============");
        return result;
    }



    private String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }
}
