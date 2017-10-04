package com.miskowskij.app.persistence.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig{
 
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AuditingConfig.class);
	
	public AuditingConfig() {
		logger.debug("AuditingConfig initiated");
	}

   
}