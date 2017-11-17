package com.miskowskij.app.profile.jpa;

import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevJpaConfig implements JpaConfig {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(DevJpaConfig.class);

	private Environment env;
	private Properties properties;

	public DevJpaConfig() { }

	@Autowired
	public void setEnvironment(Environment env) {
		this.env = env;
	}

	public Properties getProperties() {
		return this.properties;
	}

	@Override
	public void setup() {
    	logger.info("Setting up JpaProperties config for DEV environment");

        Properties properties = new Properties();

        properties.put("hibernate.dialect", env.getRequiredProperty("spring.jpa.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.c3p0.min_size", "16");
        properties.put("hibernate.c3p0.max_size", "32");
        properties.put("hibernate.c3p0.timeout", "240");
        properties.put("hibernate.c3p0.max_statements", "50");
        properties.put("hibernate.c3p0.aquire_increment", "8");
        properties.put("hibernate.c3p0.idle_test_period", "120");

		this.properties = properties;
	}

}
