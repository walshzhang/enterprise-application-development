# Web API 应用开发

[toc]

## MVC 模式

![](images\MVC 模式.png)

## 三层架构

* Web层
* 服务层
* 数据层

## WEB API开发基础

* REST API

  * 基于 HTTP 协议的数据交换编程模型，是当前主流的前后端通讯方式

    ```java
    @RestController // 控制器，用于提供 REST API 接口
    public class HomeController {
    }
    ```

  * 资源：每个资源都有至少一个地址（URL）

    * 资源的表示：前后端分离情况下，一般采用 JSON 进行表示

  * 操作：增（POST）、删（DELETE）、改（PUT）、查（GET）

    ```java
    @GetMapping("/todos")         // 处理地址为 /todos 的 GET 请求
    public List<Todo> listAll() {
        // 获取所有的 Todo
    } 
    
    @GetMapping("/todos")     // 处理地址为 /todos?title=作业&done=false 的请求
    public List<Todo> list(@RequestParam String title, 
                           @RequestParam(name="done") boolean bool) {  
        // 返回是与参数 title 模糊匹配的 Todo
    }
    
    @PostMapping("/todos")    // 处理地址为 /todos 的 POST 请求
    public void addOne(@RequestBody Todo todo) {}
    
    @DeleteMapping("/todos/1")  // 处理地址为 /todos/1 的 DELETE 请求
    public void deleteById()
    
    @PutMapping("/todos")  // 处理地址为 /todos 的 PUT 请求
    public void update(@RequestBody Todo dodo) {}
    ```

    