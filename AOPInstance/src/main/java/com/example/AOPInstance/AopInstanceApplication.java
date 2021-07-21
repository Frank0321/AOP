package com.example.AOPInstance;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class AopInstanceApplication {

	public static void main(String[] args) {

		//因為 helloimplements 所以還是要啟動 spring
		SpringApplication.run(AopInstanceApplication.class, args);

		final Hello hello = new Hello();
		//預計有100個地方，在 hi 的 method 前後都加上"握手"和 "bye"
		System.out.println("握手");
		hello.hi();  //*100
		System.out.println("bye");

//		=========================
		System.out.println("=============================");
		Agent agent = new Agent();
		agent.hi();

//		=========================
		System.out.println("=============================");
		final Hello helloextends = new MyClass();
		helloextends.hi();

//		=========================
		System.out.println("=============================");
		final Greeting helloimplements = new MyGreeting(new MyHello());
		helloimplements.hi();


	}

	//以前別人寫好的，不能被更動的
	static class Hello{
		public void hi(){
			System.out.println("Hi");
		}
	}

	//sol(0)
	//利用一個新 class 將她包起來
	static class Agent{
		public void hi(){
			System.out.println("握手");
			new Hello().hi();
			System.out.println("bye");
		}
	}

	//sol(1) 往下抽 cglib
	//AOP 實作的方式之一 (1/2)
	static class MyClass extends Hello{
		@Override
		public void hi(){
			System.out.println("握手");
			super.hi();
			System.out.println("bye");
		}
	}

	//sol(2) 往上抽  jdk
	//AOP 實作的方式之一 (2/2)
	@RequiredArgsConstructor
	static class MyGreeting implements Greeting{
		//真正的 Greeting 是誰不重要
		//原本要做的事情，放在 Greeting 裡面
		final Greeting greeting;

		@Override
		public void hi() {
			System.out.println("握手");
			greeting.hi();
			System.out.println("bye");
		}
	}

	@Service
	static class MyHello implements Greeting{
		@Override
		public void hi() {
			System.out.println("Hi");

		}

	}


}
