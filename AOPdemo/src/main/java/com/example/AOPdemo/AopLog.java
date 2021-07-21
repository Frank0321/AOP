package com.example.AOPdemo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

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



    private String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }
}
