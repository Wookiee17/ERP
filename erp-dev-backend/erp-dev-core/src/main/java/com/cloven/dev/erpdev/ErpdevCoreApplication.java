package com.cloven.dev.erpdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.cloven.dev")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.cloven.dev.erpdev.model"})
public class ErpdevCoreApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ErpdevCoreApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ErpdevCoreApplication.class);
	}

}
