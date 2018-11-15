# Documentation

Ogloba的技術文件(Guide)採用業界標準 **Markdown** 語法搭配 **Mkdocs** 編譯為HTML格式，JAVA API採用 **Javadoc** 技術編譯成HTML格式，本節說明文件的撰寫及工具的使用方式。
## Markdown
### Syntax
我們使用的Markdown語法格式為[GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown/#GitHub-flavored-markdown)

### Editor
#### Online
- [Stack Edit](https://stackedit.io/)
- [HackMD](https://hackmd.io/)

#### Desktop
- [Typora](https://typora.io/)

## MkDocs
[MkDocs](https://www.mkdocs.org/) - Project documentation with Markdown 

MkDocs為一個文件組織及發佈的工具，可以接受markdown語法的文件及文件大網設定，最終可以輸出HTML網站，也有多種佈景主題可以選用，目前我們預設使用的主題為[Material for MkDocs](https://squidfunk.github.io/mkdocs-material/。), 此主題可支援語法高亮、文件內容搜尋、及響應式網頁。

下面為一些實際產出文件網頁的樣式：
![mkdocs-site-sample](images\mkdocs-site-sample.png)
![mkdocs-site-search](images\mkdocs-site-search.png)
![mkdocs-site-rwd](images\mkdocs-site-rwd.png)



### MkDocs Installation

- Install latest Python

MkDocs是一個Python的工具，所以在使用mkdocs前要先安裝Python。Python從網站上下載最新版本安裝即可，安裝後要把程式放到執行路徑環境變數Path中 。

- Install mkdocs with pip

```shell
pip install mkdocs 
```

  如此mkdocs就安裝完成了。



## Javadoc

這個沒什麼好說的就是Javadoc，JDK中的命令叫javadoc，在gradle中編譯Javadoc的task也叫`javadoc`




## Gradle Integration

為達到文件自動化編譯及佈署，我們使用 [Gradle Mkdocs plugin](https://github.com/xvik/gradle-mkdocs-plugin)跟我們的Gradle做整合。

在文件所在專案的build.gradle中加入Gradle Mkdocs plugin如下：

```groovy
plugins {
    id "ru.vyarus.mkdocs" version "1.0.1"
}
```

或是在舊版的Gradle中可以使用以下語法：

```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'ru.vyarus:gradle-mkdocs-plugin:1.0.1'
    }
}
apply plugin: 'ru.vyarus.mkdocs'
```

### MkDocs的版本問題
Gradle Mkdocs Plugin自動下載的MkDocs的版本為 **0.17.3** ，此版本在Python3.7下會有錯，所以必需在build.gradle設定自動下載版為 **0.17.5** 或以上才能正常運作。設定如下：

```groovy
//It's needed that mkdocs version to 0.17.5 to used with mkdocs gradle plugin
python {
    pip 'mkdocs:0.17.5'
}
```

### 檔案結構
Gradle Mkdocs Plugin預設的文件目錄在專案的src/doc底下：

- src/doc/mkdocs.yml : 為mkdocs的設定檔，定義mkdocs的參數及文件結構
- src/doc/docs : 放置所有文件的markdown原始檔
- build/mkdocs : 編譯後HTML site的產出目錄，mkdocs會在此目錄下依目前專案的版本號產生子目錄以放置HTML
- build/mkdocs/x.y.z : 依專案版本產生的HTML site所在目錄

![gradle-mkdocs-dir](images\gradle-mkdocs-dir.png)

### Gradle Tasks
- mkdocsInit

  建立mkdocs的初始檔案結構，包括文件大鋼設定檔，sample文件等

- mkdocsBuild

  把文件原始檔編譯成網頁

- mkdocsServe

  把文件原始檔編譯成網頁並執行一個自帶的http server來呈現網頁

- mkdocsPublish

  把編譯後的網站佈署到特定的網站，例如github上。

## Live Documentation Site

使用mkdocs文件系統編譯後可以進一步將產出的文件佈署到gateway成為線上版的文件，如此可以讓gateway起來時就文件也可以線上版的型式一起提供給客戶或供內部開發參考，線上版文件網址如以下樣式：[https://localhost:8443/solution-product-gateway/doc/guide/](https://localhost:8443/solution-product-gateway/doc/guide/)，使用browser就可以閱讀了。

要做到線上版文件佈署需要做以下幾個設定：

### Setting in build.gradle

在專案的build.gradle中加中以下設定：

```groovy
//將文件打包成resource jar以供web app讀取
task guideJar(type: Jar) {
    group "documentation"
    classifier "resource.guide"
    from mkdocsBuild {
        into "META-INF/$project.name/doc/guide"
    }
}

//將Javadoc打包成resoruce jar以供web app讀取
task javadocJar(type: Jar) {
    group "documentation"
    classifier "resource.javadoc"
    from javadoc {
        into "META-INF/$project.name/doc/javadoc"
    }
}

//將打包的好的reource jar變成web app的依賴以供web app在runtime時的classpath中讀取
dependencies {
    compile fileTree("$buildDir/libs") {
        include("*-${guideJar.classifier}.jar")
        //builtBy(guideJar)
    }
    
    compile fileTree("$buildDir/libs") {
        include("*-${javadocJar.classifier}.jar")
        //builtBy(javadocJar)
    }
}

//設定以下這些task會依賴上面的打包task
[run, bootRun, distZip, bootDistZip, distTar,bootDistTar, installDist, installDist]*.dependsOn guideJar, javadocJar
```

*task guideJar* 會將mkdocs編譯後的網站打包到到guide resource jar中的`/META-INF/$project.name/doc/guide`， *task javadocJar* 會將javadoc編譯後的網站打包到javadoc resource jar中的`/META-INF/$project.name/doc/javadoc`，後續的gradle distribution task在打包時會把guide resource jar一起包進去依賴裡成為Web app可讀取的靜態網頁資源。

### DocConfig.java

加入這個spring的Java Config是為了讓spring mvc可以讀取打包成resource jar的文件網頁來顯示：

```java
@Configuration
public class DocConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc/**")
                .addResourceLocations(String.format("classpath:/META-INF/%s/doc/", SolutionProductGatewayApplication.PROJECT_NAME));

    }
}
```

上述的設定用意是當browser讀取gateway application的/doc目錄及其下的網頁時spring mvc會知道使用resource handler去classpath的`/META-INF/${project.name}/doc中`去找相對應的檔案，例如：[https://localhost:8443/solution-product-gateway/doc/guide/index.html](https://localhost:8443/solution-product-gateway/mkdocs/index.html)就是classpath中的`/META-INF/solution-product-gateway/doc/guide/index.html`

關於spirng mvc的static resources可以參考[Static resources](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-config-static-resources)

### DocIndexController

由於文件網頁以靜態資源的型式打包進jar中，且透過spring mvc static resources機制讀取，原來的servlet container的家目錄welcome-file-list會失效，例如讀取https://localhost:8443/solution-product-gateway/doc/guide/會404找不到，而不會去自動去讀doc/guide下的index.html，解決此問題的workaround是新增一隻DocIndexController來控制網址重導(redirect)，程式如下：

```java
@Controller
public class DocIndexController {

    @GetMapping({"/doc/guide", "/doc/javadoc"})
    public String index(HttpServletRequest request) {

        String requestUrl = request.getRequestURL().toString();

        if (requestUrl.endsWith("/")) {
            return "redirect:" + requestUrl + "index.html";
        } else {
            return "redirect:" + requestUrl + "/index.html";
        }
    }
}
```

這隻controller設定會讓連到https://localhost:8443/solution-product-gateway/doc/guide或是https://localhost:8443/solution-product-gateway/doc/javadoc時會自動轉址到底下的index.html避免404找不到網頁錯誤。

>  FIXME: 是否更好的方式, ex: 修改servlet context設定<welcome-files>來達成

### Exclude interceptors for doc URL in WebMvcConfig.java

由於我們的gateway有加上interceptor在做權限控管，這樣用browser讀取線上文件時會很麻煩，所以可以把相關認證的interceptors排除套用在線上文件上，範例如下：

``````java
@Override
    //TODO: seaprate the exclude pattern by different config ex: swagger, mkdocs etc...
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(applicationAuthenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/resources/**", "/tests/**", "/localtest/**, ''/test",
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc/**");

        registry.addInterceptor(terminalAuthenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/**", "/app/login", "/resources/**", "/static/**", "/tests/**", "/localtest/**", "/test",
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc/**");

        registry.addInterceptor(contextPreparationInterceptor).addPathPatterns("/**");

        registry.addInterceptor(versionValidIntercetor)
                .addPathPatterns("/**")
                .excludePathPatterns("/actionEvents/**", "/app/login", "/user/**", "/tests/**", "/test",
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc/**");
    }
``````

上述程式中的`/doc/**`就是指/doc及以下的所有網址通通不要套用interceptor以免擋著doc存取。