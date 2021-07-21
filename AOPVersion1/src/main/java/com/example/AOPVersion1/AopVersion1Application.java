package com.example.AOPVersion1;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class AopVersion1Application {

	public static void main(String[] args) {
		SpringApplication.run(AopVersion1Application.class, args);
	}

	//ApplicationRunner : 這個Bean (MyService) 在 spring 啟動後，會直接 run 的 method
	//要先把 MyService 標註為 service，這樣啟動時，才可以找的到
	@Service
	@RequiredArgsConstructor
	static class MyService implements ApplicationRunner {
		final MyHello myHello;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			myHello.hi();

		}


	}

	//首先，要先定義目標 : 哪寫 spring bean 是我們的目標
	@Aspect
	@Component   //註冊給 spring，當成一個 bean
	static class GreetingAspect {

		//定義我們的目標在哪
		//針對 Greeting 這一個 interface 以及它下面的實作
		@Pointcut("target(Greeting)")
		public void greeting(){}

		//定義 AOP 的內容
		//Around 定義的目標在 greeting() 這一個 method
		//greeting 的目標，則是定義在 Greeting
		//    ↑↑ 方便抽出一個來共用 ↑↑
		//@Around("target(Greeting)")  //可以改成這樣寫
		@Around("greeting()")
		public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
			//需要傳遞的參數 ProceedingJoinPoint
			//找到的前面

			//在目標(Greeting 這個 interface 下的所以 method)
			//進去前會先經過這裡
			System.out.println("握手");
//			System.out.println("Start transaction, fetch connection from Hikari pool");
			final Object proceed;
			try {
				proceed = proceedingJoinPoint.proceed();
				//進去後則會經過這個地方
				System.out.println("bye");
//				System.out.println("commit transaction, give connection back to pool");
			}catch (Exception e){
				System.out.println("rollback transaction, give connection back to pool");
				throw e;
			}

			return proceed;

		}
	}


	@Service
	@RequiredArgsConstructor
	static class MyHello implements Greeting {

		final BeanFactory factory;

		@Override
		public void hi() {
			System.out.println("Hi");

		}


	}

}
