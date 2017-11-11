package com.miskowskij.app.profile.datasource;

import javax.sql.DataSource;

public interface DataSourceConfig {
	
	public void setup();
    public DataSource getDataSource();
    
}