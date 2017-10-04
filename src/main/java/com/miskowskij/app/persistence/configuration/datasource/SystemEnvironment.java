package com.miskowskij.app.persistence.configuration.datasource;

public class SystemEnvironment extends DataSourceConfig {

	public SystemEnvironment() {
		super();
		
		this.create();
	}
	
	@Override
	public void create() {
		setDriver(System.getenv(PROPERTY_NAME_DB_DRIVER_CLASS));
		setUrl(System.getenv(PROPERTY_NAME_DB_URL));
		setUsername(System.getenv(PROPERTY_NAME_DB_USER));
		setPassword(System.getenv(PROPERTY_NAME_DB_PASSWORD));
	}

}
