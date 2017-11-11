package com.miskowskij.app;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.miskowskij.app.*" })
public class AppConfig {
     
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AppConfig.class);

	public AppConfig() {
		logger.debug("AppConfiguration initiated");
	}
	
}