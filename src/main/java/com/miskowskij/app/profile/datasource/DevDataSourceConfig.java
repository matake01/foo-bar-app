package com.miskowskij.app.profile.datasource;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevDataSourceConfig implements DataSourceConfig {

	private final static String PROPERTY_NAME_DRIVER_CLASS_NAME = "spring.datasource.jdbc.driver";
	private final static String PROPERTY_NAME_URL = "spring.datasource.jdbc.url";
	private final static String PROPERTY_NAME_USERNAME = "spring.datasource.jdbc.username";
	private final static String PROPERTY_NAME_PASSWORD = "spring.datasource.jdbc.password";

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(DevDataSourceConfig.class);

	private DataSource dataSource;
	private Environment env;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Autowired
	public void setEnvironment(Environment env) {
		this.env = env;
	}

	@Override
    public void setup() {
    	logger.info("Setting up DataSource config for DEV environment");

    	DataSourceProperties properties = new DataSourceProperties();

    	properties.setProperty(PROPERTY_NAME_DRIVER_CLASS_NAME, env.getRequiredProperty(PROPERTY_NAME_DRIVER_CLASS_NAME));
    	properties.setProperty(PROPERTY_NAME_URL, env.getRequiredProperty(PROPERTY_NAME_URL));
    	properties.setProperty(PROPERTY_NAME_USERNAME, env.getRequiredProperty(PROPERTY_NAME_USERNAME));
    	properties.setProperty(PROPERTY_NAME_PASSWORD, env.getRequiredProperty(PROPERTY_NAME_PASSWORD));

    	logger.debug("DataSourceProperties: " + properties.toString());

    	DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(properties.getProperty(PROPERTY_NAME_DRIVER_CLASS_NAME));
	    dataSource.setUrl(properties.getProperty(PROPERTY_NAME_URL));
	    dataSource.setUsername(properties.getProperty(PROPERTY_NAME_USERNAME));
	    dataSource.setPassword(properties.getProperty(PROPERTY_NAME_PASSWORD));

    	this.dataSource = dataSource;
    }

}
