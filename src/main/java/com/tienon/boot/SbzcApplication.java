package com.tienon.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

import com.tienon.auto.conf.FilterConfiguration;

/***
 * import参数 1、加载流程：Ejx4easConfig 2、加载流程冲正机制：EasReversConf
 * 3、默认登录权限控制FilterConfiguration.class
 * 
 * ServletComponentScan：扫描servlet EnableScheduling：启动定时任�?
 * ImportResource：还在xml配置文件，不常用
 * 
 * @author xiongyi
 *
 */
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@ImportResource({ "classpath:spring/spring-ejx4component-pro.xml" })
@Import({ FilterConfiguration.class })
public class SbzcApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SbzcApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SbzcApplication.class);
	}
}
