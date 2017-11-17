package com.miskowskij.app.persistence;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.miskowskij.app.profile.datasource.DataSourceConfig;
import com.miskowskij.app.profile.jpa.JpaConfig;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackages = { "com.miskowskij.app.*" })
public class PersistenceContext{

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(PersistenceContext.class);

    private static final String[] ENTITY_PACKAGES = {
            "com.miskowskij.app.persistence"
    };

	public PersistenceContext() {
		logger.debug("PersistenceContext initiated");
	}

    /**
     * Creates the bean that creates the JPA entity manager factory.
     *
     * @param dataSource    The datasource that provides the database connections.
     * @param env           The runtime environment of  our application.
     * @return
     */
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSourceConfig dataSourceConfig, JpaConfig jpaConfig) {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

      dataSourceConfig.setup();
      jpaConfig.setup();

      DataSource dataSource = dataSourceConfig.getDataSource();
      Properties jpaProperties = jpaConfig.getProperties();

      logger.debug("DataSource: " + dataSource.toString());
      logger.debug("JpaProperties: " + jpaProperties.toString());

      entityManagerFactoryBean.setDataSource(dataSource);
      entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);
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

}
