package com.miskowskij.app.persistence;

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

import com.miskowskij.app.persistence.configuration.datasource.DataSourceConfig;
import com.miskowskij.app.persistence.configuration.datasource.SpringEnvironment;
import com.miskowskij.app.persistence.configuration.datasource.SystemEnvironment;
import com.miskowskij.app.persistence.configuration.datasource.SystemProperties;
import com.miskowskij.app.persistence.configuration.jpa.JpaConfig;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class PersistenceContext{
 
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(PersistenceContext.class);
	
    private static final String[] ENTITY_PACKAGES = {
            "com.miskowskij.app.persistence"
    };
    
    private DataSourceConfig dataSourceConfig = null;
    private JpaConfig jpaConfig = null;

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
	  
	  dataSource.setDriverClassName(this.dataSourceConfig.getDriver());
      dataSource.setUrl(this.dataSourceConfig.getUrl());      
      dataSource.setUsername(this.dataSourceConfig.getUsername());
      dataSource.setPassword(this.dataSourceConfig.getPassword());

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

      String hbm2ddlAuto = this.jpaConfig.getHbm2ddlAuto();
      if (hbm2ddlAuto != null && !hbm2ddlAuto.isEmpty()) {
          jpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
      } 
      
      logger.debug("Final value of 'hibernate.hbm2ddl.auto'=" + jpaProperties.get("hibernate.hbm2ddl.auto"));
      
      // Configures the used database dialect. This allows Hibernate to create SQL
      // that is optimized for the used database.
      jpaProperties.put("hibernate.dialect", this.jpaConfig.getDialect());
      
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
		DataSourceConfig dataSourceConfig = new SystemProperties();
		
		if (dataSourceConfig.isEmpty()) {
			dataSourceConfig = new SpringEnvironment(env);
		}
		
		if (dataSourceConfig.isEmpty()) {
			dataSourceConfig = new SystemEnvironment();
		}
		
		if (dataSourceConfig.isEmpty()) {
			throw new RuntimeException("Failed to load DataSourceConfig properties");
		}
		
		this.dataSourceConfig = dataSourceConfig;
		
		logger.debug("DataSourceConfig: " + this.dataSourceConfig.toString());
	}
	
	private void setupJpaConfig(Environment env) {
		JpaConfig jpaConfig = new com.miskowskij.app.persistence.configuration.jpa.SystemProperties();
		
		if (jpaConfig.isEmpty()) {
			jpaConfig = new com.miskowskij.app.persistence.configuration.jpa.SpringEnvironment(env);
		}
		
		if (jpaConfig.isEmpty()) {
			jpaConfig = new com.miskowskij.app.persistence.configuration.jpa.SystemEnvironment();
		}
		
		if (jpaConfig.isEmpty()) {
			throw new RuntimeException("Failed to load JpaConfig properties");
		}
		
		this.jpaConfig = jpaConfig;
		
		logger.debug("JpaConfig: " + this.jpaConfig.toString());
	}
   
}