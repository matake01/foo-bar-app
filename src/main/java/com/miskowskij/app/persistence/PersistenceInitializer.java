package com.miskowskij.app.persistence;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersistenceInitializer {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(PersistenceInitializer.class);

	public PersistenceInitializer() {
		logger.debug("PersistenceInitializer initiated");
	}

}