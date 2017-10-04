package com.miskowskij.app.persistence.configuration.datasource;

public abstract class DataSourceConfig {

    protected static final String PROPERTY_NAME_DB_DRIVER_CLASS = "DB_DRIVER";
    protected static final String PROPERTY_NAME_DB_PASSWORD = "DB_PASSWORD";
    protected static final String PROPERTY_NAME_DB_URL = "DB_URL";
    protected static final String PROPERTY_NAME_DB_USER = "DB_USERNAME";
    
	protected String driver;
	protected String url;
	protected String username;
	protected String password;
	
	public DataSourceConfig() { }

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEmpty() {
		return this.driver == null || this.driver.isEmpty();
	}
	
	@Override
	public String toString() {
		return "{ Driver: " + driver + ", Url: " + url + ", Username: " + username + ", Password: " + password + " }";
	}
	
	abstract public void create();

}
