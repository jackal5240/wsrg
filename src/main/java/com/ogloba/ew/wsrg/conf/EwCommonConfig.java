package com.ogloba.ew.wsrg.conf;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
//@ComponentScan("com.woodoos.ew.common")
@MapperScan(basePackages="com.woodoos.ew.common" ,annotationClass=Repository.class)
//@Import(JavaMailConfig.class)
public class EwCommonConfig {

}
