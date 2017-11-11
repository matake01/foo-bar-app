package com.miskowskij.app.profile;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileManager {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ProfileManager.class);

	@Autowired Environment env;
	
	public ProfileManager() { }
	
	public boolean acceptsProfiles(String profiles) {
		return env.acceptsProfiles(profiles);
	}
	
	public void traceActiveProfiles() {		
        for (final String profileName : env.getActiveProfiles()) {
            logger.debug("Currently active profile - " + profileName);
        }           
    }
	
}