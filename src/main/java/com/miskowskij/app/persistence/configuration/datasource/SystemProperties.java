package com.miskowskij.app.persistence.configuration.datasource;

public class SystemProperties extends DataSourceConfig {

	public SystemProperties() {
		super();
		
		this.create();
	}
	
	@Override
	public void create() {
		setDriver(System.getProperty(PROPERTY_NAME_DB_DRIVER_CLASS));
		setUrl(System.getProperty(PROPERTY_NAME_DB_URL));
		setUsername(System.getProperty(PROPERTY_NAME_DB_USER));
		setPassword(System.getProperty(PROPERTY_NAME_DB_PASSWORD));
	}

}
