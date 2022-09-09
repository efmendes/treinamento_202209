package com.indracompany.treinamento.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableTransactionManagement
public class DatabaseConfigurationDev {

  @Autowired
  private DBConfigProperties dbConfig;

  @Primary
  @Bean("datasource")
  public DataSource dataSource() {
    log.info("inicializando dataSource ");
    final BasicDataSource ds = new BasicDataSource();
    ds.setUrl(dbConfig.getPrimaryDatasourceJdbcUrl());
    ds.setUsername(dbConfig.getPrimaryDatasourceUsername());
    ds.setPassword(dbConfig.getPrimaryDatasourcePassword());
    ds.setDriverClassName(dbConfig.getPrimaryDatasourceDriverClassName());
    return ds;
  }



  @Bean("transactionManager")
  @Primary
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

}
