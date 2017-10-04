package com.miskowskij.app.persistence.configuration.jpa;

public class SystemProperties extends JpaConfig {

	public SystemProperties() {
		super();
		
		this.create();
	}
	
	@Override
	public void create() {
		setDialect(System.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		setHbm2ddlAuto(System.getProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
	}

}
