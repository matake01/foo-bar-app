package com.miskowskij.app.persistence.configuration.datasource;

import org.springframework.core.env.Environment;

public class SpringEnvironment extends DataSourceConfig {

	private Environment env;
	
	public SpringEnvironment(Environment env) {
		super();
		
		this.env = env;
		
		this.create();
	}
	
	@Override
	public void create() {
		setDriver(env.getRequiredProperty(PROPERTY_NAME_DB_DRIVER_CLASS));
		setUrl(env.getRequiredProperty(PROPERTY_NAME_DB_URL));
		setUsername(env.getRequiredProperty(PROPERTY_NAME_DB_USER));
		setPassword(env.getRequiredProperty(PROPERTY_NAME_DB_PASSWORD));
	}

}
