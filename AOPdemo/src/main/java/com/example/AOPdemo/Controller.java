package com.example.AOPdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
@Slf4j
public class Controller {

    @RequestMapping
    public String hello(){
        log.info("doing Controller method");
        return "hello";
    }
}
