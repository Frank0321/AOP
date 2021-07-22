# AOP 介紹

## 使用的依賴
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
## 使用到的功能
- JoinPoint : 封裝了 SpringAop 中切面方法的訊息，再切面方法中添加
  JoinPoint 的參數，就可以獲得到封裝該方法的訊息
- 名詞介紹 :
  - Aspect（切面）
  - Pointcut（切入點）
  - Join Point（連接點，即方法）
  - Advice（建議，即共通工作）

## annotation 介紹
- @Component : 標記為元件 (bean)
- @Aspect : 讓 spring boot 將此類別視為切面 (Aspect)
- @Pointcut : 切入點，不需要實作內容
  - @Pointcut("execution(public * com.example..*(..))")，
    表示套用到 com.example 這個檔案夾下的全部 public 方法
  - 主要決定哪些方法要套用這些 Advice
- @Before : 表示 Advice 執行前，會先執行這一段
- @After : 可將 Advice 設定在方法執行後執行。即使方法發生例外也會執行
- @AfterReturning : 將 Advice 設定在方法返回後才執行
- @Around : 可實作同時涵蓋方法執行前後的 Advice
- @AfterThrowing : Advice 設定在方法拋出例外後執行

  
    

## 參考來源
 - [切面導向程式設計（AOP）, 新手工程師的教室](https://chikuwa-tech-study.blogspot.com/2021/06/spring-boot-aop-introduction.html)
 - [JoinPoint的用法，牛牛](https://javaniuniu.com/Spring/Aspect/JoinPoint)


