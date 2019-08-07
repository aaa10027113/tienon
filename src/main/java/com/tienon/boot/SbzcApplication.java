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
/**
 * @Description 启动项目
 * 
 * @author xieyongqiang
 * @date 2019/08/05
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
