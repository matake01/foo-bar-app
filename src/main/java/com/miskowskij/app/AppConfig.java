package com.miskowskij.app;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.miskowskij.app.profile.ProfileManager;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.miskowskij.app.*" })
public class AppConfig {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AppConfig.class);

    private final static String PROPERTY_NAME_LOG_DIR = "log.dir";

    public AppConfig(Environment env, ProfileManager profileManager) throws Exception {
        logger.debug("ApplicationContext initiated");

        logger.debug("Property 'log.dir': " + env.getRequiredProperty(PROPERTY_NAME_LOG_DIR));

        profileManager.handleRuntimeProfile();
    }

}
