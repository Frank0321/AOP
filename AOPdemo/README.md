# AOP 介紹

## 使用的依賴
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
## 使用到的功能
- JoinPoint : 封裝了 SpringAop 中切麵方法的訊息，再切面方法中添加
  JoinPoint 的參數，就可以獲得到封裝該方法的訊息

## annotation 介紹
- @Component : 標記為元件 (bean)
- @Aspect : 讓 spring boot 將此類別視為切面 (Aspect)
- @Pointcut : 切入點，不需要實作內容
  - @Pointcut("execution(public * com.example..*(..))")，
    表示套用到 com.example 這個檔案夾嚇得全部 public 方法
  - 主要決定哪些方法要套用這些 Advice
- @Before :
- @After :
- @AfterReturning :
- @Around : 
- @AfterThrowing : 

  
    

## 參考來源
 - [切面導向程式設計（AOP）, 新手工程師的教室](https://chikuwa-tech-study.blogspot.com/2021/06/spring-boot-aop-introduction.html)
 - [JoinPoint的用法，牛牛](https://javaniuniu.com/Spring/Aspect/JoinPoint)


