package com.miskowskij.app.profile.datasource;

import java.util.HashMap;
import java.util.Map;

public class DataSourceProperties {

	private Map<String, String> properties;
	
	public DataSourceProperties() {
		this.properties = new HashMap<String, String>();
	}
	
	public String getProperty(String key) {
		return properties.get(key);
	}
	
	public void setProperty(String key, String value) {
		this.properties.put(key, value);
	}
	
	public String toString() {
		return properties.toString();
	}
}
