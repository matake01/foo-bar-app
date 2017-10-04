package com.miskowskij.app.persistence.configuration.jpa;

public class SystemEnvironment extends JpaConfig {

	public SystemEnvironment() {
		super();
		
		this.create();
	}
	
	@Override
	public void create() {
		setDialect(System.getenv(PROPERTY_NAME_HIBERNATE_DIALECT));
		setHbm2ddlAuto(System.getenv(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
	}

}
