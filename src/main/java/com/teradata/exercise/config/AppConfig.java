package com.teradata.exercise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@EntityScan("com.teradata.exercise.db.entity")
@EnableJpaRepositories("com.teradata.exercise.db.repository")
@EnableJpaAuditing
@EnableTransactionManagement
public class AppConfig {
	
	@Value("${timeout.connection}")
	int connectionTimeout;
	
	@Value("${timeout.read}")
	int readTimeout;
	
	@Bean
	public RestTemplate getRestTemplate(){
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(getClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            protected boolean hasError(final HttpStatus statusCode) {
                return false;
            }});
        
        return restTemplate;
	}

	
    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeout);
        factory.setConnectTimeout(connectionTimeout);
        return factory;
  }
}
