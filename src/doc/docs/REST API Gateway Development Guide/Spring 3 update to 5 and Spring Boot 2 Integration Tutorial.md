# Spring 3 update to 5 && Spring Boot 2 Integration Tutorial (PPT)
8/2/2018 15:00:00 PM

---

## Revision History

|Date      |Version|Author  |Brief|
|----------|------:|--------|-----|
|2018-08-10  |1.0    |Leo Kuo|Initial version|

---

## 前言
公司Woodoos的Gateway **(gc-affinity-mgc-gateway)** 目前線上的版本為Spring 3，但因其版本過舊，以及需要利用ReactJS開發新的版本APP，而ReactJS在**發送/接收**訊息，需要借助**Spring 4.2**以上所提供的Annoation(@CrossOrigin)，故將連接的Gateway的Spring版本升等到Spring 5，並以此文件作為紀錄。

---

## 使用方法範例: (gc-affinity-mgc-gateway) #
**目標環境**
- **Gradle-4.6-all.zip**
- **SpringFramework version: 5.0.7.RELEASE**
- **Spring Boot Framework version: 2.0.3.RELEASE**

---

#### 升級Gradle版本 ####
#### ※gc-affinity-mgc-gateway原本的Gradle版本為2.12，但是不支援Spring 5的編譯，故將此版本升級到4.6 ####
- 檔案路徑 : gc-affinity-mgc-gateway/gradle/wrapper/gradle-wrapper.properties
```groovy
distributionUrl=https\://services.gradle.org/distributions/gradle-4.6-all.zip
```

---

#### 將build.gradle底下的3.2.18.RELEASE全替換成5.0.7.RELEASE ####
#### ※接著 ####
- 檔案路徑 : gc-affinity-mgc-gateway/gradle/wrapper/gradle-wrapper.properties
```groovy
plugins {
    id "org.springframework.boot" version "2.0.3.RELEASE"
}
...
apply plugin: 'io.spring.dependency-management'
...

```

---

![](https://i.imgur.com/d0Q9AVc.jpg)

---

#### dispather-servlet.xml 修改
- 升級到Spring5後，dispather-servlet.xml這邊還會有四個錯誤
- 前兩個錯誤為伺服器在Spring3時，直接回傳網頁給Client的設定，目前改為回傳格式為JSON，故先暫時拿掉

![](https://i.imgur.com/ywn6rHc.jpg)

---

#### 藏在Bean中的魔鬼
- 上面將回傳網頁的設定移除，故在Bean中的xml處理器也須跟著移除(圖中第43行)
- Spring5已改成MappingJackson2HttpMessageConverter(圖中第45行)

![](https://i.imgur.com/gHJUPsX.jpg)

**改成**

![](https://i.imgur.com/WVKPL9N.jpg)

---

#### 將Exception Resolver升級
- 利用5的ExceptionHandlerExceptionResolver，替換之前使用的AnnotationMethodHandlerExceptionResolver
- Spring 5也改用MappingJackson2HttpMessageConverter，取代原本的MappingJacksonHttpMessageConverter

![](https://i.imgur.com/U2YlXw6.jpg)

**改成**

![](https://i.imgur.com/7oguoc3.jpg)

---

#### jackson引用 (最後有完整版build.gradle)
- 承如之上XML(Jackson->Jackson2)，所以也需要另外再引用jackson的依賴

![](https://i.imgur.com/rGYqRdE.png)

---

#### 將 Spring 3 的 SimpleJdbcTemplate 替換
- Spring 5 中已將 Spring 3 中的 SimpleJdbcTemplate 替換成 JdbcTemplate
- 將所有引用到 SimpleJdbcTemplate 的class都改成引用 JdbcTemplate 
```java
...
import org.springframework.jdbc.core.JdbcTemplate;
...
```
![](https://i.imgur.com/aIN0Iwh.jpg)


---

#### 將 gc-wsc 的依賴移除，並使用新版的JAR (最後有完整版build.gradle)
- 因 gc-wsc 的一些依賴會與新版本的依賴造成版本不符的衝突，所以移除
- 同時，因為gc-wsc中的applicationDao.xml將使用 JdbcTemplate 替換原有的 SimpleJdbcTemplate，所以拜託Ayo升版0.0.3-SNAPSHOT

![](https://i.imgur.com/uwJNMrC.png)

---

#### 引用新版的 jap-common 的JAR (最後有完整版build.gradle)
- 舊版的jap-common 一樣存在 SimpleJdbcTemplate 的問題，新版的2.0-SNAPSHOT也修正了該問題

![](https://i.imgur.com/5cfU3gP.png)

---

#### CFX2升級問題 (最後有完整版build.gradle)
- 原本在Spring 3使用的CFX版本是2，但是CFX2並不支援Spring4以上，故也須將此升級
- 利用Spring Boot 2提供的整合套件來升級

![](https://i.imgur.com/pQE1yyQ.png)
![](https://i.imgur.com/WO7c8Kq.png)

---

#### httpclient / httpcore版本 (最後有完整版build.gradle)
- 原先所使用的httpclient / httpcore都是依賴於gc-wsc，但是現在移除gc-wsc了，所以需要自行引入
- httpclient / httpcore需要4版以上才能支援Spring4以上

![](https://i.imgur.com/EhNalQl.png)

---

#### 其餘的引用 (最後有完整版build.gradle)
- 上述問題解決後，會只剩下一些些少引用的錯誤，如下圖所示

![](https://i.imgur.com/NraBsiG.png)

---

#### 完整版的build.gradle

```groovy

plugins {
    id "org.springframework.boot" version "2.0.3.RELEASE"
    id "ru.vyarus.mkdocs" version "1.0.1"
}

apply plugin: 'java'
apply plugin: 'eclipse-wtp'
apply plugin: 'idea'
apply plugin: 'io.spring.dependency-management'
apply plugin: 'war'
apply plugin: 'application'
apply from: 'ogloba-repository.gradle'

group = 'com.ogloba.solution.product.gateway'
version = '1.0.1'
mainClassName = 'com.ogloba.solution.product.gateway.SolutionProductGatewayApplication'
sourceCompatibility = JavaVersion.VERSION_1_8

//Custom task config
jar.enabled = true
war.enabled = true
bootJar.classifier = 'boot'
bootWar.classifier = 'boot'

repositories {
    mavenCentral()
}

configurations {
    providedRuntime
    runtimeAgent
    all {
        resolutionStrategy {
            cacheChangingModulesFor 0, 'seconds'
        }
        //Need to exclude old commons-logging in with spring
        exclude group:'commons-logging', module: 'commons-logging'
        exclude module: 'slf4j-log4j12'
    }
}


dependencies {
	compile('org.springframework.boot:spring-boot-starter-web')
	compile('org.springframework.boot:spring-boot-starter-jdbc')
    compile("org.springframework.boot:spring-boot-starter-aop")
    //compile("org.springframework.boot:spring-boot-devtools")
	compile("org.springframework:spring-jcl")
    compile("org.springframework:spring-aspects")
    compile "org.springframework:spring-instrument"
    //compile "org.aspectj:aspectjweaver"
    compile group: 'org.mybatis.spring.boot', name: 'mybatis-spring-boot-starter', version: '1.3.1'

	compile("com.oracle:ojdbc8:12.2.0.1")

    //compile group: 'org.slf4j', name: 'jcl-over-slf4j'
    compile group: 'org.codehaus.janino', name: 'janino', version: '3.0.8'

    compile("commons-configuration:commons-configuration:1.6")
    compile("commons-io:commons-io:2.6")

    compile group: 'org.apache.commons', name: 'commons-lang3', version: '3.7'
    compile group: 'org.apache.cxf', name: 'cxf-spring-boot-starter-jaxws', version: '3.2.2'

    compile group: 'org.apache.commons', name: 'commons-compress', version: '1.16.1'
    compile group: 'org.apache.commons', name: 'commons-vfs2', version: '2.2'

    compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.7.0'
    compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.7.0'


    compile(group: 'com.woodoos', name: 'jap-common', version: '2.0-SNAPSHOT') {
        transitive = false
    }

    compile('com.ogloba.solution:solution-product-library:0.2-SNAPSHOT')
    
    compile(group: 'com.ogloba.jap', name: 'jap-core', version: '0.0.1')  {
        exclude group:'com.woodoos'
        exclude group: 'org.springframework'
        exclude group: 'org.slf4'
        exclude group: 'ch.qos.logback'
        exclude group: 'org.aspectj'
        exclude module: 'commons-logging'
        exclude module: 'commons-vfs2'
        exclude module: 'geronimo-servlet_3.0_spec'
        exclude module: 'javax.servlet-api'
        exclude module: 'servlet-api'
    }

    compileOnly "org.springframework.boot:spring-boot-configuration-processor"

    testCompile('org.springframework.boot:spring-boot-starter-test')
    testCompile 'org.mockito:mockito-core:2.21.0'

    providedRuntime('org.springframework.boot:spring-boot-starter-tomcat')

    //For spring agent
    runtimeAgent("org.aspectj:aspectjweaver")
    runtimeAgent("org.springframework:spring-instrument")
}


//FIXME: Add aspejctweaving.jar as jvm agent
configurations.runtimeAgent.resolvedConfiguration.resolvedArtifacts.each { artifact ->
    applicationDefaultJvmArgs += "-javaagent:MY_APP_HOME/lib/${artifact.file.name}"
    [run, bootRun].each { task ->
        task.jvmArgs "-javaagent:${artifact.file.absolutePath}"
    }
}

//This setting is needed for agent jar path at runtime: -javaagent:MY_APP_HOME/lib/${artifact.file.name}
startScripts {
    doLast {
        unixScript.text = unixScript.text.replace('MY_APP_HOME', '\$APP_HOME')
        windowsScript.text = windowsScript.text.replace('MY_APP_HOME', '%~dp0..')
    }
}

//It's needed that mkdocs version to 0.17.5 to used with mkdocs gradle plugin
python {
    pip 'mkdocs:0.17.5'
}

task copyMkdocs(type: Copy) {
    dependsOn mkdocsBuild
    from "${buildDir}/mkdocs"
    into "$buildDir/classes/java/main/META-INF/resources/mkdocs"
}

classes {
    dependsOn copyMkdocs
}

```

---

Thanks!
