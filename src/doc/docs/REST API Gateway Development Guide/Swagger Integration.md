# Swagger 2 and Spring MVC Integration Tutorial #
8/2/2018 15:00:00 PM

## Revision History

|Date      |Version|Author  |Brief|
|----------|------:|--------|-----|
|2017-8-3  |1.0    |Jason Chi|Initial version|
|2017-8-8  |1.1    |Jason Chi|error handling|
|2017-10-23|1.2    |Jason Chi|Index.html modified|
|2018-07-29|1.3    |Leo Kuo|Spring Boot 2.3 attach Swagger 2.0 framework|

## 前言

公司大多都是使用markdown工具來撰寫文件，markdown文件好處是在於撰寫一些使用說明也可以有版本控制的優點，也能用html的方式呈現，不過針對於API文件的開發流程上，使用markdown的時間較費時，尤其editor並不是那麼容易排版，API 文件有許多的框架工具可以來幫忙產生，且直接從程式碼中去產生文件，自己所撰寫的時間減少許多，也可以減少在開發前等待API文件訂好才開始動工的這段時間，雖然常常都是事後才補上API 文件...

[**Swagger 2**](https://swagger.io/)，API文件框架，可套用在Spring MVC 架構的 webservice，直接透過Annotation的方式讓框架去幫忙產生API文件，並提供測試環境提供訊息測試。

Swagger 2 也可搭配 Swagger UI來使用，使用官方提供的主題，如不使用會是預設。

**[Swagger UI 呈現效果](http://petstore.swagger.io/#/)**

**[Swagger UI 下載](https://github.com/swagger-api/swagger-ui)** - 下載zip 或 clone整個專案，會需要dist資料夾底下的東西。



## 使用方法範例: (mpos-gateway) #
**建置環境: Gradle**

**SpringFramework version: 4**

#### 首先套入swagger工具依賴  (build.gradle) 版本可找最新的  ####

build.gradle
```groovy
dependencies {
.
.
compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.7.0'
compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.7.0'
.
}
```

#### 接著建立一個 RestApiConfig 繼承 WebMvcConfigurationSupport ####

```java
package com.ogloba.ew.mpos.gateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@Configuration
@ComponentScan(basePackages = "com.ogloba.ew.mpos.gateway")
public class RestApiConfig extends WebMvcConfigurationSupport{
...
...
...

}
```

#### 設置Docket ####

- globalOperationParamters - 全部訊息加入參數
- apiInfo - API 文件資訊
- apis - 設定最上層路徑(packagename) 搜尋底下所有的controller
- paths - 每道訊息的path

```java
@Bean
public Docket createRestApi() {
	return new Docket(DocumentationType.SWAGGER_2)
		.globalOperationParameters(initHeaderParameters())
		.apiInfo(apiInfo())
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.ogloba.ew.mpos.gateway"))
		.paths(PathSelectors.any())
		.build();
	}
```

#### 設置ApiInfo ####

```java
private ApiInfo apiInfo() {
	return new ApiInfoBuilder()
		.title("EW-Mpos-GateWay Rest API Doc")
		.contact(new Contact("Jason Chi & Jeff Jian", null, null))
		.license("Apache License Version 2.0")
		.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
		.version("1.0.0")
		.build();
	}
```

#### ※如需要加入header的顯示或是提供測試時的輸入在每一道訊息，可加入Parameter ####

```java
private List<Parameter> initHeaderParameters() {
		List<Parameter> parameters = new ArrayList<>();
		parameters.add(buildParameter("X-Mpos-App-Id", "App ID", "String", "header", false, "com.ogloba.ew.mpos.android"));
		parameters.add(buildParameter("X-Mpos-App-Version", "App Version", "String", "header", false, ""));
		parameters.add(buildParameter("X-Mpos-Api-Key", "Api Key", "String", "header", false, "CT4S9O74WHAHYQXA"));
		parameters.add(buildParameter("X-Mpos-Api-Version", "Api Version", "String", "header", false, "0.0.1"));
		parameters.add(buildParameter("X-Mpos-App-Token", "Access Token", "String", "header", false, ""));
		parameters.add(buildParameter("X-Mpos-App-SessionId", "Session Id", "String", "header", false, ""));
		parameters.add(buildParameter("Authorization", "Base64 encoded of Terminal Id + Passphrase + device Id", "String", "header", false, ""));
		return parameters;
	}
	
	private Parameter buildParameter(String key, String description, String modelRef,
			String parameterType, boolean required, String defaultValue) {
		
		ParameterBuilder headerParam = new ParameterBuilder();
		headerParam.name(key);
		headerParam.description(description);
		headerParam.modelRef(new ModelRef(modelRef));
		headerParam.parameterType(parameterType);
		headerParam.required(required);
		headerParam.defaultValue(defaultValue);
		
		return headerParam.build();
	}
```

#### GlobalOperationParameters效果呈現 ####

![](http://i.imgur.com/qUa5LvE.png)

#### 設置完成後如果不套用官方提供的主題即可直接使用預設主題來開啟API文件 ####

- http://localhost:8080/xxx/swagger-ui.html
- MPOS範例 : https://demo.ogloba.com:8443/ew-mpos-gateway/swagger-ui.html

# 套用Swagger-UI主題 #

#### 將下載下來的SwaggerUI專案內的dist資料夾內容複製到webapp/WEB-INF/底下 ####

![](http://i.imgur.com/FiCtz0k.png)

#### 編輯Index.html裡的url ####

- 更改為 /proxyName/v2/api-docs

```html
<html>
.
.
<script>
//get exact proxy path
var path = location.pathname.split('/');
if (path[path.length-1].indexOf('.html') > -1) {
	path.length = path.length -1;
}

var proxyName = path[1];

window.onload = function() {
  
  // Build a system
  const ui = SwaggerUIBundle({
    url:"/" + proxyName + "/v2/api-docs",
    validatorUrl: null,
    dom_id: '#swagger-ui',
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
    layout: "StandaloneLayout"
  })

  window.ui = ui
}
</script>
.
.
</html>
```

#### 設定完成後Swagger會自動將文件套入index.html的畫面上 ####

- http://localhost:8080/xxx/index.html
- MPOS範例: https://demo.ogloba.com:8443/ew-mpos-gateway/swagger-ui/index.html

# 相關問題 #

**Swagger運作概念**：其實可以從index.html修改url中，觀察到後面路徑為/v2/api-docs，是因為在一開始的Docket的設定當中，將所有的controller掃過一遍，將所有的內容都轉換為api-docs，一個json格式的檔案，SwaggerUI再透過該json檔案來轉換成畫面，可以直接開啟[api-docs](https://demo.ogloba.com:8443//ew-mpos-gateway/v2/api-docs)來看內容。

**Annotation設置介紹:**
- **@Api 可增加訊息描述**
```java
@Api(description = "Authentication")
@Controller
public class AuthenticationController {
...
}
```
- **@ApiModelProperty 針對物件描述**
```java
public class LoginRequest {
    @NotBlank
    @ApiModelProperty(notes = "The Device ID or IMEI number", required = true)
    private String deviceId;

    @NotBlank
    @ApiModelProperty(notes = "Merchant ID", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(notes = "Cashier Password", required = true)
    private String password;
    ...
    ...
}
```
- **@ApiIgnore 可以設定要忽略的Controller or path**
```java
@ApiIgnore
@Controller
public class GiftCardSalesController {
   ....
   ....
}

```

**404或401問題:**


公司的webservice通常一定會做權限限制，如果有設定Interceptor，請記得將你的index.html所放置的路徑排除攔截器。

**Dispatcher-servlet.xml**
```xml
<mvc:interceptor>
      <mvc:mapping path="/**"/>
      <mvc:exclude-mapping path="/resources/**"/>
      <mvc:exclude-mapping path="/static/**"/>
      <mvc:exclude-mapping path="/registration/**"/>
      <mvc:exclude-mapping path="/login"/>
      <mvc:exclude-mapping path="/posIdentification"/>
      <mvc:exclude-mapping path="/getInitialization"/>      
      <mvc:exclude-mapping path="/swagger-ui.html"/>      
      <mvc:exclude-mapping path="/swagger-ui/**"/>
      <mvc:exclude-mapping path="/v2/api-docs"/>
      <mvc:exclude-mapping path="/webjars/**"/>
      <mvc:exclude-mapping path="/swagger-resources/**"/>	
    <ref bean="terminalAuthenticationInterceptor" />
</mvc:interceptor>
.
.
.
<mvc:resources mapping="/swagger-ui/**" location="WEB-INF/swagger-ui/"/>

```

**NoSuchMethodError:**
- **如是com.fasterxml.jackson.databind.ObjectMapper.canSerialize請加入Jackson依賴**

```logging
Caused by: java.lang.NoSuchMethodError: com.fasterxml.jackson.databind.ObjectMapper.canSerialize(Ljava/lang/Class;Ljava/util/concurrent/atomic/AtomicReference;)Z
	at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.canWrite(AbstractJackson2HttpMessageConverter.java:178)
	at org.springframework.http.converter.AbstractGenericHttpMessageConverter.canWrite(AbstractGenericHttpMessageConverter.java:74)
	at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor.getProducibleMediaTypes(AbstractMessageConverterMethodProcessor.java:313)
	at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor.writeWithMessageConverters(AbstractMessageConverterMethodProcessor.java:184)
	at org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor.handleReturnValue(HttpEntityMethodProcessor.java:203)
	at org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite.handleReturnValue(HandlerMethodReturnValueHandlerComposite.java:81)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:113)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963)
	... 34 more
```

```groovy
dependencies {
	compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.8.4'
}
```
- **如果你使用Spring 3，請試著更新Spring版本到4 or 將依賴版本改成2.0.1，因為swagger 2 主要是針對Spring 4**

```groovy
dependencies {
	compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.0.1'
}
```

# Spring Boot 2.0.3 套用 Swagger 2.0 範例 #
## 範例: (ropo-catalog-gateway) #
- **Dependency 如同上**
- 攔截器排除列表設定(WebMvcConfig.java / SwaggerConfig.java)

```java
package com.ogloba.ropo.catalog.gateway.config;
import ...

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    ...
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(applicationAuthenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/resources/**", "/tests/**", "/localtest/**, ''/test",
                		"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

        registry.addInterceptor(terminalAuthenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/**", "/app/login", "/resources/**", "/static/**", "/tests/**", "/localtest/**", "/test",
                		"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

        registry.addInterceptor(contextPreparationInterceptor).addPathPatterns("/**");

        registry.addInterceptor(versionValidIntercetor)
                .addPathPatterns("/**")
                .excludePathPatterns("/actionEvents/**", "/app/login", "/user/**", "/tests/**", "/test",
                		"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
    ...
}
```
```java
package com.ogloba.ropo.catalog.gateway.config;
import ...

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.ogloba.ropo.catalog.gateway")
public class SwaggerConfig {
    // 參考來源網址 : https://blog.csdn.net/liu0bing/article/details/80826590 (最重要的是兩個方法都需要重寫)
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html") 
			.addResourceLocations("classpath:/META-INF/resources/"); 
		registry.addResourceHandler("/webjars/**") 
			.addResourceLocations("classpath:/META-INF/resources/webjars/"); 
	}
	...
```
## Spring Boot 套上 Swagger #
主要原本Spring專案只需要在dispatch.xml去允許訪問的URL，但是當變成Boot專案後，其設定方式會有兩個部分如下:
**1**. 允許訪問的URL設定(method : **public void addInterceptors(InterceptorRegistry registry)**)
**2**. 靜態資源的處理程序(method : **public void addResourceHandlers(ResourceHandlerRegistry registry)**)。
##### 註:兩個方法都需要重寫，只加任何一個都無法生效




**結論：** 如果公司將來有對外提供webservice服務，可以使用swagger來產生API文件，也可以直接提供對方線上直接對訊息測試，本範例是使用mpos-gateway的專案結構，或許會因不同的專案或是檔案路徑不同而有些非遇期的錯誤，有問題可以到swagger的官網及相關論壇爬文。

**參考：**
- https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
- http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
- https://springfox.github.io/springfox/docs/current/
- https://indrabasak.wordpress.com/2016/04/07/swagger-2-integration-with-spring-rest/
- http://blog.didispace.com/springbootswagger2/
- https://blog.csdn.net/liu0bing/article/details/80826590 (Spring Boot)
