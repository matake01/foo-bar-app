package com.miskowskij.app.persistence.configuration.jpa;

import org.slf4j.LoggerFactory;

public abstract class JpaConfig {


	protected static final String PROPERTY_NAME_HIBERNATE_DIALECT = "HIBERNATE_DIALECT";
    protected static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "HIBERNATE_HBM2DDL_AUTO";
    
    protected String dialect;
    protected String hbm2ddlAuto;
    
    public JpaConfig() { }

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String getHbm2ddlAuto() {
		return hbm2ddlAuto;
	}

	public void setHbm2ddlAuto(String hbm2ddlAuto) {
		this.hbm2ddlAuto = hbm2ddlAuto;
	}
	
	public boolean isEmpty() {
		return dialect == null || dialect.isEmpty();
	}
	
	@Override
	public String toString() {
		return "{ Dialect: " + dialect + ", Hbm2ddlAuto: " + hbm2ddlAuto + " }";
	}
	
	abstract public void create();
    
}
