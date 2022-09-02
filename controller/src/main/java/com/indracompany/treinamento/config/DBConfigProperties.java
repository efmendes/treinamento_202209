package com.indracompany.treinamento.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class DBConfigProperties {

	@Value("${primary.datasource.jdbcUrl}")
	private String primaryDatasourceJdbcUrl;
	
	@Value("${primary.datasource.username}")
	private String primaryDatasourceUsername;
	
	@Value("${primary.datasource.password}")
	private String primaryDatasourcePassword;
	
	@Value("${primary.datasource.driverClassName}")
	private String primaryDatasourceDriverClassName;
	
	



}
