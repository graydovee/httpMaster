# 目标
* 封装其他http框架，统一提供请求方式

# 使用
克隆项目，`mvn install`打包安装

## 普通项目使用
1. 引入依赖
```xml
<dependency>
    <groupId>cn.graydove.httpmaster</groupId>
    <artifactId>httpmaster-core</artifactId>
    <version>${version}</version>
</dependency>
```
2.使用
```java
public class Demo {

    private HttpClientEngine engine = new HttpClientEngine();

    public void testRequest() {
        httpRequest = engine.newHttpRequest()
                        .url("http://localhost:8080/admin/visit")
                        .header()
                        .addHeader("Authorization", prefix + " " + token)
                        .request()
                        .keyValueBody()
                        .addParam("bodyKey", "BodyValue")
                        .request()
                        .query()
                        .addQuery("begin", "2021-01-1")
                        .addQuery("end", "2021-01-31")
                        .request();
        HttpResponse response = engine.post(httpRequest);
    }
}
```

## spring boot项目使用
1. 引入依赖
```xml
<dependency>
    <groupId>cn.graydove.httpmaster</groupId>
    <artifactId>httpmaster-spring-boot-starter</artifactId>
    <version>${parent.version}</version>
</dependency>
```
2. 使用`@EnableHttpAutoConfiguration`注解
```java
@EnableHttpAutoConfiguration
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
```
3. 新建接口类，新增方法并加上注解
```java
@HttpService(url = "http://localhost:8080")
public interface DemoClient {

    @HttpPost(path = "login")
    String login(@Body String username, @Body String password);

    @HttpPost(path = "login")
    String login2(String username, String password);

    @HttpGet(path = "admin/visit")
    String visit(@Header(HeaderConstant.AUTHORIZATION) String head, @Query("begin") String arg0, @Query("end")String arg1);
}

```
* `@HttpService`注解标记的接口会生成代理对象，其中url属性可指定该接口内所有方法的默认url
* `cn.graydove.httpmaster.starter.annotation.method`包下的注解标记该方法为相应的http请求
    * 参数url可指定url，此url优先级高于`@HttpService`的优先级
    * 参数path可指定uri路由路径
* 参数
    * `@Header`标记的参数为请求头参数
    * `@Query`标记的参数为url请求参数
    * `@Body`标记的参数为请求体参数
    * 其中两个注解的value属性为参数名，如果该属性为空则使用变量名作为参数名
    * 若不使用注解则自动根据请求方法判断，若能携带请求体则携带请求体，否则携带在请参数

# 已完成
* 接入httpclient，实现post，get方法

# 未完成
* 自定义配置属性
* 接口返回值处理
* handler支持`@order`
* okHttp接入