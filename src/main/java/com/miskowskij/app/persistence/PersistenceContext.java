package com.miskowskij.app.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class PersistenceContext{
 
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(PersistenceContext.class);
	
    private static final String[] ENTITY_PACKAGES = {
            "com.miskowskij.app.persistence"
    };
    
    private static final String PROPERTY_NAME_DB_DRIVER_CLASS = "DB_DRIVER";
    private static final String PROPERTY_NAME_DB_PASSWORD = "DB_PASSWORD";
    private static final String PROPERTY_NAME_DB_URL = "DB_URL";
    private static final String PROPERTY_NAME_DB_USER = "DB_USERNAME";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "HIBERNATE_DIALECT";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "HIBERNATE_HBM2DDL_AUTO";
    
    private Map<String, String> dataSourceConfig = null;
    private Map<String, String> jpaConfig = null;

    @Autowired
	public PersistenceContext(Environment env) {
		logger.debug("PersistenceContext initiated");
	}
	

    /**
     * Creates and configures the datasource bean.
     * 
     * @param env   The runtime environment of our application.
     * @return
     */
	@Bean
   public DataSource dataSource(Environment env){
	  DriverManagerDataSource dataSource = new DriverManagerDataSource();
	
	  setupDataSourceConfig(env);
	  
	  dataSource.setDriverClassName(this.dataSourceConfig.get(PROPERTY_NAME_DB_DRIVER_CLASS));
      dataSource.setUrl(this.dataSourceConfig.get(PROPERTY_NAME_DB_URL));      
      dataSource.setUsername(this.dataSourceConfig.get(PROPERTY_NAME_DB_USER));
      dataSource.setPassword(this.dataSourceConfig.get(PROPERTY_NAME_DB_PASSWORD));

      return dataSource;
   }
   
    /**
     * Creates the bean that creates the JPA entity manager factory.
     * 
     * @param dataSource    The datasource that provides the database connections.
     * @param env           The runtime environment of  our application.
     * @return
     */
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
      entityManagerFactoryBean.setDataSource(dataSource);
      entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);

	  setupJpaConfig(env);

      Properties jpaProperties = new Properties();
      
      // Specifies the action that is invoked to the database when the Hibernate
      // SessionFactory is created or closed.
      jpaProperties.put("hibernate.hbm2ddl.auto", "none");

      String hbm2ddlAuto = this.jpaConfig.get(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO);
      if (hbm2ddlAuto != null && !hbm2ddlAuto.isEmpty()) {
          jpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
      } 
      
      logger.debug("Final value of 'hibernate.hbm2ddl.auto'=" + jpaProperties.get("hibernate.hbm2ddl.auto"));
      
      // Configures the used database dialect. This allows Hibernate to create SQL
      // that is optimized for the used database.
      jpaProperties.put("hibernate.dialect", this.jpaConfig.get(PROPERTY_NAME_HIBERNATE_DIALECT));
      
      // Specifies extended C3P0/pooling properties for use in need of extended customizations
      // regarding thread pooling and additional performance handling.
      // jpaProperties.put("hibernate.c3p0.min_size", "16");
      // jpaProperties.put("hibernate.c3p0.max_size", "32");
      // jpaProperties.put("hibernate.c3p0.timeout", "240");
      // jpaProperties.put("hibernate.c3p0.max_statements", "50");
      // jpaProperties.put("hibernate.c3p0.aquire_increment", "8");
      // jpaProperties.put("hibernate.c3p0.idle_test_period", "120");

      entityManagerFactoryBean.setJpaProperties(jpaProperties);
 
      return entityManagerFactoryBean;
   }


   /**
    * Creates the transaction manager bean that integrates the used JPA provider with the
    * Spring transaction mechanism.
    * 
    * @param entityManagerFactory  The used JPA entity manager factory.
    * @return
    */
   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory);
 
      return transactionManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }
	   
   private void setupDataSourceConfig(Environment env) {
		this.dataSourceConfig = new HashMap<String, String>();

		this.dataSourceConfig.put(PROPERTY_NAME_DB_DRIVER_CLASS, System.getProperty(PROPERTY_NAME_DB_DRIVER_CLASS));
		this.dataSourceConfig.put(PROPERTY_NAME_DB_URL, System.getProperty(PROPERTY_NAME_DB_URL));
		this.dataSourceConfig.put(PROPERTY_NAME_DB_USER, System.getProperty(PROPERTY_NAME_DB_USER));
		this.dataSourceConfig.put(PROPERTY_NAME_DB_PASSWORD, System.getProperty(PROPERTY_NAME_DB_PASSWORD));

		this.dataSourceConfig.putIfAbsent(PROPERTY_NAME_DB_DRIVER_CLASS, env.getRequiredProperty(PROPERTY_NAME_DB_DRIVER_CLASS));
		this.dataSourceConfig.putIfAbsent(PROPERTY_NAME_DB_URL, env.getRequiredProperty(PROPERTY_NAME_DB_URL));
		this.dataSourceConfig.putIfAbsent(PROPERTY_NAME_DB_USER, env.getRequiredProperty(PROPERTY_NAME_DB_USER));
		this.dataSourceConfig.putIfAbsent(PROPERTY_NAME_DB_PASSWORD, env.getRequiredProperty(PROPERTY_NAME_DB_PASSWORD));

		logger.debug("DataSourceConfig: " + this.dataSourceConfig.toString());
	}
	
	private void setupJpaConfig(Environment env) {
		this.jpaConfig = new HashMap<String, String>();
		
		this.jpaConfig.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, System.getProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
		this.jpaConfig.put(PROPERTY_NAME_HIBERNATE_DIALECT, System.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
	
		this.jpaConfig.putIfAbsent(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
		this.jpaConfig.putIfAbsent(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
	
		logger.debug("JpaConfig: " + this.jpaConfig.toString());
	}
   
}