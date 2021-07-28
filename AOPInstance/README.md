# AOPInstance

## 前言
AOP 的介紹，在一個方法的前後，加上其他方法 : 
如一個 hi() 的 method，
在執行 hi() 之前，要先握手的method；
在執行完hi() 之後，要執行掰掰的 method，
要如何進行 ?
(hi() 的內容要做甚麼，在這裡就不重要了)

## sol(0) 直接來
- 利用一個新 class 將她包起來
  ```java
    static class Agent{
        public void hi(){
            System.out.println("握手");
            new Hello().hi();
            System.out.println("bye");
        }
    }
  ```

## sol(1) :往下抽 cglib(使用繼承)
- 使用新的 class 去繼承原本的 class，並 override 掉該方法
  ```java
    static class MyClass extends Hello{
        @Override
        public void hi(){
            System.out.println("握手");
            super.hi();
            System.out.println("bye");
        }
    }
  ```
## sol(2) : 往上抽  jdk(將方法抽出來變成介面)  
- 將方法抽出來變成介面
  ```java
  public interface Greeting {
      void hi();
  }
  ```
- 先使用 MyHello 實作，並標註為 @Service 交給 Spring 管理 (也可以不用)
  ```java
  @Service
  static class MyHello implements Greeting{
      @Override
      public void hi() {
          System.out.println("Hi");
      }
  }
  ```
- 使用 MyGreeting 再次進行實作
- 並將原本實作 Greeting 的 MyHello，當作建構子的參數，輸入到方法中(@RequiredArgsConstructor)
  ```java
  @RequiredArgsConstructor
  static class MyGreeting implements Greeting{
      
      final Greeting greeting;

      @Override
      public void hi() {
          System.out.println("握手");
          greeting.hi();
          System.out.println("bye");
      }
  }
  ```

