package com.miskowskij.app.profile;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import java.lang.Exception;

@Component
public class ProfileManager {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ProfileManager.class);

	@Autowired ConfigurableEnvironment env;

	public ProfileManager() { }

	public boolean isActiveProfile(String profileName) {
		return env.acceptsProfiles(profileName);
	}

	public void handleRuntimeProfile() throws Exception {

		String[] activeProfiles = getActiveProfiles();
		for (final String profileName : activeProfiles) {

			if (profileName.equalsIgnoreCase("production")) {

				boolean shouldExitIfProduction = true;
				if (shouldExitIfProduction) {
					logger.error("Invalid request. The application was booted in not allowed state 'production'.");
					throw new Exception("Production is not allowed");
				}

				logger.debug("Active Profile: " + profileName);
			}
		}
	}

	public	String[] getActiveProfiles() {
    	return env.getActiveProfiles();
  	}

}
