# Overview

為統一分散在各個Project的程式及開發方式，整理此份Ogloba REST Gateway Development Guide做法開發手冊。

文件內容皆會有相對應的參考實作可以參考。



文件內容將包括下列內容：

[1.	API	4](#__RefHeading___Toc256000002)

 [1.1.	API Version	4](#__RefHeading___Toc256000003)

 [1.1.1.	Version Definition	4](#__RefHeading___Toc256000004)

 [1.1.2.	@ApiVersion	4](#__RefHeading___Toc256000005)

 [1.1.3.	Default: newest	4](#__RefHeading___Toc256000006)

 [1.1.4.	Supply	4](#__RefHeading___Toc256000007)

 [1.1.5.	Principle	4](#__RefHeading___Toc256000008)

 [1.2.	Spec	4](#__RefHeading___Toc256000009)

 [1.2.1.	Request/Response	4](#__RefHeading___Toc256000010)

 [1.2.2.	DTO	4](#__RefHeading___Toc256000011)

 [1.3.	Publishing	4](#__RefHeading___Toc256000012)

 [1.3.1.	Swagger/Open API	4](#__RefHeading___Toc256000013)

[2.	Data Binding	4](#__RefHeading___Toc256000014)

 [2.1.	API/Message DTO	5](#__RefHeading___Toc256000015)

 [2.1.1.	Request	5](#__RefHeading___Toc256000016)

 [2.1.2.	Response	5](#__RefHeading___Toc256000017)

 [2.1.3.	Domain DTO	5](#__RefHeading___Toc256000018)

 [2.2.	Data Validation	5](#__RefHeading___Toc256000019)

 [2.2.1.	Hibernate Validation	5](#__RefHeading___Toc256000020)

[3.	Authentication	5](#__RefHeading___Toc256000021)

 [3.1.	App Level	5](#__RefHeading___Toc256000022)

 [3.1.1.	API ID/API Key	5](#__RefHeading___Toc256000023)

 [3.2.	Device Level	5](#__RefHeading___Toc256000024)

 [3.2.1.	id/passphrase	5](#__RefHeading___Toc256000025)

 [3.2.2.	AccessToken	5](#__RefHeading___Toc256000026)

 [3.3.	User Level	5](#__RefHeading___Toc256000027)

 [3.3.1.	username/password	5](#__RefHeading___Toc256000028)

[4.	Authorization	5](#__RefHeading___Toc256000029)

[5.	Media Type	5](#__RefHeading___Toc256000030)

 [5.1.	JSON	5](#__RefHeading___Toc256000031)

 [5.2.	HTML	5](#__RefHeading___Toc256000032)

[6.	Data Type	5](#__RefHeading___Toc256000033)

[7.	Naming	6](#__RefHeading___Toc256000034)

 [7.1.	URL	6](#__RefHeading___Toc256000035)

 [7.2.	Message	6](#__RefHeading___Toc256000036)

 [7.3.	Parameter	6](#__RefHeading___Toc256000037)

[8.	Error Handling	6](#__RefHeading___Toc256000038)

 [8.1.	Protocol Level	6](#__RefHeading___Toc256000039)

 [8.2.	Application Level	6](#__RefHeading___Toc256000040)

 [8.3.	Exception Handling	6](#__RefHeading___Toc256000041)

[9.	Framework	6](#__RefHeading___Toc256000042)

 [9.1.	SpringBoot/SpringMVC	6](#__RefHeading___Toc256000043)

[10.	Best Practice	6](#__RefHeading___Toc256000044)

 [10.1.	URIEncoding	6](#__RefHeading___Toc256000045)

[11.	Interceptor	6](#__RefHeading___Toc256000046)

[12.	Spring Config	6](#__RefHeading___Toc256000047)

 [12.1.	Java Config	6](#__RefHeading___Toc256000048)

 [12.2.	Zero XML config	6](#__RefHeading___Toc256000049)

 [12.2.1.	Java Config	6](#__RefHeading___Toc256000050)

[13.	Configuration	6](#__RefHeading___Toc256000051)

 [13.1.	Load from application.properties/yml	6](#__RefHeading___Toc256000052)

 [13.2.	Every Properties has a Java POJO	7](#__RefHeading___Toc256000053)

 [13.2.1.	@ConfigurationProperties("xxx")	7](#__RefHeading___Toc256000054)

 [13.2.2.	[ConfigName\]Properties	7](#__RefHeading___Toc256000055)

 [13.2.3.	To support runtime change	7](#__RefHeading___Toc256000056)

 [13.3.	Naming	7](#__RefHeading___Toc256000057)

 [13.3.1.	SolutionId.ModuleId.ConfigId	7](#__RefHeading___Toc256000058)

 [13.4.	Reasonable Default	7](#__RefHeading___Toc256000059)

 [13.5.	From build tool	7](#__RefHeading___Toc256000060)

 [13.6.	load from custom properties	7](#__RefHeading___Toc256000061)

 [13.7.	@Value	7](#__RefHeading___Toc256000062)

 [13.8.	Profile	7](#__RefHeading___Toc256000063)

[14.	Dao	7](#__RefHeading___Toc256000064)

 [14.1.	MyBatis	7](#__RefHeading___Toc256000065)

[15.	Logging	7](#__RefHeading___Toc256000066)

 [15.1.	Notice the log lib conflict	7](#__RefHeading___Toc256000067)

 [15.1.1.	Exclude	7](#__RefHeading___Toc256000068)

 [15.2.	Use the spring config	7](#__RefHeading___Toc256000069)

 [15.3.	Logback-Spring integration	8](#__RefHeading___Toc256000070)

 [15.3.1.	logback-spring.xml	8](#__RefHeading___Toc256000071)

 [15.4.	Session	8](#__RefHeading___Toc256000072)

 [15.4.1.	MDC	8](#__RefHeading___Toc256000073)

[16.	Build	8](#__RefHeading___Toc256000074)

 [16.1.	Gradle	8](#__RefHeading___Toc256000075)

 [16.2.	Dep management	8](#__RefHeading___Toc256000076)

[17.	Feature	8](#__RefHeading___Toc256000077)

 [17.1.	feature toggle	8](#__RefHeading___Toc256000078)

[18.	Dependency	8](#__RefHeading___Toc256000079)

 [18.1.	Exclude	8](#__RefHeading___Toc256000080)

 [18.1.1.	commons-logging	8](#__RefHeading___Toc256000081)

 [18.1.2.	slf4j-log4j12	8](#__RefHeading___Toc256000082)

 [18.2.	Dependency management	8](#__RefHeading___Toc256000083)

 [18.3.	Most errors caused by the duplicated jar with versions	8](#__RefHeading___Toc256000084)

[19.	JMX	8](#__RefHeading___Toc256000085)

 [19.1.	MBean	8](#__RefHeading___Toc256000086)

[20.	Async/Schedule	8](#__RefHeading___Toc256000087)

[21.	Doc	8](#__RefHeading___Toc256000088)

 [21.1.	mkdocs	9](#__RefHeading___Toc256000089)

[22.	Release	9](#__RefHeading___Toc256000090)

[23.	Eclipse安裝 與 Gradle plugin整合](#__RefHeading___Toc256000091)

[24.	Spring 3 update to 5 and Spring Boot 2 Integration Tutorial](#__RefHeading___Toc256000092)
