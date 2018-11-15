package com.ogloba.ew.wsrg;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages ="com.ogloba.ew.wsrg", annotationClass = Mapper.class)
@ComponentScan({"com.woodoos.ew.common", "com.ogloba.ew.wsrg"})
@ServletComponentScan
public class EwWsrgApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EwWsrgApplication.class, args);
//		JapCommon.setApplicationContext(context);
	}
}
