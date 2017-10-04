package com.miskowskij.app.persistence.configuration.jpa;

import org.springframework.core.env.Environment;

public class SpringEnvironment extends JpaConfig {

	private Environment env;
	
	public SpringEnvironment(Environment env) {
		super();
		
		this.env = env;
		this.create();
	}
	
	@Override
	public void create() {
		setDialect(env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		setHbm2ddlAuto(env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
	}

}
