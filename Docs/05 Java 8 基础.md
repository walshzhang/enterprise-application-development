# Java 8 基础

[toc]

## 例子



## Lambda 表达式

* 类似于 ES6 中的箭头函数

* 把函数式编程风格引入到 Java 语言

* 可以极大提高 Java 开发人员的效率

  ```java
  // 没使用 lambda 表达式之前
  public class OldThread {
   public static void main(String[] args) {
     new Thread(new Runnable() {
       public void run() {
         System.out.println("Hello World!");
       }
     }).start();
   }
  }
  
  // 使用 lambda 表达式之后
  public class LambdaThread {
    public static void main(String[] args) {
      new Thread(() -> System.out.println("Hello World!")).start();
    }
  }
  ```

  

## 函数式接口

* 像 `Runnable` 这样的接口被称为函数式接口

* 函数式接口的两个必要条件

  * 必须是接口
  * 只有一个方法（不包含`Object` 类提供的方法）

* 函数式接口的实现可以使用 lambda 表达式进行表示，而无须编写专门的匿名类

* 可使用 `@FunctionalInterface` 表示函数式接口

  ```java
  class Apple {
    String color;
    int weight; // 克数
  }
  
  @FunctionalInterface
  interface AppleFilter {
    boolean doFilter(Apple apple);
  }
  
  class Robot {
    static List<Apple> choose(Apple[] inventory, 
                              AppleFilter filter) {
      List<Apple> apples = new ArrayList<>();
      for (Apple apple : inventory) {
        if (filter.doFilter(apple)) {
          apples.add(apple);
        }
      }
      return apples;
    }
  }
  
  class Program {
    static void main(String[] args) {
      Apple a1 = new Apple("red", 60);
      Apple a2 = new Apple("green", 70);
      Apple a3 = new Apple("red", 80);
      Apple[] apples = {a1, a2, a3};
      
      List<Apple> list = Robot.choose(apples, 
                                      apple -> apple.weight > 30);
    }
  }
  ```

  

*  `java.util.function` 包提供了许多函数式接口

  ![](images\java 内置的函数式接口.png)

  * 有了这些接口，我们就无需要再定义接口
  * 如可以用 `Predicate` 替代前面的 `AppleFilter`

## Stream 流式操作